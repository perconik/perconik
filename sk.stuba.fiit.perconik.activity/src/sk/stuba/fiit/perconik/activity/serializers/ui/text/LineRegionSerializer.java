package sk.stuba.fiit.perconik.activity.serializers.ui.text;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class LineRegionSerializer extends AbstractConfigurableSerializer<LineRegion> {
  public LineRegionSerializer(final Option ... options) {
    super(options);
  }

  public LineRegionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putLineRegion(final StructuredContent content, final LineRegion region) {
    content.put(key("start", "length"), region.start.line);
    content.put(key("start", "offset"), region.start.offset);

    content.put(key("end", "length"), region.end.line);
    content.put(key("end", "offset"), region.end.offset);

    content.put(key("text"), region.text);
  }

  @Override
  protected void put(final StructuredContent content, final LineRegion region) {
    putLineRegion(content, region);
  }
}
