package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;

import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

import sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions;

/**
 * Static utility methods pertaining to Eclipse resource deltas.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourceDeltas {
  private ResourceDeltas() {}

  public static void accept(final IResourceDelta delta, final IResourceDeltaVisitor visitor) {
    try {
      delta.accept(visitor);
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }
  }

  public static void accept(final IResourceDelta delta, final IResourceDeltaVisitor visitor, final Set<ResourceMemberFlag> flags) {
    try {
      delta.accept(visitor, ResourceMemberFlag.valuesAsInteger(flags));
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }
  }
}
