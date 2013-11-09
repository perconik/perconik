package sk.stuba.fiit.perconik.utilities.function;

import java.util.Set;
import javax.annotation.Nullable;

public interface SetCollector<T, R> extends Collector<T, R>
{
	@Override
	public Set<R> apply(@Nullable T input);
}
