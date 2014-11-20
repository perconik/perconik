package sk.stuba.fiit.perconik.activity.serializers.ui.text;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class RegionSerializer extends AbstractConfigurableSerializer<IRegion> {
  public RegionSerializer(final Option ... options) {
    super(options);
  }

  public RegionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putRegion(final StructuredContent content, final IRegion region) {
    content.put(key("offset"), region.getOffset());
    content.put(key("length"), region.getLength());

    if (region instanceof ITypedRegion) {
      content.put(key("content", "type"), ((ITypedRegion) region).getType());
    }
  }

  @Override
  protected void put(final StructuredContent content, final IRegion region) {
    putRegion(content, region);
  }
}
