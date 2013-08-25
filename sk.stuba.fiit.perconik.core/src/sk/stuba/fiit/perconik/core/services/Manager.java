package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Nameable;

public interface Manager extends Nameable
{
	// equals by name
	@Override
	public boolean equals(@Nullable Object o);
}
