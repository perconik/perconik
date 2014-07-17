package com.gratex.perconik.activity.ide;

import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAsState;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.RepositoryCache;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.project.GitProjectData;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

@SuppressWarnings("restriction") // TODO resolve: not sure why is egit core restricted
public final class IdeGitProjects
{
	private IdeGitProjects()
	{
		throw new AssertionError();
	}

	static final RepositoryCache repositories()
	{
		return checkNotNullAsState(Activator.getDefault().getRepositoryCache());
	}

	static final RepositoryUtil utilities()
	{
		return checkNotNullAsState(Activator.getDefault().getRepositoryUtil());
	}

	public static final GitProjectData getProjectData(final IProject project)
	{
		return GitProjectData.get(project);
	}

	public static final Repository getRepository(final IPath path)
	{
		return repositories().getRepository(path);
	}

	public static final boolean isIgnored(final IPath path) throws IOException
	{
		return RepositoryUtil.isIgnored(path);
	}
}
