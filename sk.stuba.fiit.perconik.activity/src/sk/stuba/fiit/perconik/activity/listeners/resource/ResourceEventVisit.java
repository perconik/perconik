package sk.stuba.fiit.perconik.activity.listeners.resource;

import com.google.common.base.Optional;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

import static com.google.common.base.Preconditions.checkNotNull;

import static com.google.common.base.Optional.fromNullable;

final class ResourceEventVisit {
  /**
   * Initial resource event input data.
   */
  final ResourceEventInput input;

  /**
   * Currently visited resource delta, not necessarily the one returned by
   * {@code event.getDelta()} since it may also another deeper in the delta tree.
   *
   * <p>Note that if resource delta is set, then resource is not set. To obtain
   * current resource corresponding to this delta use {@code delta.getResource()}.
   */
  final Optional<IResourceDelta> delta;

  /**
   * Currently visited resource, not necessarily the one returned by
   * {@code event.getDelta()} since it may also another deeper in the resource tree.
   *
   * <p>Note that if resource is set, then resource delta is not set.
   */
  final Optional<IResource> resource;

  private ResourceEventVisit(final ResourceEventInput input, final Optional<IResourceDelta> delta, final Optional<IResource> resource) {
    this.input = input;
    this.delta = delta;
    this.resource = resource;
  }

  static ResourceEventVisit of(final ResourceEventInput input, final IResourceDelta delta) {
    return new ResourceEventVisit(checkNotNull(input), fromNullable(delta), Optional.<IResource>absent());
  }

  static ResourceEventVisit of(final ResourceEventInput input, final IResource resource) {
    return new ResourceEventVisit(checkNotNull(input), Optional.<IResourceDelta>absent(), fromNullable(resource));
  }
}
