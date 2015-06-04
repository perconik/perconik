package sk.stuba.fiit.perconik.activity.listeners.ui.element;

import java.util.LinkedList;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PartSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.selection.StructuredSelectionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener;
import sk.stuba.fiit.perconik.data.events.Event;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import static sk.stuba.fiit.perconik.activity.listeners.ui.element.ElementSelectionListener.Action.SELECT;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializers.asDisplayTask;
import static sk.stuba.fiit.perconik.activity.serializers.ui.Ui.dereferencePart;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.8.alpha")
public final class ElementSelectionListener extends ActivityListener implements PartListener, StructuredSelectionListener {
  // TODO note that an element selection is generated on each part activation meaning that same
  //      selection events are generated when one switches-by-clicking for example between
  //      an editor and an outline; should it be fixed or left as is?

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
    assert sequence.getLast().selection.equals(selection);

    Event data = this.build(time, action, part);

    data.put(key("sequence", "first", "timestamp"), sequence.getFirst().time);
    data.put(key("sequence", "first", "raw"), new StructuredSelectionSerializer(TREE).serialize(sequence.getFirst().selection));

    data.put(key("sequence", "last", "timestamp"), sequence.getLast().time);
    data.put(key("sequence", "last", "raw"), new StructuredSelectionSerializer(TREE).serialize(sequence.getLast().selection));

    data.put(key("sequence", "count"), sequence.size());

    data.put(key("elements"), data.get("sequence", "last", "raw", "elements"));

    return data;
  }

  void process(final long time, final Action action, final LinkedList<ElementSelectionEvent> sequence, final IWorkbenchPart part, final IStructuredSelection selection) {
    this.send(action.getPath(), this.build(time, action, sequence, part, selection));
  }

  static final class ElementSelectionEvents extends ContinuousEvent<ElementSelectionListener, ElementSelectionEvent> {
    ElementSelectionEvents(final ElementSelectionListener listener) {
      super(listener, "element-selection", selectionEventPause, selectionEventWindow);
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

  public void partOpened(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partClosed(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partActivated(final IWorkbenchPartReference reference) {
    // ensures that a selection change is always generated on part activation,
    // this primarily helps handling selection context switch in some cases,
    // as a side effect it breaks event continuation

    this.selectionChanged(reference);
  }

  public void partDeactivated(final IWorkbenchPartReference reference) {
    // ensures that pending events are flushed on part deactivation,
    // this primarily handles proper selection termination on shutdown,
    // as a side effect it breaks event continuation

    this.events.flush();
  }

  public void partVisible(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partHidden(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partBroughtToTop(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partInputChanged(final IWorkbenchPartReference reference) {
    // ignore
  }

  private void push(final long time, final IWorkbenchPart part, final IStructuredSelection selection) {
    this.events.push(new ElementSelectionEvent(time, part, selection));
  }

  public void selectionChanged(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    IWorkbenchPart part = dereferencePart(reference);
    ISelectionProvider provider;

    if (part instanceof ISelectionProvider) {
      provider = (ISelectionProvider) part;
    } else {
      provider = part.getSite().getSelectionProvider();
    }

    if (provider == null) {
      if (this.log.isEnabled()) {
        this.log.print("%s: provider not found for %s -> ignore", "element-selection", part);
      }

      return;
    }

    ISelection selection = provider.getSelection();

    if (!(selection instanceof IStructuredSelection)) {
      if (this.log.isEnabled()) {
        this.log.print("%s: selection not structured in %s of %s -> ignore", "text-selection", provider, part);
      }

      return;
    }

    this.push(time, part, (IStructuredSelection) selection);
  }

  public void selectionChanged(final IWorkbenchPart part, final IStructuredSelection selection) {
    final long time = this.currentTime();

    this.push(time, part, selection);
  }
}
