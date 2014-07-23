package sk.stuba.fiit.perconik.core.services.resources;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.ResourceRegistrationException;
import sk.stuba.fiit.perconik.core.ResourceUnregistrationException;
import sk.stuba.fiit.perconik.core.services.AbstractManager;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An abstract implementation of {@link ResourceManager}. This class
 * implements the resource registration mechanism based on an underlying
 * {@code SetMultimap} of listener types to registered resources.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractResourceManager extends AbstractManager implements ResourceManager {
  // TODO add javadocs

  /**
   * Constructor for use by subclasses.
   */
  protected AbstractResourceManager() {}

  protected abstract SetMultimap<Class<? extends Listener>, Resource<?>> multimap();

  protected static final <L extends Listener> void check(final Class<L> type, final Resource<? super L> resource) {
    if (!Listener.class.isAssignableFrom(type)) {
      throw new IllegalArgumentException(type.getName() + " is not assignable to " + Listener.class.getName());
    }

    checkNotNull(resource);
  }

  public final <L extends Listener> void register(final Class<L> type, final Resource<? super L> resource) {
    check(type, resource);

    try {
      resource.preRegister();

      this.multimap().put(type, resource);

      resource.postRegister();
    } catch (Exception failure) {
      throw MoreThrowables.initializeCause(new ResourceRegistrationException(), failure);
    }
  }

  public final <L extends Listener> void unregister(final Class<L> type, final Resource<? super L> resource) {
    check(type, resource);

    try {
      resource.preUnregister();

      // TODO consider
      //			for (L listener: resource.registered(type))
      //			{
      //				resource.unregister(listener);
      //			}

      this.multimap().remove(type, resource);

      resource.postUnregister();
    } catch (Exception failure) {
      throw MoreThrowables.initializeCause(new ResourceUnregistrationException(), failure);
    }
  }
}
