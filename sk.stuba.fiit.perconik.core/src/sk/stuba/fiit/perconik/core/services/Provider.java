package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Nameable;

public interface Provider extends Nameable
{
	public Provider parent();
	
	// equals by name
	@Override
	public boolean equals(@Nullable Object o);
}
