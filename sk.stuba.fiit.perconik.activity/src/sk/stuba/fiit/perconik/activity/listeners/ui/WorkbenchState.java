package sk.stuba.fiit.perconik.activity.listeners.ui;

import java.util.concurrent.atomic.AtomicBoolean;

enum WorkbenchState {
  STARTUP,

  SHUTDOWN;

  private final AtomicBoolean state;

  private WorkbenchState() {
    this.state = new AtomicBoolean(false);
  }

  boolean canProcess() {
    return this.state.compareAndSet(false, true);
  }

  boolean isProcessed() {
    return this.state.get();
  }
}
