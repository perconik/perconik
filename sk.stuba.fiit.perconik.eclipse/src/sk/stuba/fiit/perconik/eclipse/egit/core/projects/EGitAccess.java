package sk.stuba.fiit.perconik.eclipse.egit.core.projects;

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

  static RepositoryMapping getMapping(final IResource resource) {
    GitProjectData data = getProjectData(resource.getProject());

    return data != null ? data.getRepositoryMapping(resource) : null;
  }

  static Repository getRepository(final IResource resource) {
    RepositoryMapping mapping = getMapping(resource);

    return mapping != null ? mapping.getRepository() : null;
  }
}
