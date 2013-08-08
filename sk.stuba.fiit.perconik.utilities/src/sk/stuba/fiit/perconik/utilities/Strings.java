package sk.stuba.fiit.perconik.utilities;

public final class Strings
{
	private Strings()
	{
		throw new AssertionError();
	}
	
	public static final String toDefaultString(final Object o)
	{
		return o.getClass().getName() + "@" + Integer.toHexString(o.hashCode());
	}
	
	public static final String toCanonicalString(final Object o)
	{
		String name = o.getClass().getCanonicalName();
		
		if (name == null)
		{
			return null;
		}
		
		return name + "@" + Integer.toHexString(o.hashCode());
	}
	
	public static final String toImplementedString(final Object o)
	{
		String result = o.toString();
		
		if (result == toDefaultString(o))
		{
			return null;
		}
		
		return result;
	}

	public static final String toStringFallback(final Object o)
	{
		String result = toImplementedString(o);
		
		if (result != null)
		{
			return result;
		}

		result = toCanonicalString(o);
		
		if (result == null)
		{
			return result;
		}

		return toDefaultString(o);
	}
}
