package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.base.Ticker;

import sk.stuba.fiit.perconik.activity.data.ObjectData;
import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.activity.probes.Prober;
import sk.stuba.fiit.perconik.activity.probes.Probers;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.store.Store;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

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
import static sk.stuba.fiit.perconik.utilities.MoreSuppliers.ofNull;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class RegularEventListener extends AbstractEventListener {
  private static final Ticker ticker = systemTicker();

  protected final PluginConsole pluginConsole;

  protected final DisplayExecutor displayExecutor;

  protected final ExecutorService sharedExecutor;

  protected final DataInjector dataInjector;

  protected final EventValidator eventValidator;

  protected final PersistenceStore persistenceStore;

  protected final SendFailureHandler failureHandler;

  private final DisposalHook disposalHook;

  final Statistics statistics;

  /**
   * Constructor for use by subclasses.
   */
  protected RegularEventListener(final Configuration configuration) {
    this.pluginConsole = requireNonNull(configuration.pluginConsole());
    this.displayExecutor = requireNonNull(configuration.diplayExecutor());
    this.sharedExecutor = requireNonNull(configuration.sharedExecutor());
    this.persistenceStore = requireNonNull(configuration.persistenceStore());

    this.dataInjector = resolveDataInjector(configuration);

    this.eventValidator = configuration.eventValidator().or(StandardEventValidator.instance);
    this.failureHandler = configuration.failureHandler().or(PropagatingSendFailureHandler.instance);
    this.disposalHook = configuration.disposalHook().or(IgnoringDisposalHook.instance);

    this.statistics = new Statistics();
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

  public interface Configuration {
    public PluginConsole pluginConsole();

    public DisplayExecutor diplayExecutor();

    public ExecutorService sharedExecutor();

    public Optional<DataInjector> dataInjector();

    public Optional<Map<String, Probe<?>>> probeMappings();

    public Optional<ExecutorService> probeExecutor();

    public Optional<EventValidator> eventValidator();

    public PersistenceStore persistenceStore();

    public Optional<SendFailureHandler> failureHandler();

    public Optional<DisposalHook> disposalHook();

    public static interface Builder {
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

    private final Supplier<SendFailureHandler> failureHandler;

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
      this.failureHandler = requireNonNull(builder.failureHandler);
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

      Supplier<SendFailureHandler> failureHandler = ofNull();

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

      public final B failureHandler(final SendFailureHandler handler) {
        return this.failureHandler(ofInstance(requireNonNull(handler)));
      }

      public final B failureHandler(final Supplier<SendFailureHandler> handler) {
        this.failureHandler = requireNonNull(handler);

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

    public final Optional<SendFailureHandler> failureHandler() {
      return fromNullable(this.failureHandler.get());
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

  @Override
  protected final <V> V execute(final DisplayTask<V> task) {
    this.statistics.displayTaskCount.incrementAndGet();

    return task.get(this.displayExecutor);
  }

  @Override
  protected final void execute(final Runnable command) {
    this.statistics.runnableCommandCount.incrementAndGet();

    this.sharedExecutor.execute(command);
  }

  @Override
  public final void preRegister() {
    this.statistics.registrationCount.incrementAndGet();
  }

  @Override
  public final void postUnregister() {
    this.statistics.unregistrationCount.incrementAndGet();
  }

  public static interface DataInjector {
    public void inject(String path, Event event);
  }

  private enum NoDataInjector implements DataInjector {
    instance;

    public void inject(final String path, final Event event) {}
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

    public void inject(final String path, final Event event) {
      this.prober.inject(event);
    }

    @Override
    public String toString() {
      return toStringHelper(this.getClass(), this.prober);
    }
  }

  @Override
  protected final void inject(final String path, final Event data) {
    this.statistics.injectCount.incrementAndGet();

    final Stopwatch watch = Stopwatch.createStarted(ticker);

    this.dataInjector.inject(path, data);

    final long delta = watch.elapsed(NANOSECONDS);

    this.statistics.injectTime.addAndGet(delta);
  }

  public static interface EventValidator {
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
    this.statistics.validateCount.incrementAndGet();

    final Stopwatch watch = Stopwatch.createStarted(ticker);

    this.eventValidator.validate(path, data);

    final long delta = watch.elapsed(NANOSECONDS);

    this.statistics.validateTime.addAndGet(delta);
  }

  public static interface PersistenceStore extends AutoCloseable {
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
    this.statistics.persistCount.incrementAndGet();

    final Stopwatch watch = Stopwatch.createStarted(ticker);

    this.persistenceStore.persist(path, data);

    final long delta = watch.elapsed(NANOSECONDS);

    this.statistics.persistTime.addAndGet(delta);
  }

  public static interface SendFailureHandler {
    public void handleSendFailure(final String path, final Event data, final Exception failure);
  }

  private enum PropagatingSendFailureHandler implements SendFailureHandler {
    instance;

    public void handleSendFailure(final String path, final Event data, final Exception failure) {
      propagate(failure);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected final void sendFailure(final String path, final Event data, final Exception failure) {
    this.statistics.sendFailures.incrementAndGet();

    this.failureHandler.handleSendFailure(path, data, failure);
  }

  static String toStringHelper(final Class<?> wrapper, final Object delegate) {
    return new StringBuilder(wrapper.getClass().getSimpleName()).append("(").append(delegate).append(")").toString();
  }

  private static final class Statistics {
    final AtomicLong registrationCount = zero();

    final AtomicLong unregistrationCount = zero();

    final AtomicLong displayTaskCount = zero();

    final AtomicLong runnableCommandCount = zero();

    final AtomicLong sendInvocations = zero();

    final AtomicLong sendFailures = zero();

    final AtomicLong injectCount = zero();

    final AtomicLong injectTime = zero();

    final AtomicLong validateCount = zero();

    final AtomicLong validateTime = zero();

    final AtomicLong persistCount = zero();

    final AtomicLong persistTime = zero();

    Statistics() {}

    private static final AtomicLong zero() {
      return new AtomicLong();
    }
  }

  @Override
  final void preSend(final String path, final Event data) {
    this.statistics.sendInvocations.incrementAndGet();
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
      data.put(key("failureHandler"), ObjectData.of(listener.failureHandler));

      return data;
    }
  }

  protected final class RegularConfigurationProbe extends AbstractConfigurationProbe {
    protected RegularConfigurationProbe() {}
  }

  protected abstract class AbstractStatisticsProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractStatisticsProbe() {}

    public Content get() {
      Statistics statistics = RegularEventListener.this.statistics;

      AnyStructuredData data = new AnyStructuredData();

      data.put(key("registrationCount"), statistics.registrationCount);
      data.put(key("unregistrationCount"), statistics.unregistrationCount);

      data.put(key("displayTaskCount"), statistics.displayTaskCount);
      data.put(key("runnableCommandCount"), statistics.runnableCommandCount);

      data.put(key("sendInvocations"), statistics.sendInvocations);
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
    public void onDispose(Listener listener) throws Exception;
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

    public void onDispose(final Listener listener) throws Exception {
      if (listener instanceof RegularEventListener) {
        RegularEventListener implementation = (RegularEventListener) listener;

        this.tryToClosePersistenceStore(implementation);
        this.tryToShutdownSharedExecutor(implementation);
        this.tryToDisposeDisplayExecutor(implementation);
        this.tryToCloseLoggerConsole(implementation);
      }
    }

    protected abstract void report(final Object reference, final Exception failure);

    protected final void tryToClosePersistenceStore(final RegularEventListener listener) {
      if (this.closePersistenceStore) {
        try {
          listener.persistenceStore.close();
        } catch (Exception failure) {
          this.report(listener.persistenceStore, failure);
        }
      }
    }

    protected final void tryToShutdownSharedExecutor(final RegularEventListener listener) {
      if (this.shutdownSharedExecutor) {
        try {
          listener.sharedExecutor.shutdown();
        } catch (Exception failure) {
          this.report(listener.sharedExecutor, failure);
        }
      }
    }

    protected final void tryToDisposeDisplayExecutor(final RegularEventListener listener) {
      if (this.disposeDisplayExecutor) {
        try {
          listener.displayExecutor.getDisplay().dispose();
        } catch (Exception failure) {
          this.report(listener.displayExecutor.getDisplay(), failure);
        }
      }
    }

    protected final void tryToCloseLoggerConsole(final RegularEventListener listener) {
      if (this.closeLoggerConsole) {
        try {
          listener.pluginConsole.close();
        } catch (Exception failure) {
          this.report(listener.pluginConsole, failure);
        }
      }
    }
  }

  private enum IgnoringDisposalHook implements DisposalHook {
    instance;

    public void onDispose(final Listener listener) {}

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
    protected void report(final Object reference, final Exception failure) {
      this.logger.log(Level.INFO, "Unable to dispose " + reference, failure);
    }
  }

  @Override
  protected final void dispose() throws Exception {
    this.disposalHook.onDispose(this);
  }
}
