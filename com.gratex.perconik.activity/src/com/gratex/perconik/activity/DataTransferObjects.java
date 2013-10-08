package com.gratex.perconik.activity;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import sk.stuba.fiit.perconik.eclipse.core.resources.Workspaces;
import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitRepositories;
import com.gratex.perconik.services.activity.EventDto;
import com.gratex.perconik.services.activity.IdeDocumentDto;
import com.gratex.perconik.services.activity.IdeEventDto;
import com.gratex.perconik.services.activity.IdePathTypeEnum;
import com.gratex.perconik.services.activity.IdeSlnPrjEventDto;
import com.gratex.perconik.services.activity.RcsServerDto;

public final class DataTransferObjects
{
	private DataTransferObjects()
	{
		throw new AssertionError();
	}
	
	public static final IdeDocumentDto newDocumentData(final IFile file)
	{
		IdeDocumentDto data = new IdeDocumentDto();
		
		String path = file.getFullPath().makeRelative().toString();
		
		data.setPath(path);
		data.setPathType(IdePathTypeEnum.RELATIVE_LOCAL);
		
		Repository repository = GitRepositories.fromProject(file.getProject());
		
		if (repository != null)
		{
			data.setRcsServer(newGitServerData(GitRepositories.getRemoteOriginUrl(repository)));
			data.setBranchName(GitRepositories.getBranch(repository));
			
			RevCommit commit = GitRepositories.getMostRecentCommit(repository, path);
			
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
		
		data.setPath(url);
		data.setType("git");
		
		return data;
	}
	
	public static final void setEventData(final EventDto data)
	{
		data.setIsMilestone(Internals.milestoneResolver().apply(data));
		data.setTime(Internals.timeSupplier().get());
	}

	public static final void setApplicationData(final IdeEventDto data)
	{
		Application application = Application.getInstance();
		
		data.setIdePid(application.getPid());
		data.setApplicationName(application.getName());
		data.setApplicationVersion(application.getVersion());
	}
	
	public static final void setProjectData(final IdeSlnPrjEventDto data, final IProject project)
	{
		data.setProjectName(project.getName());
		data.setSolutionName(Workspaces.getName(project.getWorkspace()));
	}
}
