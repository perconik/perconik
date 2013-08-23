package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Nameable;

public interface Provider extends Nameable
{
	// equals by name
	@Override
	public boolean equals(Object o);
}
