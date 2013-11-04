package com.gratex.perconik.activity.ide.listeners;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
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
	
	static final IEditorPart dereferenceEditor(final IEditorReference reference)
	{
		return reference.getEditor(false);
	}
}
