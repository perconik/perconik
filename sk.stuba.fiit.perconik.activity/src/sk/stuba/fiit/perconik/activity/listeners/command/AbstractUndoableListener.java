package sk.stuba.fiit.perconik.activity.listeners.command;

import org.eclipse.core.commands.operations.OperationHistoryEvent;

import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
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
abstract class AbstractUndoableListener extends CommonEventListener implements OperationHistoryListener {
  AbstractUndoableListener() {}

  static final void put(final StructuredContent content, final OperationHistoryEvent event) {
    content.put(key("type"), OperationHistoryEventType.valueOf(event.getEventType()).toString().toLowerCase());
    content.put(key("status"), new StatusSerializer(TREE).serialize(event.getStatus()));
    content.put(key("operation"), new UndoableOperationSerializer(TREE).serialize(event.getOperation()));
  }

  abstract void process(final long time, final OperationHistoryEvent event);

  public final void historyNotification(final OperationHistoryEvent event) {
    final long time = currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, event);
      }
    });
  }
}
