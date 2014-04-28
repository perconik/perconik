package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

final class EnumeratedAnnotable implements Annotable
{
	private final ImmutableMap<Class<? extends Annotation>, Annotation> map;
	
	EnumeratedAnnotable(Iterator<Annotation> annotations)
	{
		Builder<Class<? extends Annotation>, Annotation> builder = ImmutableMap.builder();
		
		while (annotations.hasNext())
		{
			Annotation annotation = annotations.next();
			
			builder.put(annotation.annotationType(), annotation);
		}
		
		this.map = builder.build();
	}

	public final boolean hasAnnotation(Class<? extends Annotation> type)
	{
		return this.map.containsKey(type);
	}

	public final <A extends Annotation> A getAnnotation(Class<A> type)
	{
		return (A) this.map.get(type);
	}

	public final List<Annotation> getAnnotations()
	{
		return this.map.values().asList();
	}
}
