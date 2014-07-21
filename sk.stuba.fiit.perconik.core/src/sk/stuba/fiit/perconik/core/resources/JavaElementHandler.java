package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jdt.core.JavaCore;

import sk.stuba.fiit.perconik.core.listeners.JavaElementListener;

enum JavaElementHandler implements Handler<JavaElementListener>
{
	INSTANCE;
	
	public final void register(final JavaElementListener listener)
	{
		JavaCore.addElementChangedListener(listener, Handlers.mask(listener));
	}

	public final void unregister(final JavaElementListener listener)
	{
		JavaCore.removeElementChangedListener(listener);
	}
}
