package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.core.resources.IResourceChangeListener;
import sk.stuba.fiit.perconik.core.FilteringListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceChangeEventType;

public interface ResourceChangeListener extends FilteringListener<ResourceChangeEventType>, IResourceChangeListener
{
}
