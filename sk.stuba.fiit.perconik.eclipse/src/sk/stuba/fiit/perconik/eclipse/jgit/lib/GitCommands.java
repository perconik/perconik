package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
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
			IPath path = project.getLocation();

			while (true)
			{
				try
				{
					return Git.open(path.toFile());
				}
				catch (RepositoryNotFoundException e)
				{
					if (path.segmentCount() == 0)
					{
						break;
					}
					
					path = path.removeLastSegments(1);
					
					continue;
				}
			}
			
			return null;
		}
		catch (Exception e)
		{
			throw Throwables.propagate(e);
		}
	}
}
