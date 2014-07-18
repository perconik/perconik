package com.gratex.perconik.activity.ide;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.project.GitProjectData;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

@SuppressWarnings("restriction") // TODO resolve: not sure why is egit core restricted
public final class IdeGitProjects
{
	private IdeGitProjects()
	{
		throw new AssertionError();
	}

	public static final GitProjectData getProjectData(final IProject project)
	{
		return GitProjectData.get(project);
	}

	public static final Repository getRepository(final IResource resource)
	{
		RepositoryMapping mapping = getRepositoryMapping(resource);

		return mapping != null ? mapping.getRepository() : null;
	}

	public static final RepositoryMapping getRepositoryMapping(final IResource resource)
	{
		GitProjectData data = getProjectData(resource.getProject());

		return data != null ? data.getRepositoryMapping(resource) : null;
	}

	public static final boolean isIgnored(final IPath path) throws IOException
	{
		return RepositoryUtil.isIgnored(path);
	}

	public static final boolean isMapped(final IPath path)
	{
		return RepositoryMapping.getMapping(path) != null;
	}
}
