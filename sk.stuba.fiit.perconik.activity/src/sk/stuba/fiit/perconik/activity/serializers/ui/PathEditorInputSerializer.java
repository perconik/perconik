package sk.stuba.fiit.perconik.activity.serializers.ui;

import java.util.Set;

import org.eclipse.ui.IPathEditorInput;

import sk.stuba.fiit.perconik.activity.serializers.resource.PathSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class PathEditorInputSerializer extends AbstractEditorInputSerializer<IPathEditorInput> {
  public PathEditorInputSerializer(final Option ... options) {
    super(options);
  }

  public PathEditorInputSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putPathEditorInput(final StructuredContent content, final IPathEditorInput input, final Set<Option> options) {
    content.put(key("path"), new PathSerializer(options).serialize(input.getPath()));
  }

  @Override
  protected void put(final StructuredContent content, final IPathEditorInput input) {}
}
