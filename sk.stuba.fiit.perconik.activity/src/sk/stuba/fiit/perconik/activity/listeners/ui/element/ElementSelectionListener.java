package sk.stuba.fiit.perconik.activity.listeners.ui.element;

import java.util.LinkedList;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PartSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.selection.StructuredSelectionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import static sk.stuba.fiit.perconik.activity.listeners.ui.element.ElementSelectionListener.Action.SELECT;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializers.asDisplayTask;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.4.alpha")
public final class ElementSelectionListener extends ActivityListener implements StructuredSelectionListener {
  static final TimeValue selectionEventPause = of(500L, MILLISECONDS);

  static final TimeValue selectionEventWindow = of(10L, SECONDS);

  private final ElementSelectionEvents events;

  public ElementSelectionListener() {
    this.events = new ElementSelectionEvents(this);
  }

  enum Action implements ActivityListener.Action {
    SELECT;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "element", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  Event build(final long time, final Action action, final IWorkbenchPart part) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("part"), this.execute(asDisplayTask(new PartSerializer(), part)));

    IWorkbenchPartSite site = part.getSite();

    if (site != null) {
      IWorkbenchPage page = site.getPage();
      IWorkbenchWindow window = page.getWorkbenchWindow();
      IWorkbench workbench = window.getWorkbench();

      data.put(key("part", "page"), identifyObject(page));
      data.put(key("part", "page", "window"), identifyObject(window));
      data.put(key("part", "page", "window", "workbench"), identifyObject(workbench));
    }

    return data;
  }

  Event build(final long time, final Action action, final LinkedList<ElementSelectionEvent> sequence, final IWorkbenchPart part, final IStructuredSelection selection) {
    Event data = this.build(time, action, part);

    data.merge(new StructuredSelectionSerializer().serialize(selection));

    data.put(key("sequence", "first", "timestamp"), sequence.getFirst().time);

    data.put(key("sequence", "last", "timestamp"), sequence.getLast().time);

    data.put(key("sequence", "count"), sequence.size());

    return data;
  }

  void process(final long time, final Action action, final LinkedList<ElementSelectionEvent> sequence, final IWorkbenchPart part, final IStructuredSelection selection) {
    this.send(action.getPath(), this.build(time, action, sequence, part, selection));
  }

  static final class ElementSelectionEvents extends ContinuousEvent<ElementSelectionListener, ElementSelectionEvent> {
    ElementSelectionEvents(final ElementSelectionListener listener) {
      super(listener, "selection", selectionEventPause, selectionEventWindow);
    }

    @Override
    protected boolean accept(final LinkedList<ElementSelectionEvent> sequence, final ElementSelectionEvent event) {
      boolean empty = event.selection.isEmpty();

      if (sequence.isEmpty()) {
        return !empty;
      }

      ElementSelectionEvent last = sequence.getLast();

      if (empty && last.part != event.part) {
        return false;
      }

      if (last.contentEquals(event)) {
        return false;
      }

      return true;
    }

    @Override
    protected boolean continuous(final LinkedList<ElementSelectionEvent> sequence, final ElementSelectionEvent event) {
      return sequence.getLast().isContinuousWith(event);
    }

    @Override
    protected void process(final LinkedList<ElementSelectionEvent> sequence) {
      this.listener.handleSelectionEvents(sequence);
    }
  }

  void handleSelectionEvents(final LinkedList<ElementSelectionEvent> sequence) {
    this.execute(new Runnable() {
      public void run() {
        ElementSelectionEvent event = sequence.getLast();

        process(event.time, SELECT, sequence, event.part, event.selection);
      }
    });
  }

  public void selectionChanged(final IWorkbenchPart part, final IStructuredSelection selection) {
    final long time = this.currentTime();

    this.events.push(new ElementSelectionEvent(time, part, selection));
  }
}
