package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import org.eclipse.core.resources.IProject;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import com.google.common.base.Throwables;

public final class GitCommands
{
	private GitCommands()
	{
		throw new AssertionError();
	}
	
	public static final Git fromProject(final IProject project)
	{
		try
		{
			return Git.open(project.getLocation().toFile());
		}
		catch (RepositoryNotFoundException e)
		{
			return null;
		}
		catch (Exception e)
		{
			throw Throwables.propagate(e);
		}
	}
}
