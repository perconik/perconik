package sk.stuba.fiit.perconik.activity.data.core;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotations;
import com.google.common.collect.Sets;

public class AnnotationData extends AnyStructuredData
{
	protected Class<? extends Annotation> type;
	
	protected String name;
	
	protected Map<String, Object> elements;
	
	public AnnotationData()
	{
	}
	
	protected AnnotationData(Annotation annotation)
	{
		this.setType(annotation.annotationType());
		this.setName(this.type.getName());
		this.setElements(Annotations.toElements(annotation));
	}
	
	public static AnnotationData of(Annotation annotation)
	{
		return new AnnotationData(annotation);
	}

	public static Set<AnnotationData> of(Iterable<Annotation> annotations)
	{
		Set<AnnotationData> data = Sets.newLinkedHashSet();
		
		for (Annotation annotation: annotations)
		{
			data.add(new AnnotationData(annotation));
		}
		
		return data;
	}

	public void setType(Class<? extends Annotation> type)
	{
		this.type = type;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setElements(Map<String, Object> elements)
	{
		this.elements = elements;
	}

	public Class<? extends Annotation> getType()
	{
		return this.type;
	}

	public String getName()
	{
		return this.name;
	}

	public Map<String, Object> getElements()
	{
		return this.elements;
	}
}
