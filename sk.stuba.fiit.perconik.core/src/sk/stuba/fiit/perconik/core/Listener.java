package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

public interface Listener extends Registrable
{
	// listeners should be equal if their implementing classes are equal
	@Override
	public boolean equals(@Nullable Object o);
}
