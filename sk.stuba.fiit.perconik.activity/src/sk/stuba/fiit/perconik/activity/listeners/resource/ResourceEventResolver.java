package sk.stuba.fiit.perconik.activity.listeners.resource;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;

import sk.stuba.fiit.perconik.eclipse.core.resources.ParametricResourceEventResolver;

abstract class ResourceEventResolver extends ParametricResourceEventResolver<ResourceEventInput> {
  final long time;

  ResourceEventResolver(final long time, final ResourceEventInput input) {
    super(input);

    assert time >= 0L;

    this.time = time;
  }

  @Override
  protected boolean resolveEvent(final ResourceEventInput input, final IResourceChangeEvent event) throws CoreException {
    return true;
  }

  @Override
  protected boolean resolveDelta(final ResourceEventInput input, final IResourceDelta delta) throws CoreException {
    return false;
  }

  @Override
  protected boolean resolveResource(final ResourceEventInput input, final IResource resource) throws CoreException {
    return false;
  }
}
