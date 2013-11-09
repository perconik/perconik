package sk.stuba.fiit.perconik.utilities.function;

import javax.annotation.Nullable;

public interface Numerate<T>
{
	public int apply(@Nullable T input);
	
	@Override
	public boolean equals(@Nullable Object object);
}
