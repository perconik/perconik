package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class EditorReferenceSerializer extends AbstractPartReferenceSerializer<IEditorReference> {
  public EditorReferenceSerializer(final Option ... options) {
    super(options);
  }

  public EditorReferenceSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putEditorReference(final StructuredContent content, final IEditorReference reference) {
    content.put(key("isPinned"), reference.isPinned());
  }
}
