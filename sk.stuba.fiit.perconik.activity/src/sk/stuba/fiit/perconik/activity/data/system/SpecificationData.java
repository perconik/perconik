package sk.stuba.fiit.perconik.activity.data.system;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;

public class SpecificationData extends AnyStructuredData
{
	protected String name;
	
	protected String vendor;
	
	protected String version;
	
	public SpecificationData()
	{
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getName()
	{
		return this.name;
	}

	public String getVendor()
	{
		return this.vendor;
	}

	public String getVersion()
	{
		return this.version;
	}
}
