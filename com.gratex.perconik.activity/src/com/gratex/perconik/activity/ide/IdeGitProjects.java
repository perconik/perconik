package com.gratex.perconik.activity.ide;

import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitProjectCache;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

public final class IdeGitProjects
{
	private static final GitProjectCache cache = new GitProjectCache();

	private IdeGitProjects()
	{
		throw new AssertionError();
	}

	private static final RepositoryUtil utilities()
	{
		return Activator.getDefault().getRepositoryUtil();
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

	public static final boolean isIgnored(final IPath path) throws IOException
	{
		return utilities().isIgnored(path);
	}
}
