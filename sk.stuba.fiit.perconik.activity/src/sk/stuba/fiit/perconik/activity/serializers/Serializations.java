package sk.stuba.fiit.perconik.activity.serializers;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

import static com.google.common.collect.Lists.newArrayListWithExpectedSize;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.MoreLists.newArrayListSuitableFor;

// TODO remove all (most of) serialization from listeners?

public final class Serializations {
  private Serializations() {}

  public static StructuredContent newStructuredContent() {
    return new AnyStructuredData();
  }

  public static <T> List<T> newEmptyListSuitableFor(final Iterable<?> iterable) {
    return newArrayListSuitableFor(iterable);
  }

  public static <T> List<T> newEmptyListSuitableFor(@SuppressWarnings("unused") final Iterator<?> iterator) {
    return newArrayListWithExpectedSize(16);
  }

  public static StructuredContent identifyObject(@Nullable final Object object) {
    if (object == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObjectIdentity(content, object);

    return content;
  }

  public static void putObjectIdentity(final StructuredContent content, final Object object) {
    content.put(key("class"), object.getClass().getName());
    content.put(key("hash"), object.hashCode());
  }

  public static <T> DisplayTask<Content> asDisplayTask(final Serializer<? super T> serializer, @Nullable final T object) {
    return new DisplayTask<Content>() {
      public Content call() throws Exception {
        return serializer.serialize(object);
      }
    };
  }
}
