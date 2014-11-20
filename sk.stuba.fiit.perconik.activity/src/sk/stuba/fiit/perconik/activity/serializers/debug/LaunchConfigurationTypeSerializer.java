package sk.stuba.fiit.perconik.activity.serializers.debug;

import org.eclipse.debug.core.ILaunchConfigurationType;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class LaunchConfigurationTypeSerializer extends AbstractConfigurableSerializer<ILaunchConfigurationType> {
  public LaunchConfigurationTypeSerializer(final Option ... options) {
    super(options);
  }

  public LaunchConfigurationTypeSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putLaunchConfigurationType(final StructuredContent content, final ILaunchConfigurationType type) {
    content.put(key("category"), type.getCategory());

    content.put(key("identifier"), type.getIdentifier());
    content.put(key("name"), type.getName());

    content.put(key("plugin", "identifier"), type.getPluginIdentifier());
    content.put(key("contributor", "name"), type.getContributorName());

    content.put(key("supportedModeCombinations"), type.getSupportedModeCombinations());

    content.put(key("source", "pathComputer", "identifier"), type.getSourcePathComputer().getId());
    content.put(key("source", "locator", "identifier"), type.getSourceLocatorId());

    content.put(key("isPublic"), type.isPublic());
  }

  @Override
  protected void put(final StructuredContent content, final ILaunchConfigurationType type) {
    putObjectIdentity(content, type);
    putLaunchConfigurationType(content, type);
  }
}
