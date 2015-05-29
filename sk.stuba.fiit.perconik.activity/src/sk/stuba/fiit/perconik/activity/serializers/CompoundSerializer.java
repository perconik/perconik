package sk.stuba.fiit.perconik.activity.serializers;

import com.google.common.collect.ImmutableList;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

final class CompoundSerializer<T> extends AbstractMultiSerializer<T> {
  ImmutableList<AbstractSerializer<? super T>> serializers;

  CompoundSerializer(final Iterable<? extends AbstractSerializer<? super T>> serializers) {
    this.serializers = ImmutableList.copyOf(serializers);
  }

  @Override
  protected void put(final StructuredContent content, final T object) {
    for (AbstractSerializer<? super T> serializer: this.serializers) {
      serializer.put(content, object);
    }
  }
}
