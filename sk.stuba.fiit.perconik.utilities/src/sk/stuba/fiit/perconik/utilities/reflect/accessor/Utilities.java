package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import com.google.common.annotations.Beta;

@Beta
final class Utilities
{
	private Utilities()
	{
		throw new AssertionError();
	}
	
	static final String format(String format, Object ... args)
	{
		for (int i = 0; i < args.length; i ++)
		{
			if (args[i] instanceof Class)
			{
				args[i] = ((Class<?>) args[i]).getName();
			}
		}
		
		return String.format(format, args);
	}
	
	static final RuntimeException createArgument(String format, Object ... args)
	{
		return new IllegalArgumentException(format(format, args));
	}
	
	static final RuntimeException createState(String format, Object ... args)
	{
		return new IllegalStateException(format(format, args));
	}
	
	static final void throwArgument(String format, Object ... args)
	{
		throw createArgument(format, args);
	}
	
	static final void throwState(String format, Object ... args)
	{
		throw createState(format(format, args));
	}

	static final void checkArgument(boolean expression, String format, Object ... args)
	{
		if (!expression)
		{
			throwArgument(format, args);
		}
	}

	static final void checkState(boolean expression, String format, Object ... args)
	{
		if (!expression)
		{
			throwState(format, args);
		}
	}
}
