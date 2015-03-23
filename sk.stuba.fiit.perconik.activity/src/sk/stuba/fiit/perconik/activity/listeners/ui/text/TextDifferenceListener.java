package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.LinkedList;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView;
import sk.stuba.fiit.perconik.eclipse.jface.text.Documents;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static com.google.common.cache.CacheBuilder.newBuilder;

import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextDifferenceListener.Action.DIFFERENCE;
import static sk.stuba.fiit.perconik.activity.serializers.ui.Ui.dereferenceEditor;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
@Unsupported
public final class TextDifferenceListener extends AbstractTextListener implements DocumentListener, EditorListener, FileBufferListener {
  static final TimeValue differenceEventWindow = of(500, MILLISECONDS);

  // TODO make options out of this:
  static final int cacheConcurrencyLevel = 4;

  static final int cacheInitialCapacity = 16;

  private final TextDocumentEventProcessor processor;

  public TextDifferenceListener() {
    this.processor = new TextDocumentEventProcessor(this);

  }

  enum Action implements CommonEventListener.Action {
    DIFFERENCE;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "text", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  Event build(final long time, final Action action, final IEditorPart editor, final UnderlyingView<?> view, final String before, final String after) {
    Event data = this.build(time, action, editor, view);

    data.put(key("before"), before);
    data.put(key("after"), after);

    return data;
  }

  void process(final long time, final Action action, final TextDocumentEvent event, final String before, final String after) {
    IDocument document = event.document;
    IEditorPart editor = Editors.forDocument(document);
    UnderlyingView<?> view = UnderlyingView.resolve(document, editor);

    this.send(action.getPath(), this.build(time, action, editor, view, before, after));
  }

  static final class TextDocumentEventProcessor extends ContinuousEventWindow<TextDifferenceListener, TextDocumentEvent> {
    final Cache<IDocument, String> cache;

    TextDocumentEventProcessor(final TextDifferenceListener listener) {
      super(listener, "difference", differenceEventWindow);

      CacheBuilder<Object, Object> builder = newBuilder();

      builder.concurrencyLevel(cacheConcurrencyLevel);
      builder.initialCapacity(cacheInitialCapacity);
      builder.ticker(this.listener.getTimeContext().elapsedTimeTicker());
      builder.weakValues();

      this.cache = builder.build();
    }

    @Override
    protected boolean accept(final LinkedList<TextDocumentEvent> sequence, final TextDocumentEvent event) {
      if (sequence.isEmpty()) {
        IDocument document = event.document;

        String text = document.get();

        this.cache.put(document, text);

        if (this.log.isEnabled()) {
          this.log.print("%s: sequence empty, document %x with text %x of %d characters put in cache", this.identifier, document.hashCode(), text.hashCode(), text.length());
        }
      }

      return true;
    }

    @Override
    protected boolean continuous(final LinkedList<TextDocumentEvent> sequence, final TextDocumentEvent event) {
      return sequence.getLast().isContinuousWith(event);
    }

    @Override
    protected void process(final LinkedList<TextDocumentEvent> sequence) {
      assert sequence.getFirst().document.equals(sequence.getLast().document);

      IDocument document = sequence.getLast().document;

      String before = this.cache.getIfPresent(document);
      String after = document.get();

      if (before == null) {
        if (this.log.isEnabled()) {
          this.log.print("%s: original text for document %x not cached, nothing to process", this.identifier, document.hashCode());
        }

        return;
      }

      if (before.equals(after)) {
        if (this.log.isEnabled()) {
          this.log.print("%s: document %x texts equal, nothing to process", this.identifier, document.hashCode());
        }

        return;
      }

      if (this.log.isEnabled()) {
        this.log.print("%s: document %x texts not equal, processing difference", this.identifier, document.hashCode());
      }

      this.listener.handleAcceptedDifference(sequence.getLast(), before, after);
    }
  }

  void handleAcceptedDifference(final TextDocumentEvent event, final String before, final String after) {
    this.execute(new Runnable() {
      public void run() {
        process(event.time, DIFFERENCE, event, before, after);
      }
    });
  }

  void handleUnsentDifferenceOnUnregistration() {
    this.processor.flush();
  }

  public void documentAboutToBeChanged(final DocumentEvent event) {
    // ignore
  }

  public void documentChanged(final DocumentEvent event) {
    final long time = this.currentTime();

    this.processor.push(new TextDocumentEvent(time, event));
  }

  public void editorOpened(final IEditorReference reference) {
    // ignore
  }

  public void editorClosed(final IEditorReference reference) {
    final long time = this.currentTime();

    IEditorPart editor = dereferenceEditor(reference);
    IDocument document = Editors.getDocument(editor);

    this.processor.push(new TextDocumentEvent(time, document, true));
  }

  public void editorActivated(final IEditorReference reference) {
    // ignore
  }

  public void editorDeactivated(final IEditorReference reference) {
    // ignore
  }

  public void editorVisible(final IEditorReference reference) {
    // ignore
  }

  public void editorHidden(final IEditorReference reference) {
    // ignore
  }

  public void editorBroughtToTop(final IEditorReference reference) {
    // ignore
  }

  public void editorInputChanged(final IEditorReference reference) {
    // ignore
  }

  public void bufferCreated(final IFileBuffer buffer) {
    // ignore
  }

  public void bufferDisposed(final IFileBuffer buffer) {
    // ignore
  }

  public void bufferContentAboutToBeReplaced(final IFileBuffer buffer) {
    // ignore
  }

  public void bufferContentReplaced(final IFileBuffer buffer) {
    // ignore
  }

  public void stateChanging(final IFileBuffer buffer) {
    // ignore
  }

  public void stateChangeFailed(final IFileBuffer buffer) {
    // ignore
  }

  public void stateValidationChanged(final IFileBuffer buffer, final boolean stateValidated) {
    // ignore
  }

  public void dirtyStateChanged(final IFileBuffer buffer, final boolean dirty) {
    final long time = this.currentTime();

    if (dirty) {
      return;
    }

    IDocument document = Documents.fromFileBuffer(buffer);

    if (document == null) {
      return;
    }

    this.processor.push(new TextDocumentEvent(time, document, true));
  }

  public void underlyingFileMoved(final IFileBuffer buffer, final IPath path) {
    // ignore
  }

  public void underlyingFileDeleted(final IFileBuffer buffer) {
    // ignore
  }
}
