package sk.stuba.fiit.perconik.activity.serializers.ui;

import java.util.Set;

import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.activity.serializers.ui.text.TextViewerSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class EditorSerializer extends AbstractPartSerializer<IEditorPart> {
  public EditorSerializer(final Option ... options) {
    super(options);
  }

  public EditorSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putEditor(final StructuredContent content, final IEditorPart part, final Set<Option> options) {
    content.put(key("input"), new EditorInputSerializer(options).serialize(part.getEditorInput()));

    content.put(key("isSaveAsAllowed"), part.isSaveAsAllowed());
    content.put(key("isSaveOnCloseNeeded"), part.isSaveOnCloseNeeded());

    Object viewer = part.getAdapter(ITextOperationTarget.class);

    if (viewer instanceof ITextViewer) {
      content.put(key("viewer"), new TextViewerSerializer(options).serialize((ITextViewer) viewer));
    }
  }
}
