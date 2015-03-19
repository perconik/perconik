package sk.stuba.fiit.perconik.activity.serializers.git;

import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static com.google.common.collect.Lists.newArrayListWithExpectedSize;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.newStructuredContent;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class RepositorySerializer extends AbstractConfigurableMultiSerializer<Repository> {
  public RepositorySerializer(final Option ... options) {
    super(options);
  }

  public RepositorySerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putRepository(final StructuredContent content, final Repository repository) {
    putRepositoryDirectories(content, repository);
    putRepositoryRemotes(content, repository);
    putRepositoryBranch(content, repository);
    putRepositoryBranches(content, repository);

    content.put(key("state"), repository.getRepositoryState().toString().toLowerCase());

    content.put(key("isBare"), repository.isBare());
  }

  static void putRepositoryDirectories(final StructuredContent content, final Repository repository) {
    try {
      content.put(key("directory", "root"), repository.getWorkTree().toPath());
    } catch (NoWorkTreeException ignore) {}

    content.put(key("directory", "meta"), repository.getDirectory().toPath());
  }

  static void putRepositoryRemotes(final StructuredContent content, final Repository repository) {
    List<Content> remotes = newArrayListWithExpectedSize(16);

    Config configuration = repository.getConfig();

    for (String name: repository.getRemoteNames()) {
      StructuredContent remoteContent = newStructuredContent();

      remoteContent.put(key("name"), name);
      remoteContent.put(key("url"), configuration.getString("remote", name, "url"));

      remotes.add(remoteContent);
    }

    content.put(key("remotes"), remotes);
  }

  static void putRepositoryBranch(final StructuredContent content, final Repository repository) {
    try {
      content.put(key("branch", "short"), repository.getBranch());
      content.put(key("branch", "full"), repository.getFullBranch());
    } catch (IOException ignore) {}
  }

  static void putRepositoryBranches(final StructuredContent content, final Repository repository) {
    try {
      List<Content> branches = newArrayListWithExpectedSize(32);

      for (Ref reference: new Git(repository).branchList().setListMode(ListMode.ALL).call()) {
        StructuredContent branchContent = newStructuredContent();

        branchContent.put(key("name"), reference.getName());
        branchContent.put(key("commit"), reference.getObjectId().getName());

        branches.add(branchContent);
      }

      content.put(key("branches"), branches);
    } catch (GitAPIException ignore) {}
  }

  @Override
  protected void put(final StructuredContent content, final Repository repository) {
    putRepository(content, repository);
  }
}
