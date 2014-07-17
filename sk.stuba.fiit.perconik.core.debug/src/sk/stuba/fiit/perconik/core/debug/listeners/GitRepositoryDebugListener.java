package sk.stuba.fiit.perconik.core.debug.listeners;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.GitRepositoryListener;

import org.eclipse.egit.core.project.RepositoryMapping;

@SuppressWarnings("restriction")
public final class GitRepositoryDebugListener extends AbstractDebugListener implements GitRepositoryListener
{
	public GitRepositoryDebugListener()
	{
	}

	public GitRepositoryDebugListener(final DebugConsole console)
	{
		super(console);
	}

	public final void repositoryChanged(final RepositoryMapping mapping)
	{
		this.printHeader("Git repository changed");
		this.printGitRepositoryMapping(mapping);
	}

	private final void printGitRepositoryMapping(final RepositoryMapping mapping)
	{
		this.put(Debug.dumpGitRepositoryMapping(mapping));
	}
}
