package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import javax.annotation.Nullable;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;

final class TextDocumentEvent {
  final long time;

  final IDocument document;

  @Nullable
  final DocumentEvent event;

  final boolean last;

  TextDocumentEvent(final long time, final DocumentEvent event) {
    assert time >= 0L;

    this.time = time;
    this.document = event.getDocument();
    this.event = event;
    this.last = false;
  }

  TextDocumentEvent(final long time, final IDocument document, final boolean last) {
    assert time >= 0L && document != null;

    this.time = time;
    this.document = document;
    this.event = null;
    this.last = last;
  }

  boolean isContinuousWith(final TextDocumentEvent other) {
    return !this.last && this.document.equals(other.document);
  }
}
