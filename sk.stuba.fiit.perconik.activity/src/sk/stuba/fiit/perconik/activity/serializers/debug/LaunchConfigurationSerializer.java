package sk.stuba.fiit.perconik.activity.serializers.debug;

import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.activity.serializers.resource.FileSerializer;
import sk.stuba.fiit.perconik.activity.serializers.resource.ProjectSerializer;
import sk.stuba.fiit.perconik.activity.serializers.resource.ResourceSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class LaunchConfigurationSerializer extends AbstractConfigurableMultiSerializer<ILaunchConfiguration> {
  public LaunchConfigurationSerializer(final Option ... options) {
    super(options);
  }

  public LaunchConfigurationSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putLaunchConfiguration(final StructuredContent content, final ILaunchConfiguration configuration, final Set<Option> options) {
    content.put(key("name"), configuration.getName());
    content.put(key("file"), new FileSerializer(options).serialize(configuration.getFile()));

    try {
      content.put(key("type"), configuration.getType());
    } catch (CoreException ignore) {}

    try {
      content.put(key("category"), configuration.getCategory());
    } catch (CoreException ignore) {}

    try {
      content.put(key("modes"), configuration.getModes());
    } catch (CoreException ignore) {}

    content.put(key("projects"), new ProjectSerializer(options).serialize(Projects.fromLaunchConfiguration(configuration)));

    try {
      IResource[] resources = configuration.getMappedResources();

      if (resources != null) {
        content.put(key("mappedResources"), new ResourceSerializer(options).serialize(asList(resources)));
      }
    } catch (CoreException ignore) {}

    content.put(key("exists"), configuration.exists());

    content.put(key("isLocal"), configuration.isLocal());
    content.put(key("isReadOnly"), configuration.isReadOnly());
    content.put(key("isWorkingCopy"), configuration.isWorkingCopy());
  }

  @Override
  protected void put(final StructuredContent content, final ILaunchConfiguration configuration) {
    putObjectIdentity(content, configuration);
    putLaunchConfiguration(content, configuration, this.options);
  }
}
