package sk.stuba.fiit.perconik.activity.serializers.ui;

import java.util.Set;

import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.ui.Ui.dereferencePart;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class PartReferenceSerializer extends AbstractPartReferenceSerializer<IWorkbenchPartReference> {
  public PartReferenceSerializer(final Option ... options) {
    super(options);
  }

  public PartReferenceSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putPartReference(final StructuredContent content, final IWorkbenchPartReference reference, final Set<Option> options) {
    content.put(key("identifier"), reference.getId());
    content.put(key("title"), reference.getTitle());
    content.put(key("titleTooltip"), reference.getTitleToolTip());

    content.put(key("part"), new PartSerializer(options).serialize(dereferencePart(reference)));
    content.put(key("partName"), reference.getPartName());

    content.put(key("isDirty"), reference.isDirty());
  }
}
