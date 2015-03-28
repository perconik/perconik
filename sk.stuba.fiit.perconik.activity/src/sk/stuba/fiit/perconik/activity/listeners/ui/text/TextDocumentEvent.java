package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import javax.annotation.Nullable;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;

final class TextDocumentEvent extends AbstractTextEvent {
  final IDocument document;

  @Nullable
  final DocumentEvent event;

  final boolean force;

  TextDocumentEvent(final long time, final DocumentEvent event) {
    super(time);

    this.document = event.getDocument();
    this.event = event;
    this.force = false;
  }

  TextDocumentEvent(final long time, final IDocument document, final boolean force) {
    super(time);

    assert document != null;

    this.document = document;
    this.event = null;
    this.force = force;
  }

  boolean isContinuousWith(final TextDocumentEvent other) {
    return !this.force && this.document.equals(other.document);
  }
}
