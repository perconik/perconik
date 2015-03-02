package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;

import com.gratex.perconik.uaca.UacaConsole;
import com.gratex.perconik.uaca.preferences.UacaOptions;

import sk.stuba.fiit.perconik.activity.data.core.StandardCoreProbe;
import sk.stuba.fiit.perconik.activity.data.platform.StandardPlatformProbe;
import sk.stuba.fiit.perconik.activity.data.process.StandardProcessProbe;
import sk.stuba.fiit.perconik.activity.data.system.StandardSystemProbe;
import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.RegularEventListener.RegularConfiguration.Builder;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.activity.uaca.UacaProxy;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ForwardingPluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

import static com.google.common.base.Preconditions.checkState;
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
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAsState;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.requireNonNullOrEmpty;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCaseFunction;
import static sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors.defaultPoolSizeScalingFactor;
import static sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors.newLimitedThreadPool;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.compound;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.defaults;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.mappings;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.option;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.booleanParser;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class CommonEventListener extends RegularEventListener {
  static final float sharedExecutorPoolSizeScalingFactor = defaultPoolSizeScalingFactor();

  static final float probeExecutorPoolSizeScalingFactor = 0.5f;

  protected static final String qualifier = join(PLUGIN_ID, "preferences");

  private static final Builder<CommonEventListener> sharedBuilder;

  static {
    sharedBuilder = builder();

    sharedBuilder.contextType(CommonEventListener.class);

    sharedBuilder.defaultOptions(compound(defaults(UacaOptions.Schema.class), defaults(StandardProbingOptionsSchema.class), defaults(StandardLoggingOptionsSchema.class)));

    sharedBuilder.diplayExecutor(defaultSynchronous());
    sharedBuilder.sharedExecutor(newLimitedThreadPool(sharedExecutorPoolSizeScalingFactor));

    sharedBuilder.pluginConsole(UacaConsoleSupplierFunction.instance);
    sharedBuilder.persistenceStore(UacaProxySupplierFunction.instance);
    sharedBuilder.sendFailureHandler(UacaProxySaveFailureHandler.instance);

    Map<String, Probe<?>> probes = newHashMap();

    probes.put(key("monitor", "core"), new StandardCoreProbe());
    //probes.put(key("monitor", "management"), new StandardManagementProbe());// TODO
    probes.put(key("monitor", "platform"), new StandardPlatformProbe());
    probes.put(key("monitor", "process"), new StandardProcessProbe());
    probes.put(key("monitor", "system"), new StandardSystemProbe());

    sharedBuilder.probeMappings(probes);
    sharedBuilder.probeFilter(ProbingOptionsFilterSupplierFunction.instance);
    sharedBuilder.probeExecutor(newLimitedThreadPool(probeExecutorPoolSizeScalingFactor));

    sharedBuilder.registerFailureHandler(UacaLoggingRegisterFailureHandler.instance);
    sharedBuilder.disposalHook(UacaProxyDisposalHook.instance);
  }

  protected final Log log;

  /**
   * Constructor for use by subclasses.
   */
  protected CommonEventListener() {
    super(newConfiguration());

    this.log = new Log(this);
  }

  static final Configuration<CommonEventListener> newConfiguration() {
    return sharedBuilder.build();
  }

  protected static final class StandardProbingOptionsSchema {
    public static final OptionAccessor<Boolean> monitorCore = option(booleanParser(), join(qualifier, "monitor", "core"), false);

    // TODO public static final OptionAccessor<Boolean> monitorManagement = option(booleanParser(), join(qualifier, "monitor", "management"), false);

    public static final OptionAccessor<Boolean> monitorPlatform = option(booleanParser(), join(qualifier, "monitor", "platform"), true);

    public static final OptionAccessor<Boolean> monitorProcess = option(booleanParser(), join(qualifier, "monitor", "process"), true);

    public static final OptionAccessor<Boolean> monitorSystem = option(booleanParser(), join(qualifier, "monitor", "system"), true);

    public static final OptionAccessor<Boolean> listenerInstance = option(booleanParser(), join(qualifier, "listener", "instance"), true);

    public static final OptionAccessor<Boolean> listenerRegistration = option(booleanParser(), join(qualifier, "listener", "registration"), false);

    public static final OptionAccessor<Boolean> listenerConfiguration = option(booleanParser(), join(qualifier, "listener", "configuration"), false);

    public static final OptionAccessor<Boolean> listenerOptions = option(booleanParser(), join(qualifier, "listener", "options"), false);

    public static final OptionAccessor<Boolean> listenerStatistics = option(booleanParser(), join(qualifier, "listener", "statistics"), true);

    static final ImmutableMap<String, OptionAccessor<Boolean>> probeKeyToOptionAccessor;

    static {
      @SuppressWarnings("serial")
      TypeToken<OptionAccessor<Boolean>> type = new TypeToken<OptionAccessor<Boolean>>() {};

      Map<String, OptionAccessor<Boolean>> map = newHashMap();

      for (OptionAccessor<Boolean> accessor: mappings(StandardProbingOptionsSchema.class, type)) {
        String key = optionKeyToInternalProbeKey(accessor.getKey());

        checkState(!map.containsKey(key), "%s: internal probe key conflict detected on %s", StandardProbingOptionsSchema.class, key);

        map.put(key, accessor);
      }

      probeKeyToOptionAccessor = ImmutableMap.copyOf(map);
    }

    private StandardProbingOptionsSchema() {}

    public static String optionKeyToInternalProbeKey(final String key) {
      return key.replace(qualifier, internalProbeKeyPrefix);
    }
  }

  protected static final class StandardLoggingOptionsSchema {
    public static final OptionAccessor<Boolean> logDebug = option(booleanParser(), join(qualifier, "log", "debug"), true);

    private StandardLoggingOptionsSchema() {}
  }

  private enum UacaConsoleSupplierFunction implements Function<CommonEventListener, PluginConsole> {
    instance;

    public PluginConsole apply(final CommonEventListener listener) {
      // TODO set time source
      return UacaConsole.create(listener.getUacaOptions());
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UacaProxySupplierFunction implements Function<CommonEventListener, PersistenceStore> {
    instance;

    public PersistenceStore apply(final CommonEventListener listener) {
      try {
        return StoreWrapper.of(new UacaProxy(listener.getUacaOptions()));
      } catch (Exception failure) {
        listener.log.error(failure, "%s: unable to open UACA proxy", listener);

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
      // TODO use log
      listener.pluginConsole.error(failure, "%s: unable to save data at %s using UACA proxy", listener, path);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UacaLoggingRegisterFailureHandler implements RegisterFailureHandler {
    instance;

    static void report(final RegularEventListener listener, final RegistrationHook hook, final Runnable task, final Exception failure) {
      // TODO use log
      listener.pluginConsole.error(failure, "%s: unexpected failure while executing %s as %s hook", listener, task, hook);
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
        // TODO use log
        listener.pluginConsole.error(failure, "%s: unable to close UACA proxy", listener);
      }
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected Map<String, InternalProbe<?>> internalProbeMappings() {
    ImmutableMap.Builder<String, InternalProbe<?>> builder = ImmutableMap.builder();

    builder.put(key("listener", "instance"), new RegularInstanceProbe());
    builder.put(key("listener", "registration"), new RegularRegistrationProbe());
    builder.put(key("listener", "configuration"), new RegularConfigurationProbe());
    builder.put(key("listener", "options"), new RegularOptionsProbe());
    builder.put(key("listener", "statistics"), new RegularStatisticsProbe());

    return builder.build();
  }

  private enum ProbingOptionsFilterSupplierFunction implements Function<CommonEventListener, Predicate<Entry<String, Probe<?>>>> {
    instance;

    public Predicate<Entry<String, Probe<?>>> apply(final CommonEventListener listener) {
      return new ProbingOptionsFilter(listener);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private static final class ProbingOptionsFilter implements Predicate<Entry<String, Probe<?>>> {
    private final CommonEventListener listener;

    private final Options options;

    private final Map<String, OptionAccessor<Boolean>> accessors;

    ProbingOptionsFilter(final CommonEventListener listener) {
      this.listener = requireNonNull(listener);
      this.accessors = requireNonNull(listener.probeKeyToOptionAccessor());
      this.options = requireNonNull(listener.effectiveOptions());
    }

    public boolean apply(final Entry<String, Probe<?>> entry) {
      String key = entry.getKey();

      OptionAccessor<Boolean> accessor = this.accessors.get(key);

      checkNotNullAsState(accessor, "%s: no accessor found for %s option", this.listener, key);

      return accessor.getValue(this.options);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  // TODO
  protected Map<String, OptionAccessor<Boolean>> probeKeyToOptionAccessor() {
    return StandardProbingOptionsSchema.probeKeyToOptionAccessor;
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

  protected static abstract class ContinuousEventWindow<L extends CommonEventListener, E> extends RegularEventListener.ContinuousEventWindow<L, E> {
    protected final String identifier;

    protected final Log log;

    protected ContinuousEventWindow(final L listener, final String identifier, final long window) {
      this(listener, identifier, window, TimeUnit.MILLISECONDS);
    }

    protected ContinuousEventWindow(final L listener, final String identifier, final long window, final TimeUnit unit) {
      super(listener, window, unit);

      this.identifier = requireNonNullOrEmpty(identifier);
      this.log = this.listener.log;
    }

    @Override
    protected void watchRunningButEventsNotContinouous() {
      if (this.listener.log.isEnabled()) {
        this.listener.log.print("%s: watch running but %s events not continuous", this.identifier, this.identifier);
      }
    }

    @Override
    protected void watchNotRunning() {
      if (this.listener.log.isEnabled()) {
        this.listener.log.print("%s: watch not running", this.identifier);
      }
    }

    @Override
    protected void watchWindowNotElapsed(final long delta) {
      if (this.listener.log.isEnabled()) {
        this.listener.log.print("%s: window not elapsed, %d < %d %s", this.identifier, delta, this.window, this.unit.toString().toLowerCase());
      }
    }
  }

  protected static final class Log extends ForwardingPluginConsole {
    private final CommonEventListener listener;

    Log(final CommonEventListener listener) {
      this.listener = requireNonNull(listener);
    }

    @Override
    protected PluginConsole delegate() {
      return this.isEnabled() ? this.listener.pluginConsole : PluginConsoles.silent();
    }

    public static void format(final String content) {
      SmartStringBuilder.builder(2 * content.length()).format(valueOf(content));
    }

    public static void format(final String format, final Object ... args) {
      SmartStringBuilder.builder(4 * format.length()).format(format, args);
    }

    public boolean isEnabled() {
      return StandardLoggingOptionsSchema.logDebug.getValue(this.listener.effectiveOptions());
    }
  }

  final UacaOptions getUacaOptions() {
    return UacaOptions.View.of(this.getOptions());
  }
}
