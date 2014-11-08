package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.egit.core.project.GitProjectData;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jgit.lib.Repository;

//TODO resolve why EGit Core is restricted
@SuppressWarnings("restriction")
final class EGitAccess {
  private EGitAccess() {}

  static GitProjectData getProjectData(final IProject project) {
    return GitProjectData.get(project);
  }

  static Repository getRepository(final IResource resource) {
    RepositoryMapping mapping = getRepositoryMapping(resource);

    return mapping != null ? mapping.getRepository() : null;
  }

  static RepositoryMapping getRepositoryMapping(final IResource resource) {
    GitProjectData data = getProjectData(resource.getProject());

    return data != null ? data.getRepositoryMapping(resource) : null;
  }

  static String getRepositoryRelativePath(final IResource resource) {
    RepositoryMapping mapping = getRepositoryMapping(resource);

    return mapping != null ? mapping.getRepoRelativePath(resource) : null;
  }
}
