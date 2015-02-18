package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;

import com.gratex.perconik.uaca.UacaConsole;
import com.gratex.perconik.uaca.preferences.UacaOptions;
import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.RegularEventListener.RegularConfiguration.Builder;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.activity.uaca.UacaProxy;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.util.Arrays.asList;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractEventListener.RegistrationHook.POST_REGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractEventListener.RegistrationHook.POST_UNREGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractEventListener.RegistrationHook.PRE_REGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractEventListener.RegistrationHook.PRE_UNREGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.RegularEventListener.RegularConfiguration.builder;
import static sk.stuba.fiit.perconik.activity.plugin.Activator.PLUGIN_ID;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.sequence;
import static sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor.defaultSynchronous;
import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCaseFunction;
import static sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors.defaultPoolSizeScalingFactor;
import static sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors.newLimitedThreadPool;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.compound;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.defaults;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.option;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.booleanParser;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class CommonEventListener extends RegularEventListener {
  // TODO need to resolve 2 problems:
  //   - prober needs to filter probes by listener's options
  //   - listener needs to initialize proxy via options
  // solution probably lies in injecting 'this' listener into every part of construction
  // via current Configuration mechanism

  static final float sharedExecutorPoolSizeScalingFactor = defaultPoolSizeScalingFactor();

  static final float probeExecutorPoolSizeScalingFactor = 0.5f;

  protected static final String qualifier = join(PLUGIN_ID, "preferences");

  private static final Builder sharedBuilder;

  private static final Options sharedDefaults;

  static {
    sharedBuilder = builder();

    sharedBuilder.diplayExecutor(defaultSynchronous());
    sharedBuilder.sharedExecutor(newLimitedThreadPool(sharedExecutorPoolSizeScalingFactor));

    sharedBuilder.pluginConsole(UacaConsole.getInstance());
    sharedBuilder.persistenceStore(UacaProxySupplier.instance);
    sharedBuilder.sendFailureHandler(UacaProxySaveFailureHandler.instance);
    sharedBuilder.registerFailureHandler(UacaLoggingRegisterFailureHandler.instance);
    sharedBuilder.disposalHook(UacaProxyDisposalHook.instance);

    Map<String, Probe<?>> probes = newHashMap();

    key("remove-this");// TODO enable probes
    //probes.put(key("monitor", "core"), new StandardCoreProbe());
    //probes.put(key("monitor", "management"), new StandardManagementProbe());// TODO ?
    //probes.put(key("monitor", "platform"), new StandardPlatformProbe());
    //probes.put(key("monitor", "system"), new StandardSystemProbe());
    //probes.put(key("monitor", "process"), new StandardProcessProbe());

    sharedBuilder.probeMappings(probes);
    sharedBuilder.probeExecutor(newLimitedThreadPool(probeExecutorPoolSizeScalingFactor));


    sharedDefaults = compound(defaults(UacaOptions.Schema.class), defaults(StandardProbingOptionsSchema.class), defaults(StandardLoggingOptionsSchema.class));
  }

  /**
   * Constructor for use by subclasses.
   */
  protected CommonEventListener() {
    super(newConfiguration());
  }

  static final RegularConfiguration newConfiguration() {
    return sharedBuilder.build();
  }

  protected static final class StandardProbingOptionsSchema {
    public static final OptionAccessor<Boolean> monitorCore = option(booleanParser(), join(qualifier, "monitor", "core"), true);

    // TODO public static final OptionAccessor<Boolean> monitorManagement = option(booleanParser(), join(qualifier, "monitor", "management"), true);

    public static final OptionAccessor<Boolean> monitorPlatform = option(booleanParser(), join(qualifier, "monitor", "platform"), true);

    public static final OptionAccessor<Boolean> monitorProcess = option(booleanParser(), join(qualifier, "monitor", "process"), true);

    public static final OptionAccessor<Boolean> monitorSystem = option(booleanParser(), join(qualifier, "monitor", "system"), true);

    public static final OptionAccessor<Boolean> listenerConfiguration = option(booleanParser(), join(qualifier, "monitor", "configuration"), true);

    public static final OptionAccessor<Boolean> listenerOptions = option(booleanParser(), join(qualifier, "monitor", "options"), true);

    public static final OptionAccessor<Boolean> listenerStatistics = option(booleanParser(), join(qualifier, "monitor", "statistics"), true);

    private StandardProbingOptionsSchema() {}
  }

  protected static final class StandardLoggingOptionsSchema {
    public static final OptionAccessor<Boolean> logDebug = option(booleanParser(), join(qualifier, "log", "debug"), true);

    private StandardLoggingOptionsSchema() {}
  }

  @Override
  protected Options defaultOptions() {
    return sharedDefaults;
  }

  private enum UacaProxySupplier implements Supplier<PersistenceStore> {
    instance;

    public PersistenceStore get() {
      try {
        // TODO use custom opts
        return StoreWrapper.of(new UacaProxy(UacaPreferences.getShared()));
      } catch (Exception failure) {
        UacaConsole.getInstance().error(failure, "Unable to open UACA proxy");

        throw propagate(failure);
      }
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UacaProxySaveFailureHandler implements SendFailureHandler {
    instance;

    public void handleSendFailure(final RegularEventListener listener, final String path, final Event data, final Exception failure) {
      UacaConsole.getInstance().error(failure, listener + ": Unable to save data at " + path + " using UACA proxy");
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UacaLoggingRegisterFailureHandler implements RegisterFailureHandler {
    instance;

    static void report(final RegularEventListener listener, final RegistrationHook hook, final Runnable task, final Exception failure) {
      listener.pluginConsole.error(failure, "Unexpected failure while executing %s as %s %s hook", task, listener, hook);
    }

    public void preRegisterFailure(final RegularEventListener listener, final Runnable task, final Exception failure) {
      report(listener, PRE_REGISTER, task, failure);
    }

    public void postRegisterFailure(final RegularEventListener listener, final Runnable task, final Exception failure) {
      report(listener, POST_REGISTER, task, failure);
    }

    public void preUnregisterFailure(final RegularEventListener listener, final Runnable task, final Exception failure) {
      report(listener, PRE_UNREGISTER, task, failure);
    }

    public void postUnregisterFailure(final RegularEventListener listener, final Runnable task, final Exception failure) {
      report(listener, POST_UNREGISTER, task, failure);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UacaProxyDisposalHook implements DisposalHook {
    instance;

    public void onDispose(final RegularEventListener listener) {
      try {
        listener.persistenceStore.close();
      } catch (Exception failure) {
        listener.pluginConsole.error(failure, listener + ": Unable to close UACA proxy");
      }
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected final Map<String, InternalProbe<?>> internalProbeMappings() {
    ImmutableMap.Builder<String, InternalProbe<?>> builder = ImmutableMap.builder();

    // TODO enable probes
    //builder.put(key("listener", "configuration"), new RegularConfigurationProbe());
    //builder.put(key("listener", "options"), new RegularOptionsProbe());
    //builder.put(key("listener", "statistics"), new RegularStatisticsProbe());

    return builder.build();
  }

  public interface Action {
    public String getName();

    public String getPath();
  }

  public static final String actionName(final Object ... components) {
    return key(transform(asList(components), toLowerCaseFunction()));
  }

  public static final String actionPath(final Object ... components) {
    StringBuilder builder = new StringBuilder(16 * components.length);

    for (Object component: components) {
      for (String item: sequence(component.toString())) {
        builder.append(item.replace('_', '-').toLowerCase()).append("/");
      }
    }

    return builder.substring(0, builder.length() - 1);
  }

  protected static final class Log {
    private Log() {}

    public static SmartStringBuilder message(final String content) {
      return SmartStringBuilder.builder(content.length()).format(content);
    }

    public static SmartStringBuilder message(final String format, final Object ... args) {
      return SmartStringBuilder.builder(4 * format.length()).format(format, args);
    }
  }

  protected final boolean isLogEnabled() {
    return StandardLoggingOptionsSchema.logDebug.getValue(this.effectiveOptions());
  }
}
