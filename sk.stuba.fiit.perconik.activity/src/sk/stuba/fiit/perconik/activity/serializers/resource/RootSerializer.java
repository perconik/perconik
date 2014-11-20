package sk.stuba.fiit.perconik.activity.serializers.resource;

import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IWorkspaceRoot;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

public final class RootSerializer extends AbstractResourceSerializer<IContainer> {
  public RootSerializer(final Option ... options) {
    super(options);
  }

  public RootSerializer(final Iterable<Option> options) {
    super(options);
  }

  @SuppressWarnings("unused")
  static void putRoot(final StructuredContent content, final IWorkspaceRoot root, final Set<Option> options) {}
}
