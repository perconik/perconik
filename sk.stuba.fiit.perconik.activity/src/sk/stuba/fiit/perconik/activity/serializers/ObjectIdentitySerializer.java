package sk.stuba.fiit.perconik.activity.serializers;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;

public final class ObjectIdentitySerializer extends AbstractMultiSerializer<Object> {
  public ObjectIdentitySerializer() {}

  @Override
  protected void put(final StructuredContent content, final Object object) {
    putObjectIdentity(content, object);
  }
}
