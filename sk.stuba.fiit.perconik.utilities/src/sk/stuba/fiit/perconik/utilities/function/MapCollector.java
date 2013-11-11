package sk.stuba.fiit.perconik.utilities.function;

import java.util.Map;
import javax.annotation.Nullable;
import com.google.common.base.Function;

public interface MapCollector<T, K, V> extends Function<T, Map<K, V>>
{
	@Override
	public Map<K, V> apply(@Nullable T input);
}
