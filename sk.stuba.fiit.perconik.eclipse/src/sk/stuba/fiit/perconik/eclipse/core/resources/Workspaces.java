package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.nio.file.Paths;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Static utility methods pertaining to Eclipse workspace.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Workspaces {
  private Workspaces() {}

  public static IWorkspace getWorkspace() {
    return ResourcesPlugin.getWorkspace();
  }

  public static String getName(final IWorkspace workspace) {
    return Paths.get(workspace.getRoot().getLocationURI()).getFileName().toString();
  }
}
