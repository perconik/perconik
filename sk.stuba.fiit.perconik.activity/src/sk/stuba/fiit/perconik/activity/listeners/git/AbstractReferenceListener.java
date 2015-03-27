package sk.stuba.fiit.perconik.activity.listeners.git;

import org.eclipse.jgit.events.RefsChangedEvent;
import org.eclipse.jgit.lib.Repository;

import sk.stuba.fiit.perconik.activity.listeners.ActivityEventListener;
import sk.stuba.fiit.perconik.core.listeners.GitReferenceListener;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractEventListener.RegistrationHook.POST_REGISTER;
import static sk.stuba.fiit.perconik.eclipse.core.resources.Workspaces.getWorkspace;
import static sk.stuba.fiit.perconik.eclipse.egit.core.projects.GitProjects.fromWorkspace;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractReferenceListener extends ActivityEventListener implements GitReferenceListener {
  AbstractReferenceListener() {
    POST_REGISTER.add(this, new NamedRunnable(this.getClass(), "RepositoryLoader") {
      public void run() {
        loadRepositories();
      }
    });
  }

  final void loadRepositories() {
    for (Repository repository: fromWorkspace(getWorkspace()).values()) {
      try {
        this.loadRepository(repository);
      } catch (RuntimeException failure) {
        this.log.error(failure, "%s: cache initialization failure while reading %s", this, repository);
      }
    }
  }

  abstract void loadRepository(Repository repository);

  abstract void process(long time, Repository repository);

  public final void onRefsChanged(final RefsChangedEvent event) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, event.getRepository());
      }
    });
  }
}
