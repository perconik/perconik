package com.gratex.perconik.activity.ide.listeners;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import com.gratex.perconik.activity.ide.plugin.Activator;

public abstract class IdeListener extends Adapter
{
	static final PluginConsole console = Activator.getDefault().getConsole();
	
	static final Executor executor = Executors.newCachedThreadPool();
	
	IdeListener()
	{
	}
	
	static final long currentTime()
	{
		return System.currentTimeMillis();
	}
}
