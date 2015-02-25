package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.EditorSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.text.LineRegionSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.text.UnderlyingResourceSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractTextListener extends CommonEventListener {
  AbstractTextListener() {}

  static final void put(final StructuredContent content, final IEditorPart editor) {
    content.put(key("editor"), new EditorSerializer().serialize(editor));

    IEditorSite site = editor.getEditorSite();

    if (site != null) {
      IWorkbenchPage page = site.getPage();
      IWorkbenchWindow window = page.getWorkbenchWindow();
      IWorkbench workbench = window.getWorkbench();

      content.put(key("editor", "page"), identifyObject(page));
      content.put(key("editor", "page", "window"), identifyObject(window));
      content.put(key("editor", "page", "window", "workbench"), identifyObject(workbench));
    }
  }

  static final void put(final StructuredContent content, final UnderlyingView<?> view) {
    content.put(key("document"), identifyObject(view.getDocument()));
    content.put(key("resource"), new UnderlyingResourceSerializer().serialize(view.getResource()));
  }

  static final void put(final StructuredContent content, final LineRegion region) {
    content.put(key("region"), new LineRegionSerializer().serialize(region));
  }

  static final Event build(final long time, final Action action, final IEditorPart editor, final UnderlyingView<?> view) {
    Event data = LocalEvent.of(time, action.getName());

    put(data, editor);
    put(data, view);

    return data;
  }

  static final Event build(final long time, final Action action, final IEditorPart editor, final UnderlyingView<?> view, final LineRegion region) {
    Event data = build(time, action, editor, view);

    put(data, region);

    return data;
  }

  final void process(final long time, final Action action, final IEditorPart editor, final IDocument document, final LineRegion region) {
    UnderlyingView<?> view = UnderlyingView.resolve(document, editor);

    this.send(action.getPath(), build(time, action, editor, view, region));
  }
}
