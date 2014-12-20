package sk.stuba.fiit.perconik.core.services.resources;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.ResourceUnregistrationException;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;
import sk.stuba.fiit.perconik.utilities.reflect.Reflections;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Sets.newHashSet;

final class StandardResourceManager extends AbstractResourceManager {
  static final int typeToResourcesMapExpectedSize = 128;

  static final int typeToResourcesMapExpectedSizePerType = 4;

  static final int typeMatchCacheMaximumSize = 512;

  private final SetMultimap<Class<? extends Listener>, Resource<?>> typeToResources;

  private final LoadingCache<TypeMatch, Boolean> typeMatchCache;

  StandardResourceManager() {
    this.typeToResources = HashMultimap.create(typeToResourcesMapExpectedSize, typeToResourcesMapExpectedSizePerType);

    this.typeMatchCache = newBuilder().maximumSize(typeMatchCacheMaximumSize).build(new CacheLoader<TypeMatch, Boolean>() {
      @Override
      public Boolean load(final TypeMatch match) throws Exception {
        return match.compute();
      }
    });
  }

  @Override
  protected SetMultimap<Class<? extends Listener>, Resource<?>> typeToResources() {
    return this.typeToResources;
  }

  public <L extends Listener> void unregisterAll(final Class<L> type) {
    List<Exception> failures = newLinkedList();

    for (Entry<Class<? extends L>, Resource<? extends L>> entry: this.assignablesAsSetMultimap(type).entries()) {
      Resource<L> resource = Unsafe.cast(type, entry.getValue());

      try {
        this.unregister(entry.getKey(), resource);
      } catch (Exception failure) {
        failures.add(failure);
      }
    }

    if (!failures.isEmpty()) {
      throw MoreThrowables.initializeSuppressor(new ResourceUnregistrationException(), failures);
    }
  }

  public <L extends Listener> Set<Resource<? extends L>> assignables(final Class<L> type) {
    return newHashSet(this.assignablesAsSetMultimap(type).values());
  }

  private <L extends Listener> SetMultimap<Class<? extends L>, Resource<? extends L>> assignablesAsSetMultimap(final Class<L> type) {
    SetMultimap<Class<? extends L>, Resource<? extends L>> result = HashMultimap.create();

    for (Entry<Class<? extends Listener>, Resource<?>> entry: this.typeToResources.entries()) {
      if (type.isAssignableFrom(entry.getKey())) {
        // safe cast as key type is a subtype of specified type
        @SuppressWarnings("unchecked")
        Class<? extends L> subtype = (Class<? extends L>) entry.getKey();

        // safe cast as value resource is always corresponding to key type
        @SuppressWarnings("unchecked")
        Resource<? extends L> resource = (Resource<? extends L>) entry.getValue();

        result.put(subtype, resource);
      }
    }

    return result;
  }

  private static final class TypeMatch {
    final Class<? extends Listener> type;

    final Class<? extends Listener> supertype;

    TypeMatch(final Class<? extends Listener> type, final Class<? extends Listener> supertype) {
      assert type != null && supertype != null;

      this.type = type;
      this.supertype = supertype;
    }

    @Override
    public boolean equals(@Nullable final Object object) {
      if (object instanceof TypeMatch) {
        TypeMatch other = (TypeMatch) object;

        return this.type == other.type && this.supertype == other.supertype;
      }

      return false;
    }

    @Override
    public int hashCode() {
      return this.type.hashCode() ^ this.supertype.hashCode();
    }

    Boolean compute() {
      for (Class<?> supertype: Reflections.collectInterfaces(this.type)) {
        if (supertype == this.supertype) {
          return TRUE;
        }
      }

      return FALSE;
    }
  }

  public <L extends Listener> Set<Resource<? super L>> registrables(final Class<L> type) {
    Set<Resource<? super L>> result = newHashSet();

    for (Entry<Class<? extends Listener>, Resource<?>> entry: this.typeToResources.entries()) {
      if (type == entry.getKey() || this.typeMatchCache.getUnchecked(new TypeMatch(type, entry.getKey()))) {
        // safe cast as L was matched with actual type
        @SuppressWarnings("unchecked")
        Resource<? super L> resource = (Resource<? super L>) entry.getValue();

        result.add(resource);
      }
    }

    return result;
  }

  public SetMultimap<Class<? extends Listener>, Resource<?>> registrations() {
    return HashMultimap.create(this.typeToResources);
  }

  public boolean registered(final Class<? extends Listener> type, final Resource<?> resource) {
    return this.typeToResources.containsEntry(type, resource);
  }
}
