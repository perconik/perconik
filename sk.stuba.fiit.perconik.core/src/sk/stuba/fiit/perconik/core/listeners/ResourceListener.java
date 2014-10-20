package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.core.resources.IResourceChangeListener;

import sk.stuba.fiit.perconik.core.FilteringListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;

/**
 * A resource listener.
 *
 * @see FilteringListener
 * @see IResourceChangeListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceListener extends FilteringListener<ResourceEventType>, IResourceChangeListener {
}
