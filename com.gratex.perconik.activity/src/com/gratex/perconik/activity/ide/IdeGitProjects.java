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

import static com.google.common.base.Throwables.propagate;

//TODO resolve: not sure why is egit core restricted
@SuppressWarnings("restriction")
public final class IdeGitProjects {
  private IdeGitProjects() {}

  public static GitProjectData getProjectData(final IProject project) {
    return GitProjectData.get(project);
  }

  public static RepositoryMapping getMapping(final IResource resource) {
    GitProjectData data = getProjectData(resource.getProject());

    return data != null ? data.getRepositoryMapping(resource) : null;
  }

  public static Repository getRepository(final IResource resource) {
    RepositoryMapping mapping = getMapping(resource);

    return mapping != null ? mapping.getRepository() : null;
  }

  public static boolean isIgnored(final IPath path) throws IOException {
    // TODO find better way to resolve this dependency:
    // RepositoryUtil.isIgnored(path) requires egit 2.3, but eclipse 3.8 has only egit 2.2
    //return RepositoryUtil.isIgnored(path);

    // TODO resolve: code copied from egit 3.4
    RepositoryMapping mapping = RepositoryMapping.getMapping(path);
    if (mapping == null) {
      return true; // Linked resources may not be mapped
    }

    Repository repository = mapping.getRepository();
    String repoRelativePath = mapping.getRepoRelativePath(path);
    TreeWalk walk = new TreeWalk(repository);

    try {
      walk.addTree(new FileTreeIterator(repository));
      walk.setFilter(PathFilter.create(repoRelativePath));

      while (walk.next()) {
        WorkingTreeIterator workingTreeIterator = walk.getTree(0, WorkingTreeIterator.class);

        if (walk.getPathString().equals(repoRelativePath)) {
          return workingTreeIterator.isEntryIgnored();
        }

        if (workingTreeIterator.getEntryFileMode().equals(FileMode.TREE)) {
          walk.enterSubtree();
        }
      }
    } finally {
      // TODO resolve: jgit 2.2.0 (and its successors up to Luna) have TreeWalk.release()
      // but jgit 4.0.0 (as of Mars) replaces it with TreeWalk.close()
      try {
        try {
          walk.getClass().getDeclaredMethod("release").invoke(walk);
        } catch (NoSuchMethodException e) {
          walk.getClass().getDeclaredMethod("close").invoke(walk);
        }
      } catch (Exception e) {
        propagate(e);
      }
    }

    return false;
  }

  public static boolean isMapped(final IPath path) {
    return RepositoryMapping.getMapping(path) != null;
  }
}
