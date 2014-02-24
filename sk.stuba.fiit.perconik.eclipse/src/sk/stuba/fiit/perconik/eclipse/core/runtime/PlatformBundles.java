package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Static utility methods pertaining to standard Eclipse bundles.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class PlatformBundles
{
	static final String platform = "org.eclipse.platform";

	static final String jdt = "org.eclipse.jdt";
	
	static final String pde = "org.eclipse.pde";
	
	static final String rcp = "org.eclipse.rcp";
	
	static final String sdk = "org.eclipse.sdk";
	
	private PlatformBundles()
	{
		throw new AssertionError();
	}
	
	public static final Bundle getPlatformBundle()
	{
		return Platform.getBundle(platform);
	}
	
	public static final Bundle getJdtBundle()
	{
		return Platform.getBundle(jdt);
	}
	
	public static final Bundle getPdeBundle()
	{
		return Platform.getBundle(pde);
	}
	
	public static final Bundle getRcpBundle()
	{
		return Platform.getBundle(rcp);
	}

	public static final Bundle getSdkBundle()
	{
		return Platform.getBundle(sdk);
	}
}
