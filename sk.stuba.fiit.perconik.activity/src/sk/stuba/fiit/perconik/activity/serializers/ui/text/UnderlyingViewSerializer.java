package sk.stuba.fiit.perconik.activity.serializers.ui.text;

import java.util.Set;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class UnderlyingViewSerializer extends AbstractConfigurableMultiSerializer<UnderlyingView<?>> {
  public UnderlyingViewSerializer(final Option ... options) {
    super(options);
  }

  public UnderlyingViewSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putLineRegion(final StructuredContent content, final UnderlyingView<?> view, final Set<Option> options) {
    content.put(key("document"), new DocumentSerializer(options).serialize(view.getDocument()));
    content.put(key("resource"), new UnderlyingResourceSerializer(options).serialize(view.getResource()));
  }

  @Override
  protected void put(final StructuredContent content, final UnderlyingView<?> view) {
    putLineRegion(content, view, this.options);
  }
}
