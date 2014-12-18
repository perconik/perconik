package sk.stuba.fiit.perconik.eclipse.egit.core.projects;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.jgit.lib.Repository;

import static com.google.common.collect.Maps.newHashMap;

public final class GitProjects {
  private GitProjects() {}

  public static Map<IProject, Repository> fromWorkspace(final IWorkspace workspace) {
    Map<IProject, Repository> projects = newHashMap();

    for (IProject project: workspace.getRoot().getProjects()) {
      Repository repository = EGitAccess.getRepository(project);

      if (repository != null) {
        projects.put(project, repository);
      }
    }

    return projects;
  }
}
