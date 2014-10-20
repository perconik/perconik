package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.core.commands.operations.OperationHistoryFactory;

import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;

enum OperationHistoryHandler implements Handler<OperationHistoryListener> {
  INSTANCE;

  public void register(final OperationHistoryListener listener) {
    OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(listener);
  }

  public void unregister(final OperationHistoryListener listener) {
    OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(listener);
  }
}
