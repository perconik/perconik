package sk.stuba.fiit.perconik.core.listeners;

import java.util.EnumSet;
import java.util.Set;
import sk.stuba.fiit.perconik.core.AbstractFilteringListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceChangeEventType;

public abstract class AbstractResourceListener extends AbstractFilteringListener<ResourceChangeEventType> implements ResourceListener
{
	protected AbstractResourceListener()
	{
		super(EnumSet.allOf(ResourceChangeEventType.class));
	}

	protected AbstractResourceListener(final Set<ResourceChangeEventType> types)
	{
		super(types);
	}
}
