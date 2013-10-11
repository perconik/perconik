package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public final class Annotations
{
	private Annotations()
	{
		throw new AssertionError();
	}
	
	public static final Set<Annotation> ofClass(Class<?> type)
	{
		return ofElement(type);
	}

	public static final Set<Annotation> ofClasses(Iterable<? extends Class<?>> types)
	{
		return ofElements(types);
	}

	public static final Set<Annotation> ofElement(AnnotatedElement element)
	{
		return ImmutableSet.copyOf(element.getAnnotations());
	}
	
	public static final Set<Annotation> ofElements(Iterable<? extends AnnotatedElement> elements)
	{
		Set<Annotation> annotations = Sets.newLinkedHashSet();
		
		for (AnnotatedElement element: elements)
		{
			annotations.addAll(ofElement(element));
		}
		
		return annotations;
	}

	public static final Annotable asAnnotable(Collection<Annotation> annotations)
	{
		return new EnumeratedAnnotable(annotations.iterator());
	}
	
	public static final Annotable asAnnotable(Iterable<Annotation> annotations)
	{
		return new EnumeratedAnnotable(annotations.iterator());
	}
	
	public static final Annotable asAnnotable(Iterator<Annotation> annotations)
	{
		return new EnumeratedAnnotable(annotations);
	}
}
