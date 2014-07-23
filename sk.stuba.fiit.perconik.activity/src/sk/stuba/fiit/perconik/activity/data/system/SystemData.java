package sk.stuba.fiit.perconik.activity.data.system;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;

public class SystemData extends AnyStructuredData
{
	protected JavaData java;
	
	protected OperatingSystemData operatingSystem;
	
	protected UserData user;
	
	public SystemData()
	{
	}

	public void setJava(JavaData java)
	{
		this.java = java;
	}

	public void setOperatingSystem(OperatingSystemData operatingSystem)
	{
		this.operatingSystem = operatingSystem;
	}

	public void setUser(UserData user)
	{
		this.user = user;
	}

	public JavaData getJava()
	{
		return this.java;
	}

	public OperatingSystemData getOperatingSystem()
	{
		return this.operatingSystem;
	}

	public UserData getUser()
	{
		return this.user;
	}
}
