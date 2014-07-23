package sk.stuba.fiit.perconik.activity.data.base;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;

public class BaseData extends AnyStructuredData
{
	protected ClassData implementation;
	
	public BaseData()
	{
	}

	protected BaseData(Class<?> implementation)
	{
		if (implementation == null)
		{
			return;
		}
		
		this.setImplementation(ClassData.of(implementation));
	}

	public static BaseData of(Class<?> type)
	{
		return new BaseData(type);
	}

	public void setImplementation(ClassData implementation)
	{
		this.implementation = implementation;
	}

	public ClassData getImplementation()
	{
		return this.implementation;
	}
}
