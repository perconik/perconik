package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jgit.events.IndexChangedListener;

import sk.stuba.fiit.perconik.core.listeners.GitIndexListener;

enum GitIndexHandler implements Handler<GitIndexListener> {
  INSTANCE;

  private final GitHandlerSupport<IndexChangedListener> support = new GitHandlerSupport<>(IndexChangedListener.class);

  public void register(final GitIndexListener listener) {
    this.support.register(listener);
  }

  public void unregister(final GitIndexListener listener) {
    this.support.unregister(listener);
  }
}
