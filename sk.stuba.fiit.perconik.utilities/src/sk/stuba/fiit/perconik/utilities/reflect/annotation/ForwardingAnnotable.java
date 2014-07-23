package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.util.List;

import com.google.common.collect.ForwardingObject;

public abstract class ForwardingAnnotable extends ForwardingObject implements Annotable
{
	protected ForwardingAnnotable()
	{
	}
	
	@Override
	protected abstract Annotable delegate();

	public boolean hasAnnotation(Class<? extends Annotation> type)
	{
		return this.delegate().hasAnnotation(type);
	}

	public <A extends Annotation> A getAnnotation(Class<A> type)
	{
		return this.delegate().getAnnotation(type);
	}

	public List<Annotation> getAnnotations()
	{
		return this.delegate().getAnnotations();
	}
}
