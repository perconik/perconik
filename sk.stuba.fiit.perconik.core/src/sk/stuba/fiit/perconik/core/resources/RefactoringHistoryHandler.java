package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ltk.core.refactoring.RefactoringCore;

import sk.stuba.fiit.perconik.core.listeners.RefactoringHistoryListener;

enum RefactoringHistoryHandler implements Handler<RefactoringHistoryListener> {
  INSTANCE;

  public final void register(final RefactoringHistoryListener listener) {
    RefactoringCore.getHistoryService().addHistoryListener(listener);
  }

  public final void unregister(final RefactoringHistoryListener listener) {
    RefactoringCore.getHistoryService().removeHistoryListener(listener);
  }
}
