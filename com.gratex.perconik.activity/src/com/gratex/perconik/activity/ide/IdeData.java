package com.gratex.perconik.activity.ide;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import com.gratex.perconik.activity.uaca.IdeUacaUris;
import com.gratex.perconik.services.uaca.ide.IdeDocumentData;
import com.gratex.perconik.services.uaca.ide.IdeEventData;
import com.gratex.perconik.services.uaca.ide.IdeRcsServerData;

import sk.stuba.fiit.perconik.eclipse.core.resources.Workspaces;
import sk.stuba.fiit.perconik.eclipse.jdt.core.ClassFiles;
import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitRepositories;

import static com.google.common.base.Preconditions.checkState;

//TODO resolve: not sure why is egit core restricted
@SuppressWarnings("restriction")
public final class IdeData {
  private IdeData() {}

  public static IdeDocumentData newDocumentData(final IFile file) {
    return newDocumentFileData(file);
  }

  public static IdeDocumentData newDocumentData(final IClassFile file) {
    return newDocumentPathData(ClassFiles.path(file));
  }

  private static IdeDocumentData newDocumentPathData(final IPath path) {
    IdeDocumentData data = new IdeDocumentData();

    data.setLocalPath(path.toString());

    return data;
  }

  private static IdeDocumentData newDocumentFileData(final IFile file) {
    IdeDocumentData data = newDocumentPathData(file.getFullPath().makeRelative());

    RepositoryMapping mapping = IdeGitProjects.getMapping(file);

    if (mapping != null) {
      Repository repository = mapping.getRepository();

      if (repository != null) {
        data.setRcsServer(newGitServerData(GitRepositories.getRemoteOriginUrl(repository)));
        data.setBranch(GitRepositories.getShortBranch(repository));
        data.setServerPath(data.getLocalPath());

        RevCommit repositoryCommit = GitRepositories.getMostRecentCommit(repository);

        if (repositoryCommit != null) {
          data.setChangesetIdInRcs(repositoryCommit.getName());
        }

        String path = mapping.getRepoRelativePath(file);

        RevCommit fileCommit = GitRepositories.getMostRecentCommit(repository, path);

        if (fileCommit != null) {
          data.setChangesetIdInRcsOfPath(fileCommit.getName());
        }
      }
    }

    return data;
  }

  public static IdeRcsServerData newGitServerData(final String url) {
    IdeRcsServerData data = new IdeRcsServerData();

    data.setUrl(url);
    data.setTypeUri(IdeUacaUris.forRcsServerType("git"));

    return data;
  }

  public static void setApplicationData(final IdeEventData data) {
    IdeApplication application = IdeApplication.getInstance();

    data.setSessionId(Integer.toString(application.getPid()));
    data.setAppName(application.getName());
    data.setAppVersion(application.getVersion());
  }

  public static void setEventData(final IdeEventData data, final long time) {
    data.setTimestamp(new Date(time));
  }

  public static void setProjectData(final IdeEventData data, final IFile file) {
    setProjectData(data, file.getProject());
  }

  public static void setProjectData(final IdeEventData data, final IClassFile file) {
    IJavaElement root = file.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT);

    checkState(root != null, "Package fragment root not found");

    setProjectData(data, Workspaces.getName(file.getJavaProject().getProject().getWorkspace()), root.getElementName());
  }

  public static void setProjectData(final IdeEventData data, final IProject project) {
    setProjectData(data, Workspaces.getName(project.getWorkspace()), project.getName());
  }

  private static void setProjectData(final IdeEventData data, final String workspace, final String project) {
    data.setSolutionName(workspace);
    data.setProjectName(project);
  }
}
