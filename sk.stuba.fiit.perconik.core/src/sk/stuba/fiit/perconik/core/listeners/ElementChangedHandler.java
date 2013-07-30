package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.core.JavaCore;

enum ElementChangedHandler implements Handler<ElementChangedListener>
{
	INSTANCE;
	
	public final void add(final ElementChangedListener listener)
	{
		JavaCore.addElementChangedListener(listener, Handlers.mask(listener));
	}

	public final void remove(final ElementChangedListener listener)
	{
		JavaCore.removeElementChangedListener(listener);
	}
}
