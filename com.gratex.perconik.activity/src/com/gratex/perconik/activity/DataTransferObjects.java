package com.gratex.perconik.activity;

import java.util.LinkedList;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import sk.stuba.fiit.perconik.eclipse.core.resources.Workspaces;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementType;
import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitRepositories;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.gratex.perconik.services.vs.EventDto;
import com.gratex.perconik.services.vs.IdeDocumentDto;
import com.gratex.perconik.services.vs.IdeEventDto;
import com.gratex.perconik.services.vs.IdePathTypeEnum;
import com.gratex.perconik.services.vs.IdeSlnPrjEventDto;
import com.gratex.perconik.services.vs.RcsServerDto;

public final class DataTransferObjects
{
	private DataTransferObjects()
	{
		throw new AssertionError();
	}
	
	public static final IdeDocumentDto newDocumentData(final IFile file)
	{
		return newDocumentData(file.getFullPath(), file.getProject());
	}
	
	public static final IdeDocumentDto newDocumentData(final IClassFile file)
	{
		LinkedList<String> segments = Lists.newLinkedList();
		
		IJavaElement element = file;
		
		do
		{
			JavaElementType type = JavaElementType.valueOf(element);
			
			if (type == JavaElementType.PACKAGE_FRAGMENT_ROOT) break;
			
			String segment = element.getElementName();
			
			if (type == JavaElementType.PACKAGE_FRAGMENT)
			{
				segment = segment.replace('.', '/');
			}
			
			segments.addFirst(segment);
		}
		while ((element = element.getParent()) != null);
		
		IdeDocumentDto data = new IdeDocumentDto();
		
		data.setPath(Joiner.on('/').join(segments));
		data.setPathType(IdePathTypeEnum.RELATIVE_LOCAL);
		
		return data;
	}
	
	private static final IdeDocumentDto newDocumentData(final IPath path)
	{
		IdeDocumentDto data = new IdeDocumentDto();
		
		data.setPath(path.makeRelative().toString());
		data.setPathType(IdePathTypeEnum.RELATIVE_LOCAL);

		return data;
	}
	
	private static final IdeDocumentDto newDocumentData(final IPath path, final IProject project)
	{
		IdeDocumentDto data = newDocumentData(path);

		Repository repository = GitRepositories.fromProject(project);
		
		if (repository != null)
		{
			data.setRcsServer(newGitServerData(GitRepositories.getRemoteOriginUrl(repository)));
			data.setBranchName(GitRepositories.getBranch(repository));
			
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
	
	public static final void setProjectData(final IdeSlnPrjEventDto data, final IFile file)
	{
		setProjectData(data, file.getProject());
	}

	public static final void setProjectData(final IdeSlnPrjEventDto data, final IClassFile file)
	{
		IJavaElement root = file.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT);
		
		Preconditions.checkState(root != null, "Package fragment root not found");
		
		setProjectData(data, file.getJavaProject().getElementName(), root.getElementName());
	}

	public static final void setProjectData(final IdeSlnPrjEventDto data, final IProject project)
	{
		setProjectData(data, Workspaces.getName(project.getWorkspace()), project.getName());
	}

	private static final void setProjectData(final IdeSlnPrjEventDto data, final String workspace, final String project)
	{
		data.setSolutionName(workspace);
		data.setProjectName(project);
	}
}
