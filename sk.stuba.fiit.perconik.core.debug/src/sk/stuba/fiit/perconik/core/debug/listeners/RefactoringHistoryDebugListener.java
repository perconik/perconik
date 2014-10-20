package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.ltk.core.refactoring.history.RefactoringHistoryEvent;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.RefactoringHistoryListener;

public final class RefactoringHistoryDebugListener extends AbstractDebugListener implements RefactoringHistoryListener {
  public RefactoringHistoryDebugListener() {}

  public RefactoringHistoryDebugListener(final DebugConsole console) {
    super(console);
  }

  public void historyNotification(final RefactoringHistoryEvent event) {
    this.printHeader("Refectoring history");
    this.printRefactoringHistoryEvent(event);
  }

  private void printRefactoringHistoryEvent(final RefactoringHistoryEvent event) {
    this.put(Debug.dumpRefactoringHistoryEvent(event));
  }
}
