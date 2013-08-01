package sk.stuba.fiit.perconik.core.listeners;

import java.util.EnumSet;
import java.util.Set;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementChangeEventType;

public abstract class AbstractElementChangedListener extends AbstractFilteringListener<JavaElementChangeEventType> implements JavaElementChangeListener
{
	protected AbstractElementChangedListener()
	{
		super(EnumSet.allOf(JavaElementChangeEventType.class));
	}
	
	protected AbstractElementChangedListener(final Set<JavaElementChangeEventType> types)
	{
		super(types);
	}
}
