package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import java.util.NoSuchElementException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import com.google.common.base.Throwables;

/**
 * Static utility methods pertaining to Git repositories.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class GitRepositories
{
	private GitRepositories()
	{
		throw new AssertionError();
	}

	public static final String getBranch(final Repository repository)
	{
		try
		{
			return repository.getBranch();
		}
		catch (Exception e)
		{
			throw Throwables.propagate(e);
		}
	}
	
	public static final RevCommit getLastCommit(final Repository repository)
	{
		try
		{
			return new Git(repository).log().setMaxCount(1).call().iterator().next();
		}
		catch (NoSuchElementException e)
		{
			return null;
		}
		catch (Exception e)
		{
			throw Throwables.propagate(e);
		}
	}
	
	public static final String getRemoteOriginUrl(final Repository repository)
	{
		return GitConfigurations.getRemoteOriginUrl(repository.getConfig());
	}
}
