package com.gratex.perconik.activity.listeners;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import com.gratex.perconik.activity.plugin.Activator;

public abstract class IdeListener extends Adapter
{
	final PluginConsole console;
	
	IdeListener()
	{
		this.console = Activator.getDefault().getConsole();
	}
}
