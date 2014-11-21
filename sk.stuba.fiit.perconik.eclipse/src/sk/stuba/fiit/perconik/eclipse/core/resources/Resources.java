package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.net.URI;
import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nullable;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions;

/**
 * Static utility methods pertaining to Eclipse resources.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Resources {
  private Resources() {}

  public static void accept(final IResource resource, final IResourceVisitor visitor) {
    try {
      resource.accept(visitor);
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }
  }

  public static void accept(final IResource resource, final IResourceVisitor visitor, final ResourceVisitorDepth depth) {
    accept(resource, visitor, depth, EnumSet.allOf(ResourceMemberFlag.class));
  }

  public static void accept(final IResource resource, final IResourceVisitor visitor, final ResourceVisitorDepth depth, final Set<ResourceMemberFlag> flags) {
    try {
      resource.accept(visitor, depth.getValue(), ResourceMemberFlag.valuesAsInteger(flags));
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }
  }

  public static IPath getLocation(@Nullable final IResource resource) {
    return resource != null ? resource.getLocation() : null;
  }

  public static URI getLocationUri(@Nullable final IResource resource) {
    return resource != null ? resource.getLocationURI() : null;
  }
}
