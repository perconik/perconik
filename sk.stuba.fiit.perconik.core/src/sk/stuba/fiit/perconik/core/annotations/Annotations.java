package sk.stuba.fiit.perconik.core.annotations;

import java.lang.annotation.Annotation;
import sk.stuba.fiit.perconik.utilities.Exceptional;
import sk.stuba.fiit.perconik.utilities.MoreLists;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.Accessor;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.Accessors;
import com.google.common.base.CaseFormat;

/**
 * Static helper methods pertaining to the core annotations. 
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Annotations
{
	private Annotations()
	{
		throw new AssertionError();
	}
	
	public static final String toString(final Annotation annotation)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, annotation.annotationType().getSimpleName()));
		
		Exceptional<Accessor<Object>> result = Accessors.ofInstanceMethod(annotation, Object.class, "value");
		
		if (result.isSuccess())
		{
			builder.append(" (");
			
			for (Object value: MoreLists.wrap(result.get().get()))
			{
				builder.append(value instanceof Class ? ((Class<?>) value).getCanonicalName() : String.valueOf(value));
				builder.append(", ");
			}
			
			builder.setLength(builder.length() - 2);
			builder.append(")");
		}
		
		return builder.toString();
	}
}
