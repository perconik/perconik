package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import org.eclipse.jgit.lib.Config;

/**
 * Static utility methods pertaining to Git configuration objects.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class GitConfigurations {
  private GitConfigurations() {}

  public static String getRemoteOriginUrl(final Config configuration) {
    return configuration.getString("remote", "origin", "url");
  }
}
