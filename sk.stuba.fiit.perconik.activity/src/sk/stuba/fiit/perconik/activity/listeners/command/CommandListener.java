package sk.stuba.fiit.perconik.activity.listeners.command;

import javax.annotation.Nullable;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;

import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ObjectDescriptionSerializer;
import sk.stuba.fiit.perconik.activity.serializers.command.CommandSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.data.events.Event;
import sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState;

import static sk.stuba.fiit.perconik.activity.listeners.command.CommandListener.Action.EXECUTE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.describeObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.DISABLED;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.EXECUTING;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.FAILED;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.SUCCEEDED;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.UNDEFINED;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.UNHANDLED;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.4.alpha")
public final class CommandListener extends ActivityListener implements CommandExecutionListener {
  public CommandListener() {}

  enum Action implements ActivityListener.Action {
    EXECUTE;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "command", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action, final String identifier, final CommandExecutionState state) {
    assert !identifier.isEmpty();

    Event event = LocalEvent.of(time, action.getName());

    event.put(key("command", "identifier"), identifier);

    event.put(key("execution", "state"), state.toString().toLowerCase());

    return event;
  }

  static Event build(final long time, final Action action, final String identifier, final CommandExecutionState state, final ExecutionEvent execution) {
    assert identifier.equals(execution.getCommand().getId());

    Event data = build(time, action, identifier, state);

    data.put(key("execution", "parameters"), new ObjectDescriptionSerializer().serialize(execution.getParameters()));
    data.put(key("execution", "trigger"), describeObject(execution.getTrigger()));

    data.put(key("command"), new CommandSerializer().serialize(execution.getCommand()));

    return data;
  }

  static Event build(final long time, final Action action, final String identifier, final CommandExecutionState state, @Nullable final Object result) {
    Event data = build(time, action, identifier, state);

    data.put(key("execution", "result"), describeObject(result));

    return data;
  }

  void process(final long time, final Action action, final String identifier, final CommandExecutionState state) {
    this.send(action.getPath(), build(time, action, identifier, state));
  }

  void process(final long time, final Action action, final String identifier, final CommandExecutionState state, final ExecutionEvent execution) {
    this.send(action.getPath(), build(time, action, identifier, state, execution));
  }

  void process(final long time, final Action action, final String identifier, final CommandExecutionState state, @Nullable final Object result) {
    this.send(action.getPath(), build(time, action, identifier, state, result));
  }

  public void notDefined(final String identifier, final NotDefinedException exception) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, EXECUTE, identifier, UNDEFINED);
      }
    });
  }

  public void notEnabled(final String identifier, final NotEnabledException exception) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, EXECUTE, identifier, DISABLED);
      }
    });
  }

  public void notHandled(final String identifier, final NotHandledException exception) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, EXECUTE, identifier, UNHANDLED);
      }
    });
  }

  public void preExecute(final String identifier, final ExecutionEvent event) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, EXECUTE, identifier, EXECUTING, event);
      }
    });
  }

  public void postExecuteSuccess(final String identifier, final Object result) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, EXECUTE, identifier, SUCCEEDED, result);
      }
    });
  }

  public void postExecuteFailure(final String identifier, final ExecutionException exception) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, EXECUTE, identifier, FAILED);
      }
    });
  }
}
