package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IViewReference;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class ViewReferenceSerializer extends AbstractPartReferenceSerializer<IViewReference> {
  public ViewReferenceSerializer(final Option ... options) {
    super(options);
  }

  public ViewReferenceSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putViewReference(final StructuredContent content, final IViewReference reference) {
    content.put(key("secondaryIdentifier"), reference.getSecondaryId());

    content.put(key("isFastView"), reference.isFastView());
  }
}
