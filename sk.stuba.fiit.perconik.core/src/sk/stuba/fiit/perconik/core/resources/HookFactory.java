package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

public interface HookFactory<T, L extends Listener>
{
	public Hook<T, L> create(L listener);
}
