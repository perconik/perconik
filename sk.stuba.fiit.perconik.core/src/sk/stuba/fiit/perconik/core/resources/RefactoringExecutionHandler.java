package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ltk.core.refactoring.RefactoringCore;

import sk.stuba.fiit.perconik.core.listeners.RefactoringExecutionListener;

enum RefactoringExecutionHandler implements Handler<RefactoringExecutionListener> {
  INSTANCE;

  public void register(final RefactoringExecutionListener listener) {
    RefactoringCore.getHistoryService().addExecutionListener(listener);
  }

  public void unregister(final RefactoringExecutionListener listener) {
    RefactoringCore.getHistoryService().removeExecutionListener(listener);
  }
}
