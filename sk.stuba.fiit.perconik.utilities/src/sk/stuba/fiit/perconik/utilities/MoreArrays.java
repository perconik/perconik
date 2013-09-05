package sk.stuba.fiit.perconik.utilities;

import com.google.common.annotations.Beta;

@Beta
public final class MoreArrays
{
	private MoreArrays()
	{
		throw new AssertionError();
	}
	
	public static final boolean contains(Object[] a, Object key)
	{
		return search(a, key) >= 0;
	}
	
	public static final int search(Object[] a, Object key)
	{
		int length = a.length;
		
		for (int i = 0; i < length; i ++)
		{
			Object o = a[i];
			
			if (key == null ? o == null : key.equals(o))
			{
				return i;
			}
		}
		
		return -1;
	}
}
