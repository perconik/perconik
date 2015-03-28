package sk.stuba.fiit.perconik.eclipse.core.resources;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ParametricResourceEventResolver<P> extends ResourceEventResolver {
  private final P parameter;

  protected ParametricResourceEventResolver(final P parameter) {
    this.parameter = checkNotNull(parameter);
  }

  @Override
  protected final boolean resolveEvent(final IResourceChangeEvent event) throws CoreException {
    return this.resolveEvent(this.parameter, event);
  }

  @Override
  protected final boolean resolveDelta(final IResourceDelta delta) throws CoreException {
    return this.resolveDelta(this.parameter, delta);
  }

  @Override
  protected final boolean resolveResource(final IResource resource) throws CoreException {
    return this.resolveResource(this.parameter, resource);
  }

  protected abstract boolean resolveEvent(P parameter, IResourceChangeEvent event) throws CoreException;

  protected abstract boolean resolveDelta(P parameter, IResourceDelta delta) throws CoreException;

  protected abstract boolean resolveResource(P parameter, IResource resource) throws CoreException;

  @Override
  protected final void preResolve() {
    this.preResolve(this.parameter);
  }

  @Override
  protected final void postResolve() {
    this.postResolve(this.parameter);
  }

  protected void preResolve(@SuppressWarnings("unused") final P parameter) {}

  protected void postResolve(@SuppressWarnings("unused") final P parameter) {}
}
