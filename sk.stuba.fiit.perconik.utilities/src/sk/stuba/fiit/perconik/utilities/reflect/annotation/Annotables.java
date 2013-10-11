package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.reflect.AnnotatedElement;

public final class Annotables
{
	private Annotables()
	{
		throw new AssertionError();
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
