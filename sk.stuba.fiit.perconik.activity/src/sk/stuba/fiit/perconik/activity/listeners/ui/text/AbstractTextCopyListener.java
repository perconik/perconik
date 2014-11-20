package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.eclipse.jface.text.Documents;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractTextCopyListener<A> extends AbstractTextOperationListener {
  AbstractTextCopyListener() {}

  final void process(final long time, final A action) {
    String text = this.execute(ClipboardReader.instance);

    TextSelectionCapture capture = this.execute(TextSelectionReader.instance);

    IEditorPart editor = capture.editor;
    ISourceViewer viewer = capture.viewer;
    IDocument document = viewer.getDocument();

    Point range = capture.range;

    int offset = range.x;
    int length = range.y;

    LineRegion region = LineRegion.of(document, offset, length, text);
    String selection = Documents.get(document, offset, length);

    this.process(time, action, editor, document, region, selection);
  }

  abstract void process(final long time, final A action, final IEditorPart editor, final IDocument document, final LineRegion region, final String selection);
}
