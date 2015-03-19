package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IPerspectiveDescriptor;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class PerspectiveDescriptorSerializer extends AbstractConfigurableMultiSerializer<IPerspectiveDescriptor> {
  public PerspectiveDescriptorSerializer(final Option ... options) {
    super(options);
  }

  public PerspectiveDescriptorSerializer(final Iterable<Option> options) {
    super(options);
  }

  private static void putPerspective(final StructuredContent content, final IPerspectiveDescriptor descriptor) {
    content.put(key("identifier"), descriptor.getId());
    content.put(key("description"), descriptor.getDescription());
    content.put(key("label"), descriptor.getLabel());
  }

  @Override
  protected void put(final StructuredContent content, final IPerspectiveDescriptor descriptor) {
    putObjectIdentity(content, descriptor);
    putPerspective(content, descriptor);
  }
}
