package sk.stuba.fiit.perconik.utilities.constant;

import javax.annotation.Nullable;

public interface TypeConstant<T> extends Constant
{
	public Class<? extends T> getType();
	
	@Override
	public boolean equals(@Nullable Object o);
}
