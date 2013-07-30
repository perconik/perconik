package sk.stuba.fiit.perconik.core.listeners;

import sk.stuba.fiit.perconik.core.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.core.utilities.IntegralConstantSupport;

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
