package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

interface Wrapper<L extends Listener> extends Listener
{
	public L forListener();
}
