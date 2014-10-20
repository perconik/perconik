package sk.stuba.fiit.perconik.core.persistence.data;

import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Standard resource registration with lively updated registration status.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class LiveResourceRegistration extends AbstractResourceRegistration {
  private final Class<? extends Listener> type;

  private final String name;

  private final Resource<?> resource;

  private LiveResourceRegistration(final Class<? extends Listener> type, final String name, final Resource<?> resource) {
    this.type = type;
    this.name = name;
    this.resource = resource;
  }

  static LiveResourceRegistration construct(final Class<? extends Listener> type, final String name, final Resource<?> resource) {
    Utilities.checkListenerType(type);
    Utilities.checkResourceName(name);
    Utilities.checkResourceImplementation(name, resource);

    return copy(type, name, resource);
  }

  static LiveResourceRegistration copy(final Class<? extends Listener> type, final String name, final Resource<?> resource) {
    return new LiveResourceRegistration(type, name, resource);
  }

  public static <L extends Listener> LiveResourceRegistration of(final Class<L> type, final String name) {
    return of(type, Unsafe.cast(type, Resources.forName(name)));
  }

  public static <L extends Listener> LiveResourceRegistration of(final Class<L> type, final Resource<? super L> resource) {
    return construct(type, resource.getName(), resource);
  }

  public static Set<LiveResourceRegistration> snapshot() {
    ResourceProvider provider = Services.getResourceService().getResourceProvider();

    Set<LiveResourceRegistration> data = newHashSet();

    for (Class<? extends Listener> type: provider.types()) {
      for (Resource<?> resource: provider.forType(type)) {
        data.add(construct(type, resource.getName(), resource));
      }
    }

    return data;
  }

  public ResourcePersistenceData toPersistenceData() {
    return ResourcePersistenceData.copy(this.isRegistered(), this.type, this.name, Utilities.serializableOrNull(this.resource));
  }

  public Class<? extends Listener> getListenerType() {
    return this.type;
  }

  public Resource<?> getResource() {
    return this.resource;
  }

  public String getResourceName() {
    return this.name;
  }
}
