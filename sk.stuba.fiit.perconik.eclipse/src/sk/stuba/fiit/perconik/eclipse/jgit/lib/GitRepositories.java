package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import java.util.NoSuchElementException;

import org.eclipse.core.resources.IProject;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.lib.Ref;
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
	
	public static final Repository fromProject(final IProject project)
	{
		Git git = GitCommands.fromProject(project);
		
		return git != null ? git.getRepository() : null;
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
	
	public static final Ref switchBranch(final Repository repository, final String branchName)
	{
		return handleCheckoutCommand(new Git(repository).checkout().setName(branchName));
	}
	
	private static final Ref handleCheckoutCommand(final CheckoutCommand command)
	{
		try
		{
			return command.call();
		}
		catch(Exception e)
		{
			throw Throwables.propagate(e);
		}
	}
	
	private static final RevCommit handleMostRecentCommit(final LogCommand command)
	{
		try
		{
			return command.setMaxCount(1).call().iterator().next();
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

	public static final Ref switchBranch(final Repository repository, final String branch)
	{
		return handleCheckoutCommand(new Git(repository).checkout().setName(branch));
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
	
	public static final RevCommit getMostRecentCommit(final Repository repository)
	{
		return handleMostRecentCommit(new Git(repository).log());
	}
	
	public static final RevCommit getMostRecentCommit(final Repository repository, final String path)
	{
		return handleMostRecentCommit(new Git(repository).log().addPath(path));
	}
	
	public static final String getRemoteOriginUrl(final Repository repository)
	{
		return GitConfigurations.getRemoteOriginUrl(repository.getConfig());
	}
}
