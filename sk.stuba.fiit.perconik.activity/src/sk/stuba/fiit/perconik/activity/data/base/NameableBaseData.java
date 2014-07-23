package sk.stuba.fiit.perconik.activity.data.base;

import sk.stuba.fiit.perconik.core.Nameable;

public class NameableBaseData extends BaseData
{
	protected String name;
	
	public NameableBaseData()
	{
	}

	protected NameableBaseData(Nameable nameable)
	{
		if (nameable == null)
		{
			return;
		}
		
		this.setName(nameable.getName());
		this.setImplementation(ClassData.of(nameable.getClass()));
	}
	
	protected NameableBaseData(String name, Class<?> implementation)
	{
		super(implementation);
		
		if (name == null)
		{
			return;
		}
		
		this.setName(name);
	}

	public static NameableBaseData of(Nameable nameable)
	{
		return new NameableBaseData(nameable);
	}

	public static NameableBaseData of(String name, Class<?> implementation)
	{
		return new NameableBaseData(name, implementation);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}
}
