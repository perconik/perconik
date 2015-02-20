package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.WorkbenchSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class TextDifferenceListener extends CommonEventListener implements FileBufferListener {


  public TextDifferenceListener() {
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

  // TODO
  static Event build(final long time, final Action action, final IWorkbench workbench) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("workbench"), new WorkbenchSerializer(TREE).serialize(workbench));

    return data;
  }

  // TODO
  void process(final long time, final Action action, final IWorkbench workbench) {
    this.send(action.getPath(), build(time, action, workbench));
  }

  // TODO
  void execute(final long time, final Action action, final IWorkbench workbench) {
    this.execute(DisplayTask.of(new Runnable() {
      public void run() {
        process(time, action, workbench);
      }
    }));
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
