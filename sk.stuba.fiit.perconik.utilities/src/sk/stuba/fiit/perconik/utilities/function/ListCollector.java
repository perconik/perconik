package sk.stuba.fiit.perconik.utilities.function;

import java.util.List;

import javax.annotation.Nullable;

public interface ListCollector<T, E> extends Collector<T, E>
{
	@Override
	public List<E> apply(@Nullable T input);
}
