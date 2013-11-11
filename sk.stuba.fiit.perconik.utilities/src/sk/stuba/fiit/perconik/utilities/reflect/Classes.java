package sk.stuba.fiit.perconik.utilities.reflect;

import com.google.common.base.CaseFormat;

public final class Classes
{
	private Classes()
	{
		throw new AssertionError();
	}
	
	public static final String toMethodName(Class<?> type)
	{
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, type.getSimpleName());
	}
}
