package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.WorkbenchSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;

import static com.google.common.cache.CacheBuilder.newBuilder;

import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextDifferenceListener.Action.DIFFERENCE;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class TextDifferenceListener extends AbstractTextOperationListener implements DocumentListener, FileBufferListener {
  // TODO make options out of this:
  static final int cacheConcurrencyLevel = 4;

  static final int cacheInitialCapacity = 16;

  static final int differenceEventWindow = 500;

  private final Cache<IDocument, String> cache;

  public TextDifferenceListener() {
    CacheBuilder<Object, Object> builder = newBuilder();

    builder.concurrencyLevel(cacheConcurrencyLevel);
    builder.initialCapacity(cacheInitialCapacity);
    builder.ticker(this.timeSource());
    builder.weakValues();

    this.cache = builder.build();
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


  // TODO implement



  static Event build(final long time, final Action action, final IWorkbench workbench) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("workbench"), new WorkbenchSerializer(TREE).serialize(workbench));

    return data;
  }



  void process(final long time, final Action action, final DocumentEvent event) {
    IDocument document = event.getDocument();



    //this.send(action.getPath(), build(time, action, workbench));
  }

  public void documentAboutToBeChanged(final DocumentEvent event) {}

  public void documentChanged(final DocumentEvent event) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, DIFFERENCE, event);
      }
    });
  }

  public void bufferCreated(final IFileBuffer buffer) {}

  public void bufferDisposed(final IFileBuffer buffer) {}

  public void bufferContentAboutToBeReplaced(final IFileBuffer buffer) {}

  public void bufferContentReplaced(final IFileBuffer buffer) {}

  public void stateChanging(final IFileBuffer buffer) {}

  public void stateChangeFailed(final IFileBuffer buffer) {}

  public void stateValidationChanged(final IFileBuffer buffer, final boolean stateValidated) {}

  public void dirtyStateChanged(final IFileBuffer buffer, final boolean dirty) {}

  public void underlyingFileMoved(final IFileBuffer buffer, final IPath path) {}

  public void underlyingFileDeleted(final IFileBuffer buffer) {}
}
