package sk.stuba.fiit.perconik.activity.serializers.ui.text;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.source.ISourceViewer;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.ui.text.SourceViewerSerializer.putSourceViewer;
import static sk.stuba.fiit.perconik.activity.serializers.ui.text.TextViewerSerializer.putTextViewer;

abstract class AbstractTextViewerSerializer<T extends ITextViewer> extends AbstractConfigurableSerializer<T> {
  AbstractTextViewerSerializer(final Option ... options) {
    super(options);
  }

  AbstractTextViewerSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T viewer) {
    putObjectIdentity(content, viewer);
    putTextViewer(content, viewer, this.options);

    if (viewer instanceof ISourceViewer) {
      putSourceViewer(content, (ISourceViewer) viewer, this.options);
    }
  }
}
