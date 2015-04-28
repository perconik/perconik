package sk.stuba.fiit.perconik.activity.serializers.ui.selection;

import java.util.Set;

import org.eclipse.jface.text.IBlockTextSelection;

import sk.stuba.fiit.perconik.activity.serializers.ui.text.RegionSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class BlockTextSelectionSerializer extends AbstractSelectionSerializer<IBlockTextSelection> {
  public BlockTextSelectionSerializer(final Option ... options) {
    super(options);
  }

  public BlockTextSelectionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putBlockTextSelection(final StructuredContent content, final IBlockTextSelection selection, final Set<Option> options) {
    content.put(key("column", "start"), selection.getStartColumn());
    content.put(key("column", "end"), selection.getEndColumn());

    content.put(key("regions"), new RegionSerializer(options).serialize(asList(selection.getRegions())));
  }
}
