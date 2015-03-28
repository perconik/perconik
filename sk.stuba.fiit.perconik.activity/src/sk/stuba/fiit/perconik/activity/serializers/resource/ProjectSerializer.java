package sk.stuba.fiit.perconik.activity.serializers.resource;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IProjectNatureDescriptor;
import org.eclipse.core.runtime.CoreException;

import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static com.google.common.collect.Lists.newArrayListWithExpectedSize;

import static org.eclipse.core.resources.IncrementalProjectBuilder.AUTO_BUILD;
import static org.eclipse.core.resources.IncrementalProjectBuilder.CLEAN_BUILD;
import static org.eclipse.core.resources.IncrementalProjectBuilder.FULL_BUILD;
import static org.eclipse.core.resources.IncrementalProjectBuilder.INCREMENTAL_BUILD;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.newStructuredContent;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class ProjectSerializer extends AbstractResourceSerializer<IProject> {
  public ProjectSerializer(final Option ... options) {
    super(options);
  }

  public ProjectSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putProject(final StructuredContent content, final IProject project, @SuppressWarnings("unused") final Set<Option> options) {
    try {
      List<Content> natures = newArrayListWithExpectedSize(8);

      for (IProjectNatureDescriptor descriptor: project.getWorkspace().getNatureDescriptors()) {
        String identifier = descriptor.getNatureId();

        if (project.hasNature(identifier)) {
          IProjectNature nature = project.getNature(identifier);

          StructuredContent natureContent = newStructuredContent();

          if (nature != null) {
            natureContent.put(key("class"), nature.getClass().getName());
          }

          natureContent.put(key("identifier"), identifier);
          natureContent.put(key("label"), descriptor.getLabel());

          natureContent.put(key("isEnabled"), project.isNatureEnabled(identifier));

          natures.add(natureContent);
        }
      }
    } catch (CoreException ignore) {}

    try {
      IProjectDescription description = project.getDescription();

      content.put(key("comment"), description.getComment());

      List<Content> builders = newArrayListWithExpectedSize(8);

      for (ICommand builder: description.getBuildSpec()) {
        StructuredContent builderContent = newStructuredContent();

        builderContent.put(key("name"), builder.getBuilderName());
        builderContent.put(key("arguments"), builder.getArguments());

        builderContent.put(key("canAuto"), builder.isBuilding(AUTO_BUILD));
        builderContent.put(key("canFull"), builder.isBuilding(FULL_BUILD));
        builderContent.put(key("canIncremental"), builder.isBuilding(INCREMENTAL_BUILD));
        builderContent.put(key("canClean"), builder.isBuilding(CLEAN_BUILD));

        builderContent.put(key("isConfigurable"), builder.isConfigurable());

        builders.add(builderContent);
      }

      content.put(key("build", "builders"), builders);
    } catch (CoreException ignore) {}

    try {
      content.put(key("build", "activeConfiguration"), project.getActiveBuildConfig().getName());

      List<String> configurations = newArrayListWithExpectedSize(8);

      for (IBuildConfiguration configuration: project.getBuildConfigs()) {
        String name = configuration.getName();

        if (name != null) {
          configurations.add(configuration.getName());
        }
      }

      content.put(key("build", "configurations"), configurations);
    } catch (CoreException ignore) {}

    content.put(key("isOpen"), project.isOpen());
  }
}
