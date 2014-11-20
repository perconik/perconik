package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IViewPart;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

public final class ViewSerializer extends AbstractPartSerializer<IViewPart> {
  public ViewSerializer(final Option ... options) {
    super(options);
  }

  public ViewSerializer(final Iterable<Option> options) {
    super(options);
  }

  @SuppressWarnings("unused")
  static void putView(final StructuredContent content, final IViewPart part) {}
}
