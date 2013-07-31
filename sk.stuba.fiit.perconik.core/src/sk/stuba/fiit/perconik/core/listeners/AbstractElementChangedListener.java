package sk.stuba.fiit.perconik.core.listeners;

import java.util.EnumSet;
import java.util.Set;
import sk.stuba.fiit.perconik.eclipse.jdt.core.ElementChangedEventType;

public abstract class AbstractElementChangedListener extends AbstractFilteringListener<ElementChangedEventType> implements JavaElementChangeListener
{
	protected AbstractElementChangedListener()
	{
		super(EnumSet.allOf(ElementChangedEventType.class));
	}
	
	protected AbstractElementChangedListener(final Set<ElementChangedEventType> types)
	{
		super(types);
	}
}
