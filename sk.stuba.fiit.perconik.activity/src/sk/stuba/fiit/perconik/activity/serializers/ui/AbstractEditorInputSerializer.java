package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IURIEditorInput;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.ui.EditorInputSerializer.putEditorInput;
import static sk.stuba.fiit.perconik.activity.serializers.ui.FileEditorInputSerializer.putFileEditorInput;
import static sk.stuba.fiit.perconik.activity.serializers.ui.PathEditorInputSerializer.putPathEditorInput;
import static sk.stuba.fiit.perconik.activity.serializers.ui.UriEditorInputSerializer.putUriEditorInput;

abstract class AbstractEditorInputSerializer<T extends IEditorInput> extends AbstractConfigurableSerializer<T> {
  AbstractEditorInputSerializer(final Option ... options) {
    super(options);
  }

  AbstractEditorInputSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final IEditorInput input) {
    putObjectIdentity(content, input);
    putEditorInput(content, input);

    if (input instanceof IFileEditorInput) {
      putFileEditorInput(content, (IFileEditorInput) input, this.options);
    }

    if (input instanceof IPathEditorInput) {
      putPathEditorInput(content, (IPathEditorInput) input, this.options);
    }

    if (input instanceof IURIEditorInput) {
      putUriEditorInput(content, (IURIEditorInput) input);
    }
  }
}
