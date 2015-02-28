package sk.stuba.fiit.perconik.activity.listeners.command;

import com.google.common.base.Optional;

import org.eclipse.core.commands.operations.OperationHistoryEvent;

import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.eclipse.core.commands.operations.OperationHistoryEventType;

import static java.util.Objects.requireNonNull;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import static sk.stuba.fiit.perconik.activity.listeners.command.UndoableOperationListener.Action.fromType;
import static sk.stuba.fiit.perconik.eclipse.core.commands.operations.OperationHistoryEventType.DONE;
import static sk.stuba.fiit.perconik.eclipse.core.commands.operations.OperationHistoryEventType.REDONE;
import static sk.stuba.fiit.perconik.eclipse.core.commands.operations.OperationHistoryEventType.UNDONE;
import static sk.stuba.fiit.perconik.eclipse.core.commands.operations.OperationHistoryEventType.valueOf;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
@Unsupported
public final class UndoableOperationListener extends AbstractUndoableListener {
  public UndoableOperationListener() {}

  enum Action implements CommonEventListener.Action {
    EXECUTE(DONE),

    UNDO(UNDONE),

    REDO(REDONE);

    private final String name;

    private final String path;

    private final OperationHistoryEventType type;

    private Action(final OperationHistoryEventType type) {
      this.name = actionName("eclipse", "command", "operation", this);
      this.path = actionPath(this.name);
      this.type = requireNonNull(type);
    }

    static Optional<Action> fromType(final OperationHistoryEventType type) {
      for (Action action: values()) {
        if (action.type == type) {
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

  @Override
  Optional<Action> resolve(final OperationHistoryEvent event) {
    return fromType(valueOf(event.getEventType()));
  }
}
