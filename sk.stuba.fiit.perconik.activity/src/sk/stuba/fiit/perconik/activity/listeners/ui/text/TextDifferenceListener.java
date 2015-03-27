package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentMap;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.ActivityEventListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView;
import sk.stuba.fiit.perconik.eclipse.jface.text.Documents;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import static com.google.common.cache.CacheBuilder.newBuilder;

import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextDifferenceListener.Action.DIFFERENCE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCase;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.1.alpha")
public final class TextDifferenceListener extends AbstractTextListener implements DocumentListener, FileBufferListener {
  static final TimeValue differenceEventPause = of(500, MILLISECONDS);

  static final TimeValue differenceEventWindow = of(10, SECONDS);

  static final int cacheConcurrencyLevel = 4;

  static final int cacheInitialCapacity = 16;

  static final long cacheMaximumSize = 128;

  private final TextDocumentEventProcessor processor;

  public TextDifferenceListener() {
    this.processor = new TextDocumentEventProcessor(this);
  }

  enum Action implements ActivityEventListener.Action {
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

    data.put(key("text", "before"), before);
    data.put(key("text", "after"), after);

    return data;
  }

  void process(final long time, final Action action, final TextDocumentEvent event, final String before, final String after) {
    IDocument document = event.document;
    IEditorPart editor = Editors.forDocument(document);
    UnderlyingView<?> view = UnderlyingView.resolve(document, editor);

    this.send(action.getPath(), this.build(time, action, editor, view, before, after));
  }

  static final class TextDocumentEventProcessor extends ContinuousEventProcessor<TextDifferenceListener, TextDocumentEvent> {
    final Cache<IDocument, String> cache;

    TextDocumentEventProcessor(final TextDifferenceListener listener) {
      super(listener, "difference", differenceEventPause, differenceEventWindow);

      CacheBuilder<Object, Object> builder = newBuilder();

      builder.concurrencyLevel(cacheConcurrencyLevel);
      builder.initialCapacity(cacheInitialCapacity);
      builder.maximumSize(cacheMaximumSize);
      builder.ticker(this.listener.getTimeContext().elapsedTimeTicker());
      builder.weakKeys();

      final Log log = this.log;

      builder.removalListener(new RemovalListener<IDocument, String>() {
        @SuppressWarnings({"synthetic-access", "unqualified-field-access"})
        public void onRemoval(final RemovalNotification<IDocument, String> notification) {
          if (log.isEnabled()) {
            IDocument document = notification.getKey();
            RemovalCause cause = notification.getCause();

            if (cause != RemovalCause.EXPLICIT && cause != RemovalCause.REPLACED) {
              log.print("%s: document %x removed (%s) from cache", identifier, document.hashCode(), toLowerCase(cause));
            }
          }
        }
      });

      this.cache = builder.build();
    }

    boolean update(final IDocument document, final String text, final boolean force) {
      ConcurrentMap<IDocument, String> map = this.cache.asMap();

      String previous;

      boolean result;

      if (force) {
        previous = map.put(document, text);
        result = !text.equals(previous);
      } else {
        previous = map.putIfAbsent(document, text);
        result = previous == null;
      }

      if (result) {
        if (this.log.isEnabled()) {
          String operation = force && previous != null ? "updated" : "put";
          String forced = force ? " (forced)" : "";

          this.log.print("%s: document %x of %d characters %s in cache%s", this.identifier, document.hashCode(), text.length(), operation, forced);
        }
      }

      return result;
    }

    @Override
    protected boolean accept(final LinkedList<TextDocumentEvent> sequence, final TextDocumentEvent event) {
      if (sequence.isEmpty()) {
        IDocument document = event.document;

        String text = document.get();

        this.update(document, text, false);
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

        this.update(document, after, true);

        return;
      }

      if (before.equals(after)) {
        if (this.log.isEnabled()) {
          this.log.print("%s: document %x texts equal, nothing to process", this.identifier, document.hashCode());
        }

        return;
      }

      if (this.log.isEnabled()) {
        this.log.print("%s: document %x texts not equal -> difference", this.identifier, document.hashCode());
      }

      this.update(document, after, true);

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
    IDocument document = event.getDocument();

    this.processor.update(document, document.get(), false);
  }

  public void documentChanged(final DocumentEvent event) {
    final long time = this.currentTime();

    this.processor.push(new TextDocumentEvent(time, event));
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
