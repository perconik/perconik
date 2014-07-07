package com.gratex.perconik.activity.ide;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import sk.stuba.fiit.perconik.core.java.ClassFiles;
import sk.stuba.fiit.perconik.eclipse.core.resources.Workspaces;
import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitRepositories;
import com.google.common.base.Preconditions;
import com.gratex.perconik.services.uaca.ide.BaseIdeEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeDocumentDto;
import com.gratex.perconik.services.uaca.ide.RcsServerDto;

public final class IdeData
{
	private IdeData()
	{
		throw new AssertionError();
	}

	public static final IdeDocumentDto newDocumentData(final IFile file)
	{
		return newDocumentData(file.getFullPath(), file.getProject());
	}

	public static final IdeDocumentDto newDocumentData(final IClassFile file)
	{
		return newDocumentData(ClassFiles.path(file));
	}

	private static final IdeDocumentDto newDocumentData(final IPath path)
	{
		IdeDocumentDto data = new IdeDocumentDto();

		data.setLocalPath(path.toString());

		return data;
	}

	private static final IdeDocumentDto newDocumentData(final IPath path, final IProject project)
	{
		IdeDocumentDto data = newDocumentData(path.makeRelative());

		Repository repository = IdeGitProjects.getRepository(project);

		if (repository != null)
		{
			data.setRcsServer(newGitServerData(GitRepositories.getRemoteOriginUrl(repository)));
			data.setBranch(GitRepositories.getBranch(repository));
			data.setServerPath(data.getLocalPath());

			RevCommit commit = GitRepositories.getMostRecentCommit(repository, path.makeRelative().toString());

			if (commit != null)
			{
				data.setChangesetIdInRcs(commit.getName());
			}
		}

		return data;
	}

	public static final RcsServerDto newGitServerData(final String url)
	{
		RcsServerDto data = new RcsServerDto();

		data.setUrl(url);
		data.setTypeUri(UacaUriHelper.forRcsServerType("git"));

		return data;
	}

	public static final void setApplicationData(final BaseIdeEventRequest data)
	{
		IdeApplication application = IdeApplication.getInstance();

		data.setSessionId(Integer.toString(application.getPid()));
		data.setAppName(application.getName());
		data.setAppVersion(application.getVersion());
	}

	public static final void setEventData(final BaseIdeEventRequest data, final long time)
	{
		data.setTimestamp(Internals.timeSupplier.from(time));
	}

	public static final void setProjectData(final BaseIdeEventRequest data, final IFile file)
	{
		setProjectData(data, file.getProject());
	}
	public static final void setProjectData(final BaseIdeEventRequest data, final IClassFile file)
	{
		IJavaElement root = file.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT);

		Preconditions.checkState(root != null, "Package fragment root not found");

		setProjectData(data, Workspaces.getName(file.getJavaProject().getProject().getWorkspace()), root.getElementName());
	}

	public static final void setProjectData(final BaseIdeEventRequest data, final IProject project)
	{
		setProjectData(data, Workspaces.getName(project.getWorkspace()), project.getName());
	}

	private static final void setProjectData(final BaseIdeEventRequest data, final String workspace, final String project)
	{
		data.setSolutionName(workspace);
		data.setProjectName(project);
	}
}
