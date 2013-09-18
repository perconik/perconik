package sk.stuba.fiit.perconik.utilities.constant;

import javax.annotation.Nullable;

public interface IntegralConstant extends Constant
{
	public int getValue();
	
	@Override
	public boolean equals(@Nullable Object o);
}
