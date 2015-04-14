package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.selection.MarkSelectionSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.text.LineRegionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.MarkSelectionListener;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextMarkListener.Action.SELECT;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
@Unsupported
public final class TextMarkListener extends AbstractTextListener implements MarkSelectionListener {
  static final String selectionEventType = "mark";

  public TextMarkListener() {}

  enum Action implements ActivityListener.Action {
    SELECT;

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

  Event build(final long time, final Action action, final IWorkbenchPart part, final IMarkSelection selection, final LineRegion region) {
    Event data = this.build(time, action, part);

    data.put(key("selection", "type"), selectionEventType);

    data.put(key("selection", "events", "first", "timestamp"), time);
    data.put(key("selection", "events", "first", "raw"), new MarkSelectionSerializer(TREE).serialize(selection));

    data.put(key("selection", "events", "last"), data.get(key("selection", "events", "first")));

    data.put(key("selection", "region"), new LineRegionSerializer().serialize(region));

    return data;
  }

  void process(final long time, final Action action, final IWorkbenchPart part, final IMarkSelection selection) {
    IDocument document = selection.getDocument();

    LineRegion region = LineRegion.compute(document, selection.getOffset(), selection.getLength()).normalize();

    this.send(action.getPath(), this.build(time, action, part, selection, region));
  }

  void execute(final long time, final Action action, final IWorkbenchPart part, final IMarkSelection selection) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, part, selection);
      }
    });
  }

  public void selectionChanged(final IWorkbenchPart part, final IMarkSelection selection) {
    final long time = this.currentTime();

    this.execute(time, SELECT, part, selection);
  }
}
