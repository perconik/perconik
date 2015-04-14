package sk.stuba.fiit.perconik.activity.serializers.ui.selection;
import org.eclipse.jface.viewers.ITreeSelection;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TreeSelectionSerializer extends AbstractSelectionSerializer<ITreeSelection> {
  public TreeSelectionSerializer(final Option ... options) {
    super(options);
  }

  public TreeSelectionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTreeSelection(final StructuredContent content, final ITreeSelection selection) {
    content.put(key("paths"), new TreePathSerializer().serialize(asList(selection.getPaths())));
  }
}
