package sk.stuba.fiit.perconik.utilities.function;

import javax.annotation.Nullable;

import com.google.common.collect.Multiset;

public interface MultisetCollector<T, E> extends Collector<T, E>
{
	@Override
	public Multiset<E> apply(@Nullable T input);
}
