package com.gratex.perconik.activity;

import java.net.URL;
import java.util.Set;
import javax.xml.namespace.QName;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;
import com.google.common.collect.ImmutableSet;
import com.gratex.perconik.activity.plugin.Activator;
import com.gratex.perconik.services.activity.IdeCheckinDto;
import com.gratex.perconik.services.activity.IdeCodeOperationDto;
import com.gratex.perconik.services.activity.IdeDocumentOperationTypeEnum;
import com.gratex.perconik.services.activity.IdeEventDto;
import com.gratex.perconik.services.activity.IdeProjectOperationDto;
import com.gratex.perconik.services.activity.IdeStateChangeDto;

public final class ActivityDefaults
{
	public static final URL watcherUrl;
	
	public static final QName watcherName;
	
	static
	{
		watcherUrl  = UniformResources.newUrl("http://localhost:7979/VsActivityWatcherService?wsdl");
		watcherName = new QName("http://tempuri.org/", "VsActivityWatcherService");
	}
			
	static final Set<Class<? extends IdeEventDto>> milestoneDataTypes;

	static final Set<IdeDocumentOperationTypeEnum> milestoneDocumentOperationTypes;

	static
	{
		milestoneDataTypes = ImmutableSet.of
		(
			IdeStateChangeDto.class,
			IdeProjectOperationDto.class,
			IdeCodeOperationDto.class,
			IdeCheckinDto.class
		);
	
		milestoneDocumentOperationTypes = ImmutableSet.of
		(
			IdeDocumentOperationTypeEnum.ADD,
			IdeDocumentOperationTypeEnum.REMOVE,
			IdeDocumentOperationTypeEnum.SAVE
		);
	}

	private ActivityDefaults()
	{
		throw new AssertionError();
	}

	static final PluginConsole console()
	{
		return Activator.getDefault().getConsole();
	}
}
