package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import com.google.common.base.Supplier;

public interface Accessor<T> extends Supplier<T>
{
	@Override
	public T get();
}
