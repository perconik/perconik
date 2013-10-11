package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.Iterator;
import com.google.common.collect.Iterators;

public final class Annotables
{
	private Annotables()
	{
		throw new AssertionError();
	}
	
	public static final Annotable fromAnnotations(Collection<Annotation> annotations)
	{
		return new EnumeratedAnnotable(annotations.iterator());
	}
	
	public static final Annotable fromAnnotations(Iterable<Annotation> annotations)
	{
		return new EnumeratedAnnotable(annotations.iterator());
	}
	
	public static final Annotable fromAnnotations(Iterator<Annotation> annotations)
	{
		return new EnumeratedAnnotable(annotations);
	}

	public static final Annotable fromAnnotations(Annotation[] annotations)
	{
		return new EnumeratedAnnotable(Iterators.forArray(annotations));
	}

	public static final Annotable fromClass(Class<?> type)
	{
		return new DelegatedAnnotable(type);
	}

	public static final Annotable fromElement(AnnotatedElement element)
	{
		return new DelegatedAnnotable(element);
	}
}
