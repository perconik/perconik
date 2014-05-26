package sk.stuba.fiit.perconik.activity.data.eclipse;

import java.net.URL;
import java.util.List;
import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;

public class PlatformData extends AnyStructuredData
{
	protected ProductData product;
	
	protected List<String> applicationArguments;

	protected List<String> commandLineArguments;

	protected URL configurationLocation;
	
	protected URL installLocation;
	
	protected URL instanceLocation;
	
	protected URL userLocation;
	
	protected URL workingLocation;
	
	protected String nationalLanguage;
	
	protected String operatingSystem;
	
	protected String operatingSystemArchitecture;
	
	protected String windowSystem;
	
	protected long stateStamp;

	protected boolean running;

	protected boolean debugMode;
	
	protected boolean developmentMode;

	public PlatformData()
	{
	}

	public void setProduct(ProductData product)
	{
		this.product = product;
	}

	public void setApplicationArguments(List<String> applicationArguments)
	{
		this.applicationArguments = applicationArguments;
	}

	public void setCommandLineArguments(List<String> commandLineArguments)
	{
		this.commandLineArguments = commandLineArguments;
	}

	public void setConfigurationLocation(URL configurationLocation)
	{
		this.configurationLocation = configurationLocation;
	}

	public void setInstallLocation(URL installLocation)
	{
		this.installLocation = installLocation;
	}

	public void setInstanceLocation(URL instanceLocation)
	{
		this.instanceLocation = instanceLocation;
	}

	public void setUserLocation(URL userLocation)
	{
		this.userLocation = userLocation;
	}

	public void setWorkingLocation(URL workingLocation)
	{
		this.workingLocation = workingLocation;
	}

	public void setNationalLanguage(String nationalLanguage)
	{
		this.nationalLanguage = nationalLanguage;
	}

	public void setOperatingSystem(String operatingSystem)
	{
		this.operatingSystem = operatingSystem;
	}

	public void setOperatingSystemArchitecture(String operatingSystemArchitecture)
	{
		this.operatingSystemArchitecture = operatingSystemArchitecture;
	}

	public void setWindowSystem(String windowSystem)
	{
		this.windowSystem = windowSystem;
	}

	public void setStateStamp(long stateStamp)
	{
		this.stateStamp = stateStamp;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	public void setDebugMode(boolean debugMode)
	{
		this.debugMode = debugMode;
	}

	public void setDevelopmentMode(boolean developmentMode)
	{
		this.developmentMode = developmentMode;
	}

	public ProductData getProduct()
	{
		return this.product;
	}

	public List<String> getApplicationArguments()
	{
		return this.applicationArguments;
	}

	public List<String> getCommandLineArguments()
	{
		return this.commandLineArguments;
	}

	public URL getConfigurationLocation()
	{
		return this.configurationLocation;
	}

	public URL getInstallLocation()
	{
		return this.installLocation;
	}

	public URL getInstanceLocation()
	{
		return this.instanceLocation;
	}

	public URL getUserLocation()
	{
		return this.userLocation;
	}

	public URL getWorkingLocation()
	{
		return this.workingLocation;
	}

	public String getNationalLanguage()
	{
		return this.nationalLanguage;
	}

	public String getOperatingSystem()
	{
		return this.operatingSystem;
	}

	public String getOperatingSystemArchitecture()
	{
		return this.operatingSystemArchitecture;
	}

	public String getWindowSystem()
	{
		return this.windowSystem;
	}

	public long getStateStamp()
	{
		return this.stateStamp;
	}

	public boolean isRunning()
	{
		return this.running;
	}

	public boolean isDebugMode()
	{
		return this.debugMode;
	}

	public boolean isDevelopmentMode()
	{
		return this.developmentMode;
	}
}
