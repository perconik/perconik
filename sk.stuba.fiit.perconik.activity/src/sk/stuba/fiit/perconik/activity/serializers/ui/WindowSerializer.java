package sk.stuba.fiit.perconik.activity.serializers.ui;

import java.util.Set;

import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class WindowSerializer extends AbstractConfigurableMultiSerializer<IWorkbenchWindow> {
  public WindowSerializer(final Option ... options) {
    super(options);
  }

  public WindowSerializer(final Iterable<Option> options) {
    super(options);
  }

  private static void putWindow(final StructuredContent content, final IWorkbenchWindow window, final Set<Option> options) {
    if (options.contains(StandardOption.TREE)) {
      content.put(key("activePage"), identifyObject(window.getActivePage()));
      content.put(key("pages"), new PageSerializer(options).serialize(asList(window.getPages())));

      try {
        content.put(key("selection"), identifyObject(window.getSelectionService().getSelection()));
      } catch (NullPointerException ignore) {}
    }
  }

  @Override
  protected void put(final StructuredContent content, final IWorkbenchWindow window) {
    putObjectIdentity(content, window);
    putWindow(content, window, this.options);
  }
}
