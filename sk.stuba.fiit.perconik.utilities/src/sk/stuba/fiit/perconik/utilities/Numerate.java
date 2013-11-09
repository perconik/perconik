package sk.stuba.fiit.perconik.utilities;

import javax.annotation.Nullable;

public interface Numerate<T>
{
	  int apply(@Nullable T input);

	  @Override
	  boolean equals(@Nullable Object object);
}
