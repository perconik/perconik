package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IURIEditorInput;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class UriEditorInputSerializer extends AbstractEditorInputSerializer<IURIEditorInput> {
  public UriEditorInputSerializer(final Option ... options) {
    super(options);
  }

  public UriEditorInputSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putUriEditorInput(final StructuredContent content, final IURIEditorInput input) {
    content.put(key("uri"), input.getURI());
  }
}
