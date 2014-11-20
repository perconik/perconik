package sk.stuba.fiit.perconik.activity.listeners.git;

import org.eclipse.jgit.events.RefsChangedEvent;
import org.eclipse.jgit.lib.Repository;

import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.listeners.GitReferenceListener;

import static sk.stuba.fiit.perconik.eclipse.core.resources.Workspaces.getWorkspace;
import static sk.stuba.fiit.perconik.eclipse.egit.core.projects.GitProjects.fromWorkspace;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractReferenceListener extends CommonEventListener implements GitReferenceListener {
  AbstractReferenceListener() {}

  @Override
  public final void postRegister() {
    super.postRegister();

    for (Repository repository: fromWorkspace(getWorkspace()).values()) {
      try {
        this.postRegisterRepository(repository);
      } catch (RuntimeException failure) {
        this.log.error(failure, "%s cache initialization failure while reading %s", this, repository);
      }
    }
  }

  abstract void postRegisterRepository(Repository repository);

  abstract void process(final long time, final Repository repository);

  public final void onRefsChanged(final RefsChangedEvent event) {
    final long time = currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, event.getRepository());
      }
    });
  }
}
