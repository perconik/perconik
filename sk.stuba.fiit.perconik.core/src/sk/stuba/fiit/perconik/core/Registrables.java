package sk.stuba.fiit.perconik.core;

import sk.stuba.fiit.perconik.core.annotations.Experimental;
import sk.stuba.fiit.perconik.core.annotations.Unsafe;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;

/**
 * Static accessor methods pertaining to the registrables core. 
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Registrables
{
	private Registrables()
	{
		throw new AssertionError();
	}
	
	public static final boolean isExperimental(final Class<? extends Registrable> type)
	{
		return type.isAnnotationPresent(Experimental.class);
	}

	public static final boolean isExperimental(final Registrable registrable)
	{
		return isExperimental(registrable.getClass());
	}
	
	public static final boolean isUnsafe(final Class<? extends Registrable> type)
	{
		return type.isAnnotationPresent(Unsafe.class);
	}

	public static final boolean isUnsafe(final Registrable registrable)
	{
		return isExperimental(registrable.getClass());
	}
	
	public static final boolean isUnsupported(final Class<? extends Registrable> type)
	{
		return type.isAnnotationPresent(Unsupported.class);
	}

	public static final boolean isUnsupported(final Registrable registrable)
	{
		return isExperimental(registrable.getClass());
	}
}
