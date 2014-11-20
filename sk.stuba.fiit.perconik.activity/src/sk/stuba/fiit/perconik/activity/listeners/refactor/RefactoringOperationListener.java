package sk.stuba.fiit.perconik.activity.listeners.refactor;

import com.google.common.base.Optional;

import org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.RefactoringExecutionListener;
import sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringEventProxy;
import sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringEventType;
import sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringExecutionEventType;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.activity.listeners.refactor.RefactoringOperationListener.Action.fromType;
import static sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringExecutionEventType.PERFORMED;
import static sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringExecutionEventType.REDONE;
import static sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringExecutionEventType.UNDONE;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class RefactoringOperationListener extends AbstractRefactoringListener implements RefactoringExecutionListener {
  public RefactoringOperationListener() {}

  enum Action {
    EXECUTE(PERFORMED),

    UNDO(UNDONE),

    REDO(REDONE);

    final String name;

    final String path;

    RefactoringExecutionEventType type;

    private Action(final RefactoringExecutionEventType type) {
      this.name = actionName("eclipse", "refactor", "operation", this);
      this.path = actionPath(this.name);
      this.type = requireNonNull(type);
    }

    static Optional<Action> fromType(final RefactoringEventType type) {
      for (Action action: values()) {
        if (action.type == type) {
          return Optional.of(action);
        }
      }

      return Optional.absent();
    }
  }

  static Event build(final long time, final Action action, final RefactoringEventProxy<?> event) {
    Event data = LocalEvent.of(time, action.name);

    put(data, event);

    return data;
  }

  @Override
  void process(final long time, final RefactoringEventProxy<?> event) {
    Optional<Action> action = fromType(event.getType());

    if (action.isPresent()) {
      this.send(action.get().path, build(time, action.get(), event));
    }
  }

  public void executionNotification(final RefactoringExecutionEvent event) {
    final long time = currentTime();

    this.execute(time, RefactoringEventProxy.wrap(event));
  }
}
