package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.base.Ticker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ListMultimap;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;

import sk.stuba.fiit.perconik.activity.data.ObjectData;
import sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.activity.probes.Prober;
import sk.stuba.fiit.perconik.activity.probes.Probers;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.preferences.ListenerPreferences;
import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.events.Event;
import sk.stuba.fiit.perconik.data.store.Store;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeUnits;
import sk.stuba.fiit.perconik.utilities.configuration.ForwardingOptions;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Options;
import sk.stuba.fiit.perconik.utilities.configuration.Scope;
import sk.stuba.fiit.perconik.utilities.configuration.ScopedConfigurable;
import sk.stuba.fiit.perconik.utilities.configuration.StandardScope;
import sk.stuba.fiit.perconik.utilities.time.TimeSource;

import static java.lang.String.format;
import static java.util.Collections.emptyMap;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import static com.google.common.base.Functions.constant;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Suppliers.ofInstance;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.activity.data.DataCollections.toObjectData;
import static sk.stuba.fiit.perconik.activity.plugin.Activator.defaultInstance;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor.defaultSynchronous;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAsState;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toDefaultString;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.compound;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.emptyOptions;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.inheritedRawOptions;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class RegularListener<L extends RegularListener<L>> extends AbstractListener implements ScopedConfigurable {
  static final String internalProbeKeyPrefix = "meta";

  /**
   * Underlying listener options holder.
   */
  protected final OptionsLoader<? super L> optionsLoader;

  /**
   * Underlying listener options provider.
   */
  final OptionsProvider<L> optionsProvider;

  /**
   * Underlying time context.
   */
  protected final TimeContext timeContext;

  /**
   * Underlying plug-in console for general logging.
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
  protected final DataInjector<? super L> dataInjector;

  /**
   * Underlying event data validator for validating
   * event objects before persistence.
   */
  protected final EventValidator<? super L> eventValidator;

  /**
   * Underlying persistence store for persisting event data.
   */
  protected final PersistenceStore<? super L> persistenceStore;

  /**
   * Underlying event data send failure handler.
   */
  protected final SendFailureHandler<? super L> sendFailureHandler;

  /**
   * Underlying listener statistics.
   */
  final RuntimeStatistics runtimeStatistics;

  /**
   * Underlying listener registration failure handler.
   */
  final RegisterFailureHandler<? super L> registerFailureHandler;

  /**
   * Underlying listener disposal hook.
   */
  final DisposalHook<? super L> disposalHook;

  /**
   * Constructor for use by subclasses.
   */
  protected <C> RegularListener(final Configuration<C, L> configuration) {
    this(configuration, ofInstance((C) null));
  }

  /**
   * Constructor for use by subclasses.
   */
  protected <C> RegularListener(final Configuration<C, L> configuration, final C context) {
    this(configuration, ofInstance(context));
  }

  // TODO note that if C is the instance being constructed - which actually can be,
  // and almost always is - great care must be taken when accessing fields during the
  // construction, since some may be observed as uninitialized in different construction states;
  // this behavior should be well documented (or refactored) as well as field initialization order;
  // for example one can not read dataInjector while configuring sharedExecutor

  private <C> RegularListener(final Configuration<C, L> configuration, final Supplier<? extends C> supplier) {
    final C context = this.resolveContext(configuration, supplier);

    // note that field initialization order is significant
    this.optionsLoader = this.resolveOptionsLoader(configuration, context);
    this.optionsProvider = this.setupOptionsProvider();

    this.timeContext = configuration.timeContext(context).or(SystemTimeContext.instance);
    this.pluginConsole = configuration.pluginConsole(context).or(defaultInstance().getConsole());

    this.displayExecutor = configuration.diplayExecutor(context).or(defaultSynchronous());
    this.sharedExecutor = configuration.sharedExecutor(context).or(newSingleThreadExecutor());

    this.dataInjector = this.resolveDataInjector(configuration, context);

    this.eventValidator = configuration.eventValidator(context).or(StandardEventValidator.instance);
    this.persistenceStore = configuration.persistenceStore(context).or(VoidPersistenceStore.instance);
    this.sendFailureHandler = configuration.sendFailureHandler(context).or(PropagatingSendFailureHandler.instance);

    this.runtimeStatistics = this.setupRuntimeStatistics();

    this.registerFailureHandler = configuration.registerFailureHandler(context).or(PropagatingRegisterFailureHandler.instance);
    this.disposalHook = configuration.disposalHook(context).or(IgnoringDisposalHook.instance);
  }

  /**
   * Always returns {@code this}.
   */
  final L asSubtype() {
    // cast is most likely to be safe since listener base
    // type has been already checked at context resolving

    @SuppressWarnings("unchecked")
    L self = (L) this;

    return self;
  }

  private <C> C resolveContext(final Configuration<C, L> configuration, final Supplier<? extends C> supplier) {
    Class<? extends C> contextType = checkNotNull(configuration.contextType());
    Class<? extends L> listenerType = checkNotNull(configuration.listenerType());

    C context = checkNotNull(supplier).get();

    checkState(listenerType.isInstance(this), "%s: %s is not an instance of %s", this, toDefaultString(this), listenerType.getName());

    try {
      return contextType.cast(context == null ? this : context);
    } catch (ClassCastException failure) {
      throw new IllegalStateException(format("%s: %s is not an instance of %s", this, toDefaultString(this), contextType.getName()));
    }
  }

  private <C> OptionsLoader<? super L> resolveOptionsLoader(final Configuration<C, L> configuration, final C context) {
    Optional<OptionsLoader<? super L>> loader = configuration.optionsLoader(context);

    Optional<ActivityPreferences> activity = configuration.activityPreferences(context);
    Optional<ListenerPreferences> listener = configuration.listenerPreferences(context);

    if (loader.isPresent()) {
      checkState(!activity.isPresent(), "%s: options loader configured but activity preferences also present", this);
      checkState(!listener.isPresent(), "%s: options loader configured but listener preferences also present", this);

      return loader.get();
    }

    return UpdatingOptionsLoader.of(this.asSubtype(), activity.or(ActivityPreferences.getShared()), listener.or(ListenerPreferences.getShared()));
  }

  private <C> DataInjector<? super L> resolveDataInjector(final Configuration<C, L> configuration, final C context) {
    Optional<DataInjector<? super L>> injector = configuration.dataInjector(context);
    Optional<Map<String, Probe<?>>> mappings = configuration.probeMappings(context);

    if (injector.isPresent()) {
      checkState(!mappings.isPresent(), "%s: custom data injector configured but probe mappings also present", this);

      return injector.get();
    }

    if (mappings.isPresent()) {
      Map<String, Probe<?>> mix = newHashMap(mappings.get());

      for (Entry<String, InternalProbe<?>> entry: this.internalProbeMappings().entrySet()) {
        // potentially unsafe raw cast to InternalProbe is correct and safe in this case
        mix.put(key(internalProbeKeyPrefix, entry.getKey()), InternalProbe.class.cast(entry.getValue()));
      }

      Optional<Predicate<Entry<String, Probe<?>>>> filter = configuration.probeFilter(context);
      Optional<ExecutorService> executor = configuration.probeExecutor(context);

      if (filter.isPresent() && executor.isPresent()) {
        return ProbingDataInjector.of(Probers.create(mix, filter.get(), executor.get()));
      } else if (filter.isPresent()) {
        return ProbingDataInjector.of(Probers.create(mix, filter.get()));
      } else if (executor.isPresent()) {
        return ProbingDataInjector.of(Probers.create(mix, executor.get()));
      }

      return ProbingDataInjector.of(Probers.create(mix));
    }

    return NoDataInjector.instance;
  }

  private OptionsProvider<L> setupOptionsProvider() {
    final OptionsProvider<L> provider = new LazyOptionsProvider<>(this.optionsLoader, this.asSubtype());

    RegistrationHook.PRE_REGISTER.add(this, new NamedRunnable(OptionsProvider.class) {
      public void run() {
        reloadOptions();
      }
    });

    return provider;
  }

  private RuntimeStatistics setupRuntimeStatistics() {
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

  public interface Configuration<C, L extends Listener> {
    public Class<? extends C> contextType();

    public Class<? extends L> listenerType();

    public Optional<ActivityPreferences> activityPreferences(C context);

    public Optional<ListenerPreferences> listenerPreferences(C context);

    public Optional<OptionsLoader<? super L>> optionsLoader(C context);

    public Optional<TimeContext> timeContext(C context);

    public Optional<PluginConsole> pluginConsole(C context);

    public Optional<DisplayExecutor> diplayExecutor(C context);

    public Optional<ExecutorService> sharedExecutor(C context);

    public Optional<Map<String, Probe<?>>> probeMappings(C context);

    public Optional<Predicate<Entry<String, Probe<?>>>> probeFilter(C context);

    public Optional<ExecutorService> probeExecutor(C context);

    public Optional<DataInjector<? super L>> dataInjector(C context);

    public Optional<EventValidator<? super L>> eventValidator(C context);

    public Optional<PersistenceStore<? super L>> persistenceStore(C context);

    public Optional<SendFailureHandler<? super L>> sendFailureHandler(C context);

    public Optional<RegisterFailureHandler<? super L>> registerFailureHandler(C context);

    public Optional<DisposalHook<? super L>> disposalHook(C context);

    public interface Builder<C, L extends Listener> {
      public Configuration<C, L> build();
    }
  }

  public static abstract class AbstractConfiguration<C, L extends Listener> implements Configuration<C, L> {
    private final Class<? extends C> contextType;

    private final Class<? extends L> listenerType;

    private final Function<? super C, ? extends ActivityPreferences> activityPreferences;

    private final Function<? super C, ? extends ListenerPreferences> listenerPreferences;

    private final Function<? super C, ? extends OptionsLoader<? super L>> optionsLoader;

    private final Function<? super C, ? extends TimeContext> timeContext;

    private final Function<? super C, ? extends PluginConsole> pluginConsole;

    private final Function<? super C, ? extends DisplayExecutor> diplayExecutor;

    private final Function<? super C, ? extends ExecutorService> sharedExecutor;

    private final Function<? super C, ? extends Map<String, Probe<?>>> probeMappings;

    private final Function<? super C, ? extends Predicate<Entry<String, Probe<?>>>> probeFilter;

    private final Function<? super C, ? extends ExecutorService> probeExecutor;

    private final Function<? super C, ? extends DataInjector<? super L>> dataInjector;

    private final Function<? super C, ? extends EventValidator<? super L>> eventValidator;

    private final Function<? super C, ? extends PersistenceStore<? super L>> persistenceStore;

    private final Function<? super C, ? extends SendFailureHandler<? super L>> sendFailureHandler;

    private final Function<? super C, ? extends RegisterFailureHandler<? super L>> registerFailureHandler;

    private final Function<? super C, ? extends DisposalHook<? super L>> disposalHook;

    /**
     * Constructor for use by subclasses.
     */
    protected AbstractConfiguration(final AbstractBuilder<?, C, L> builder) {
      this.contextType = checkNotNull(builder.contextType);
      this.listenerType = checkNotNull(builder.listenerType);
      this.activityPreferences = checkNotNull(builder.activityPreferences);
      this.listenerPreferences = checkNotNull(builder.listenerPreferences);
      this.optionsLoader = checkNotNull(builder.optionsLoader);
      this.timeContext = checkNotNull(builder.timeContext);
      this.pluginConsole = checkNotNull(builder.pluginConsole);
      this.diplayExecutor = checkNotNull(builder.diplayExecutor);
      this.sharedExecutor = checkNotNull(builder.sharedExecutor);
      this.probeMappings = checkNotNull(builder.probeMappings);
      this.probeFilter = checkNotNull(builder.probeFilter);
      this.probeExecutor = checkNotNull(builder.probeExecutor);
      this.dataInjector = checkNotNull(builder.dataInjector);
      this.eventValidator = checkNotNull(builder.eventValidator);
      this.persistenceStore = checkNotNull(builder.persistenceStore);
      this.sendFailureHandler = checkNotNull(builder.sendFailureHandler);
      this.registerFailureHandler = checkNotNull(builder.registerFailureHandler);
      this.disposalHook = checkNotNull(builder.disposalHook);
    }

    public static abstract class AbstractBuilder<B extends AbstractBuilder<B, C, L>, C, L extends Listener> implements Builder<C, L> {
      Class<? extends C> contextType;

      Class<? extends L> listenerType;

      Function<? super C, ? extends ActivityPreferences> activityPreferences = constant(null);

      Function<? super C, ? extends ListenerPreferences> listenerPreferences = constant(null);

      Function<? super C, ? extends OptionsLoader<? super L>> optionsLoader = constant(null);

      Function<? super C, ? extends TimeContext> timeContext = constant(null);

      Function<? super C, ? extends PluginConsole> pluginConsole = constant(null);

      Function<? super C, ? extends DisplayExecutor> diplayExecutor = constant(null);

      Function<? super C, ? extends ExecutorService> sharedExecutor = constant(null);

      Function<? super C, ? extends Map<String, Probe<?>>> probeMappings = constant(null);

      Function<? super C, ? extends Predicate<Entry<String, Probe<?>>>> probeFilter = constant(null);

      Function<? super C, ? extends ExecutorService> probeExecutor = constant(null);

      Function<? super C, ? extends DataInjector<? super L>> dataInjector = constant(null);

      Function<? super C, ? extends EventValidator<? super L>> eventValidator = constant(null);

      Function<? super C, ? extends PersistenceStore<? super L>> persistenceStore = constant(null);

      Function<? super C, ? extends SendFailureHandler<? super L>> sendFailureHandler = constant(null);

      Function<? super C, ? extends RegisterFailureHandler<? super L>> registerFailureHandler = constant(null);

      Function<? super C, ? extends DisposalHook<? super L>> disposalHook = constant(null);

      /**
       * Constructor for use by subclasses.
       */
      protected AbstractBuilder() {}

      /**
       * Always returns {@code this}.
       */
      protected abstract B asSubtype();

      public final B contextType(final Class<? extends C> type) {
        this.contextType = checkNotNull(type);

        return this.asSubtype();
      }

      public final B listenerType(final Class<? extends L> type) {
        this.listenerType = checkNotNull(type);

        return this.asSubtype();
      }

      public final B activityPreferences(final ActivityPreferences preferences) {
        return this.activityPreferences(constant(checkNotNull(preferences)));
      }

      public final B activityPreferences(final Function<? super C, ? extends ActivityPreferences> relation) {
        this.activityPreferences = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B listenerPreferences(final ListenerPreferences preferences) {
        return this.listenerPreferences(constant(checkNotNull(preferences)));
      }

      public final B listenerPreferences(final Function<? super C, ? extends ListenerPreferences> relation) {
        this.listenerPreferences = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B optionsLoader(final OptionsLoader<? super L> loader) {
        return this.optionsLoader(constant(checkNotNull(loader)));
      }

      public final B optionsLoader(final Function<? super C, ? extends OptionsLoader<? super L>> relation) {
        this.optionsLoader = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B timeContext(final TimeContext context) {
        return this.timeContext(constant(checkNotNull(context)));
      }

      public final B timeContext(final Function<? super C, ? extends TimeContext> relation) {
        this.timeContext = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B pluginConsole(final PluginConsole console) {
        return this.pluginConsole(constant(checkNotNull(console)));
      }

      public final B pluginConsole(final Function<? super C, ? extends PluginConsole> relation) {
        this.pluginConsole = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B diplayExecutor(final DisplayExecutor executor) {
        return this.diplayExecutor(constant(checkNotNull(executor)));
      }

      public final B diplayExecutor(final Function<? super C, ? extends DisplayExecutor> relation) {
        this.diplayExecutor = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B sharedExecutor(final ExecutorService executor) {
        return this.sharedExecutor(constant(checkNotNull(executor)));
      }

      public final B sharedExecutor(final Function<? super C, ? extends ExecutorService> relation) {
        this.sharedExecutor = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B probeMappings(final Map<String, Probe<?>> probes) {
        return this.probeMappings(constant(checkNotNull(probes)));
      }

      public final B probeMappings(final Function<? super C, ? extends Map<String, Probe<?>>> relation) {
        this.probeMappings = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B probeFilter(final Predicate<Entry<String, Probe<?>>> filter) {
        return this.probeFilter(constant(checkNotNull(filter)));
      }

      public final B probeFilter(final Function<? super C, ? extends Predicate<Entry<String, Probe<?>>>> relation) {
        this.probeFilter = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B probeExecutor(final ExecutorService executor) {
        return this.probeExecutor(constant(checkNotNull(executor)));
      }

      public final B probeExecutor(final Function<? super C, ? extends ExecutorService> relation) {
        this.probeExecutor = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B dataInjector(final DataInjector<? super L> injector) {
        return this.dataInjector(constant(checkNotNull(injector)));
      }

      public final B dataInjector(final Function<? super C, ? extends DataInjector<? super L>> relation) {
        this.dataInjector = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B eventValidator(final EventValidator<? super L> validator) {
        return this.eventValidator(constant(checkNotNull(validator)));
      }

      public final B eventValidator(final Function<? super C, ? extends EventValidator<? super L>> relation) {
        this.eventValidator = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B persistenceStore(final PersistenceStore<? super L> store) {
        return this.persistenceStore(constant(checkNotNull(store)));
      }

      public final B persistenceStore(final Function<? super C, ? extends PersistenceStore<? super L>> relation) {
        this.persistenceStore = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B sendFailureHandler(final SendFailureHandler<? super L> handler) {
        return this.sendFailureHandler(constant(checkNotNull(handler)));
      }

      public final B sendFailureHandler(final Function<? super C, ? extends SendFailureHandler<? super L>> relation) {
        this.sendFailureHandler = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B registerFailureHandler(final RegisterFailureHandler<? super L> handler) {
        return this.registerFailureHandler(constant(checkNotNull(handler)));
      }

      public final B registerFailureHandler(final Function<? super C, ? extends RegisterFailureHandler<? super L>> relation) {
        this.registerFailureHandler = checkNotNull(relation);

        return this.asSubtype();
      }

      public final B disposalHook(final DisposalHook<? super L> hook) {
        return this.disposalHook(constant(checkNotNull(hook)));
      }

      public final B disposalHook(final Function<? super C, ? extends DisposalHook<? super L>> relation) {
        this.disposalHook = checkNotNull(relation);

        return this.asSubtype();
      }

      public abstract Configuration<C, L> build();
    }

    private static <T> Optional<T> fromNullableUnsafe(final Object object) {
      // although the cast itself is unsafe, this method is used only to correct the compiler
      // then creating an optional from a function's result which has a bounded type parameter

      @SuppressWarnings("unchecked")
      Optional<T> optional = (Optional<T>) fromNullable(object);

      return optional;
    }

    public final Class<? extends C> contextType() {
      return this.contextType;
    }

    public final Class<? extends L> listenerType() {
      return this.listenerType;
    }

    public final Optional<ActivityPreferences> activityPreferences(final C context) {
      return fromNullable((ActivityPreferences) this.activityPreferences.apply(context));
    }

    public final Optional<ListenerPreferences> listenerPreferences(final C context) {
      return fromNullable((ListenerPreferences) this.listenerPreferences.apply(context));
    }

    public final Optional<OptionsLoader<? super L>> optionsLoader(final C context) {
      return fromNullableUnsafe(this.optionsLoader.apply(context));
    }

    public final Optional<TimeContext> timeContext(final C context) {
      return fromNullable((TimeContext) this.timeContext.apply(context));
    }

    public final Optional<PluginConsole> pluginConsole(final C context) {
      return fromNullable((PluginConsole) this.pluginConsole.apply(context));
    }

    public final Optional<DisplayExecutor> diplayExecutor(final C context) {
      return fromNullable((DisplayExecutor) this.diplayExecutor.apply(context));
    }

    public final Optional<ExecutorService> sharedExecutor(final C context) {
      return fromNullable((ExecutorService) this.sharedExecutor.apply(context));
    }

    public final Optional<Map<String, Probe<?>>> probeMappings(final C context) {
      return fromNullable((Map<String, Probe<?>>) this.probeMappings.apply(context));
    }

    public final Optional<Predicate<Entry<String, Probe<?>>>> probeFilter(final C context) {
      return fromNullable((Predicate<Entry<String, Probe<?>>>) this.probeFilter.apply(context));
    }

    public final Optional<ExecutorService> probeExecutor(final C context) {
      return fromNullable((ExecutorService) this.probeExecutor.apply(context));
    }

    public final Optional<DataInjector<? super L>> dataInjector(final C context) {
      return fromNullableUnsafe((DataInjector<? super L>) this.dataInjector.apply(context));
    }

    public final Optional<EventValidator<? super L>> eventValidator(final C context) {
      return fromNullableUnsafe((EventValidator<? super L>) this.eventValidator.apply(context));
    }

    public final Optional<PersistenceStore<? super L>> persistenceStore(final C context) {
      return fromNullableUnsafe((PersistenceStore<? super L>) this.persistenceStore.apply(context));
    }

    public final Optional<SendFailureHandler<? super L>> sendFailureHandler(final C context) {
      return fromNullableUnsafe((SendFailureHandler<? super L>) this.sendFailureHandler.apply(context));
    }

    public final Optional<RegisterFailureHandler<? super L>> registerFailureHandler(final C context) {
      return fromNullableUnsafe((RegisterFailureHandler<? super L>) this.registerFailureHandler.apply(context));
    }

    public final Optional<DisposalHook<? super L>> disposalHook(final C context) {
      return fromNullableUnsafe((DisposalHook<? super L>) this.disposalHook.apply(context));
    }
  }

  public static final class RegularConfiguration<C, L extends Listener> extends AbstractConfiguration<C, L> {
    RegularConfiguration(final Builder<C, L> builder) {
      super(builder);
    }

    public static final class Builder<C, L extends Listener> extends AbstractBuilder<Builder<C, L>, C, L> {
      public Builder() {}

      @Override
      protected Builder<C, L> asSubtype() {
        return this;
      }

      @Override
      public RegularConfiguration<C, L> build() {
        return new RegularConfiguration<>(this);
      }
    }

    public static <C, L extends Listener> Builder<C, L> builder() {
      return new Builder<>();
    }
  }

  /**
   * Returns an updating view of default options.
   * Updates are dependent on configured {@link OptionsLoader}.
   *
   * <p><b>Note:</b> always returns the same instance.
   */
  protected final Options defaultOptions() {
    return this.optionsProvider.defaultOptions(this.optionsLoader, this.asSubtype());
  }

  /**
   * Returns an updating view of custom options.
   * Updates are dependent on configured {@link OptionsLoader}.
   *
   * <p><b>Note:</b> always returns the same instance.
   *
   * @see #reloadOptions()
   */
  protected final Options customOptions() {
    return this.optionsProvider.customOptions(this.optionsLoader, this.asSubtype());
  }

  /**
   * Returns an updating view of effective options.
   * Updates are dependent on configured {@link OptionsLoader}.
   *
   * <p><b>Note:</b> always returns the same instance.
   *
   * @see #reloadOptions()
   */
  protected final Options effectiveOptions() {
    return this.optionsProvider.effectiveOptions(this.optionsLoader, this.asSubtype());
  }

  /**
   * An {@code OptionsLoader} invokes this method to reload effective options.
   * Always invokes {@link #onOptionsReload()} after reloading options.
   *
   * @see #onOptionsReload()
   */
  protected final void reloadOptions() {
    this.reloadOptions(this.optionsProvider);
  }

  private final void reloadOptions(final OptionsProvider<L> provider) {
    checkNotNullAsState(this.optionsLoader, "%s: options loader not initialized", this);

    if (provider.reload(this.optionsLoader, this.asSubtype())) {
      this.onOptionsReload();
    }
  }

  public interface OptionsLoader<L extends Listener> {
    public Options loadDefaultOptions(L listener);

    public Options loadCustomOptions(L listener);
  }

  public static abstract class AbstractOptionsLoader<L extends RegularListener<L>> implements OptionsLoader<L> {
    private final OptionsReloader reloader;

    protected AbstractOptionsLoader(final L listener) {
      this.reloader = new OptionsReloader(listener);

      this.hook(listener);
    }

    private void hook(final L listener) {
      final OptionsReloader reloader = this.reloader;

      RegistrationHook.PRE_REGISTER.add(listener, new NamedRunnable(OptionsReloader.class) {
        public void run() {
          defaultPreferences().node().addPreferenceChangeListener(reloader);
          customPreferences().node().addPreferenceChangeListener(reloader);
        }
      });

      RegistrationHook.POST_UNREGISTER.add(listener, new NamedRunnable(OptionsReloader.class) {
        public void run() {
          defaultPreferences().node().removePreferenceChangeListener(reloader);
          customPreferences().node().removePreferenceChangeListener(reloader);
        }
      });
    }

    private static final class OptionsReloader implements IPreferenceChangeListener {
      private final RegularListener<?> listener;

      OptionsReloader(final RegularListener<?> listener) {
        this.listener = checkNotNull(listener);
      }

      public void preferenceChange(final PreferenceChangeEvent event) {
        String key = event.getKey();

        if (ActivityPreferences.Keys.listenerDefaultOptions.equals(key) || ListenerPreferences.Keys.configuration.equals(key)) {
          this.listener.reloadOptions();
        }
      }
    }

    protected abstract ActivityPreferences defaultPreferences();

    protected abstract ListenerPreferences customPreferences();

    public final Options loadDefaultOptions(final L listener) {
      Options defaults = checkNotNull(this.defaultPreferences().getListenerDefaultOptions());

      return compound(defaults, this.adjustDefaultOptions(listener));
    }

    public final Options loadCustomOptions(final L listener) {
      Options custom = this.customPreferences().getListenerConfigurationData().get(listener.getClass());

      return compound(custom != null ? custom : emptyOptions(), this.adjustCustomOptions(listener));
    }

    protected abstract Options adjustDefaultOptions(final L listener);

    protected abstract Options adjustCustomOptions(final L listener);

    @Override
    public String toString() {
      return this.toStringHelper().toString();
    }

    protected ToStringHelper toStringHelper() {
      ToStringHelper helper = MoreObjects.toStringHelper(this);

      helper.add("default", this.defaultPreferences());
      helper.add("custom", this.customPreferences());

      return helper;
    }
  }

  public static final class UpdatingOptionsLoader<L extends RegularListener<L>> extends AbstractOptionsLoader<L> {
    private final ActivityPreferences defaults;

    private final ListenerPreferences custom;

    private UpdatingOptionsLoader(final L listener, final ActivityPreferences defaults, final ListenerPreferences custom) {
      super(listener);

      this.defaults = checkNotNull(defaults);
      this.custom = checkNotNull(custom);
    }

    public static <L extends RegularListener<L>> UpdatingOptionsLoader<L> of(final L listener, final ActivityPreferences defaults, final ListenerPreferences custom) {
      return new UpdatingOptionsLoader<>(listener, defaults, custom);
    }

    @Override
    protected ActivityPreferences defaultPreferences() {
      return this.defaults;
    }

    @Override
    protected ListenerPreferences customPreferences() {
      return this.custom;
    }

    @Override
    protected Options adjustDefaultOptions(final L listener) {
      return emptyOptions();
    }

    @Override
    protected Options adjustCustomOptions(final L listener) {
      return emptyOptions();
    }
  }

  private interface OptionsProvider<L extends Listener> {
    public Options defaultOptions(OptionsLoader<? super L> loader, L listener);

    public Options customOptions(OptionsLoader<? super L> loader, L listener);

    public Options effectiveOptions(OptionsLoader<? super L> loader, L listener);

    public boolean reload(OptionsLoader<? super L> loader, L listener);
  }

  // TODO OptionsProvider must be as lazy as possible: options can not be loaded and effectively
  // used (but can be referenced) during listener construction since an infinite loop may occur
  // (defaults require listener instance and listener instance requires defaults, hence the loop)

  private static final class LazyOptionsProvider<L extends RegularListener<L>> implements OptionsProvider<L> {
    private final DefaultOptions<L> defaults;

    private final CustomOptions<L> custom;

    private final EffectiveOptions<L> effective;

    LazyOptionsProvider(final OptionsLoader<? super L> loader, final L listener) {
      this.defaults = new DefaultOptions<>(loader, listener);
      this.custom = new CustomOptions<>(loader, listener);
      this.effective = new EffectiveOptions<>(loader, listener);
    }

    public boolean reload(final OptionsLoader<? super L> loader, final L listener) {
      checkNotNull(loader);
      checkNotNull(listener);

      Options effective = this.effective.delegate;

      this.defaults.load(loader, listener);
      this.custom.load(loader, listener);
      this.effective.load(loader, listener);

      return effective == null || !this.effective.delegate.toMap().equals(effective.toMap());
    }

    private static abstract class LoadingOptions<L extends RegularListener<L>> extends ForwardingOptions {
      private final OptionsLoader<? super L> loader;

      private final L listener;

      @Nullable
      Options delegate;

      LoadingOptions(final OptionsLoader<? super L> loader, final L listener) {
        this.loader = checkNotNull(loader);
        this.listener = checkNotNull(listener);
      }

      static final Options secure(@Nullable final Options untrusted) {
        return untrusted != null ? MapOptions.from(ImmutableMap.copyOf(untrusted.toMap())) : emptyOptions();
      }

      abstract void load(OptionsLoader<? super L> loader, L listener);

      @Override
      protected final Options delegate() {
        if (this.delegate == null) {
          this.load(this.loader, this.listener);
        }

        return checkNotNullAsState(this.delegate);
      }

      @Override
      public final String toString() {
        return this.getClass().getSimpleName() + this.toMap().toString();
      }
    }

    private static final class DefaultOptions<L extends RegularListener<L>> extends LoadingOptions<L> {
      DefaultOptions(final OptionsLoader<? super L> loader, final L listener) {
        super(loader, listener);
      }

      @Override
      void load(final OptionsLoader<? super L> loader, final L listener) {
        // TODO to secure defaults one needs to properly listen to UacaPreferences.getShared() changes

        Options defaults = ActivityPreferences.getDefault().getListenerDefaultOptions();

        this.delegate = compound(defaults, secure(loader.loadDefaultOptions(listener)));
      }
    }

    private static final class CustomOptions<L extends RegularListener<L>> extends LoadingOptions<L> {
      CustomOptions(final OptionsLoader<? super L> loader, final L listener) {
        super(loader, listener);
      }

      @Override
      void load(final OptionsLoader<? super L> loader, final L listener) {
        this.delegate = secure(loader.loadCustomOptions(listener));
      }
    }

    private static final class EffectiveOptions<L extends RegularListener<L>> extends LoadingOptions<L> {
      EffectiveOptions(final OptionsLoader<? super L> loader, final L listener) {
        super(loader, listener);
      }

      @Override
      void load(final OptionsLoader<? super L> loader, final L listener) {
        this.delegate = compound(listener.defaultOptions(), listener.customOptions());
      }
    }

    public Options defaultOptions(final OptionsLoader<? super L> loader, final L listener) {
      return this.defaults;
    }

    public Options customOptions(final OptionsLoader<? super L> loader, final L listener) {
      return this.custom;
    }

    public Options effectiveOptions(final OptionsLoader<? super L> loader, final L listener) {
      return this.effective;
    }
  }

  /**
   * Invoked always after {@link OptionsLoader} loads any options.
   *
   * @see #reloadOptions()
   */
  protected void onOptionsReload() {}

  public interface TimeContext {
    public long currentTime();

    public TimeSource wallTimeSource();

    public Stopwatch createStopwatch();

    public Ticker elapsedTimeTicker();
  }

  private enum SystemTimeContext implements TimeContext {
    instance;

    public long currentTime() {
      return this.wallTimeSource().read();
    }

    public TimeSource wallTimeSource() {
      return TimeSource.systemTimeSource();
    }

    public Stopwatch createStopwatch() {
      return Stopwatch.createUnstarted(this.elapsedTimeTicker());
    }

    public Ticker elapsedTimeTicker() {
      return Ticker.systemTicker();
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  protected final long currentTime() {
    return this.timeContext.currentTime();
  }

  protected final TimeSource wallTimeSource() {
    return this.timeContext.wallTimeSource();
  }

  protected final Stopwatch createStopwatch() {
    return this.timeContext.createStopwatch();
  }

  protected final Ticker elapsedTimeTicker() {
    return this.timeContext.elapsedTimeTicker();
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

  public interface RegisterFailureHandler<L extends Listener> {
    public void preRegisterFailure(L listener, Runnable task, Exception failure);

    public void postRegisterFailure(L listener, Runnable task, Exception failure);

    public void preUnregisterFailure(L listener, Runnable task, Exception failure);

    public void postUnregisterFailure(L listener, Runnable task, Exception failure);
  }

  private enum PropagatingRegisterFailureHandler implements RegisterFailureHandler<Listener> {
    instance;

    public void preRegisterFailure(final Listener listener, final Runnable task, final Exception failure) {
      propagate(failure);
    }

    public void postRegisterFailure(final Listener listener, final Runnable task, final Exception failure) {
      propagate(failure);
    }

    public void preUnregisterFailure(final Listener listener, final Runnable task, final Exception failure) {
      propagate(failure);
    }

    public void postUnregisterFailure(final Listener listener, final Runnable task, final Exception failure) {
      propagate(failure);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected final void preRegisterFailure(final Runnable task, final Exception failure) {
    this.registerFailureHandler.preRegisterFailure(this.asSubtype(), task, failure);
  }

  @Override
  protected final void postRegisterFailure(final Runnable task, final Exception failure) {
    this.registerFailureHandler.postRegisterFailure(this.asSubtype(), task, failure);
  }

  @Override
  protected final void preUnregisterFailure(final Runnable task, final Exception failure) {
    this.registerFailureHandler.preUnregisterFailure(this.asSubtype(), task, failure);
  }

  @Override
  protected final void postUnregisterFailure(final Runnable task, final Exception failure) {
    this.registerFailureHandler.postUnregisterFailure(this.asSubtype(), task, failure);
  }

  public interface DataInjector<L extends Listener> {
    public void inject(L listener, String path, Event data);
  }

  private enum NoDataInjector implements DataInjector<Listener> {
    instance;

    public void inject(final Listener listener, final String path, final Event data) {}

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static final class ProbingDataInjector implements DataInjector<Listener> {
    private final Prober<? super Event, Probe<?>> prober;

    private ProbingDataInjector(final Prober<? super Event, Probe<?>> prober) {
      this.prober = checkNotNull(prober);
    }

    public static ProbingDataInjector of(final Prober<? super Event, Probe<?>> prober) {
      return new ProbingDataInjector(prober);
    }

    public void inject(final Listener listener, final String path, final Event data) {
      this.prober.inject(data);
    }

    @Override
    public String toString() {
      return toStringDelegate(this.getClass(), this.prober);
    }
  }

  @Override
  protected final void inject(final String path, final Event data) {
    this.runtimeStatistics.injectCount.incrementAndGet();

    final Stopwatch watch = this.createStopwatch().start();

    this.dataInjector.inject(this.asSubtype(), path, data);

    final long delta = watch.elapsed(RuntimeStatistics.timeUnit);

    this.runtimeStatistics.injectTime.addAndGet(delta);
  }

  protected static abstract class ContinuousEvent<L extends ActivityListener, E> extends AbstractListener.ContinuousEvent<E> {
    protected final L listener;

    protected ContinuousEvent(final L listener, final long pause, final long window, final TimeUnit unit) {
      super(listener.createStopwatch(), pause, window, unit);

      this.listener = checkNotNull(listener);
    }

    @Override
    public final void push(final E event) {
      this.listener.execute(new Runnable() {
        public void run() {
          synchronizedPush(event);
        }
      });
    }

    @Override
    public final void flush() {
      this.listener.execute(new Runnable() {
        public void run() {
          synchronizedFlush();
        }
      });
    }
  }

  public interface EventValidator<L extends Listener> {
    public void validate(L listener, String path, Event data);
  }

  private enum StandardEventValidator implements EventValidator<Listener> {
    instance;

    public void validate(final Listener listener, final String path, final Event data) {
      checkArgument(!isNullOrEmpty(path), "%s: path is null or empty", listener);
      checkArgument(data.getTimestamp() > 0L, "%s: timestamp is less than zero", listener);
      checkArgument(!isNullOrEmpty(data.getAction()), "%s: action is null or empty", listener);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected final void validate(final String path, final Event data) {
    this.runtimeStatistics.validateCount.incrementAndGet();

    final Stopwatch watch = this.createStopwatch().start();

    this.eventValidator.validate(this.asSubtype(), path, data);

    final long delta = watch.elapsed(RuntimeStatistics.timeUnit);

    this.runtimeStatistics.validateTime.addAndGet(delta);
  }

  public interface PersistenceStore<L extends Listener> extends AutoCloseable {
    public void persist(L listener, String path, Event data) throws Exception;
  }

  private enum VoidPersistenceStore implements PersistenceStore<Listener> {
    instance;

    public void persist(final Listener listener, final String path, final Event data) {}

    public void close() {}

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static final class StoreWrapper<L extends Listener> implements PersistenceStore<L> {
    private final Store<? super L, ? super Event> store;

    private StoreWrapper(final Store<? super L, ? super Event> store) {
      this.store = checkNotNull(store);
    }

    public static <L extends Listener> StoreWrapper<L> of(final Store<? super L, ? super Event> store) {
      return new StoreWrapper<>(store);
    }

    public void persist(final L listener, final String path, final Event data) throws Exception {
      this.store.save(listener, path, data);
    }

    public void close() throws Exception {
      this.store.close();
    }

    @Override
    public String toString() {
      return toStringDelegate(this.getClass(), this.store);
    }
  }

  @Override
  protected final void persist(final String path, final Event data) throws Exception {
    this.runtimeStatistics.persistCount.incrementAndGet();

    final Stopwatch watch = this.createStopwatch().start();

    this.persistenceStore.persist(this.asSubtype(), path, data);

    final long delta = watch.elapsed(RuntimeStatistics.timeUnit);

    this.runtimeStatistics.persistTime.addAndGet(delta);
  }

  public interface SendFailureHandler<L extends Listener> {
    public void handleSendFailure(L listener, String path, Event data, Exception failure);
  }

  private enum PropagatingSendFailureHandler implements SendFailureHandler<Listener> {
    instance;

    public void handleSendFailure(final Listener listener, final String path, final Event data, final Exception failure) {
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

    this.sendFailureHandler.handleSendFailure(this.asSubtype(), path, data, failure);
  }

  static String toStringDelegate(final Class<?> wrapper, final Object delegate) {
    return MoreObjects.toStringHelper(wrapper).add("delegate", delegate).toString();
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

  protected abstract class AbstractInstanceProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractInstanceProbe() {}

    public Content get() {
      return ObjectData.of(RegularListener.this);
    }
  }

  protected final class RegularInstanceProbe extends AbstractInstanceProbe {
    protected RegularInstanceProbe() {}
  }

  protected abstract class AbstractRegistrationProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractRegistrationProbe() {}

    private void put(final AnyStructuredData data, final RegistrationHook hook, final ListMultimap<RegistrationHook, Runnable> tasks) {
      data.put(key("hooks", hook.toString()), toObjectData(tasks.get(hook)));
    }

    public Content get() {
      ListMultimap<RegistrationHook, Runnable> tasks = ArrayListMultimap.create(RegularListener.this.registerHooks);

      AnyStructuredData data = new AnyStructuredData();

      this.put(data, RegistrationHook.PRE_REGISTER, tasks);
      this.put(data, RegistrationHook.POST_REGISTER, tasks);
      this.put(data, RegistrationHook.PRE_UNREGISTER, tasks);
      this.put(data, RegistrationHook.POST_UNREGISTER, tasks);

      return data;
    }
  }

  protected final class RegularRegistrationProbe extends AbstractRegistrationProbe {
    protected RegularRegistrationProbe() {}
  }

  protected abstract class AbstractConfigurationProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractConfigurationProbe() {}

    public Content get() {
      RegularListener<?> listener = RegularListener.this;

      AnyStructuredData data = new AnyStructuredData();

      data.put(key("optionsLoader"), ObjectData.of(listener.optionsLoader));
      data.put(key("timeContext"), ObjectData.of(listener.timeContext));
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

  protected final class RegularConfigurationProbe extends AbstractConfigurationProbe {
    protected RegularConfigurationProbe() {}
  }

  protected abstract class AbstractOptionsProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractOptionsProbe() {}

    public Content get() {
      RegularListener<?> listener = RegularListener.this;

      AnyStructuredData data = new AnyStructuredData();

      Map<String, Object> defaults = listener.defaultOptions().toMap();
      Map<String, Object> custom = listener.customOptions().toMap();

      data.put(key("custom"), custom);
      data.put(key("inherited"), inheritedRawOptions(custom, defaults));

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
      RuntimeStatistics statistics = RegularListener.this.runtimeStatistics;

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

      data.put(key("timeUnit"), TimeUnits.toString(RuntimeStatistics.timeUnit));

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

  public interface DisposalHook<L extends Listener> {
    public void onDispose(L listener) throws Exception;
  }

  public static abstract class AbstractDisposalHook<L extends RegularListener<L>> implements DisposalHook<L> {
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

      public abstract <L extends RegularListener<L>> DisposalHook<L> build();
    }

    public void onDispose(final L listener) throws Exception {
      this.tryToClosePersistenceStore(listener);
      this.tryToShutdownSharedExecutor(listener);
      this.tryToDisposeDisplayExecutor(listener);
      this.tryToCloseLoggerConsole(listener);
    }

    protected abstract void report(L listener, final Object subject, final Exception failure);

    protected final void tryToClosePersistenceStore(final L listener) {
      if (this.closePersistenceStore) {
        try {
          listener.persistenceStore.close();
        } catch (Exception failure) {
          this.report(listener, listener.persistenceStore, failure);
        }
      }
    }

    protected final void tryToShutdownSharedExecutor(final L listener) {
      if (this.shutdownSharedExecutor) {
        try {
          listener.sharedExecutor.shutdown();
        } catch (Exception failure) {
          this.report(listener, listener.sharedExecutor, failure);
        }
      }
    }

    protected final void tryToDisposeDisplayExecutor(final L listener) {
      if (this.disposeDisplayExecutor) {
        try {
          listener.displayExecutor.getDisplay().dispose();
        } catch (Exception failure) {
          this.report(listener, listener.displayExecutor.getDisplay(), failure);
        }
      }
    }

    protected final void tryToCloseLoggerConsole(final L listener) {
      if (this.closeLoggerConsole) {
        try {
          listener.pluginConsole.close();
        } catch (Exception failure) {
          this.report(listener, listener.pluginConsole, failure);
        }
      }
    }

    @Override
    public String toString() {
      return this.toStringHelper().toString();
    }

    protected ToStringHelper toStringHelper() {
      ToStringHelper helper = MoreObjects.toStringHelper(this);

      helper.add("closePersistenceStore", this.closePersistenceStore);
      helper.add("shutdownSharedExecutor", this.shutdownSharedExecutor);
      helper.add("disposeDisplayExecutor", this.disposeDisplayExecutor);
      helper.add("closeLoggerConsole", this.closeLoggerConsole);

      return helper;
    }
  }

  private enum IgnoringDisposalHook implements DisposalHook<Listener> {
    instance;

    public void onDispose(final Listener listener) {}

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static final class BasicDisposalHook<L extends RegularListener<L>> extends AbstractDisposalHook<L> {
    private final Logger logger;

    BasicDisposalHook(final Builder builder) {
      super(builder);

      this.logger = checkNotNull(builder.logger);
    }

    public static final class Builder extends AbstractBuilder<Builder> {
      Logger logger;

      protected Builder() {}

      @Override
      protected Builder asSubtype() {
        return this;
      }

      public Builder logger(final Logger logger) {
        this.logger = checkNotNull(logger);

        return this;
      }

      @Override
      public <L extends RegularListener<L>> BasicDisposalHook<L> build() {
        return new BasicDisposalHook<>(this);
      }
    }

    public static Builder builder() {
      return new Builder();
    }

    @Override
    protected void report(final L listener, final Object subject, final Exception failure) {
      this.logger.log(Level.INFO, listener + ": Unable to dispose " + subject, failure);
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>Current implementation does nothing.
   */
  @Override
  protected final void onWorkbenchShutdown() throws Exception {
    // unused
  }

  /**
   * {@inheritDoc}
   *
   * <p>Current implementation invokes configured {@link DisposalHook}.
   */
  @Override
  protected final void onFinalUnregistration() throws Exception {
    this.disposalHook.onDispose(this.asSubtype());
  }

  public final Options getOptions() {
    return this.effectiveOptions();
  }

  public final Options getOptions(final Scope scope) {
    if (scope == StandardScope.DEFAULT) {
      return this.defaultOptions();
    } else if (scope == StandardScope.CUSTOM) {
      return this.customOptions();
    } else if (scope == StandardScope.EFFECTIVE) {
      return this.effectiveOptions();
    }

    return this.getOptionsForNonStandardScope(scope);
  }

  protected Options getOptionsForNonStandardScope(final Scope scope) {
    throw new IllegalArgumentException(format("%s: unable to get options for %s scope", this, scope));
  }

  public final TimeContext getTimeContext() {
    return this.timeContext;
  }
}
