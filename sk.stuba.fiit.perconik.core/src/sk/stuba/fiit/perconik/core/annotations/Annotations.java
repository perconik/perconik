package sk.stuba.fiit.perconik.core.annotations;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;
import sk.stuba.fiit.perconik.utilities.Exceptional;
import sk.stuba.fiit.perconik.utilities.MoreLists;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.Accessor;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.Accessors;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;

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
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.append(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, annotation.annotationType().getSimpleName()));
		
		Exceptional<Accessor<Object>> result = Accessors.ofInstanceMethod(annotation, Object.class, "value");
		
		if (result.isSuccess())
		{
			Set<String> elements = Sets.newTreeSet();
			
			for (Object element: MoreLists.wrap(result.get().get()))
			{
				if (element instanceof Class)
				{
					elements.add(((Class<?>) element).getCanonicalName());
					
					continue;
				}

				String value = Objects.toString(element, "");
				
				if (value.isEmpty())
				{
					continue;
				}
				
				elements.add(value);
			}

			if (!elements.isEmpty())
			{
				builder.append(" (").list(elements, ", ").append(")");
			}
		}
		
		return builder.toString();
	}
}
