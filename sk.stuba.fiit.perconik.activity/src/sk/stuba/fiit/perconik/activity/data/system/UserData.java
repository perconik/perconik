package sk.stuba.fiit.perconik.activity.data.system;

import java.nio.file.Path;
import sk.stuba.fiit.perconik.activity.data.AnyData;

public class UserData extends AnyData
{
	protected String name;
	
	protected Path home;
	
	protected Path directory;
	
	public UserData()
	{
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setHome(Path home)
	{
		this.home = home;
	}

	public void setDirectory(Path directory)
	{
		this.directory = directory;
	}

	public String getName()
	{
		return this.name;
	}

	public Path getHome()
	{
		return this.home;
	}

	public Path getDirectory()
	{
		return this.directory;
	}
}
