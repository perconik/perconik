package sk.stuba.fiit.perconik.activity.listeners.ui.selection;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Unsupported
@Version("0.0.0.alpha")
public final class StructuredSelectionListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener {
  public StructuredSelectionListener() {}

  enum Action implements CommonEventListener.Action {
    ;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action) {
    Event data = LocalEvent.of(time, action.getName());



    return data;
  }

  void process(final long time, final Action action) {
    this.send(action.getPath(), build(time, action));
  }

  void execute(final long time, final Action action) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action);
      }
    });
  }

  public void selectionChanged(final IWorkbenchPart part, final IStructuredSelection selection) {
  }
}
