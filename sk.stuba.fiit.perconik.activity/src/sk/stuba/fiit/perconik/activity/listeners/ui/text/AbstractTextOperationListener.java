package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

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
abstract class AbstractTextOperationListener extends CommonEventListener {
  AbstractTextOperationListener() {}

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
}
