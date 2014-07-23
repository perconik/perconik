package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jgit.events.RefsChangedEvent;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.GitReferenceListener;

public final class GitReferenceDebugListener extends AbstractDebugListener implements GitReferenceListener {
  public GitReferenceDebugListener() {}

  public GitReferenceDebugListener(final DebugConsole console) {
    super(console);
  }

  public final void onRefsChanged(final RefsChangedEvent event) {
    this.printHeader("Git reference changed");
    this.printGitReferenceEvent(event);
  }

  private final void printGitReferenceEvent(final RefsChangedEvent event) {
    this.put(Debug.dumpGitReferenceEvent(event));
  }
}
