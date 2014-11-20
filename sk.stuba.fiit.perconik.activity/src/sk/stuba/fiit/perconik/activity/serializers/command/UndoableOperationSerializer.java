package sk.stuba.fiit.perconik.activity.serializers.command;

import java.util.Set;

import org.eclipse.core.commands.operations.IUndoableOperation;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class UndoableOperationSerializer extends AbstractConfigurableSerializer<IUndoableOperation> {
  public UndoableOperationSerializer(final Option ... options) {
    super(options);
  }

  public UndoableOperationSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putUndoableOperation(final StructuredContent content, final IUndoableOperation operation, final Set<Option> options) {
    content.put(key("label"), operation.getLabel());

    content.put(key("contexts"), new UndoContextSerializer(options).serialize(asList(operation.getContexts())));

    content.put(key("canExecute"), operation.canExecute());
    content.put(key("canRedo"), operation.canRedo());
    content.put(key("canUndo"), operation.canUndo());
  }

  @Override
  protected void put(final StructuredContent content, final IUndoableOperation operation) {
    putObjectIdentity(content, operation);
    putUndoableOperation(content, operation, this.options);
  }
}
