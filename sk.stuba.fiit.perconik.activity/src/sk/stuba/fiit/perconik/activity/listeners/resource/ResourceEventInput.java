package sk.stuba.fiit.perconik.activity.listeners.resource;

import com.google.common.base.Optional;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;

import sk.stuba.fiit.perconik.eclipse.core.resources.ProjectBuildKind;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;

import static com.google.common.base.Optional.fromNullable;

final class ResourceEventInput {
  private static final int UNSET_BUILD_KIND = 0;

  final Object source;

  final ResourceEventType type;

  final Optional<ProjectBuildKind> build;

  /**
   * Resource event delta returned by {@code event.getDelta()}.
   */
  final Optional<IResourceDelta> delta;

  /**
   * Resource in question returned by {@code event.getResource()}.
   */
  final Optional<IResource> resource;

  private ResourceEventInput(final IResourceChangeEvent event) {
    this.source = event.getSource();

    int type = event.getType();
    int kind = event.getBuildKind();

    this.type = ResourceEventType.valueOf(type);
    this.build = fromNullable(kind != UNSET_BUILD_KIND ? ProjectBuildKind.valueOf(kind) : null);

    this.delta = fromNullable(event.getDelta());
    this.resource = fromNullable(event.getResource());
  }

  static ResourceEventInput of(final IResourceChangeEvent event) {
    return new ResourceEventInput(event);
  }
}
