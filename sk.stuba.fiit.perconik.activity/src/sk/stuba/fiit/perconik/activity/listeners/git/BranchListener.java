package sk.stuba.fiit.perconik.activity.listeners.git;

import java.util.List;
import java.util.Set;

import org.eclipse.jgit.lib.Repository;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.git.RepositorySerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static com.google.common.collect.Sets.difference;
import static com.google.common.collect.Sets.newHashSet;

import static sk.stuba.fiit.perconik.activity.listeners.git.BranchListener.Action.CREATE;
import static sk.stuba.fiit.perconik.activity.listeners.git.BranchListener.Action.DELETE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.newEmptyListSuitableFor;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.jgit.lib.GitReferences.toNames;
import static sk.stuba.fiit.perconik.eclipse.jgit.lib.GitRepositories.getBranches;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class BranchListener extends AbstractReferenceListener {
  private final RepositorySetCache<Set<String>> cache;

  public BranchListener() {
    this.cache = new RepositorySetCache<>();
  }

  @Override
  void loadRepository(final Repository repository) {
    this.cache.update(repository, newHashSet(toNames(getBranches(repository))));
  }

  enum Action implements CommonEventListener.Action {
    CREATE,

    DELETE;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "git", "branch", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Iterable<Event> build(final long time, final Action action, final Repository repository, final Set<String> branches) {
    StructuredContent content = new RepositorySerializer().serialize(repository);

    List<Event> collection = newEmptyListSuitableFor(branches);

    for (String branch: branches) {
      Event data = LocalEvent.of(time, action.getName());

      data.put(key("repository"), content);
      data.put(key("branch", "full"), branch);

      collection.add(data);
    }

    return collection;
  }

  @Override
  void process(final long time, final Repository repository) {
    Set<String> after = newHashSet(toNames(getBranches(repository)));
    Set<String> before = this.cache.update(repository, after);

    if (before != null) {
      this.send(CREATE.getPath(), build(time, CREATE, repository, difference(after, before)));
      this.send(DELETE.getPath(), build(time, DELETE, repository, difference(before, after)));
    }
  }
}
