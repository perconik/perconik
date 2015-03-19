package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.ui.EditorReferenceSerializer.putEditorReference;
import static sk.stuba.fiit.perconik.activity.serializers.ui.PartReferenceSerializer.putPartReference;
import static sk.stuba.fiit.perconik.activity.serializers.ui.ViewReferenceSerializer.putViewReference;

abstract class AbstractPartReferenceSerializer<T extends IWorkbenchPartReference> extends AbstractConfigurableMultiSerializer<T> {
  AbstractPartReferenceSerializer(final Option ... options) {
    super(options);
  }

  AbstractPartReferenceSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final IWorkbenchPartReference reference) {
    putObjectIdentity(content, reference);
    putPartReference(content, reference, this.options);

    if (reference instanceof IViewReference) {
      putViewReference(content, (IViewReference) reference);
    }

    if (reference instanceof IEditorReference) {
      putEditorReference(content, (IEditorReference) reference);
    }
  }
}
