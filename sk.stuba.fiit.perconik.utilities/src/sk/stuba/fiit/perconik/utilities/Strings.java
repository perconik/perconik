package sk.stuba.fiit.perconik.utilities;

import java.util.Comparator;

public final class Strings
{
	private Strings()
	{
		throw new AssertionError();
	}

	private enum ToStringComparator implements Comparator<Object>
	{
		INSTANCE;
		
		public final int compare(final Object a, final Object b)
		{
			return a.toString().compareTo(b.toString());
		}
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
		
		if (toDefaultString(o).equals(result))
		{
			return null;
		}
		
		return result;
	}

	public static final <T> Comparator<T> toStringComparator()
	{
		@SuppressWarnings("unchecked")
		Comparator<T> comparator = (Comparator<T>) ToStringComparator.INSTANCE;
		
		return comparator;
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
