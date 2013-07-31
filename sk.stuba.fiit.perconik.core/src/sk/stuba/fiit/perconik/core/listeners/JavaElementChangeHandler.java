package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.core.JavaCore;

enum JavaElementChangeHandler implements Handler<JavaElementChangeListener>
{
	INSTANCE;
	
	public final void add(final JavaElementChangeListener listener)
	{
		JavaCore.addElementChangedListener(listener, Handlers.mask(listener));
	}

	public final void remove(final JavaElementChangeListener listener)
	{
		JavaCore.removeElementChangedListener(listener);
	}
}
