package sk.stuba.fiit.perconik.utilities.function;

import javax.annotation.Nullable;
import com.google.common.base.Function;
import com.google.common.collect.Multimap;

public interface MultimapCollector<T, K, V> extends Function<T, Multimap<K, V>>
{
	@Override
	public Multimap<K, V> apply(@Nullable T input);
}
