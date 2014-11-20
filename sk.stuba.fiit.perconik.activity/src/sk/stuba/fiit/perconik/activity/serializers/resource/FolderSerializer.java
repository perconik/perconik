package sk.stuba.fiit.perconik.activity.serializers.resource;

import java.util.Set;

import org.eclipse.core.resources.IFolder;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

public final class FolderSerializer extends AbstractResourceSerializer<IFolder> {
  public FolderSerializer(final Option ... options) {
    super(options);
  }

  public FolderSerializer(final Iterable<Option> options) {
    super(options);
  }

  @SuppressWarnings("unused")
  static void putFolder(final StructuredContent content, final IFolder folder, final Set<Option> options) {}
}
