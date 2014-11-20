package sk.stuba.fiit.perconik.activity.serializers.ui;

import java.util.Set;

import org.eclipse.ui.IWorkbenchPage;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static com.google.common.collect.Iterables.concat;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class PageSerializer extends AbstractConfigurableSerializer<IWorkbenchPage> {
  public PageSerializer(final Option ... options) {
    super(options);
  }

  public PageSerializer(final Iterable<Option> options) {
    super(options);
  }

  private static void putPage(final StructuredContent content, final IWorkbenchPage page, final Set<Option> options) {
    content.put(key("label"), page.getLabel());

    content.put(key("perspective"), new PerspectiveDescriptorSerializer(options).serialize(page.getPerspective()));

    content.put(key("isEditorAreaVisible"), page.isEditorAreaVisible());
    content.put(key("isPageZoomed"), page.isPageZoomed());

    if (options.contains(StandardOption.TREE)) {
      content.put(key("activePart"), identifyObject(page.getActivePartReference()));
      content.put(key("parts"), new PartReferenceSerializer(options).serialize(concat(asList(page.getViewReferences()), asList(page.getEditorReferences()))));

      try {
        content.put(key("selection"), identifyObject(page.getSelection()));
      } catch (NullPointerException ignore) {}
    }
  }

  @Override
  protected void put(final StructuredContent content, final IWorkbenchPage page) {
    putObjectIdentity(content, page);
    putPage(content, page, this.options);
  }
}
