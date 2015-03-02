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
import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.base.Ticker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ListMultimap;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;

import sk.stuba.fiit.perconik.activity.data.ObjectData;
import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.activity.probes.Prober;
import sk.stuba.fiit.perconik.activity.probes.Probers;
import sk.stuba.fiit.perconik.core.preferences.ListenerPreferences;
import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.store.Store;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;
import sk.stuba.fiit.perconik.utilities.configuration.ForwardingOptions;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Options;
import sk.stuba.fiit.perconik.utilities.configuration.Scope;
import sk.stuba.fiit.perconik.utilities.configuration.ScopedConfigurable;
import sk.stuba.fiit.perconik.utilities.configuration.StandardScope;
import sk.stuba.fiit.perconik.utilities.time.TimeSource;

import static java.lang.String.format;
import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import static com.google.common.base.Functions.constant;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
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
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.compound;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.emptyOptions;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class RegularEventListener extends AbstractEventListener implements ScopedConfigurable {
  // TODO maybe make GenericEventListener out of this class

  static final String internalProbeKeyPrefix = "meta";

  /**
   * Underlying listener options holder.
   */
  protected final OptionsLoader optionsLoader;

  /**
   * Underlying listener options provider.
   */
  final OptionsProvider optionsProvider;

  /**
   * Underlying time helper.
   */
  protected final TimeHelper timeHelper;

  /**
   * Underlying plug-in console for logging.
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
   * Underlying listener statistics.
   */
  final RuntimeStatistics runtimeStatistics;

  /**
   * Underlying listener registration failure handler.
   */
  final RegisterFailureHandler registerFailureHandler;

  /**
   * Underlying listener disposal hook.
   */
  final DisposalHook disposalHook;

  /**
   * Constructor for use by subclasses.
   */
  protected <C> RegularEventListener(final Configuration<C> configuration) {
    this(configuration, ofInstance((C) null));
  }

  /**
   * Constructor for use by subclasses.
   */
  protected <C> RegularEventListener(final Configuration<C> configuration, final C context) {
    this(configuration, ofInstance(context));
  }

  private <C> RegularEventListener(final Configuration<C> configuration, final Supplier<? extends C> supplier) {
    final C context = this.resolveContext(configuration, supplier);

    // note that field initialization order is significant
    this.optionsLoader = this.resolveOptionsLoader(configuration, context);
    this.optionsProvider = this.setupOptionsProvider(configuration.defaultOptions(context).orNull());

    this.timeHelper = configuration.timeHelper(context).or(SystemTimeHelper.instance);
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

  private <C> C resolveContext(final Configuration<C> configuration, final Supplier<? extends C> supplier) {
    Class<? extends C> type = requireNonNull(configuration.contextType());
    C context = requireNonNull(supplier).get();

    try {
      return type.cast(context == null ? this : context);
    } catch (ClassCastException failure) {
      throw new IllegalStateException(failure);
    }
  }

  private <C> OptionsLoader resolveOptionsLoader(final Configuration<C> configuration, final C context) {
    Optional<OptionsLoader> loader = configuration.optionsLoader(context);
    Optional<ListenerPreferences> preferences = configuration.listenerPreferences(context);

    if (loader.isPresent()) {
      checkState(!preferences.isPresent(), "%s: custom options loader configured but listener preferences also present", this);

      return loader.get();
    }

    if (preferences.isPresent()) {
      return UpdatingOptionsLoader.of(preferences.get(), this);
    }

    return UpdatingOptionsLoader.of(ListenerPreferences.getShared(), this);
  }

  private <C> DataInjector resolveDataInjector(final Configuration<C> configuration, final C context) {
    Optional<DataInjector> injector = configuration.dataInjector(context);
    Optional<Map<String, Probe<?>>> mappings = configuration.probeMappings(context);

    if (injector.isPresent()) {
      checkState(!mappings.isPresent(), "%s: custom data injector configured but probe mappings also present", this);

      return injector.get();
    }

    if (mappings.isPresent()) {
      Map<String, Probe<?>> mix = newHashMap(mappings.get());

      for (Entry<String, InternalProbe<?>> entry: this.internalProbeMappings().entrySet()) {
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

  private OptionsProvider setupOptionsProvider(@Nullable final Options defaults) {
    final OptionsProvider provider = new OptionsProvider(this, defaults);

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

  public interface Configuration<C> {
    public Class<? extends C> contextType();

    public Optional<Options> defaultOptions(C context);

    public Optional<ListenerPreferences> listenerPreferences(C context);

    public Optional<OptionsLoader> optionsLoader(C context);

    public Optional<TimeHelper> timeHelper(C context);

    public Optional<PluginConsole> pluginConsole(C context);

    public Optional<DisplayExecutor> diplayExecutor(C context);

    public Optional<ExecutorService> sharedExecutor(C context);

    public Optional<Map<String, Probe<?>>> probeMappings(C context);

    public Optional<Predicate<Entry<String, Probe<?>>>> probeFilter(C context);

    public Optional<ExecutorService> probeExecutor(C context);

    public Optional<DataInjector> dataInjector(C context);

    public Optional<EventValidator> eventValidator(C context);

    public Optional<PersistenceStore> persistenceStore(C context);

    public Optional<SendFailureHandler> sendFailureHandler(C context);

    public Optional<RegisterFailureHandler> registerFailureHandler(C context);

    public Optional<DisposalHook> disposalHook(C context);

    public interface Builder<C> {
      public Configuration<C> build();
    }
  }

  public static abstract class AbstractConfiguration<C> implements Configuration<C> {
    private final Class<? extends C> contextType;

    private final Function<? super C, ? extends Options> defaultOptions;

    private final Function<? super C, ? extends ListenerPreferences> listenerPreferences;

    private final Function<? super C, ? extends OptionsLoader> optionsLoader;

    private final Function<? super C, ? extends TimeHelper> timeHelper;

    private final Function<? super C, ? extends PluginConsole> pluginConsole;

    private final Function<? super C, ? extends DisplayExecutor> diplayExecutor;

    private final Function<? super C, ? extends ExecutorService> sharedExecutor;

    private final Function<? super C, ? extends Map<String, Probe<?>>> probeMappings;

    private final Function<? super C, ? extends Predicate<Entry<String, Probe<?>>>> probeFilter;

    private final Function<? super C, ? extends ExecutorService> probeExecutor;

    private final Function<? super C, ? extends DataInjector> dataInjector;

    private final Function<? super C, ? extends EventValidator> eventValidator;

    private final Function<? super C, ? extends PersistenceStore> persistenceStore;

    private final Function<? super C, ? extends SendFailureHandler> sendFailureHandler;

    private final Function<? super C, ? extends RegisterFailureHandler> registerFailureHandler;

    private final Function<? super C, ? extends DisposalHook> disposalHook;

    /**
     * Constructor for use by subclasses.
     */
    protected AbstractConfiguration(final AbstractBuilder<?, C> builder) {
      this.contextType = requireNonNull(builder.contextType);
      this.defaultOptions = requireNonNull(builder.defaultOptions);
      this.listenerPreferences = requireNonNull(builder.listenerPreferences);
      this.optionsLoader = requireNonNull(builder.optionsLoader);
      this.timeHelper = requireNonNull(builder.timeHelper);
      this.pluginConsole = requireNonNull(builder.pluginConsole);
      this.diplayExecutor = requireNonNull(builder.diplayExecutor);
      this.sharedExecutor = requireNonNull(builder.sharedExecutor);
      this.probeMappings = requireNonNull(builder.probeMappings);
      this.probeFilter = requireNonNull(builder.probeFilter);
      this.probeExecutor = requireNonNull(builder.probeExecutor);
      this.dataInjector = requireNonNull(builder.dataInjector);
      this.eventValidator = requireNonNull(builder.eventValidator);
      this.persistenceStore = requireNonNull(builder.persistenceStore);
      this.sendFailureHandler = requireNonNull(builder.sendFailureHandler);
      this.registerFailureHandler = requireNonNull(builder.registerFailureHandler);
      this.disposalHook = requireNonNull(builder.disposalHook);
    }

    public static abstract class AbstractBuilder<B extends AbstractBuilder<B, C>, C> implements Builder<C> {
      Class<? extends C> contextType;

      Function<? super C, ? extends Options> defaultOptions = constant(null);

      Function<? super C, ? extends ListenerPreferences> listenerPreferences = constant(null);

      Function<? super C, ? extends OptionsLoader> optionsLoader = constant(null);

      Function<? super C, ? extends TimeHelper> timeHelper = constant(null);

      Function<? super C, ? extends PluginConsole> pluginConsole = constant(null);

      Function<? super C, ? extends DisplayExecutor> diplayExecutor = constant(null);

      Function<? super C, ? extends ExecutorService> sharedExecutor = constant(null);

      Function<? super C, ? extends Map<String, Probe<?>>> probeMappings = constant(null);

      Function<? super C, ? extends Predicate<Entry<String, Probe<?>>>> probeFilter = constant(null);

      Function<? super C, ? extends ExecutorService> probeExecutor = constant(null);

      Function<? super C, ? extends DataInjector> dataInjector = constant(null);

      Function<? super C, ? extends EventValidator> eventValidator = constant(null);

      Function<? super C, ? extends PersistenceStore> persistenceStore = constant(null);

      Function<? super C, ? extends SendFailureHandler> sendFailureHandler = constant(null);

      Function<? super C, ? extends RegisterFailureHandler> registerFailureHandler = constant(null);

      Function<? super C, ? extends DisposalHook> disposalHook = constant(null);

      /**
       * Constructor for use by subclasses.
       */
      protected AbstractBuilder() {}

      /**
       * Always returns {@code this}.
       */
      protected abstract B asSubtype();

      public final B contextType(final Class<? extends C> type) {
        this.contextType = requireNonNull(type);

        return this.asSubtype();
      }

      public final B defaultOptions(final Options options) {
        return this.defaultOptions(constant(requireNonNull(options)));
      }

      public final B defaultOptions(final Function<? super C, ? extends Options> relation) {
        this.defaultOptions = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B listenerPreferences(final ListenerPreferences preferences) {
        return this.listenerPreferences(constant(requireNonNull(preferences)));
      }

      public final B listenerPreferences(final Function<? super C, ? extends ListenerPreferences> relation) {
        this.listenerPreferences = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B optionsLoader(final OptionsLoader loader) {
        return this.optionsLoader(constant(requireNonNull(loader)));
      }

      public final B optionsLoader(final Function<? super C, ? extends OptionsLoader> relation) {
        this.optionsLoader = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B timeHelper(final TimeHelper helper) {
        return this.timeHelper(constant(requireNonNull(helper)));
      }

      public final B timeHelper(final Function<? super C, ? extends TimeHelper> relation) {
        this.timeHelper = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B pluginConsole(final PluginConsole console) {
        return this.pluginConsole(constant(requireNonNull(console)));
      }

      public final B pluginConsole(final Function<? super C, ? extends PluginConsole> relation) {
        this.pluginConsole = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B diplayExecutor(final DisplayExecutor executor) {
        return this.diplayExecutor(constant(requireNonNull(executor)));
      }

      public final B diplayExecutor(final Function<? super C, ? extends DisplayExecutor> relation) {
        this.diplayExecutor = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B sharedExecutor(final ExecutorService executor) {
        return this.sharedExecutor(constant(requireNonNull(executor)));
      }

      public final B sharedExecutor(final Function<? super C, ? extends ExecutorService> relation) {
        this.sharedExecutor = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B probeMappings(final Map<String, Probe<?>> probes) {
        return this.probeMappings(constant(requireNonNull(probes)));
      }

      public final B probeMappings(final Function<? super C, ? extends Map<String, Probe<?>>> relation) {
        this.probeMappings = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B probeFilter(final Predicate<Entry<String, Probe<?>>> filter) {
        return this.probeFilter(constant(requireNonNull(filter)));
      }

      public final B probeFilter(final Function<? super C, ? extends Predicate<Entry<String, Probe<?>>>> relation) {
        this.probeFilter = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B probeExecutor(final ExecutorService executor) {
        return this.probeExecutor(constant(requireNonNull(executor)));
      }

      public final B probeExecutor(final Function<? super C, ? extends ExecutorService> relation) {
        this.probeExecutor = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B dataInjector(final DataInjector injector) {
        return this.dataInjector(constant(requireNonNull(injector)));
      }

      public final B dataInjector(final Function<? super C, ? extends DataInjector> relation) {
        this.dataInjector = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B eventValidator(final EventValidator validator) {
        return this.eventValidator(constant(requireNonNull(validator)));
      }

      public final B eventValidator(final Function<? super C, ? extends EventValidator> relation) {
        this.eventValidator = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B persistenceStore(final PersistenceStore store) {
        return this.persistenceStore(constant(requireNonNull(store)));
      }

      public final B persistenceStore(final Function<? super C, ? extends PersistenceStore> relation) {
        this.persistenceStore = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B sendFailureHandler(final SendFailureHandler handler) {
        return this.sendFailureHandler(constant(requireNonNull(handler)));
      }

      public final B sendFailureHandler(final Function<? super C, ? extends SendFailureHandler> relation) {
        this.sendFailureHandler = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B registerFailureHandler(final RegisterFailureHandler handler) {
        return this.registerFailureHandler(constant(requireNonNull(handler)));
      }

      public final B registerFailureHandler(final Function<? super C, ? extends RegisterFailureHandler> relation) {
        this.registerFailureHandler = requireNonNull(relation);

        return this.asSubtype();
      }

      public final B disposalHook(final DisposalHook hook) {
        return this.disposalHook(constant(requireNonNull(hook)));
      }

      public final B disposalHook(final Function<? super C, ? extends DisposalHook> relation) {
        this.disposalHook = requireNonNull(relation);

        return this.asSubtype();
      }

      public abstract Configuration<C> build();
    }

    public final Class<? extends C> contextType() {
      return this.contextType;
    }

    public final Optional<Options> defaultOptions(final C context) {
      return fromNullable((Options) this.defaultOptions.apply(context));
    }

    public final Optional<ListenerPreferences> listenerPreferences(final C context) {
      return fromNullable((ListenerPreferences) this.listenerPreferences.apply(context));
    }

    public final Optional<OptionsLoader> optionsLoader(final C context) {
      return fromNullable((OptionsLoader) this.optionsLoader.apply(context));
    }

    public final Optional<TimeHelper> timeHelper(final C context) {
      return fromNullable((TimeHelper) this.timeHelper.apply(context));
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

    public final Optional<DataInjector> dataInjector(final C context) {
      return fromNullable((DataInjector) this.dataInjector.apply(context));
    }

    public final Optional<EventValidator> eventValidator(final C context) {
      return fromNullable((EventValidator) this.eventValidator.apply(context));
    }

    public final Optional<PersistenceStore> persistenceStore(final C context) {
      return fromNullable((PersistenceStore) this.persistenceStore.apply(context));
    }

    public final Optional<SendFailureHandler> sendFailureHandler(final C context) {
      return fromNullable((SendFailureHandler) this.sendFailureHandler.apply(context));
    }

    public final Optional<RegisterFailureHandler> registerFailureHandler(final C context) {
      return fromNullable((RegisterFailureHandler) this.registerFailureHandler.apply(context));
    }

    public final Optional<DisposalHook> disposalHook(final C context) {
      return fromNullable((DisposalHook) this.disposalHook.apply(context));
    }
  }

  public static final class RegularConfiguration<C> extends AbstractConfiguration<C> {
    RegularConfiguration(final Builder<C> builder) {
      super(builder);
    }

    public static final class Builder<C> extends AbstractBuilder<Builder<C>, C> {
      public Builder() {
      }

      @Override
      protected Builder<C> asSubtype() {
        return this;
      }

      @Override
      public RegularConfiguration<C> build() {
        return new RegularConfiguration<>(this);
      }
    }

    public static <C> Builder<C> builder() {
      return new Builder<>();
    }
  }

  /**
   * Returns an immutable snapshot of default options.
   * Snapshot is taken from configuration on listener creation.
   *
   * <p><b>Note:</b> always returns the same instance.
   */
  protected final Options defaultOptions() {
    return this.optionsProvider.defaultOptions();
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
    return this.optionsProvider.customOptions();
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
    return this.optionsProvider.effectiveOptions();
  }

  /**
   * An {@code OptionsLoader} invokes this method to reload effective options.
   * Always invokes {@link #onOptionsReload()} after reloading options.
   *
   * @see #onOptionsReload()
   */
  protected final void reloadOptions() {
    this.optionsProvider.load();
    this.onOptionsReload();
  }

  public interface OptionsLoader {
    public Options loadCustomOptions(RegularEventListener listener);
  }

  public static final class UpdatingOptionsLoader implements OptionsLoader {
    private final ListenerPreferences preferences;

    final OptionsReloader reloader;

    private UpdatingOptionsLoader(final ListenerPreferences preferences, final RegularEventListener listener) {
      this.preferences = requireNonNull(preferences);
      this.reloader = new OptionsReloader(listener);

      hook(this.preferences, listener, this.reloader);
    }

    private static void hook(final ListenerPreferences preferences, final RegularEventListener listener, final OptionsReloader reloader) {
      final IEclipsePreferences node = preferences.node();

      RegistrationHook.POST_REGISTER.add(listener, new NamedRunnable(OptionsReloader.class) {
        public void run() {
          node.addPreferenceChangeListener(reloader);
        }
      });

      RegistrationHook.PRE_UNREGISTER.add(listener, new NamedRunnable(OptionsReloader.class) {
        public void run() {
          node.removePreferenceChangeListener(reloader);
        }
      });
    }

    public static UpdatingOptionsLoader of(final ListenerPreferences preferences, final RegularEventListener listener) {
      return new UpdatingOptionsLoader(preferences, listener);
    }

    private static final class OptionsReloader implements IPreferenceChangeListener {
      private final RegularEventListener listener;

      OptionsReloader(final RegularEventListener listener) {
        this.listener = requireNonNull(listener);
      }

      public void preferenceChange(final PreferenceChangeEvent event) {
        if (ListenerPreferences.Keys.configuration.equals(event.getKey())) {
          this.listener.reloadOptions();
        }
      }
    }

    public Options loadCustomOptions(final RegularEventListener listener) {
      return this.preferences.getListenerConfigurationData().get(listener.getClass());
    }

    @Override
    public String toString() {
      return this.toStringHelper().toString();
    }

    protected ToStringHelper toStringHelper() {
      ToStringHelper helper = Objects.toStringHelper(this);

      helper.add("preferences", this.preferences);

      return helper;
    }
  }

  private static final class OptionsProvider {
    private final DefaultOptions defaults;

    private final CustomOptions custom;

    private final EffectiveOptions effective;

    OptionsProvider(final RegularEventListener listener, final Options defaults) {
      this.defaults = new DefaultOptions(defaults);
      this.custom = new CustomOptions(listener);
      this.effective = new EffectiveOptions(listener);
    }

    void load() {
      this.custom.load();
      this.effective.load();
    }

    private static abstract class AbstractOptions extends ForwardingOptions {
      AbstractOptions() {}

      static Options secure(@Nullable final Options untrusted) {
        return untrusted != null ? MapOptions.from(ImmutableMap.copyOf(untrusted.toMap())) : emptyOptions();
      }

      @Override
      public final String toString() {
        return this.getClass().getSimpleName() + this.toMap().toString();
      }
    }

    private static final class DefaultOptions extends AbstractOptions {
      private final Options delegate;

      DefaultOptions(final Options untrusted) {
        this.delegate = secure(untrusted);
      }

      @Override
      protected Options delegate() {
        return this.delegate;
      }
    }

    private static final class CustomOptions extends AbstractOptions {
      private final RegularEventListener listener;

      @Nullable
      private Options delegate;

      CustomOptions(final RegularEventListener listener) {
        this.listener = requireNonNull(listener);
      }

      void load() {
        this.delegate = secure(this.listener.optionsLoader.loadCustomOptions(this.listener));
      }

      @Override
      protected Options delegate() {
        return checkNotNullAsState(this.delegate, "%s: Custom options requested but not loaded by %s yet", this.listener, this.listener.optionsLoader);
      }
    }

    private static final class EffectiveOptions extends AbstractOptions {
      private final RegularEventListener listener;

      @Nullable
      private Options delegate;

      EffectiveOptions(final RegularEventListener listener) {
        this.listener = requireNonNull(listener);
      }

      final void load() {
        this.delegate = compound(this.listener.effectiveOptions(), this.listener.defaultOptions());
      }

      @Override
      protected Options delegate() {
        return this.delegate;
      }
    }

    Options defaultOptions() {
      return this.defaults;
    }

    Options customOptions() {
      return this.custom;
    }

    Options effectiveOptions() {
      return this.effective;
    }
  }

  /**
   * Invoked always after {@link OptionsLoader} loads custom options.
   *
   * @see #reloadOptions()
   */
  protected void onOptionsReload() {}

  public interface TimeHelper {
    public long currentTime();

    public TimeSource wallTimeSource();

    public Stopwatch createStopwatch();

    public Ticker elapsedTimeTicker();
  }

  private enum SystemTimeHelper implements TimeHelper {
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
    return this.timeHelper.currentTime();
  }

  protected final TimeSource wallTimeSource() {
    return this.timeHelper.wallTimeSource();
  }

  protected final Stopwatch createStopwatch() {
    return this.timeHelper.createStopwatch();
  }

  protected final Ticker elapsedTimeTicker() {
    return this.timeHelper.elapsedTimeTicker();
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

  // TODO parametrize?
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

    public static ProbingDataInjector of(final Prober<? super Event, Probe<?>> prober) {
      return new ProbingDataInjector(prober);
    }

    public void inject(final String path, final Event data) {
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

    this.dataInjector.inject(path, data);

    final long delta = watch.elapsed(RuntimeStatistics.timeUnit);

    this.runtimeStatistics.injectTime.addAndGet(delta);
  }

  protected static abstract class ContinuousEventWindow<L extends CommonEventListener, E> extends AbstractEventListener.ContinuousEventWindow<E> {
    protected final L listener;

    protected ContinuousEventWindow(final L listener, final long window, final TimeUnit unit) {
      super(listener.createStopwatch(), window, unit);

      this.listener = requireNonNull(listener);
    }

    public final void push(final E event) {
      this.listener.execute(new Runnable() {
        public void run() {
          synchronizedPush(event);
        }
      });
    }

    public final void flush() {
      this.listener.execute(new Runnable() {
        public void run() {
          synchronizedFlush();
        }
      });
    }
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

    final Stopwatch watch = this.createStopwatch().start();

    this.eventValidator.validate(path, data);

    final long delta = watch.elapsed(RuntimeStatistics.timeUnit);

    this.runtimeStatistics.validateTime.addAndGet(delta);
  }

  //TODO parametrize with L?
  public interface PersistenceStore extends AutoCloseable {
    public void persist(String path, Event data) throws Exception;
  }

  private enum VoidPersistenceStore implements PersistenceStore {
    instance;

    public void persist(final String path, final Event data) {}

    public void close() {}
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
      return toStringDelegate(this.getClass(), this.store);
    }
  }

  @Override
  protected final void persist(final String path, final Event data) throws Exception {
    this.runtimeStatistics.persistCount.incrementAndGet();

    final Stopwatch watch = this.createStopwatch().start();

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

  static String toStringDelegate(final Class<?> wrapper, final Object delegate) {
    return new StringBuilder(wrapper.getClass().getSimpleName()).append("(").append(delegate).append(")").toString();
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
      return ObjectData.of(RegularEventListener.this);
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
      ListMultimap<RegistrationHook, Runnable> tasks = ArrayListMultimap.create(RegularEventListener.this.registerHooks);

      AnyStructuredData data = new AnyStructuredData();

      put(data, RegistrationHook.PRE_REGISTER, tasks);
      put(data, RegistrationHook.POST_REGISTER, tasks);
      put(data, RegistrationHook.PRE_UNREGISTER, tasks);
      put(data, RegistrationHook.POST_UNREGISTER, tasks);

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
      RegularEventListener listener = RegularEventListener.this;

      AnyStructuredData data = new AnyStructuredData();

      data.put(key("optionsLoader"), ObjectData.of(listener.optionsLoader));
      data.put(key("timeHelper"), ObjectData.of(listener.timeHelper));
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

  // TODO parametrize?
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

    @Override
    public String toString() {
      return this.toStringHelper().toString();
    }

    protected ToStringHelper toStringHelper() {
      ToStringHelper helper = Objects.toStringHelper(this);

      helper.add("closePersistenceStore", this.closePersistenceStore);
      helper.add("shutdownSharedExecutor", this.shutdownSharedExecutor);
      helper.add("disposeDisplayExecutor", this.disposeDisplayExecutor);
      helper.add("closeLoggerConsole", this.closeLoggerConsole);

      return helper;
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
    this.disposalHook.onDispose(this);
  }

  public final Options getOptions() {
    return this.effectiveOptions();
  }

  public final Options getOptions(final Scope scope) {
    if (scope == StandardScope.DEFAULT) {
      return this.defaultOptions();
    } else if (scope == StandardScope.EFFECTIVE) {
      return this.effectiveOptions();
    }

    return this.getOptionsForCustomScope(scope);
  }

  protected Options getOptionsForCustomScope(final Scope scope) {
    throw new IllegalArgumentException(format("%s: unable to get options for %s scope", this, scope));
  }

  public final TimeHelper getTimeHelper() {
    return this.timeHelper;
  }
}
