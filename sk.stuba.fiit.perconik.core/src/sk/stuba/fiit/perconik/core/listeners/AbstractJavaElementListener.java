package sk.stuba.fiit.perconik.core.listeners;

import java.util.EnumSet;
import java.util.Set;
import sk.stuba.fiit.perconik.core.AbstractFilteringListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementChangeEventType;

public abstract class AbstractJavaElementListener extends AbstractFilteringListener<JavaElementChangeEventType> implements JavaElementListener
{
	protected AbstractJavaElementListener()
	{
		super(EnumSet.allOf(JavaElementChangeEventType.class));
	}
	
	protected AbstractJavaElementListener(final Set<JavaElementChangeEventType> types)
	{
		super(types);
	}
}
