package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Set;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.ResourceNotRegistredException;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.AbstractManager;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;

import static com.google.common.cache.CacheBuilder.newBuilder;

/**
 * An abstract implementation of {@link ListenerManager}. This class
 * implements the listener registration mechanism based on the underlying
 * {@link ResourceManager}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractListenerManager extends AbstractManager implements ListenerManager {
  // TODO add javadocs

  static final int resolvedTypesCacheMaximumSize = 128;

  private final LoadingCache<Class<? extends Listener>, Set<Class<? extends Listener>>> resolvedTypesCache;

  /**
   * Constructor for use by subclasses.
   */
  protected AbstractListenerManager() {
    this.resolvedTypesCache = newBuilder().maximumSize(resolvedTypesCacheMaximumSize).build(new CacheLoader<Class<? extends Listener>, Set<Class<? extends Listener>>>() {
      @Override
      public Set<Class<? extends Listener>> load(final Class<? extends Listener> supertype) throws Exception {
        return Listeners.resolveTypes(supertype);
      }
    });
  }

  protected abstract ResourceManager underlyingResourceManager();

  private <L extends Listener> Set<Resource<? super L>> registrables(final L listener) {
    // safe cast as registrables always fetch resources with listener supertypes
    @SuppressWarnings("unchecked")
    Class<L> supertype = (Class<L>) listener.getClass();

    ResourceManager manager = this.underlyingResourceManager();

    Set<Resource<? super L>> resources = manager.registrables(supertype);

    for (Class<? extends Listener> type: this.resolvedTypesCache.getUnchecked(supertype)) {
      if (manager.registrables(type).isEmpty()) {
        throw new ResourceNotRegistredException("No registred resources for listener implementation " + listener.getClass().getName() + " as " + type.getName());
      }
    }

    return resources;
  }

  public final <L extends Listener> void register(final L listener) {
    DefaultResources.registerTo(this.registrables(listener), listener);
  }

  public final <L extends Listener> void unregister(final L listener) {
    DefaultResources.unregisterFrom(this.registrables(listener), listener);
  }
}
