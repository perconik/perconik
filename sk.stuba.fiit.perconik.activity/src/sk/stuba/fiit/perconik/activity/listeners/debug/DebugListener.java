package sk.stuba.fiit.perconik.activity.listeners.debug;

import com.google.common.base.Optional;

import org.eclipse.debug.core.DebugEvent;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;
import sk.stuba.fiit.perconik.eclipse.debug.core.DebugEventDetail;
import sk.stuba.fiit.perconik.eclipse.debug.core.DebugEventKind;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import static sk.stuba.fiit.perconik.activity.listeners.debug.DebugListener.Action.fromKind;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.describeObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.debug.core.DebugEventKind.valueOf;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.3.alpha")
public final class DebugListener extends ActivityListener implements DebugEventsListener {
  public DebugListener() {}

  enum Action implements ActivityListener.Action {
    CREATE,

    SUSPEND,

    RESUME,

    CHANGE,

    TERMINATE,

    OTHER(DebugEventKind.MODEL_SPECIFIC);

    private final String name;

    private final String path;

    private final DebugEventKind kind;

    private Action() {
      this(null);
    }

    private Action(final DebugEventKind kind) {
      this.name = actionName("eclipse", "debug", this);
      this.path = actionPath(this.name);
      this.kind = kind != null ? kind : DebugEventKind.valueOf(this.name());
    }

    static Optional<Action> fromKind(final DebugEventKind kind) {
      for (Action action: values()) {
        if (action.kind == kind) {
          return of(action);
        }
      }

      return absent();
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action, final DebugEvent event) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("debug", "kind"), DebugEventKind.valueOf(event.getKind()).toString().toLowerCase());
    data.put(key("debug", "detail"), DebugEventDetail.valueOf(event.getDetail()).toString().toLowerCase());

    data.put(key("debug", "isStepStart"), event.isStepStart());
    data.put(key("debug", "isEvaluation"), event.isEvaluation());

    data.put(key("debug", "data"), describeObject(event.getData()));

    return data;
  }

  void process(final long time, final DebugEvent event) {
    Optional<Action> option = fromKind(valueOf(event.getKind()));

    if (!option.isPresent()) {
      this.log.error("%s: unable to resolve action for event kind %s", this, event.getKind());
    }

    Action action = option.get();

    this.send(action.getPath(), build(time, action, event));
  }

  public void handleDebugEvents(final DebugEvent[] events) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        for (DebugEvent event: events) {
          process(time, event);
        }
      }
    });
  }
}
