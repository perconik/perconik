package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

public interface HookFactory<T extends Listener>
{
	public Hook<?, T> create(T listener);
}
