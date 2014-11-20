package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IEditorInput;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class EditorInputSerializer extends AbstractEditorInputSerializer<IEditorInput> {
  public EditorInputSerializer(final Option ... options) {
    super(options);
  }

  public EditorInputSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putEditorInput(final StructuredContent content, final IEditorInput input) {
    content.put(key("name"), input.getName());
    content.put(key("tooltip"), input.getToolTipText());

    content.put(key("exists"), input.exists());
  }
}
