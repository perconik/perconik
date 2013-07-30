package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.core.resources.IResourceChangeListener;
import sk.stuba.fiit.perconik.core.resources.ResourceChangeEventType;

public interface ResourceChangeListener extends FilteringListener<ResourceChangeEventType>, IResourceChangeListener
{
}
