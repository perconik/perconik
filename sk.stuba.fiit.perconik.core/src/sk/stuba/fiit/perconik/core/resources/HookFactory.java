package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

public interface HookFactory<H extends Hook<?, T>, T extends Listener>
{
	public H create(T listener);
}
