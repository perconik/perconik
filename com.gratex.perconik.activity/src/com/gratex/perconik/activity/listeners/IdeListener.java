package com.gratex.perconik.activity.listeners;

import com.gratex.perconik.activity.plugin.Activator;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

abstract class IdeListener extends Adapter
{
	final PluginConsole console;
	
	IdeListener()
	{
		this.console = Activator.getDefault().getConsole();
	}
}
