package sk.stuba.fiit.perconik.activity.serializers;

import java.util.Arrays;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

public final class Serializers {
  private Serializers() {}

  public static <T> DisplayTask<Content> asDisplayTask(final Serializer<? super T> serializer, @Nullable final T object) {
    return new DisplayTask<Content>() {
      public Content call() throws Exception {
        return serializer.serialize(object);
      }
    };
  }

  public static <T> MultiSerializer<T> compound(final AbstractSerializer<? super T> primary, final AbstractSerializer<? super T> secondary) {
    return compound(Arrays.<AbstractSerializer<? super T>>asList(primary, secondary));
  }

  public static <T> MultiSerializer<T> compound(final Iterable<? extends AbstractSerializer<? super T>> serializers) {
    return new CompoundSerializer<>(serializers);
  }
}
