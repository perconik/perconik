package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Nameable;

public interface Service extends Nameable, com.google.common.util.concurrent.Service
{
	// equals by name
	@Override
	public boolean equals(Object o);
}
