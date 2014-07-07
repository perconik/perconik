package com.gratex.perconik.activity.ide;

import org.eclipse.core.resources.IProject;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitProjectCache;

public final class IdeGitProjects
{
	private static final GitProjectCache cache = new GitProjectCache();

	private IdeGitProjects()
	{
		throw new AssertionError();
	}

	public static final Git getGit(final IProject project)
	{
		try
		{
			return cache.getUnchecked(project);
		}
		catch (RuntimeException e)
		{
			return null;
		}
	}

	public static final Repository getRepository(final IProject project)
	{
		Git git = getGit(project);

		return git != null ? git.getRepository() : null;
	}
}
