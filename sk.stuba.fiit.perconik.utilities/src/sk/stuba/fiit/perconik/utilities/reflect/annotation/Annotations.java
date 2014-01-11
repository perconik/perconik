package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import sk.stuba.fiit.perconik.utilities.Exceptional;
import sk.stuba.fiit.perconik.utilities.MoreLists;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.Accessor;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.Accessors;
import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
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
	
	public static final String toString(final Annotation annotation)
	{
		Class<? extends Annotation> type = annotation.annotationType();
		
		StringBuilder builder = new StringBuilder(keyToString(type.getSimpleName()));
		
		Exceptional<Accessor<Object>> result = Accessors.ofInstanceMethod(annotation, Object.class, "value");
		
		if (result.isSuccess())
		{
			String value = valueToString(result.get().get());

			if (!value.isEmpty())
			{
				builder.append(" (").append(value).append(")");
			}
		}
		else
		{
			List<Method> methods = Arrays.asList(type.getDeclaredMethods());
			
			for (Method method: methods)
			{
				builder.append(keyToString(method.getName())).append(": ");
				
				result = Accessors.ofInstanceMethod(annotation, Object.class, method.getName());
				
				String value = valueToString(result.get().get());
				
				builder.append(!value.isEmpty() ? value : "null");
			}
		}
		
		return builder.toString();
	}
	
	public static final String toString(Iterable<Annotation> annotations)
	{
		Set<String> values = Sets.newTreeSet();
		
		for (Annotation annotation: annotations)
		{
			values.add(toString(annotation));
		}
		
		return Joiner.on(", ").join(values);
	}
	
	private static final String keyToString(Object object)
	{
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, object.toString());
	}
	
	private static final String valueToString(Object object)
	{
		Set<String> elements = Sets.newTreeSet();
		
		for (Object element: MoreLists.wrap(object))
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
		
		return Joiner.on(", ").join(elements);
	}
}
