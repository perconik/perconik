package sk.stuba.fiit.perconik.activity.serializers.resource;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.activity.serializers.git.MappedResourceSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.resource.ContainerSerializer.putContainer;
import static sk.stuba.fiit.perconik.activity.serializers.resource.FileSerializer.putFile;
import static sk.stuba.fiit.perconik.activity.serializers.resource.FolderSerializer.putFolder;
import static sk.stuba.fiit.perconik.activity.serializers.resource.ProjectSerializer.putProject;
import static sk.stuba.fiit.perconik.activity.serializers.resource.ResourceSerializer.putResource;
import static sk.stuba.fiit.perconik.activity.serializers.resource.RootSerializer.putRoot;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

abstract class AbstractResourceSerializer<T extends IResource> extends AbstractConfigurableMultiSerializer<T> {
  AbstractResourceSerializer(final Option ... options) {
    super(options);
  }

  AbstractResourceSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T resource) {
    putObjectIdentity(content, resource);
    putResource(content, resource, this.options);

    if (resource instanceof IContainer) {
      putContainer(content, (IContainer) resource, this.options);

      if (resource instanceof IWorkspaceRoot) {
        putRoot(content, (IWorkspaceRoot) resource, this.options);
      }

      if (resource instanceof IProject) {
        putProject(content, (IProject) resource, this.options);
      }

      if (resource instanceof IFolder) {
        putFolder(content, (IFolder) resource, this.options);
      }
    }

    if (resource instanceof IFile) {
      putFile(content, (IFile) resource, this.options);
    }

    content.put(key("git"), new MappedResourceSerializer(this.options).serialize(resource));
  }
}
