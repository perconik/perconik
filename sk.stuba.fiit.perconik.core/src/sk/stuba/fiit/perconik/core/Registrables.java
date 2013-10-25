package sk.stuba.fiit.perconik.core;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.Set;
import sk.stuba.fiit.perconik.core.annotations.Experimental;
import sk.stuba.fiit.perconik.core.annotations.Persistent;
import sk.stuba.fiit.perconik.core.annotations.Unsafe;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.utilities.reflect.Reflections;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotable;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotations;

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
	
	@Persistent
	private static enum PersistentMark
	{
	}
	
	private static final Persistent persistent = PersistentMark.class.getAnnotation(Persistent.class);
	
	public static final <R extends Registrable> Annotable toAnnotable(final Class<R> type)
	{
		LinkedList<Class<? super R>> types = Reflections.collectSuperclasses(type);

		types.addFirst(type);
		
		Set<Annotation> annotations = Annotations.ofClasses(types);
		
		if (Serializable.class.isAssignableFrom(type))
		{
			annotations.add(persistent);
		}
		
		return Annotations.asAnnotable(annotations); 
	}
	
	public static final boolean isExperimental(final Class<? extends Registrable> type)
	{
		return type.isAnnotationPresent(Experimental.class);
	}
	
	public static final boolean isPersistent(final Class<? extends Registrable> type)
	{
		return type.isAnnotationPresent(Persistent.class);
	}
	
	public static final boolean isUnsafe(final Class<? extends Registrable> type)
	{
		return type.isAnnotationPresent(Unsafe.class);
	}

	public static final boolean isUnsupported(final Class<? extends Registrable> type)
	{
		return type.isAnnotationPresent(Unsupported.class);
	}
}
