package sk.stuba.fiit.perconik.activity.serializers.ui.selection;

import org.eclipse.jface.viewers.ISelection;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class SelectionSerializer extends AbstractSelectionSerializer<ISelection> {
  public SelectionSerializer(final Option ... options) {
    super(options);
  }

  public SelectionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putSelection(final StructuredContent content, final ISelection selection) {
    content.put(key("isEmpty"), selection.isEmpty());
  }
}
