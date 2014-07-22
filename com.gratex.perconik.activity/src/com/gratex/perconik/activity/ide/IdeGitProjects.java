package com.gratex.perconik.activity.ide;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.egit.core.project.GitProjectData;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jgit.lib.FileMode;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.FileTreeIterator;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.WorkingTreeIterator;
import org.eclipse.jgit.treewalk.filter.PathFilter;

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
		// TODO find better way to resolve this dependency:
		// RepositoryUtil.isIgnored(path) requires egit 2.3, but eclipse 3.8 has only egit 2.2
		//return RepositoryUtil.isIgnored(path);
		
		// TODO resolve: code copied from egit 3.4
		RepositoryMapping mapping = RepositoryMapping.getMapping(path);
		if (mapping == null)
			return true; // Linked resources may not be mapped
		Repository repository = mapping.getRepository();
		String repoRelativePath = mapping.getRepoRelativePath(path);
		TreeWalk walk = new TreeWalk(repository);
		try {
			walk.addTree(new FileTreeIterator(repository));
			walk.setFilter(PathFilter.create(repoRelativePath));
			while (walk.next()) {
				WorkingTreeIterator workingTreeIterator = walk.getTree(0, WorkingTreeIterator.class);
				if (walk.getPathString().equals(repoRelativePath))
					return workingTreeIterator.isEntryIgnored();
				if (workingTreeIterator.getEntryFileMode().equals(FileMode.TREE))
					walk.enterSubtree();
			}
		} finally {
			walk.release();
		}
		return false;
	}

	public static final boolean isMapped(final IPath path)
	{
		return RepositoryMapping.getMapping(path) != null;
	}
}
