package sk.stuba.fiit.perconik.activity.serializers.git;

import org.eclipse.core.resources.IResource;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitRepositories;

import static sk.stuba.fiit.perconik.activity.serializers.git.RepositorySerializer.putRepositoryBranch;
import static sk.stuba.fiit.perconik.activity.serializers.git.RepositorySerializer.putRepositoryDirectories;
import static sk.stuba.fiit.perconik.activity.serializers.git.RepositorySerializer.putRepositoryRemotes;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class MappedResourceSerializer extends AbstractConfigurableSerializer<IResource> {
  public MappedResourceSerializer(final Option ... options) {
    super(options);
  }

  public MappedResourceSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putMappedResource(final StructuredContent content, final IResource resource) {
    Repository repository = EGitAccess.getRepository(resource);

    if (repository != null) {
      putRepositoryDirectories(content, repository);
      putRepositoryRemotes(content, repository);
      putRepositoryBranch(content, repository);

      String path = EGitAccess.getRelativePath(resource);

      if (path != null) {
        RevCommit commit = GitRepositories.getMostRecentCommit(repository, path);

        content.put(key("mostRecentCommit", "name"), commit != null ? commit.getName() : null);
      }
    }
  }

  @Override
  protected void put(final StructuredContent content, final IResource resource) {
    putMappedResource(content, resource);
  }
}
