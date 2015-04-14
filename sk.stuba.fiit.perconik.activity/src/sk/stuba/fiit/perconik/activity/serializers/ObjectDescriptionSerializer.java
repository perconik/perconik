package sk.stuba.fiit.perconik.activity.serializers;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectDescription;

public final class ObjectDescriptionSerializer extends AbstractMultiSerializer<Object> {
  public ObjectDescriptionSerializer() {}

  @Override
  protected void put(final StructuredContent content, final Object object) {
    putObjectDescription(content, object);
  }
}
