package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

final class DelegatedAnnotable implements Annotable
{
	private final AnnotatedElement element;
	
	DelegatedAnnotable(AnnotatedElement element)
	{
		this.element = Preconditions.checkNotNull(element);
	}

	public final boolean hasAnnotation(Class<? extends Annotation> type)
	{
		return this.element.isAnnotationPresent(type);
	}

	public final <A extends Annotation> A getAnnotation(Class<A> type)
	{
		return this.getAnnotation(type);
	}

	public final Collection<Annotation> getAnnotations()
	{
		return ImmutableList.copyOf(this.element.getAnnotations());
	}
}