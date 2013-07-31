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
		String result = o.toString();
		
		if (result == null)
		{
			return toDefaultString(o);
		}
		
		String name = o.getClass().getCanonicalName();
		
		if (result != toDefaultString(o) || com.google.common.base.Strings.isNullOrEmpty(name))
		{
			return result;
		}

		return name + "@" + Integer.toHexString(o.hashCode());
	}
}
