package sk.stuba.fiit.perconik.activity.serializers.ui.selection;

import java.util.Set;

import org.eclipse.jface.text.IMarkSelection;

import sk.stuba.fiit.perconik.activity.serializers.ui.text.DocumentSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class MarkSelectionSerializer extends AbstractSelectionSerializer<IMarkSelection> {
  public MarkSelectionSerializer(final Option ... options) {
    super(options);
  }

  public MarkSelectionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putMarkSelection(final StructuredContent content, final IMarkSelection selection, final Set<Option> options) {
    content.put(key("offset"), selection.getOffset());
    content.put(key("length"), selection.getLength());

    if (options.contains(StandardOption.TREE)) {
      content.put(key("document"), new DocumentSerializer(options).serialize(selection.getDocument()));
    } else {
      content.put(key("document"), identifyObject(selection.getDocument()));
    }
  }
}
