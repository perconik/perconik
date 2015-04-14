package sk.stuba.fiit.perconik.activity.serializers.ui.selection;

import org.eclipse.jface.viewers.IStructuredSelection;

import sk.stuba.fiit.perconik.activity.serializers.ObjectDescriptionSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class StructuredSelectionSerializer extends AbstractSelectionSerializer<IStructuredSelection> {
  public StructuredSelectionSerializer(final Option ... options) {
    super(options);
  }

  public StructuredSelectionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putStructuredSelection(final StructuredContent content, final IStructuredSelection selection) {
    content.put(key("elements"), new ObjectDescriptionSerializer().serialize(selection.toList()));
  }
}
