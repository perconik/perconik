package sk.stuba.fiit.perconik.activity.data.core;

import java.util.Set;
import sk.stuba.fiit.perconik.activity.data.AnyData;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotations;

public class ClassData extends AnyData
{
	protected String name;
	
	protected String canonicalName;
	
	protected Set<AnnotationData> annotations;
	
	public ClassData()
	{
	}

	protected ClassData(Class<?> type)
	{
		this.setName(type.getName());
		this.setCanonicalName(type.getCanonicalName());
		this.setAnnotations(AnnotationData.of(Annotations.ofClass(type)));
	}

	public static ClassData of(Class<?> type)
	{
		return new ClassData(type);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setCanonicalName(String canonicalName)
	{
		this.canonicalName = canonicalName;
	}

	public void setAnnotations(Set<AnnotationData> annotations)
	{
		this.annotations = annotations;
	}

	public String getName()
	{
		return this.name;
	}

	public String getCanonicalName()
	{
		return this.canonicalName;
	}

	public Set<AnnotationData> getAnnotations()
	{
		return this.annotations;
	}
}
