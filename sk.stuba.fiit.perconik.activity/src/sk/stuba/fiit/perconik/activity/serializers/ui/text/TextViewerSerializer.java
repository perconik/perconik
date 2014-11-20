package sk.stuba.fiit.perconik.activity.serializers.ui.text;

import java.util.Set;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension;

import sk.stuba.fiit.perconik.activity.serializers.ui.selection.SelectionSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TextViewerSerializer extends AbstractTextViewerSerializer<ITextViewer> {
  public TextViewerSerializer(final Option ... options) {
    super(options);
  }

  public TextViewerSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTextViewer(final StructuredContent content, final ITextViewer viewer, final Set<Option> options) {
    content.put(key("top", "inset"), viewer.getTopInset());

    content.put(key("top", "index"), viewer.getTopIndex());
    content.put(key("top", "offset"), viewer.getTopIndexStartOffset());

    content.put(key("bottom", "index"), viewer.getBottomIndex());
    content.put(key("bottom", "offset"), viewer.getBottomIndexEndOffset());

    content.put(key("selection"), new SelectionSerializer(options).serialize(viewer.getSelectionProvider().getSelection()));

    content.put(key("vision"), new RegionSerializer(options).serialize(viewer.getVisibleRegion()));

    content.put(key("document"), new DocumentSerializer(options).serialize(viewer.getDocument()));

    content.put(key("isEditable"), viewer.isEditable());

    if (viewer instanceof ITextViewerExtension) {
      ITextViewerExtension extension = (ITextViewerExtension) viewer;

      content.put(key("mark"), extension.getMark());
    }
  }
}
