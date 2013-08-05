package sk.stuba.fiit.perconik.core.listeners;

import java.util.EnumSet;
import java.util.Set;
import sk.stuba.fiit.perconik.core.AbstractFilteringListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceChangeEventType;

public abstract class AbstractResourceChangeListener extends AbstractFilteringListener<ResourceChangeEventType> implements ResourceChangeListener
{
	protected AbstractResourceChangeListener()
	{
		super(EnumSet.allOf(ResourceChangeEventType.class));
	}

	protected AbstractResourceChangeListener(final Set<ResourceChangeEventType> types)
	{
		super(types);
	}
}
