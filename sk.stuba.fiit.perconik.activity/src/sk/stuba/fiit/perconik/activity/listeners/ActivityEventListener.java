package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;

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
import sk.stuba.fiit.perconik.utilities.concurrent.TimeUnits;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;
import sk.stuba.fiit.perconik.utilities.configuration.Configurables;
import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

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
import static sk.stuba.fiit.perconik.data.content.StructuredContent.separator;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.sequence;
import static sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor.defaultSynchronous;
import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAsState;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.requireNonNullOrEmpty;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCaseFunction;
import static sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors.defaultPoolSizeScalingFactor;
import static sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors.newLimitedThreadPool;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.option;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.booleanParser;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ActivityEventListener extends RegularEventListener {
  static final float sharedExecutorPoolSizeScalingFactor = defaultPoolSizeScalingFactor();

  static final float probeExecutorPoolSizeScalingFactor = 0.5f;

  protected static final String qualifier = join(PLUGIN_ID, "preferences");

  private static final Builder<ActivityEventListener> sharedBuilder;

  static {
    sharedBuilder = builder();

    sharedBuilder.contextType(ActivityEventListener.class);

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
  protected ActivityEventListener() {
    super(newConfiguration());

    this.log = new Log(this);
  }

  static final Configuration<ActivityEventListener> newConfiguration() {
    return sharedBuilder.build();
  }

  public static final class StandardProbingOptionsSchema {
    public static final OptionAccessor<Boolean> monitorCore = option(booleanParser(), join(qualifier, "monitor", "core"), false);

    // TODO public static final OptionAccessor<Boolean> monitorManagement = option(booleanParser(), join(qualifier, "monitor", "management"), false);

    public static final OptionAccessor<Boolean> monitorPlatform = option(booleanParser(), join(qualifier, "monitor", "platform"), true);

    public static final OptionAccessor<Boolean> monitorProcess = option(booleanParser(), join(qualifier, "monitor", "process"), true);

    public static final OptionAccessor<Boolean> monitorSystem = option(booleanParser(), join(qualifier, "monitor", "system"), true);

    public static final OptionAccessor<Boolean> listenerInstance = option(booleanParser(), join(qualifier, "listener", "instance"), true);

    public static final OptionAccessor<Boolean> listenerRegistration = option(booleanParser(), join(qualifier, "listener", "registration"), false);

    public static final OptionAccessor<Boolean> listenerConfiguration = option(booleanParser(), join(qualifier, "listener", "configuration"), false);

    public static final OptionAccessor<Boolean> listenerOptions = option(booleanParser(), join(qualifier, "listener", "options"), true);

    public static final OptionAccessor<Boolean> listenerStatistics = option(booleanParser(), join(qualifier, "listener", "statistics"), true);

    static final ImmutableMap<String, OptionAccessor<Boolean>> probeKeyToOptionAccessor;

    static {
      Map<String, OptionAccessor<Boolean>> map = newHashMap();

      for (OptionAccessor<Boolean> accessor: Configurables.accessors(StandardProbingOptionsSchema.class, Boolean.class)) {
        String key = accessor.getKey();

        checkState(!map.containsKey(key), "%s: internal probe key conflict detected on %s", StandardProbingOptionsSchema.class, key);

        map.put(key, accessor);
      }

      probeKeyToOptionAccessor = ImmutableMap.copyOf(map);
    }

    private StandardProbingOptionsSchema() {}
  }

  public static final class StandardLoggingOptionsSchema {
    public static final OptionAccessor<Boolean> logDebug = option(booleanParser(), join(qualifier, "log", "debug"), false);

    private StandardLoggingOptionsSchema() {}
  }

  private enum UacaConsoleSupplierFunction implements Function<ActivityEventListener, PluginConsole> {
    instance;

    public PluginConsole apply(@Nonnull final ActivityEventListener listener) {
      return UacaConsole.create(listener.getUacaOptions(), listener.wallTimeSource());
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UacaProxySupplierFunction implements Function<ActivityEventListener, PersistenceStore> {
    instance;

    public PersistenceStore apply(final ActivityEventListener listener) {
      try {
        return StoreWrapper.of(new UacaProxy(listener.getUacaOptions(), listener.wallTimeSource()));
      } catch (Exception failure) {
        listener.pluginConsole.error(failure, "%s: unable to open UACA proxy", listener);

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

  private enum ProbingOptionsFilterSupplierFunction implements Function<ActivityEventListener, Predicate<Entry<String, Probe<?>>>> {
    instance;

    public Predicate<Entry<String, Probe<?>>> apply(@Nonnull final ActivityEventListener listener) {
      return new ProbingOptionsFilter(listener);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private static final class ProbingOptionsFilter implements Predicate<Entry<String, Probe<?>>> {
    private final ActivityEventListener listener;

    private final Options options;

    private final Map<String, OptionAccessor<Boolean>> accessors;

    ProbingOptionsFilter(final ActivityEventListener listener) {
      this.listener = requireNonNull(listener);
      this.accessors = requireNonNull(listener.probeKeyToOptionAccessor());
      this.options = requireNonNull(listener.effectiveOptions());
    }

    public boolean apply(@Nonnull final Entry<String, Probe<?>> entry) {
      String key = probeKeyToOptionKey(entry.getKey());

      OptionAccessor<Boolean> accessor = this.accessors.get(key);

      checkNotNullAsState(accessor, "%s: no accessor found for %s option, available accessors %s", this.listener, key, this.accessors);

      return accessor.getValue(this.options);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  protected static String probeKeyToOptionKey(final String key) {
    return qualifier + separator + key.replace(internalProbeKeyPrefix + separator, "");
  }

  protected Map<String, OptionAccessor<Boolean>> probeKeyToOptionAccessor() {
    requireNonNull(this.optionsProvider);

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

  protected static abstract class ContinuousEventProcessor<L extends ActivityEventListener, E> extends RegularEventListener.ContinuousEventProcessor<L, E> {
    protected final String identifier;

    protected final Log log;

    protected ContinuousEventProcessor(final L listener, final String identifier, final long pause, final long window, final TimeUnit unit) {
      super(listener, pause, window, unit);

      this.identifier = requireNonNullOrEmpty(identifier);
      this.log = this.listener.log;
    }

    protected ContinuousEventProcessor(final L listener, final String identifier, final TimeValue pause, final TimeValue window) {
      this(listener, identifier, pause.durationToMillis(), window.durationToMillis(), MILLISECONDS);
    }

    protected String formatElapsedTime(final long delta, final long total) {
      return format("pause %s, window %s", formatTimeComparison(delta, this.pause), formatTimeComparison(total, this.window));
    }

    protected String formatTimeComparison(final long value, final long limit) {
      return format("%d %s %d%s", value, value < limit ? "<" : ">=", limit, TimeUnits.toString(this.unit));
    }

    @Override
    protected void watchRunningButEventsNotContinouous() {
      if (this.log.isEnabled()) {
        this.log.print("%s: watch running but %1$s events not continuous -> process", this.identifier);
      }
    }

    @Override
    protected void watchNotRunning() {
      if (this.log.isEnabled()) {
        this.log.print("%s: watch not running -> clear", this.identifier);
      }
    }

    @Override
    protected void watchTimeNotElapsed(final long delta) {
      if (this.log.isEnabled()) {
        this.log.print("%s: %s -> wait", this.identifier, this.formatElapsedTime(delta, this.total()));
      }
    }

    @Override
    protected void watchTimeElapsedAndAboutToProcess(final long delta) {
      if (this.log.isEnabled()) {
        this.log.print("%s: %s -> process", this.identifier, this.formatElapsedTime(delta, this.total()));
      }
    }
  }

  protected static final class Log extends ForwardingPluginConsole {
    private final Options options;

    private final PluginConsole console;

    Log(final ActivityEventListener listener) {
      this.options = requireNonNull(listener.effectiveOptions());
      this.console = requireNonNull(listener.pluginConsole);
    }

    @Override
    protected PluginConsole delegate() {
      return this.isEnabled() ? this.console : PluginConsoles.silent();
    }

    public static void format(final String content) {
      SmartStringBuilder.builder(2 * content.length()).format(valueOf(content));
    }

    public static void format(final String format, final Object ... args) {
      SmartStringBuilder.builder(4 * format.length()).format(format, args);
    }

    public boolean isEnabled() {
      return StandardLoggingOptionsSchema.logDebug.getValue(this.options);
    }
  }

  final UacaOptions getUacaOptions() {
    return UacaOptions.View.of(this.getOptions());
  }
}
