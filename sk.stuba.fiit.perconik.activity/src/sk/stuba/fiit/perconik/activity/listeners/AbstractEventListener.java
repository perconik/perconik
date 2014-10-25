package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Supplier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;

import sk.stuba.fiit.perconik.activity.data.ObjectData;
import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.activity.probes.Prober;
import sk.stuba.fiit.perconik.activity.probes.Probers;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.store.Store;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Suppliers.ofInstance;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractEventListener extends Adapter {
  protected final PluginConsole console;

  protected final DisplayExecutor displayExecutor;

  protected final ExecutorService sharedExecutor;

  protected final ExecutorService probeExecutor;

  protected final Prober<Event, Probe<?>> prober;

  protected final Store<? super Event> store;

  private final SendFailureHandler failureHandler;

  private final DisposalHook disposalHook;

  /**
   * Constructor for use by subclasses.
   */
  protected AbstractEventListener(final Configuration configuration) {
    this.console = requireNonNull(configuration.loggerConsole());
    this.displayExecutor = requireNonNull(configuration.diplayExecutor());
    this.sharedExecutor = requireNonNull(configuration.sharedExecutor());
    this.probeExecutor = requireNonNull(configuration.probeExecutor());
    this.store = requireNonNull(configuration.persistenceStore());

    this.prober = this.prober(configuration);

    this.failureHandler = firstNonNull(configuration.sendFailureHandler(), PropagatingSendFailureHandler.instance);
    this.disposalHook = firstNonNull(configuration.disposalHook(), IgnoringDisposalHook.instance);

    Disposer.activateHook(this, this.disposalHook);
  }

  private Prober<Event, Probe<?>> prober(final Configuration configuration) {
    Map<String, Probe<?>> probes = newHashMap(configuration.probesMapping());

    for (Entry<String, InternalProbe<?>> entry: this.internalProbesMapping().entrySet()) {
      probes.put(key("meta", entry.getKey()), InternalProbe.class.cast(entry.getValue()));
    }

    return Probers.create(probes, this.probeExecutor);
  }

  public interface Configuration {
    public PluginConsole loggerConsole();

    public DisplayExecutor diplayExecutor();

    public ExecutorService sharedExecutor();

    public ExecutorService probeExecutor();

    public Map<String, Probe<?>> probesMapping();

    public Store<? super Event> persistenceStore();

    public SendFailureHandler sendFailureHandler();

    public DisposalHook disposalHook();

    public static interface Builder {
      public Configuration build();
    }
  }

  public static abstract class AbstractConfiguration implements Configuration {
    private final Supplier<PluginConsole> loggerConsole;

    private final Supplier<DisplayExecutor> diplayExecutor;

    private final Supplier<ExecutorService> sharedExecutor;

    private final Supplier<ExecutorService> probeExecutor;

    private final Supplier<Map<String, Probe<?>>> probesMapping;

    private final Supplier<Store<? super Event>> persistenceStore;

    private final SendFailureHandler sendFailureHandler;

    private final DisposalHook disposalHook;

    /**
     * Constructor for use by subclasses.
     */
    protected AbstractConfiguration(final AbstractBuilder<?> builder) {
      this.loggerConsole = requireNonNull(builder.loggerConsole);
      this.diplayExecutor = requireNonNull(builder.diplayExecutor);
      this.sharedExecutor = requireNonNull(builder.sharedExecutor);
      this.probeExecutor = requireNonNull(builder.probeExecutor);
      this.probesMapping = requireNonNull(builder.probesMapping);
      this.persistenceStore = requireNonNull(builder.persistenceStore);
      this.sendFailureHandler = requireNonNull(builder.sendFailureHandler);
      this.disposalHook = requireNonNull(builder.disposalHook);
    }

    public static abstract class AbstractBuilder<B extends AbstractBuilder<B>> implements Builder {
      Supplier<PluginConsole> loggerConsole;

      Supplier<DisplayExecutor> diplayExecutor;

      Supplier<ExecutorService> sharedExecutor;

      Supplier<ExecutorService> probeExecutor;

      Supplier<Map<String, Probe<?>>> probesMapping;

      Supplier<Store<? super Event>> persistenceStore;

      SendFailureHandler sendFailureHandler;

      DisposalHook disposalHook;

      /**
       * Constructor for use by subclasses.
       */
      protected AbstractBuilder() {}

      /**
       * Always returns {@code this}.
       */
      protected abstract B asSubtype();

      public final B loggerConsole(final PluginConsole console) {
        return this.loggerConsole(ofInstance(requireNonNull(console)));
      }

      public final B loggerConsole(final Supplier<PluginConsole> supplier) {
        this.loggerConsole = requireNonNull(supplier);

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

      public final B probeExecutor(final ExecutorService executor) {
        return this.probeExecutor(ofInstance(requireNonNull(executor)));
      }

      public final B probeExecutor(final Supplier<ExecutorService> supplier) {
        this.probeExecutor = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B probesMapping(final Map<String, Probe<?>> probes) {
        return this.probesMapping(ofInstance(requireNonNull(probes)));
      }

      public final B probesMapping(final Supplier<Map<String, Probe<?>>> supplier) {
        this.probesMapping = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B persistenceStore(final Store<? super Event> store) {
        // TODO resolve
        @SuppressWarnings("unchecked")
        Store<? super Event> supplier = (Store<? super Event>) ofInstance(requireNonNull(store));

        return this.persistenceStore(supplier);
      }

      public final B persistenceStore(final Supplier<Store<? super Event>> supplier) {
        this.persistenceStore = requireNonNull(supplier);

        return this.asSubtype();
      }

      public final B sendFailureHandler(final SendFailureHandler handler) {
        this.sendFailureHandler = requireNonNull(handler);

        return this.asSubtype();
      }

      public final B disposalHook(final DisposalHook hook) {
        this.disposalHook = requireNonNull(hook);

        return this.asSubtype();
      }

      public abstract Configuration build();
    }

    public final PluginConsole loggerConsole() {
      return this.loggerConsole.get();
    }

    public final DisplayExecutor diplayExecutor() {
      return this.diplayExecutor.get();
    }

    public final ExecutorService sharedExecutor() {
      return this.sharedExecutor.get();
    }

    public final ExecutorService probeExecutor() {
      return this.probeExecutor.get();
    }

    public final Map<String, Probe<?>> probesMapping() {
      return this.probesMapping.get();
    }

    public final Store<? super Event> persistenceStore() {
      return this.persistenceStore.get();
    }

    public final SendFailureHandler sendFailureHandler() {
      return this.sendFailureHandler;
    }

    public final DisposalHook disposalHook() {
      return this.disposalHook;
    }
  }

  protected final void execute(final Runnable command) {
    this.sharedExecutor.execute(command);
  }

  protected final <V> V execute(final DisplayTask<V> task) {
    return task.get(this.displayExecutor);
  }

  @Override
  public final void preRegister() {}

  @Override
  public final void postUnregister() {}

  protected abstract class InternalProbe<T> implements Probe<T> {
    /**
     * Constructor for use by subclasses.
     */
    protected InternalProbe() {}
  }

  protected abstract class AbstractConfigurationProbe extends InternalProbe<Content> {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractConfigurationProbe() {}

    public Content get() {
      AbstractEventListener listener = AbstractEventListener.this;

      AnyStructuredData data = new AnyStructuredData();

      data.put(key("instance"), ObjectData.of(listener));
      data.put(key("console"), ObjectData.of(listener.console));
      data.put(key("executor", "display"), ObjectData.of(listener.displayExecutor));
      data.put(key("executor", "shared"), ObjectData.of(listener.sharedExecutor));
      data.put(key("executor", "probe"), ObjectData.of(listener.probeExecutor));
      data.put(key("prober"), ObjectData.of(listener.prober));
      data.put(key("store"), ObjectData.of(listener.store));

      return data;
    }
  }

  // TODO resolve
  @SuppressWarnings("static-method")
  protected Map<String, InternalProbe<?>> internalProbesMapping() {
    return emptyMap();
  }

  public static interface SendFailureHandler {
    public void handleSendFailure(final String path, final Event data, final Exception failure);
  }

  private enum PropagatingSendFailureHandler implements SendFailureHandler {
    instance;

    public void handleSendFailure(final String path, final Event data, final Exception failure) {
      propagate(failure);
    }
  }

  protected final void send(final String path, final Event data) {
    try {
      this.prober.inject(data);

      checkArgument(!isNullOrEmpty(path));
      checkArgument(data.getTimestamp() > 0L);
      checkArgument(!isNullOrEmpty(data.getAction()));

      this.store.save(path, data);
    } catch (Exception failure) {
      this.failureHandler.handleSendFailure(path, data, failure);
    }
  }

  public interface DisposalHook {
    public void onDispose(Listener listener) throws Exception;
  }

  public static abstract class AbstractDisposalHook implements DisposalHook {
    protected final boolean closePersistenceStore;

    protected final boolean shutdownProbeExecutor;

    protected final boolean shutdownSharedExecutor;

    protected final boolean disposeDisplayExecutor;

    protected final boolean closeLoggerConsole;

    /**
     * Constructor for use by subclasses.
     */
    protected AbstractDisposalHook(final AbstractBuilder<?> builder) {
      this.closePersistenceStore = builder.closePersistenceStore;
      this.shutdownProbeExecutor = builder.shutdownProbeExecutor;
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
      if (listener instanceof AbstractEventListener) {
        AbstractEventListener implementation = (AbstractEventListener) listener;

        this.tryToClosePersistenceStore(implementation);
        this.tryToShutdownProbeExecutor(implementation);
        this.tryToShutdownSharedExecutor(implementation);
        this.tryToDisposeDisplayExecutor(implementation);
        this.tryToCloseLoggerConsole(implementation);
      }
    }

    protected abstract void report(final Object reference, final Exception failure);

    protected final void tryToClosePersistenceStore(final AbstractEventListener listener) {
      if (this.closePersistenceStore) {
        try {
          listener.store.close();
        } catch (Exception failure) {
          this.report(listener.store, failure);
        }
      }
    }

    protected final void tryToShutdownProbeExecutor(final AbstractEventListener listener) {
      if (this.shutdownProbeExecutor) {
        try {
          listener.probeExecutor.shutdown();
        } catch (Exception failure) {
          this.report(listener.probeExecutor, failure);
        }
      }
    }

    protected final void tryToShutdownSharedExecutor(final AbstractEventListener listener) {
      if (this.shutdownSharedExecutor) {
        try {
          listener.sharedExecutor.shutdown();
        } catch (Exception failure) {
          this.report(listener.sharedExecutor, failure);
        }
      }
    }

    protected final void tryToDisposeDisplayExecutor(final AbstractEventListener listener) {
      if (this.disposeDisplayExecutor) {
        try {
          listener.displayExecutor.getDisplay().dispose();
        } catch (Exception failure) {
          this.report(listener.displayExecutor.getDisplay(), failure);
        }
      }
    }

    protected final void tryToCloseLoggerConsole(final AbstractEventListener listener) {
      if (this.closeLoggerConsole) {
        try {
          listener.console.close();
        } catch (Exception failure) {
          this.report(listener.console, failure);
        }
      }
    }
  }

  private enum IgnoringDisposalHook implements DisposalHook {
    instance;

    public void onDispose(final Listener listener) {}
  }

  private static final class Disposer implements IWorkbenchListener {
    private static final Logger logger = Logger.getLogger(Disposer.class.getName());

    private final Listener listener;

    private final DisposalHook hook;

    private Disposer(final Listener listener, final DisposalHook hook) {
      this.listener = requireNonNull(listener);
      this.hook = requireNonNull(hook);
    }

    static void activateHook(final Listener listener, final DisposalHook hook) {
      final Disposer disposer = new Disposer(listener, hook);

      final Runnable activation = new Runnable() {
        public void run() {
          Workbenches.waitForWorkbench().addWorkbenchListener(disposer);
        }
      };

      Display.getDefault().asyncExec(activation);
    }

    public boolean preShutdown(final IWorkbench workbench, final boolean forced) {
      return true;
    }

    public void postShutdown(final IWorkbench workbench) {
      try {
        this.hook.onDispose(this.listener);
      } catch (Exception e) {
        logger.log(Level.INFO, "Internal failure on " + this.listener + " disposal", e);
      }
    }
  }

  /* TODO
  private final Statistics statistics = new Statistics();

  private static final class Statistics {
    long totalExecutedRunnableCommands;

    long totalExecutedDisplayTasks;

    long totalRegistrations;

    long totalUnregistrations;

    long totalSentEvents;

    long probingDuration;

    long sendingDuration;

    //long
  }
   */
}
