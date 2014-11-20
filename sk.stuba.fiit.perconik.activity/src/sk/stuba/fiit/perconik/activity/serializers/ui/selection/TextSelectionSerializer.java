package sk.stuba.fiit.perconik.activity.serializers.ui.selection;

import java.util.Set;

import org.eclipse.jface.text.IBlockTextSelection;
import org.eclipse.jface.text.ITextSelection;

import sk.stuba.fiit.perconik.activity.serializers.ui.text.RegionSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TextSelectionSerializer extends AbstractSelectionSerializer<ITextSelection> {
  public TextSelectionSerializer(final Option ... options) {
    super(options);
  }

  public TextSelectionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTextSelection(final StructuredContent content, final ITextSelection selection) {
    content.put(key("offset"), selection.getOffset());
    content.put(key("length"), selection.getLength());

    content.put(key("row", "start"), selection.getStartLine());
    content.put(key("row", "end"), selection.getEndLine());

    content.put(key("text"), selection.getText());
  }

  static void putBlockTextSelection(final StructuredContent content, final IBlockTextSelection selection, final Set<Option> options) {
    content.put(key("column", "start"), selection.getStartColumn());
    content.put(key("column", "end"), selection.getEndColumn());

    content.put(key("regions"), new RegionSerializer(options).serialize(asList(selection.getRegions())));
  }
}
