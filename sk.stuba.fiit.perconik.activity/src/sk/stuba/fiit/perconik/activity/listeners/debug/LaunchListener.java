package sk.stuba.fiit.perconik.activity.listeners.debug;

import java.util.List;

import org.eclipse.debug.core.ILaunch;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.debug.LaunchSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.listeners.debug.LaunchListener.Action.ADD;
import static sk.stuba.fiit.perconik.activity.listeners.debug.LaunchListener.Action.CHANGE;
import static sk.stuba.fiit.perconik.activity.listeners.debug.LaunchListener.Action.REMOVE;
import static sk.stuba.fiit.perconik.activity.listeners.debug.LaunchListener.Action.TERMINATE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class LaunchListener extends CommonEventListener implements LaunchesListener {
  public LaunchListener() {}

  enum Action {
    ADD,

    REMOVE,

    CHANGE,

    TERMINATE;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "launch", this);
      this.path = actionPath(this.name);
    }
  }

  static Event build(final long time, final Action action, final List<ILaunch> launches) {
    Event data = LocalEvent.of(time, action.name);

    data.put(key("launches"), new LaunchSerializer().serialize(launches));

    return data;
  }

  void process(final long time, final Action action, final List<ILaunch> launches) {
    this.send(action.path, build(time, action, launches));
  }

  void execute(final long time, final Action action, final List<ILaunch> launches) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, launches);
      }
    });
  }

  public void launchesAdded(final ILaunch[] launches) {
    final long time = currentTime();

    this.execute(time, ADD, asList(launches));
  }

  public void launchesRemoved(final ILaunch[] launches) {
    final long time = currentTime();

    this.execute(time, REMOVE, asList(launches));
  }

  public void launchesChanged(final ILaunch[] launches) {
    final long time = currentTime();

    this.execute(time, CHANGE, asList(launches));
  }

  public void launchesTerminated(final ILaunch[] launches) {
    final long time = currentTime();

    this.execute(time, TERMINATE, asList(launches));
  }
}
