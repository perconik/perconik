package com.gratex.perconik.activity.ide;

import java.net.URL;
import javax.xml.namespace.QName;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;
import com.gratex.perconik.activity.MilestoneResolver;
import com.gratex.perconik.activity.TimeSupplier;
import com.gratex.perconik.activity.ide.plugin.Activator;
import com.gratex.perconik.services.vs.IdeEventDto;

public final class IdeActivityDefaults
{
	public static final URL watcherUrl;
	
	public static final QName watcherName;
	
	static
	{
		watcherUrl  = UniformResources.newUrl("http://localhost:7979/VsActivityWatcherService?wsdl");
		watcherName = new QName("http://tempuri.org/", "VsActivityWatcherService");
	}
	
	private static final MilestoneResolver<IdeEventDto> milestoneResolver;
	
	private static final TimeSupplier timeSupplier;
	
	static
	{
		milestoneResolver = Debug.flags.contains("always-milestone") ? Debug.milestoneResolver : Internals.milestoneResolver;
		timeSupplier      = Debug.flags.contains("fixed-year")       ? Debug.timeSupplier      : Internals.timeSupplier;
	}

	private IdeActivityDefaults()
	{
		throw new AssertionError();
	}

	public static final PluginConsole getConsole()
	{
		return Activator.getDefault().getConsole();
	}
	
	public static final MilestoneResolver<IdeEventDto> getMilestoneResolver()
	{
		return milestoneResolver;
	}
	
	public static final TimeSupplier getTimeSupplier()
	{
		return timeSupplier;
	}
}
