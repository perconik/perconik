package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.IMarkSelection;
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
public final class MarkSelectionListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.MarkSelectionListener {
  public MarkSelectionListener() {}

  enum Action {
    ;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "", this);
      this.path = actionPath(this.name);
    }
  }

  static Event build(final long time, final Action action) {
    Event data = LocalEvent.of(time, action.name);



    return data;
  }

  void process(final long time, final Action action) {
    this.send(action.path, build(time, action));
  }

  void execute(final long time, final Action action) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action);
      }
    });
  }

  public void selectionChanged(final IWorkbenchPart part, final IMarkSelection selection) {
  }
}
