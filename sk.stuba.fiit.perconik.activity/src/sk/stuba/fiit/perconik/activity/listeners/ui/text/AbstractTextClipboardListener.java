package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.eclipse.jface.text.Documents;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractTextClipboardListener extends AbstractTextListener {
  AbstractTextClipboardListener() {}

  final void process(final long time, final Action action) {
    String text = this.execute(ClipboardReader.instance);

    TextSelectionCapture capture = this.execute(TextSelectionReader.instance);

    if (capture == null) {
      if (this.log.isEnabled()) {
        this.log.print("%s: unable to capture content", "clipboard");
      }

      return;
    }

    IWorkbenchPart part = capture.part;
    ITextViewer viewer = capture.viewer;
    IDocument document = viewer.getDocument();

    Point range = capture.range;

    int offset = range.x;
    int length = range.y;

    LineRegion region = LineRegion.compute(document, offset, length, text).normalize();
    String selection = Documents.get(document, offset, length);

    this.process(time, action, part, document, region, selection);
  }

  final void process(final long time, final Action action, final IWorkbenchPart part, final IDocument document, final LineRegion region, final String selection) {
    if (!this.validate(part, document, region, selection)) {
      return;
    }

    this.process(time, action, part, region);
  }

  abstract boolean validate(final IWorkbenchPart part, final IDocument document, final LineRegion region, final String selection);
}
