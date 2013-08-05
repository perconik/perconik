package sk.stuba.fiit.perconik.core.listeners;

import java.util.EnumSet;
import java.util.Set;
import sk.stuba.fiit.perconik.core.AbstractFilteringListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementChangeEventType;

public abstract class AbstractJavaElementChangeListener extends AbstractFilteringListener<JavaElementChangeEventType> implements JavaElementChangeListener
{
	protected AbstractJavaElementChangeListener()
	{
		super(EnumSet.allOf(JavaElementChangeEventType.class));
	}
	
	protected AbstractJavaElementChangeListener(final Set<JavaElementChangeEventType> types)
	{
		super(types);
	}
}
