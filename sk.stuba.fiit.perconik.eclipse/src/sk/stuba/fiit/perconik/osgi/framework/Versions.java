package sk.stuba.fiit.perconik.osgi.framework;

import java.util.List;
import org.osgi.framework.Version;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public final class Versions
{
	private static final String	separator = ".";
	
	private Versions()
	{
		throw new AssertionError();
	}
	
	public static enum Component
	{
		MAJOR
		{
			@Override
			public final Integer get(final Version version)
			{
				return version.getMajor();
			}
		},
		
		MINOR
		{
			@Override
			public final Integer get(final Version version)
			{
				return version.getMinor();
			}
		},
		
		MICRO
		{
			@Override
			public final Integer get(final Version version)
			{
				return version.getMicro();
			}
		},
		
		QUALIFIER
		{
			@Override
			public final String get(final Version version)
			{
				return version.getQualifier();
			}
		};

		public abstract Object get(final Version version);
		
		@Override
		public final String toString()
		{
			return this.name().toLowerCase();
		}
	}
	
	public static final String toString(final Version version, final Component ... components)
	{
		List<Object> values = Lists.newArrayListWithCapacity(components.length); 
		
		for (Component component: components)
		{
			values.add(component.get(version));
		}
		
		return Joiner.on(separator).join(values);
	}

	public static final String getSeparator()
	{
		return separator;
	}
}
