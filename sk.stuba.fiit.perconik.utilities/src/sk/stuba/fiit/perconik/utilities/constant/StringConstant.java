package sk.stuba.fiit.perconik.utilities.constant;

import javax.annotation.Nullable;

public interface StringConstant extends Constant
{
	public String getValue();
	
	@Override
	public boolean equals(@Nullable Object o);
}
