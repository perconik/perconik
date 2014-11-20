package sk.stuba.fiit.perconik.activity.serializers.resource;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class FileSerializer extends AbstractResourceSerializer<IFile> {
  public FileSerializer(final Option ... options) {
    super(options);
  }

  public FileSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putFile(final StructuredContent content, final IFile file, @SuppressWarnings("unused") final Set<Option> options) {
    try {
      content.put(key("charset"), file.getCharset());
    } catch (CoreException ignore) {}

    try {
      IContentDescription description = file.getContentDescription();

      if (description != null) {
        IContentType type = description.getContentType();

        if (type != null) {
          content.put(key("contentType", "identifier"), type.getId());
          content.put(key("contentType", "name"), type.getName());
        }
      }
    } catch (CoreException ignore) {}
  }
}
