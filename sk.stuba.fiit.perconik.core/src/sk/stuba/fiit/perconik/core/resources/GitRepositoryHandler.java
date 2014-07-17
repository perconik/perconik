package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.GitRepositoryListener;

import org.eclipse.egit.core.project.GitProjectData;

@SuppressWarnings("restriction")
enum GitRepositoryHandler implements Handler<GitRepositoryListener>
{
	INSTANCE;

	public final void register(final GitRepositoryListener listener)
	{
		GitProjectData.addRepositoryChangeListener(listener);
	}

	public final void unregister(final GitRepositoryListener listener)
	{
		GitProjectData.removeRepositoryChangeListener(listener);
	}
}
