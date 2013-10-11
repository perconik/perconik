package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.util.Collection;

public interface Annotable
{
	public boolean hasAnnotation(Class<? extends Annotation> type);
	
	public <A extends Annotation> A getAnnotation(Class<A> type);

	public Collection<Annotation> getAnnotations();
}
