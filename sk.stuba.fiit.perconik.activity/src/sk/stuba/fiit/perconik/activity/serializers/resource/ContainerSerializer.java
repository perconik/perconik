package sk.stuba.fiit.perconik.activity.serializers.resource;

import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class ContainerSerializer extends AbstractResourceSerializer<IContainer> {
  public ContainerSerializer(final Option ... options) {
    super(options);
  }

  public ContainerSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putContainer(final StructuredContent content, final IContainer container, final Set<Option> options) {
    if (options.contains(StandardOption.TREE)) {
      try {
        content.put(key("members"), new ResourceSerializer(options).serialize(asList(container.members())));
      } catch (CoreException ignore) {}
    }
  }
}
