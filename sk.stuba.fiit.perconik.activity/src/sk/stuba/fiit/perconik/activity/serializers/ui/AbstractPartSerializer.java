package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.ui.EditorSerializer.putEditor;
import static sk.stuba.fiit.perconik.activity.serializers.ui.PartSerializer.putPart;
import static sk.stuba.fiit.perconik.activity.serializers.ui.ViewSerializer.putView;

abstract class AbstractPartSerializer<T extends IWorkbenchPart> extends AbstractConfigurableMultiSerializer<T> {
  AbstractPartSerializer(final Option ... options) {
    super(options);
  }

  AbstractPartSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final IWorkbenchPart part) {
    putObjectIdentity(content, part);
    putPart(content, part);

    if (part instanceof IViewPart) {
      putView(content, (IViewPart) part);
    }

    if (part instanceof IEditorPart) {
      putEditor(content, (IEditorPart) part, this.options);
    }
  }
}
