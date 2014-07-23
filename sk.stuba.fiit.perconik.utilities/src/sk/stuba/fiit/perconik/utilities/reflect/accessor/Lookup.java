package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import com.google.common.base.Supplier;

public interface Lookup<T> extends Supplier<T>
{
	@Override
	public T get();
}
