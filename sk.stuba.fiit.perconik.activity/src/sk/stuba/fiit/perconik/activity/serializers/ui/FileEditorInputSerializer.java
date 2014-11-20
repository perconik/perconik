package sk.stuba.fiit.perconik.activity.serializers.ui;

import java.util.Set;

import org.eclipse.ui.IFileEditorInput;

import sk.stuba.fiit.perconik.activity.serializers.resource.FileSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class FileEditorInputSerializer extends AbstractEditorInputSerializer<IFileEditorInput> {
  public FileEditorInputSerializer(final Option ... options) {
    super(options);
  }

  public FileEditorInputSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putFileEditorInput(final StructuredContent content, final IFileEditorInput input, final Set<Option> options) {
    content.put(key("file"), new FileSerializer(options).serialize(input.getFile()));
  }
}
