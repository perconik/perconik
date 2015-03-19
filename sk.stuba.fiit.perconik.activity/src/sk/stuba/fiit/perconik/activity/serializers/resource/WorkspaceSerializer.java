package sk.stuba.fiit.perconik.activity.serializers.resource;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProjectNatureDescriptor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static com.google.common.collect.Lists.newArrayListWithExpectedSize;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.newStructuredContent;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class WorkspaceSerializer extends AbstractConfigurableMultiSerializer<IWorkspace> {
  public WorkspaceSerializer(final Option ... options) {
    super(options);
  }

  public WorkspaceSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putWorkspace(final StructuredContent content, final IWorkspace workspace, final Set<Option> options) {
    IWorkspaceRoot root = workspace.getRoot();

    if (options.contains(StandardOption.TREE)) {
      content.put(key("root"), new RootSerializer(options).serialize(root));
    } else {
      content.put(key("root", "name"), root.getName());
      content.put(key("root", "uri"), root.getLocationURI());
    }

    IWorkspaceDescription description = workspace.getDescription();

    content.put(key("build", "order"), description.getBuildOrder());
    content.put(key("build", "maxIterations"), description.getMaxBuildIterations());

    List<Content> natures = newArrayListWithExpectedSize(8);

    for (IProjectNatureDescriptor descriptor: workspace.getNatureDescriptors()) {
      StructuredContent natureContent = newStructuredContent();

      natureContent.put(key("identifier"), descriptor.getNatureId());
      natureContent.put(key("label"), descriptor.getLabel());

      natures.add(natureContent);
    }

    content.put(key("knownNatures"), natures);

    content.put(key("snapshotInterval"), description.getSnapshotInterval());

    content.put(key("isAutoBuilding"), workspace.isAutoBuilding());
    content.put(key("isTreeLocked"), workspace.isTreeLocked());
  }

  @Override
  protected void put(final StructuredContent content, final IWorkspace workspace) {
    putObjectIdentity(content, workspace);
    putWorkspace(content, workspace, this.options);
  }
}
