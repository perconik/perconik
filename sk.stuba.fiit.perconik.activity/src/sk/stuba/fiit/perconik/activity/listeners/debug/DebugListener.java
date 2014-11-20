package sk.stuba.fiit.perconik.activity.listeners.debug;

import org.eclipse.debug.core.DebugEvent;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;
import sk.stuba.fiit.perconik.eclipse.debug.core.DebugEventDetail;
import sk.stuba.fiit.perconik.eclipse.debug.core.DebugEventKind;

import static sk.stuba.fiit.perconik.activity.listeners.debug.DebugListener.Action.fromKind;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.debug.core.DebugEventKind.valueOf;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class DebugListener extends CommonEventListener implements DebugEventsListener {
  public DebugListener() {}

  enum Action {
    RESUME,

    SUSPEND,

    CREATE,

    TERMINATE,

    CHANGE,

    OTHER(DebugEventKind.MODEL_SPECIFIC);

    final String name;

    final String path;

    final DebugEventKind kind;

    private Action() {
      this(null);
    }

    private Action(final DebugEventKind kind) {
      this.name = actionName("eclipse", "debug", this);
      this.path = actionPath(this.name);
      this.kind = kind != null ? kind : DebugEventKind.valueOf(this.name());
    }

    static Action fromKind(final DebugEventKind kind) {
      for (Action action: values()) {
        if (action.kind == kind) {
          return action;
        }
      }

      throw new IllegalStateException();
    }
  }

  static Event build(final long time, final Action action, final DebugEvent event) {
    Event data = LocalEvent.of(time, action.name);

    data.put(key("kind"), DebugEventKind.valueOf(event.getKind()).toString().toLowerCase());
    data.put(key("detail"), DebugEventDetail.valueOf(event.getDetail()).toString().toLowerCase());

    data.put(key("isStepStart"), event.isStepStart());
    data.put(key("isEvaluation"), event.isEvaluation());

    data.put(key("application", "data"), identifyObject(event.getData()));

    return data;
  }

  void process(final long time, final DebugEvent event) {
    Action action;

    try {
      action = fromKind(valueOf(event.getKind()));
    } catch (RuntimeException failure) {
      this.log.error(failure, "%s action resolving failure for event kind %s", this, event.getKind());

      return;
    }

    this.send(action.path, build(time, action, event));
  }

  public void handleDebugEvents(final DebugEvent[] events) {
    final long time = currentTime();

    this.execute(new Runnable() {
      public void run() {
        for (DebugEvent event: events) {
          process(time, event);
        }
      }
    });
  }
}
