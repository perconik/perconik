package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.base.Ticker;
import com.google.common.collect.ImmutableMap;

import sk.stuba.fiit.perconik.activity.data.ObjectData;
import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.activity.probes.Prober;
import sk.stuba.fiit.perconik.activity.probes.Probers;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.preferences.ListenerPreferences;
import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.store.Store;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Options;
import sk.stuba.fiit.perconik.utilities.configuration.Scope;
import sk.stuba.fiit.perconik.utilities.configuration.ScopedConfigurable;
import sk.stuba.fiit.perconik.utilities.configuration.StandardScope;

import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Suppliers.ofInstance;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.base.Ticker.systemTicker;
import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAsState;
import static sk.stuba.fiit.perconik.utilities.MoreSuppliers.ofNull;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.compound;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.emptyOptions;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class RegularEventListener extends AbstractEventListener implements ScopedConfigurable {
  /**
   * Underlying plug-in console for event logging, also aliased as {@code log}.
   */
  protected final PluginConsole pluginConsole;

  /**
   * Underlying display executor for executing tasks requiring UI threads.
   */
  protected final DisplayExecutor displayExecutor;

  /**
   * Underlying executor service for executing common commands.
   */
  protected final ExecutorService sharedExecutor;

  /**
   * Underlying event data injector for injecting
   * additional data to event objects before validation.
   */
  protected final DataInjector dataInjector;

  /**
   * Underlying event data validator for validating
   * event objects before persistence.
   */
  protected final EventValidator eventValidator;

  /**
   * Underlying persistence store for persisting event data.
   */
  protected final PersistenceStore persistenceStore;

  /**
   * Underlying event data send failure handler.
   */
  protected final SendFailureHandler sendFailureHandler;

  /**
   * Underlying time source.
   */
  final Ticker ticker;

  /**
   * Underlying listener registration failure handler.
   */
  final RegisterFailureHandler registerFailureHandler;

  /**
   * Underlying listener statistics.
   */
  final RuntimeStatistics runtimeStatistics;

  /**
   * Underlying listener options holder.
   */
  final OptionsLoader optionsLoader;

  /**
   * Underlying listener disposal hook.
   */
  final DisposalHook disposalHook;

  /**
   * Convenient alias for {@code pluginConsole}.
   */
  protected final PluginConsole log;

  /**
   * Constructor for use by subclasses.
   */
  protected RegularEventListener(final Configuration configuration) {
    // TODO add to configuration
    this.ticker = systemTicker();

    this.pluginConsole = requireNonNull(configuration.pluginConsole());
    this.displayExecutor = requireNonNull(configuration.diplayExecutor());
    this.sharedExecutor = requireNonNull(configuration.sharedExecutor());
    this.persistenceStore = requireNonNull(configuration.persistenceStore());

    this.dataInjector = this.resolveDataInjector(configuration);

    this.eventValidator = configuration.eventValidator().or(StandardEventValidator.instance);
    this.sendFailureHandler = configuration.sendFailureHandler().or(PropagatingSendFailureHandler.instance);
    this.registerFailureHandler = configuration.registerFailureHandler().or(PropagatingRegisterFailureHandler.instance);
    this.disposalHook = configuration.disposalHook().or(IgnoringDisposalHook.instance);

    this.runtimeStatistics = this.initializeRuntimeStatistics();
    this.optionsLoader = this.initializeOptionsLoader();

    this.log = this.pluginConsole;
  }

  private DataInjector resolveDataInjector(final Configuration configuration) {
    Optional<DataInjector> injector = configuration.dataInjector();

    if (injector.isPresent()) {
      return injector.get();
    }

    Optional<Map<String, Probe<?>>> mappings = configuration.probeMappings();

    if (mappings.isPresent()) {
      Map<String, Probe<?>> mix = newHashMap(mappings.get());

      for (Entry<String, InternalProbe<?>> entry: this.internalProbeMappings().entrySet()) {
        mix.put(key("meta", entry.getKey()), InternalProbe.class.cast(entry.getValue()));
      }

      Optional<ExecutorService> executor = configuration.probeExecutor();

      return executor.isPresent() ? ProbingDataInjector.of(mix, executor.get()) : ProbingDataInjector.of(mix);
    }

    return NoDataInjector.instance;
  }

  private RuntimeStatistics initializeRuntimeStatistics() {
    final RuntimeStatistics statistics = new RuntimeStatistics();

    RegistrationHook.PRE_REGISTER.add(this, new NamedRunnable(RuntimeStatistics.class) {
      public void run() {
        statistics.registrationCount.incrementAndGet();
      }
    });

    RegistrationHook.POST_UNREGISTER.add(this, new NamedRunnable(RuntimeStatistics.class) {
      public void run() {
        statistics.unregistrationCount.incrementAndGet();
      }
    });

    return statistics;
  }

  private OptionsLoader initializeOptionsLoader() {
    final OptionsLoader loader = new OptionsLoader(this);

    RegistrationHook.PRE_REGISTER.add(this, new NamedRunnable(OptionsLoader.class) {
      public void run() {
        loader.load(ListenerPreferences.getShared());
      }
    });

    return loader;
  }

  // TODO parametrize with L & rename to Setup
  public interface Configuration {
    public PluginConsole pluginConsole();

    public DisplayExecutor diplayExecutor();

    public ExecutorService sharedExecutor();

    public Optional<DataInjector> dataInjector();

    public Optional<Map<String, Probe<?>>> probeMappings();

    public Optional<ExecutorService> probeExecutor();

    public Optional<EventValidator> eventValidator();

    public PersistenceStore persistenceStore();

    public Optional<SendFailureHandler> sendFailureHandler();

    public Optional<RegisterFailureHandler> registerFailureHandler();

    public Optional<DisposalHook> disposalHook();

    public interface Builder {
      public Configuration build();
    }
  }

  public static abstract class AbstractConfiguration implements Configuration {
    private final Supplier<PluginConsole> pluginConsole;

    private final Supplier<DisplayExecutor> diplayExecutor;

    private final Supplier<ExecutorService> sharedExecutor;

    private final Supplier<DataInjector> dataInjector;

    private final Supplier<Map<String, Probe<?>>> probeMappings;

    private final Supplier<ExecutorService> probeExecutor;

    private final Supplier<EventValidator> eventValidator;

    private final Supplier<PersistenceStore> persistenceStore;

    private final Supplier<SendFailureHandler> sendFailureHandler;

    private final RegisterFailureHandler registerFailureHandler;

    private final DisposalHook disposalHook;

    /**
     * Constructor for use by subclasses.
     */
    protected AbstractConfiguration(final AbstractBuilder<?> builder) {
      this.pluginConsole = requireNonNull(builder.pluginConsole);
      this.diplayExecutor = requireNonNull(builder.diplayExecutor);
      this.sharedExecutor = requireNonNull(builder.sharedExecutor);
      this.dataInjector = requireNonNull(builder.dataInjector);
      this.probeMappings = requireNonNull(builder.probeMappings);
      this.probeExecutor = requireNonNull(builder.probeExecutor);
      this.eventValidator = requireNonNull(builder.eventValidator);
      this.persistenceStore = requireNonNull(builder.persistenceStore);
      this.sendFailureHandler = requireNonNull(builder.sendFailureHandler);
      this.registerFailureHandler = requireNonNull(builder.registerFailureHandler);
      this.disposalHook = requireNonNull(builder.disposalHook);
    }

    public static abstract class AbstractBuilder<B extends AbstractBuilder<B>> implements Builder {
      Supplier<PluginConsole> pluginConsole;

      Supplier<DisplayExecutor> diplayExecutor;

      Supplier<ExecutorService> sharedExecutor;

      Supplier<DataInjector> dataInjector = ofNull();

      Supplier<Map<String, Probe<?>>> probeMappings = ofNull();

      Supplier<ExecutorService> probeExecutor = ofNull();

      Supplier<EventValidator> eventValidator = ofNull();

      Supplier<PersistenceStore> persistenceStore;

      Supplier<SendFailureHandler> sendFailureHandler = ofNull();

      RegisterFailureHandler registerFailureHandler = null;

      DisposalHook disposalHook = null;

      /**
       * Constructor for use by subclasses.
       */
      protected AbstractBuilder() {}

      /**
       * Always returns {@code this}.
       */
      protected abstract B asSubtype();

      public final B pluginConsole(final PluginConsole console) {
        return this.pluginConsole(ofInstance(requireNonNull(console)));
      }

      public final B pluginConsole(final Supplier<PluginConsole> supplier) {
        this.pluginConsole = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B diplayExecutor(final DisplayExecutor executor) {
        return this.diplayExecutor(ofInstance(requireNonNull(executor)));
      }

      public final B diplayExecutor(final Supplier<DisplayExecutor> supplier) {
        this.diplayExecutor = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B sharedExecutor(final ExecutorService executor) {
        return this.sharedExecutor(ofInstance(requireNonNull(executor)));
      }

      public final B sharedExecutor(final Supplier<ExecutorService> supplier) {
        this.sharedExecutor = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B dataInjector(final DataInjector injector) {
        return this.dataInjector(ofInstance(requireNonNull(injector)));
      }

      public final B dataInjector(final Supplier<DataInjector> supplier) {
        this.dataInjector = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B probeMappings(final Map<String, Probe<?>> probes) {
        return this.probeMappings(ofInstance(requireNonNull(probes)));
      }

      public final B probeMappings(final Supplier<Map<String, Probe<?>>> supplier) {
        this.probeMappings = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B probeExecutor(final ExecutorService executor) {
        return this.probeExecutor(ofInstance(requireNonNull(executor)));
      }

      public final B probeExecutor(final Supplier<ExecutorService> supplier) {
        this.probeExecutor = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B eventValidator(final EventValidator validator) {
        return this.eventValidator(ofInstance(requireNonNull(validator)));
      }

      public final B eventValidator(final Supplier<EventValidator> supplier) {
        this.eventValidator = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B persistenceStore(final PersistenceStore store) {
        return this.persistenceStore(ofInstance(requireNonNull(store)));
      }

      public final B persistenceStore(final Supplier<PersistenceStore> supplier) {
        this.persistenceStore = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B sendFailureHandler(final SendFailureHandler handler) {
        return this.sendFailureHandler(ofInstance(requireNonNull(handler)));
      }

      public final B sendFailureHandler(final Supplier<SendFailureHandler> handler) {
        this.sendFailureHandler = requireNonNull(handler);

        return this.asSubtype();
      }

      public final B registerFailureHandler(final RegisterFailureHandler handler) {
        this.registerFailureHandler = requireNonNull(handler);

        return this.asSubtype();
      }

      public final B disposalHook(final DisposalHook hook) {
        this.disposalHook = requireNonNull(hook);

        return this.asSubtype();
      }

      public abstract Configuration build();
    }

    public final PluginConsole pluginConsole() {
      return requireNonNull(this.pluginConsole.get());
    }

    public final DisplayExecutor diplayExecutor() {
      return requireNonNull(this.diplayExecutor.get());
    }

    public final ExecutorService sharedExecutor() {
      return requireNonNull(this.sharedExecutor.get());
    }

    public final Optional<DataInjector> dataInjector() {
      return fromNullable(this.dataInjector.get());
    }

    public final Optional<Map<String, Probe<?>>> probeMappings() {
      return fromNullable(this.probeMappings.get());
    }

    public final Optional<ExecutorService> probeExecutor() {
      return fromNullable(this.probeExecutor.get());
    }

    public final Optional<EventValidator> eventValidator() {
      return fromNullable(this.eventValidator.get());
    }

    public final PersistenceStore persistenceStore() {
      return requireNonNull(this.persistenceStore.get());
    }

    public final Optional<SendFailureHandler> sendFailureHandler() {
      return fromNullable(this.sendFailureHandler.get());
    }

    public final Optional<RegisterFailureHandler> registerFailureHandler() {
      return fromNullable(this.registerFailureHandler);
    }

    public final Optional<DisposalHook> disposalHook() {
      return fromNullable(this.disposalHook);
    }
  }

  public static final class RegularConfiguration extends AbstractConfiguration {
    RegularConfiguration(final Builder builder) {
      super(builder);
    }

    public static final class Builder extends AbstractBuilder<Builder> {
      public Builder() {
      }

      @Override
      protected Builder asSubtype() {
        return this;
      }

      @Override
      public RegularConfiguration build() {
        return new RegularConfiguration(this);
      }
    }

    public static Builder builder() {
      return new Builder();
    }
  }

  protected final long currentTime() {
    return NANOSECONDS.toMillis(this.timeSource().read());
  }

  protected final Ticker timeSource() {
    return this.ticker;
  }

  protected final Stopwatch createStartedStopwatch() {
    return Stopwatch.createStarted(this.ticker);
  }

  protected final Stopwatch createUnstartedStopwatch() {
    return Stopwatch.createUnstarted(this.ticker);
  }

  @Override
  protected final <V> V execute(final DisplayTask<V> task) {
    this.runtimeStatistics.displayTaskCount.incrementAndGet();

    return task.get(this.displayExecutor);
  }

  @Override
  protected final void execute(final Runnable command) {
    this.runtimeStatistics.runnableCommandCount.incrementAndGet();

    this.sharedExecutor.execute(command);
  }

  // TODO parametrize with L
  public interface RegisterFailureHandler {
    public void preRegisterFailure(RegularEventListener listener, Runnable task, Exception failure);

    public void postRegisterFailure(RegularEventListener listener, Runnable task, Exception failure);

    public void preUnregisterFailure(RegularEventListener listener, Runnable task, Exception failure);

    public void postUnregisterFailure(RegularEventListener listener, Runnable task, Exception failure);
  }

  private enum PropagatingRegisterFailureHandler implements RegisterFailureHandler {
    instance;

    public void preRegisterFailure(final RegularEventListener listener, final Runnable task, final Exception failure) {
      propagate(failure);
    }

    public void postRegisterFailure(final RegularEventListener listener, final Runnable task, final Exception failure) {
      propagate(failure);
    }

    public void preUnregisterFailure(final RegularEventListener listener, final Runnable task, final Exception failure) {
      propagate(failure);
    }

    public void postUnregisterFailure(final RegularEventListener listener, final Runnable task, final Exception failure) {
      propagate(failure);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected final void preRegisterFailure(final Runnable task, final Exception failure) {
    this.registerFailureHandler.preRegisterFailure(this, task, failure);
  }

  @Override
  protected final void postRegisterFailure(final Runnable task, final Exception failure) {
    this.registerFailureHandler.postRegisterFailure(this, task, failure);
  }

  @Override
  protected final void preUnregisterFailure(final Runnable task, final Exception failure) {
    this.registerFailureHandler.preUnregisterFailure(this, task, failure);
  }

  @Override
  protected final void postUnregisterFailure(final Runnable task, final Exception failure) {
    this.registerFailureHandler.postUnregisterFailure(this, task, failure);
  }

  //TODO parametrize with L? may help with optional probing
  public interface DataInjector {
    public void inject(String path, Event data);
  }

  private enum NoDataInjector implements DataInjector {
    instance;

    public void inject(final String path, final Event data) {}
  }

  public static final class ProbingDataInjector implements DataInjector {
    private final Prober<? super Event, Probe<?>> prober;

    private ProbingDataInjector(final Prober<? super Event, Probe<?>> prober) {
      this.prober = requireNonNull(prober);
    }

    public static ProbingDataInjector of(final Map<String, Probe<?>> probes) {
      return new ProbingDataInjector(Probers.create(probes));
    }

    public static ProbingDataInjector of(final Map<String, Probe<?>> probes, final ExecutorService executor) {
      return new ProbingDataInjector(Probers.create(probes, executor));
    }

    public static ProbingDataInjector of(final Prober<? super Event, Probe<?>> prober) {
      return new ProbingDataInjector(prober);
    }

    public void inject(final String path, final Event data) {
      this.prober.inject(data);
    }

    @Override
    public String toString() {
      return toStringHelper(this.getClass(), this.prober);
    }
  }

  @Override
  protected final void inject(final String path, final Event data) {
    this.runtimeStatistics.injectCount.incrementAndGet();

    final Stopwatch watch = this.createStartedStopwatch();

    this.dataInjector.inject(path, data);

    final long delta = watch.elapsed(RuntimeStatistics.timeUnit);

    this.runtimeStatistics.injectTime.addAndGet(delta);
  }

  //  TODO parametrize with L?
  public interface EventValidator {
    public void validate(String path, Event data);
  }

  private enum StandardEventValidator implements EventValidator {
    instance;

    public void validate(final String path, final Event data) {
      checkArgument(!isNullOrEmpty(path));
      checkArgument(data.getTimestamp() > 0L);
      checkArgument(!isNullOrEmpty(data.getAction()));
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected final void validate(final String path, final Event data) {
    this.runtimeStatistics.validateCount.incrementAndGet();

    final Stopwatch watch = this.createStartedStopwatch();

    this.eventValidator.validate(path, data);

    final long delta = watch.elapsed(RuntimeStatistics.timeUnit);

    this.runtimeStatistics.validateTime.addAndGet(delta);
  }

  //TODO parametrize with L?
  public interface PersistenceStore extends AutoCloseable {
    public void persist(String path, Event data) throws Exception;
  }

  public static final class StoreWrapper implements PersistenceStore {
    private final Store<? super Event> store;

    private StoreWrapper(final Store<? super Event> store) {
      this.store = requireNonNull(store);
    }

    public static StoreWrapper of(final Store<? super Event> store) {
      return new StoreWrapper(store);
    }

    public void persist(final String path, final Event data) throws Exception {
      this.store.save(path, data);
    }

    public void close() throws Exception {
      this.store.close();
    }

    @Override
    public String toString() {
      return toStringHelper(this.getClass(), this.store);
    }
  }

  @Override
  protected final void persist(final String path, final Event data) throws Exception {
    this.runtimeStatistics.persistCount.incrementAndGet();

    final Stopwatch watch = this.createStartedStopwatch();

    this.persistenceStore.persist(path, data);

    final long delta = watch.elapsed(RuntimeStatistics.timeUnit);

    this.runtimeStatistics.persistTime.addAndGet(delta);
  }

  //TODO parametrize with L
  public interface SendFailureHandler {
    public void handleSendFailure(RegularEventListener listener, String path, Event data, Exception failure);
  }

  private enum PropagatingSendFailureHandler implements SendFailureHandler {
    instance;

    public void handleSendFailure(final RegularEventListener listener, final String path, final Event data, final Exception failure) {
      propagate(failure);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected final void sendFailure(final String path, final Event data, final Exception failure) {
    this.runtimeStatistics.sendFailures.incrementAndGet();

    this.sendFailureHandler.handleSendFailure(this, path, data, failure);
  }

  static String toStringHelper(final Class<?> wrapper, final Object delegate) {
    return new StringBuilder(wrapper.getClass().getSimpleName()).append("(").append(delegate).append(")").toString();
  }

  protected abstract Options defaultOptions();

  protected final Options customOptions() {
    return this.optionsLoader.get();
  }

  protected final Options effectiveOptions() {
    return compound(this.customOptions(), this.defaultOptions());
  }

  // TODO maybe make this protected and provide cleaner interface, i.e. getEffective(), getDefault(), ... also add to config
  // TODO reload on preference change, add an internal listener here
  private static final class OptionsLoader implements Supplier<Options> {
    private final RegularEventListener listener;

    @Nullable
    private Options options;

    OptionsLoader(final RegularEventListener listener) {
      this.listener = requireNonNull(listener);
    }

    static Options load(final ListenerPreferences preferences, final RegularEventListener listener) {
      Map<Class<? extends Listener>, Options> data = preferences.getListenerConfigurationData();
      Options untrusted = data.get(listener.getClass());

      return untrusted != null ? MapOptions.from(ImmutableMap.copyOf(untrusted.toMap())) : emptyOptions();
    }

    public Options load(final ListenerPreferences preferences) {
      return this.options = load(preferences, this.listener);
    }

    public Options get() {
      return checkNotNullAsState(this.options, this.listener + ": Custom options requested but not loaded");
    }
  }

  private static final class RuntimeStatistics {
    static final TimeUnit timeUnit = NANOSECONDS;

    final AtomicLong registrationCount = zero();

    final AtomicLong unregistrationCount = zero();

    final AtomicLong displayTaskCount = zero();

    final AtomicLong runnableCommandCount = zero();

    final AtomicLong sendCalls = zero();

    final AtomicLong sendFailures = zero();

    final AtomicLong injectCount = zero();

    final AtomicLong injectTime = zero();

    final AtomicLong validateCount = zero();

    final AtomicLong validateTime = zero();

    final AtomicLong persistCount = zero();

    final AtomicLong persistTime = zero();

    RuntimeStatistics() {}

    private static AtomicLong zero() {
      return new AtomicLong();
    }
  }

  @Override
  final void preSend(final String path, final Event data) {
    this.runtimeStatistics.sendCalls.incrementAndGet();
  }

  @Override
  final void postSend(final String path, final Event data) {}

  protected abstract class AbstractConfigurationProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractConfigurationProbe() {}

    public Content get() {
      RegularEventListener listener = RegularEventListener.this;

      AnyStructuredData data = new AnyStructuredData();

      data.put(key("instance"), ObjectData.of(listener));
      data.put(key("pluginConsole"), ObjectData.of(listener.pluginConsole));
      data.put(key("displayExecutor"), ObjectData.of(listener.displayExecutor));
      data.put(key("sharedExecutor"), ObjectData.of(listener.sharedExecutor));
      data.put(key("dataInjector"), ObjectData.of(listener.dataInjector));
      data.put(key("eventValidator"), ObjectData.of(listener.eventValidator));
      data.put(key("persistenceStore"), ObjectData.of(listener.persistenceStore));
      data.put(key("sendFailureHandler"), ObjectData.of(listener.sendFailureHandler));
      data.put(key("registerFailureHandler"), ObjectData.of(listener.registerFailureHandler));
      data.put(key("disposalHook"), ObjectData.of(listener.disposalHook));

      return data;
    }
  }

  protected final class RegularConfigurationProbe extends AbstractOptionsProbe {
    protected RegularConfigurationProbe() {}
  }

  protected abstract class AbstractOptionsProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractOptionsProbe() {}

    public Content get() {
      RegularEventListener listener = RegularEventListener.this;

      AnyStructuredData data = new AnyStructuredData();

      data.put(key("options", "default"), listener.defaultOptions().toMap());
      data.put(key("options", "custom"), listener.customOptions().toMap());
      data.put(key("options", "effective"), listener.effectiveOptions().toMap());

      return data;
    }
  }

  protected final class RegularOptionsProbe extends AbstractOptionsProbe {
    protected RegularOptionsProbe() {}
  }

  protected abstract class AbstractStatisticsProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractStatisticsProbe() {}

    public Content get() {
      RuntimeStatistics statistics = RegularEventListener.this.runtimeStatistics;

      AnyStructuredData data = new AnyStructuredData();

      data.put(key("registrationCount"), statistics.registrationCount);
      data.put(key("unregistrationCount"), statistics.unregistrationCount);

      data.put(key("displayTaskCount"), statistics.displayTaskCount);
      data.put(key("runnableCommandCount"), statistics.runnableCommandCount);

      data.put(key("sendInvocations"), statistics.sendCalls);
      data.put(key("sendFailures"), statistics.sendFailures);

      data.put(key("injectCount"), statistics.injectCount);
      data.put(key("injectTime"), statistics.injectTime);

      data.put(key("validateCount"), statistics.validateCount);
      data.put(key("validateTime"), statistics.validateTime);

      data.put(key("persistCount"), statistics.persistCount);
      data.put(key("persistTime"), statistics.persistTime);

      return data;
    }
  }

  protected final class RegularStatisticsProbe extends AbstractStatisticsProbe {
    protected RegularStatisticsProbe() {}
  }

  @Override
  protected Map<String, InternalProbe<?>> internalProbeMappings() {
    return emptyMap();
  }

  public interface DisposalHook {
    public void onDispose(RegularEventListener listener) throws Exception;
  }

  public static abstract class AbstractDisposalHook implements DisposalHook {
    protected final boolean closePersistenceStore;

    protected final boolean shutdownSharedExecutor;

    protected final boolean disposeDisplayExecutor;

    protected final boolean closeLoggerConsole;

    /**
     * Constructor for use by subclasses.
     */
    protected AbstractDisposalHook(final AbstractBuilder<?> builder) {
      this.closePersistenceStore = builder.closePersistenceStore;
      this.shutdownSharedExecutor = builder.shutdownSharedExecutor;
      this.disposeDisplayExecutor = builder.disposeDisplayExecutor;
      this.closeLoggerConsole = builder.closeLoggerConsole;
    }

    public static abstract class AbstractBuilder<B extends AbstractBuilder<B>> {
      boolean closePersistenceStore;

      boolean shutdownProbeExecutor;

      boolean shutdownSharedExecutor;

      boolean disposeDisplayExecutor;

      boolean closeLoggerConsole;

      /**
       * Constructor for use by subclasses.
       */
      protected AbstractBuilder() {}

      /**
       * Always returns {@code this}.
       */
      protected abstract B asSubtype();

      public final B closePersistenceStore() {
        return this.asSubtype();
      }

      public final B shutdownProbeExecutor() {
        return this.asSubtype();
      }

      public final B shutdownSharedExecutor() {
        return this.asSubtype();
      }

      public final B disposeDisplayExecutor() {
        return this.asSubtype();
      }

      public final B closeLoggerConsole() {
        return this.asSubtype();
      }

      public abstract DisposalHook build();
    }

    public void onDispose(final RegularEventListener listener) throws Exception {
      this.tryToClosePersistenceStore(listener);
      this.tryToShutdownSharedExecutor(listener);
      this.tryToDisposeDisplayExecutor(listener);
      this.tryToCloseLoggerConsole(listener);
    }

    protected abstract void report(RegularEventListener listener, final Object subject, final Exception failure);

    protected final void tryToClosePersistenceStore(final RegularEventListener listener) {
      if (this.closePersistenceStore) {
        try {
          listener.persistenceStore.close();
        } catch (Exception failure) {
          this.report(listener, listener.persistenceStore, failure);
        }
      }
    }

    protected final void tryToShutdownSharedExecutor(final RegularEventListener listener) {
      if (this.shutdownSharedExecutor) {
        try {
          listener.sharedExecutor.shutdown();
        } catch (Exception failure) {
          this.report(listener, listener.sharedExecutor, failure);
        }
      }
    }

    protected final void tryToDisposeDisplayExecutor(final RegularEventListener listener) {
      if (this.disposeDisplayExecutor) {
        try {
          listener.displayExecutor.getDisplay().dispose();
        } catch (Exception failure) {
          this.report(listener, listener.displayExecutor.getDisplay(), failure);
        }
      }
    }

    protected final void tryToCloseLoggerConsole(final RegularEventListener listener) {
      if (this.closeLoggerConsole) {
        try {
          listener.pluginConsole.close();
        } catch (Exception failure) {
          this.report(listener, listener.pluginConsole, failure);
        }
      }
    }
  }

  private enum IgnoringDisposalHook implements DisposalHook {
    instance;

    public void onDispose(final RegularEventListener listener) {}

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static final class BasicDisposalHook extends AbstractDisposalHook {
    private final Logger logger;

    BasicDisposalHook(final Builder builder) {
      super(builder);

      this.logger = requireNonNull(builder.logger);
    }

    public static final class Builder extends AbstractBuilder<Builder> {
      Logger logger;

      protected Builder() {
      }

      @Override
      protected Builder asSubtype() {
        return this;
      }

      public Builder logger(final Logger logger) {
        this.logger = requireNonNull(logger);

        return this;
      }

      @Override
      public BasicDisposalHook build() {
        return new BasicDisposalHook(this);
      }
    }

    public static Builder builder() {
      return new Builder();
    }

    @Override
    protected void report(final RegularEventListener listener, final Object subject, final Exception failure) {
      this.logger.log(Level.INFO, listener + ": Unable to dispose " + subject, failure);
    }
  }

  @Override
  protected final void onWorkbenchShutdown() throws Exception {
    // unused
  }

  @Override
  protected final void onFinalUnregistration() throws Exception {
    this.disposalHook.onDispose(this);
  }

  public Options getOptions() {
    return this.effectiveOptions();
  }

  public Options getOptions(final Scope scope) {
    if (scope == StandardScope.DEFAULT) {
      return this.defaultOptions();
    } else if (scope == StandardScope.EFFECTIVE) {
      return this.effectiveOptions();
    }

    throw new IllegalArgumentException();
  }
}
