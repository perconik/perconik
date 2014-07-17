package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import static com.google.common.base.Preconditions.checkState;

import sk.stuba.fiit.perconik.utilities.io.MorePaths;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.ignore.IgnoreNode;
import org.eclipse.jgit.ignore.IgnoreNode.MatchResult;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

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

	private static final Ref handleCheckoutCommand(final CheckoutCommand command)
	{
		try
		{
			return command.call();
		}
		catch (Exception e)
		{
			throw Throwables.propagate(e);
		}
	}

	private static final Iterable<RevCommit> handleLogCommand(final LogCommand command)
	{
		try
		{
			return command.all().call();
		}
		catch (Exception e)
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

	public static final Ref checkoutFile(final Repository repository, final Path path, final RevCommit commit)
	{
		return checkoutFile(repository, path.toString(), commit);
	}

	public static final Ref checkoutFile(final Repository repository, final String path, final RevCommit commit)
	{
		return handleCheckoutCommand(new Git(repository).checkout().setStartPoint(commit).addPath(path));
	}

	public static final Ref checkoutFileToHead(final Repository repository, final Path path)
	{
		return checkoutFileToHead(repository, path.toString());
	}

	public static final Ref checkoutFileToHead(final Repository repository, final String path)
	{
		return handleCheckoutCommand(new Git(repository).checkout().setStartPoint(Constants.HEAD).addPath(path));
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

	public static final Iterable<RevCommit> getFileLog(final Repository repository, final Path path)
	{
		return getFileLog(repository, path.toString());
	}

	public static final Iterable<RevCommit> getFileLog(final Repository repository, final String path)
	{
		return handleLogCommand(new Git(repository).log().addPath(path));
	}

	public static final List<Path> getIgnoreFiles(final Repository repository, final Path path)
	{
		Path root = repository.getDirectory().toPath().getParent().toAbsolutePath().normalize();
		Path leaf = path.toAbsolutePath().normalize();

		checkState(leaf.startsWith(root));

		List<Path> paths = Lists.newLinkedList();

		for (Path other: MorePaths.downToRoot(path))
		{
			if (other.equals(root))
			{
				break;
			}

			Path ignore = other.resolve(Constants.DOT_GIT_IGNORE);

			if (Files.isRegularFile(ignore))
			{
				paths.add(ignore);
			}
		}

		return paths;
	}

	public static final List<Path> getIgnoreFiles(final Repository repository, final String path)
	{
		return getIgnoreFiles(repository, Paths.get(path));
	}

	public static final IgnoreNode getIgnoreNode(final Path path)
	{
		IgnoreNode node = new IgnoreNode();

		Path ignore = path.resolve(Constants.DOT_GIT_IGNORE);

		if (Files.isRegularFile(ignore))
		{
			try
			{
				node.parse(Files.newInputStream(ignore));
			}
			catch (IOException e)
			{
				Throwables.propagate(e);
			}
		}

		return node;
	}

	public static final IgnoreNode getIgnoreNode(final String path)
	{
		return getIgnoreNode(Paths.get(path));
	}

	public static final List<IgnoreNode> getIgnoreNodes(final Repository repository, final Path path)
	{
		List<IgnoreNode> nodes = Lists.newLinkedList();

		for (Path ignore: getIgnoreFiles(repository, path))
		{
			nodes.add(getIgnoreNode(ignore));
		}

		return nodes;
	}

	public static final List<IgnoreNode> getIgnoreNodes(final Repository repository, final String path)
	{
		return getIgnoreNodes(repository, Paths.get(path));
	}

	public static final RevCommit getMostRecentCommit(final Repository repository)
	{
		return handleMostRecentCommit(new Git(repository).log());
	}

	public static final RevCommit getMostRecentCommit(final Repository repository, final Path path)
	{
		return getMostRecentCommit(repository, path.toString());
	}

	public static final RevCommit getMostRecentCommit(final Repository repository, final String path)
	{
		return handleMostRecentCommit(new Git(repository).log().addPath(path));
	}

	public static final String getRemoteOriginUrl(final Repository repository)
	{
		return GitConfigurations.getRemoteOriginUrl(repository.getConfig());
	}

	public static final boolean isIgnored(final Repository repository, final Path path)
	{
		return isIgnored(getIgnoreNodes(repository, path), path);
	}

	public static final boolean isIgnored(final Repository repository, final String path)
	{
		return isIgnored(repository, Paths.get(path));
	}

	public static final boolean isIgnored(final Iterable<IgnoreNode> nodes, final Path path)
	{
		return isIgnored(nodes, path.toString(), Files.isDirectory(path));
	}

	public static final boolean isIgnored(final Iterable<IgnoreNode> nodes, final String path)
	{
		return isIgnored(nodes, path, Files.isDirectory(Paths.get(path)));
	}

	private static final boolean isIgnored(final Iterable<IgnoreNode> nodes, final String path, final boolean directory)
	{
		for (IgnoreNode node: nodes)
		{
			MatchResult result = node.isIgnored(path, directory);

			switch (result)
			{
			case IGNORED:
				return true;

			case NOT_IGNORED:
				return false;

			default:
				continue;
			}
		}

		return false;
	}
}
