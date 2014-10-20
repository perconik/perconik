package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.egit.core.project.GitProjectData;

import sk.stuba.fiit.perconik.core.listeners.GitRepositoryListener;

@SuppressWarnings("restriction")
enum GitRepositoryHandler implements Handler<GitRepositoryListener> {
  INSTANCE;

  public void register(final GitRepositoryListener listener) {
    GitProjectData.addRepositoryChangeListener(listener);
  }

  public void unregister(final GitRepositoryListener listener) {
    GitProjectData.removeRepositoryChangeListener(listener);
  }
}
