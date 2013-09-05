package sk.stuba.fiit.perconik.utilities.reflection;

import com.google.common.annotations.Beta;
import com.google.common.base.Supplier;

@Beta
public interface Accessor<T> extends Supplier<T>
{
	@Override
	public T get();
}
