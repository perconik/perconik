package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.FilteringListener;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

final class Handlers
{
	private Handlers()
	{
		throw new AssertionError();
	}
	
	static final <E extends Enum<E> & IntegralConstant> int mask(final FilteringListener<E> listener)
	{
		return IntegralConstantSupport.constantsAsInteger(listener.getEventTypes());
	}
}
