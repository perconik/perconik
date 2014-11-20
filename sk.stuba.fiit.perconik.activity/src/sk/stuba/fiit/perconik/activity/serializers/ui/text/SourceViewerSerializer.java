package sk.stuba.fiit.perconik.activity.serializers.ui.text;

import java.util.Set;

import org.eclipse.jface.text.source.ISourceViewer;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class SourceViewerSerializer extends AbstractTextViewerSerializer<ISourceViewer> {
  public SourceViewerSerializer(final Option ... options) {
    super(options);
  }

  public SourceViewerSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putSourceViewer(final StructuredContent content, final ISourceViewer viewer, final Set<Option> options) {
    content.put(key("indication"), new RegionSerializer(options).serialize(viewer.getRangeIndication()));
  }
}
