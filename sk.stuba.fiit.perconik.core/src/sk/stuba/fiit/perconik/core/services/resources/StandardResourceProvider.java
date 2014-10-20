package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.utilities.MoreSets;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Sets.newHashSet;

final class StandardResourceProvider extends AbstractResourceProvider {
  private final BiMap<String, Resource<?>> nameToResource;

  private final SetMultimap<Class<? extends Listener>, Resource<?>> typeToResources;

  private final ResourceProvider parent;

  StandardResourceProvider(final Builder builder) {
    this.nameToResource = ImmutableBiMap.copyOf(builder.nameToResource);
    this.typeToResources = ImmutableSetMultimap.copyOf(builder.typeToResources);
    this.parent = builder.parent.or(ResourceProviders.getSystemProvider());

    assert this.nameToResource.size() == newHashSet(this.typeToResources.values()).size();
  }

  public static final class Builder implements ResourceProvider.Builder {
    final BiMap<String, Resource<?>> nameToResource;

    final SetMultimap<Class<? extends Listener>, Resource<?>> typeToResources;

    Optional<ResourceProvider> parent;

    public Builder() {
      this.nameToResource = HashBiMap.create();
      this.typeToResources = HashMultimap.create();
      this.parent = Optional.absent();
    }

    public <L extends Listener> Builder add(final Class<L> type, final Resource<? super L> resource) {
      checkNotNull(type);

      this.nameToResource.put(resource.getName(), resource);
      this.typeToResources.put(type, resource);

      return this;
    }

    public Builder parent(final ResourceProvider parent) {
      checkState(!this.parent.isPresent());

      this.parent = Optional.of(checkNotNull(parent));

      return this;
    }

    public StandardResourceProvider build() {
      return new StandardResourceProvider(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  public Resource<?> forName(final String name) {
    Resource<?> resource = this.nameToResource.get(name);

    if (resource != null) {
      return resource;
    }

    return this.parentForName(name, null);
  }

  public <L extends Listener> Set<Resource<L>> forType(final Class<L> type) {
    Set<Resource<L>> resources = newHashSet();

    for (Resource<?> resource: this.typeToResources.get(type)) {
      resources.add(Unsafe.cast(type, resource));
    }

    return MoreSets.newHashSet(resources, this.parentForType(type, null));
  }

  public Set<String> names() {
    return MoreSets.newHashSet(this.nameToResource.keySet(), this.parent.names());
  }

  public Set<Class<? extends Listener>> types() {
    return MoreSets.newHashSet(this.typeToResources.keySet(), this.parent.types());
  }

  public ResourceProvider parent() {
    return this.parent;
  }
}
