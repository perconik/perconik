package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.EditorSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.text.LineRegionSerializer;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.asDisplayTask;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractTextListener extends ActivityListener {
  AbstractTextListener() {}

  final Event build(final long time, final Action action, final IEditorPart editor, final UnderlyingView<?> view) {
    assert UnderlyingView.from(editor).equals(view);

    Event data = LocalEvent.of(time, action.getName());

    data.put(key("editor"), this.execute(asDisplayTask(new EditorSerializer(), editor)));

    IEditorSite site = editor.getEditorSite();

    if (site != null) {
      IWorkbenchPage page = site.getPage();
      IWorkbenchWindow window = page.getWorkbenchWindow();
      IWorkbench workbench = window.getWorkbench();

      data.put(key("editor", "page"), identifyObject(page));
      data.put(key("editor", "page", "window"), identifyObject(window));
      data.put(key("editor", "page", "window", "workbench"), identifyObject(workbench));
    }

    return data;
  }

  final Event build(final long time, final Action action, final IEditorPart editor, final UnderlyingView<?> view, final LineRegion region) {
    Event data = build(time, action, editor, view);

    data.put(key("region"), new LineRegionSerializer().serialize(region));

    return data;
  }

  final void process(final long time, final Action action, final IEditorPart editor, final IDocument document, final LineRegion region) {
    UnderlyingView<?> view = UnderlyingView.resolve(document, editor);

    this.send(action.getPath(), this.build(time, action, editor, view, region));
  }
}
