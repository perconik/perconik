package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;

import com.gratex.perconik.uaca.UacaConsole;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.RegularEventListener.RegularConfiguration.Builder;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.activity.uaca.UacaProxy;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.activity.listeners.RegularEventListener.RegularConfiguration.builder;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class SharingEventListener extends RegularEventListener {
  private static final Builder sharedBuilder;

  static {
    sharedBuilder = builder();

    sharedBuilder.diplayExecutor(DisplayExecutor.defaultSynchronous());
    sharedBuilder.sharedExecutor(PlatformExecutors.newLimitedThreadPool());

    sharedBuilder.pluginConsole(UacaConsole.getInstance());
    sharedBuilder.persistenceStore(UacaProxySupplier.instance);
    sharedBuilder.failureHandler(UacaProxySaveFailureHandler.instance);
    sharedBuilder.disposalHook(UacaProxyDisposalHook.instance);

    Map<String, Probe<?>> probes = newHashMap();

    // TODO enable
    //probes.put(key("monitor", "core"), new StandardCoreProbe());
    //probes.put(key("monitor", "platform"), new StandardPlatformProbe());
    //probes.put(key("monitor", "management"), new StandardManagementProbe());// TODO ?
    //probes.put(key("monitor", "system"), new StandardSystemProbe());

    sharedBuilder.probeMappings(probes);
    sharedBuilder.probeExecutor(PlatformExecutors.newLimitedThreadPool(0.5f));
  }

  /**
   * Constructor for use by subclasses.
   */
  protected SharingEventListener() {
    super(newConfiguration());
  }

  static RegularConfiguration newConfiguration() {
    return sharedBuilder.build();
  }

  private enum UacaProxySupplier implements Supplier<PersistenceStore> {
    instance;

    public PersistenceStore get() {
      try {
        return StoreWrapper.of(new UacaProxy());
      } catch (Exception failure) {
        UacaConsole.getInstance().error(failure, "Unable to open UACA proxy");

        throw propagate(failure);
      }
    }
  }

  private enum UacaProxySaveFailureHandler implements SendFailureHandler {
    instance;

    public void handleSendFailure(final String path, final Event data, final Exception failure) {
      UacaConsole.getInstance().error(failure, "Unable to save data at " + path + " using UACA proxy");
    }
  }

  private enum UacaProxyDisposalHook implements DisposalHook {
    instance;

    public void onDispose(final Listener listener) {
      try {
        RegularEventListener listenerWithPersistenceStore = (RegularEventListener) listener;

        listenerWithPersistenceStore.persistenceStore.close();
      } catch (Exception failure) {
        UacaConsole.getInstance().error(failure, "Unable to close UACA proxy");
      }
    }
  }

  @Override
  protected final Map<String, InternalProbe<?>> internalProbeMappings() {
    ImmutableMap.Builder<String, InternalProbe<?>> builder = ImmutableMap.builder();

    // TODO enable
    //builder.put(key("monitor", "listener"), new RegularConfigurationProbe());
    //builder.put(key("monitor", "listener", "statistics"), new RegularStatisticsProbe());

    return builder.build();
  }
}
