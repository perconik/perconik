package sk.stuba.fiit.perconik.activity.data.eclipse;

import java.util.Arrays;

import org.eclipse.core.runtime.Platform;

import static sk.stuba.fiit.perconik.activity.data.eclipse.Utilities.urlOrNull;

public class StandardPlatformProbe implements PlatformProbe
{
	public StandardPlatformProbe()
	{
	}

	public PlatformData platform()
	{
		PlatformData data = new PlatformData();
		
		data.setProduct(ProductData.of(Platform.getProduct()));

		data.setApplicationArguments(Arrays.asList(Platform.getApplicationArgs()));
		data.setCommandLineArguments(Arrays.asList(Platform.getCommandLineArgs()));

		data.setConfigurationLocation(urlOrNull(Platform.getConfigurationLocation()));
		data.setInstallLocation(urlOrNull(Platform.getInstallLocation()));
		data.setInstanceLocation(urlOrNull(Platform.getInstanceLocation()));
		data.setUserLocation(urlOrNull(Platform.getUserLocation()));
		data.setWorkingLocation(urlOrNull(Platform.getLocation()));
		
		data.setNationalLanguage(Platform.getNL());
		data.setOperatingSystem(Platform.getOS());
		data.setOperatingSystemArchitecture(Platform.getOSArch());
		data.setWindowSystem(Platform.getWS());
		
		data.setStateStamp(Platform.getStateStamp());

		data.setRunning(Platform.isRunning());
		data.setDebugMode(Platform.inDebugMode());
		data.setDevelopmentMode(Platform.inDevelopmentMode());
		
		return data;
	}
}
