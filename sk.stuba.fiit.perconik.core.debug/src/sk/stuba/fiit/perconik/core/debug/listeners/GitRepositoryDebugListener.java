package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.egit.core.project.RepositoryMapping;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.GitRepositoryListener;

@SuppressWarnings("restriction")
public final class GitRepositoryDebugListener extends AbstractDebugListener implements GitRepositoryListener {
  public GitRepositoryDebugListener() {}

  public GitRepositoryDebugListener(final DebugConsole console) {
    super(console);
  }

  public void repositoryChanged(final RepositoryMapping mapping) {
    this.printHeader("Git repository changed");
    this.printGitRepositoryMapping(mapping);
  }

  private void printGitRepositoryMapping(final RepositoryMapping mapping) {
    this.put(Debug.dumpGitRepositoryMapping(mapping));
  }
}
