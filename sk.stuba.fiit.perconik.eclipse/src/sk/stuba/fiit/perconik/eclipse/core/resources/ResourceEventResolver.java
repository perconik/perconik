package sk.stuba.fiit.perconik.eclipse.core.resources;

import javax.annotation.Nullable;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

import sk.stuba.fiit.perconik.eclipse.core.runtime.RuntimeCoreException;

import static sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions.propagate;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ResourceEventResolver implements IResourceVisitor, IResourceDeltaVisitor {
  protected ResourceEventResolver() {}

  /**
   * Visits supplied resource change event.
   *
   * <p>Invokes {@link #resolveEvent resolveEvent(event)} and returns the computed result.
   *
   * @return {@code true} if the resource change events's delta or resource should
   *         be visited, {@code false} if they should be skipped
   *
   * @throws CoreException if the visit fails for some reason
   */
  public final boolean visit(final IResourceChangeEvent event) throws CoreException {
    return this.resolveEvent(event);
  }

  /**
   * Visits supplied resource delta.
   *
   * <p>Invokes {@link #resolveDelta resolveDelta(delta)} and returns the computed result.
   *
   * @return {@code true} if the resource delta's children should
   *         be visited, {@code false} if they should be skipped
   *
   * @throws CoreException if the visit fails for some reason
   */
  public final boolean visit(final IResourceDelta delta) throws CoreException {
    return this.resolveDelta(delta);
  }

  /**
   * Visits supplied resource.
   *
   * <p>Invokes {@link #resolveResource(resource)} and returns the computed result.
   *
   * @return {@code true} if the resource's members should
   *         be visited, {@code false} if they should be skipped
   *
   * @throws CoreException if the probe fails for some reason
   */
  public final boolean visit(final IResource resource) throws CoreException {
    return this.resolveResource(resource);
  }

  protected abstract boolean resolveEvent(IResourceChangeEvent event) throws CoreException;

  protected abstract boolean resolveDelta(IResourceDelta delta) throws CoreException;

  protected abstract boolean resolveResource(IResource resource) throws CoreException;

  /**
   * Resolves supplied resource resource change event.
   *
   * <p>Visits resource event or returns silently if event is {@code null}.
   *
   * <p>This method always invokes {@link #preResolve()} before resolving but invokes
   * {@link #postResolve()} only after successful resolving (post hook is not invoked
   * in case of an exception).
   *
   * @throws RuntimeCoreException if the visit fails for some reason
   */
  public final void resolve(@Nullable final IResourceChangeEvent event) {
    this.preResolve();
    this.resolveInternal(event);
    this.postResolve();
  }

  private final void resolveInternal(@Nullable final IResourceChangeEvent event) {
    try {
      if (event != null && this.visit(event)) {
        this.resolveInternal(event.getDelta(), event.getResource());
      }
    } catch (CoreException failure) {
      throw propagate(failure);
    }
  }

  /**
   * Resolves supplied resource delta or resource.
   *
   * <p>Visits first non {@code null} parameter or returns silently if both are {@code null}.
   *
   * <p>This method always invokes {@link #preResolve()} before resolving but invokes
   * {@link #postResolve()} only after successful resolving (post hook is not invoked
   * in case of an exception).
   *
   * @throws RuntimeCoreException if the visit fails for some reason
   */
  public final void resolve(@Nullable final IResourceDelta delta, @Nullable final IResource resource) {
    this.preResolve();
    this.resolveInternal(delta, resource);
    this.postResolve();
  }

  private final void resolveInternal(@Nullable final IResourceDelta delta, @Nullable final IResource resource) {
    try {
      if (delta != null) {
        delta.accept(this);
      } else if (resource != null) {
        resource.accept(this);
      }
    } catch (CoreException failure) {
      throw propagate(failure);
    }
  }

  protected void preResolve() {}

  protected void postResolve() {}
}
