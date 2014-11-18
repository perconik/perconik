package sk.stuba.fiit.perconik.eclipse.core.resources;

import javax.annotation.Nullable;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

import sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions;
import sk.stuba.fiit.perconik.eclipse.core.runtime.RuntimeCoreException;

/**
 * TODO
 *
 * @deprecated Use {@link ResourceEventResolver} instead.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Deprecated
public abstract class ResourceDeltaResolver implements IResourceDeltaVisitor {
  protected ResourceDeltaResolver() {}

  /**
   * Resolves supplied resource delta. Invokes
   * {@link #resolveDelta resolveDelta(delta, delta.getResource())}
   * and returns its result.
   *
   * @return {@code true} if the resource delta's children should
   *         be visited, {@code false} if they should be skipped
   *
   * @throws CoreException if the visit fails for some reason
   */
  public final boolean visit(final IResourceDelta delta) throws CoreException {
    return this.resolveDelta(delta, delta.getResource());
  }

  /**
   * Resolves supplied resource. Invokes
   * {@link #resolveResource resolveResource(resource)}
   * and returns its result.
   *
   * @return {@code true} if the resource delta's children should
   *         be visited, {@code false} if they should be skipped
   *
   * @throws CoreException if the probe fails for some reason
   */
  public final boolean probe(@Nullable final IResource resource) throws CoreException {
    if (resource == null) {
      return false;
    }

    return this.resolveResource(resource);
  }

  protected abstract boolean resolveDelta(IResourceDelta delta, IResource resource) throws CoreException;

  protected abstract boolean resolveResource(IResource resource) throws CoreException;

  /**
   * Resolves supplied resource delta or resource of a change event.
   * If the resource delta is not {@code null} then this method
   * visits it, otherwise it probes the specified resource.
   *
   * <p>This method always invokes {@link #preVisitOrProbe()} before
   * resolving specified arguments but invokes {@link #postVisitOrProbe()}
   * only after successful resolving (post hook is not invoked in case of
   * an exception).
   *
   * @throws RuntimeCoreException if the visit or probe fail for some reason
   */
  public final void visitOrProbe(@Nullable final IResourceDelta delta, @Nullable final IResource resource) {
    this.preVisitOrProbe();

    try {
      if (delta != null) {
        delta.accept(this);
      } else {
        this.probe(resource);
      }
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }

    this.postVisitOrProbe();
  }

  /**
   * Resolves supplied resource delta or resource change event.
   *
   * <p>See {@link #visitOrProbe(IResourceDelta, IResource)}
   * for more details.
   *
   * @throws RuntimeCoreException if the visit or probe fail for some reason
   */
  public final void visitOrProbe(@Nullable final IResourceDelta delta, @Nullable final IResourceChangeEvent event) {
    this.visitOrProbe(delta, event != null ? event.getResource() : null);
  }

  protected void preVisitOrProbe() {}

  protected void postVisitOrProbe() {}
}
