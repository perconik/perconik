package sk.stuba.fiit.perconik.utilities.function;

import java.util.Set;
import javax.annotation.Nullable;

public interface SetCollector<T, E> extends Collector<T, E>
{
	@Override
	public Set<E> apply(@Nullable T input);
}
