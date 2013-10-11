package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import sk.stuba.fiit.perconik.utilities.reflect.Reflections;
import com.google.common.collect.Maps;

final class EnumeratedAnnotable implements Annotable
{
	private final Map<Class<? extends Annotation>, Annotation> map;
	
	EnumeratedAnnotable(Iterator<Annotation> annotations)
	{
		this.map = Maps.uniqueIndex(annotations, Reflections.toAnnotationTypeFunction());
	}

	public final boolean hasAnnotation(Class<? extends Annotation> type)
	{
		return this.map.containsKey(type);
	}

	public final <A extends Annotation> A getAnnotation(Class<A> type)
	{
		return (A) this.map.get(type);
	}

	public final Collection<Annotation> getAnnotations()
	{
		return this.map.values();
	}
}
