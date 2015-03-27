package sk.stuba.fiit.perconik.activity.listeners.command;

import com.google.common.base.Optional;

import org.eclipse.core.commands.operations.OperationHistoryEvent;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.command.UndoableOperationSerializer;
import sk.stuba.fiit.perconik.activity.serializers.runtime.StatusSerializer;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.core.commands.operations.OperationHistoryEventType;

import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractUndoableListener extends ActivityListener implements OperationHistoryListener {
  AbstractUndoableListener() {}

  static final void put(final StructuredContent content, final OperationHistoryEvent event) {
    content.put(key("type"), OperationHistoryEventType.valueOf(event.getEventType()).toString().toLowerCase());
    content.put(key("status"), new StatusSerializer(TREE).serialize(event.getStatus()));
    content.put(key("operation"), new UndoableOperationSerializer(TREE).serialize(event.getOperation()));
  }

  static final Event build(final long time, final Action action, final OperationHistoryEvent event) {
    Event data = LocalEvent.of(time, action.getName());

    put(data, event);

    return data;
  }

  abstract Optional<? extends Action> resolve(OperationHistoryEvent event);

  final void process(final long time, final OperationHistoryEvent event) {
    Optional<? extends Action> option = this.resolve(event);

    if (option.isPresent()) {
      Action action = option.get();

      this.send(action.getPath(), build(time, action, event));
    }
  }

  public final void historyNotification(final OperationHistoryEvent event) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, event);
      }
    });
  }
}
