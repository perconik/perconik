package com.gratex.perconik.activity.ide;

import static sk.stuba.fiit.perconik.utilities.SmartStringBuilder.builder;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ForwardingPluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;
import com.gratex.perconik.activity.plugin.Activator;

public final class IdeConsole extends ForwardingPluginConsole
{
	private static final IdeConsole instance = new IdeConsole();

	private static final AtomicReference<PluginConsole> console = new AtomicReference<>();
	
	private IdeConsole()
	{
	}
	
	public static final IdeConsole getInstance()
	{
		return instance;
	}
	
	@Override
	protected final PluginConsole delegate()
	{
		Activator activator = Activator.getDefault();
		
		if (activator != null)
		{
			console.set(activator.getConsole());
		}
		
		return console.get();
	}
	
	private final String hook(final String message)
	{
		if (IdeActivityPreferences.isEventLoggerEnabled())
		{
			this.notice(message);
		}
		
		return builder().format(Internals.dateFormat, new Date()).appendln().lines(message).toString(); 
	}

	@Override
	public final PluginConsole append(@Nullable final CharSequence s)
	{
		return super.append(this.hook(String.valueOf(s)));
	}

	@Override
	public final PluginConsole append(@Nullable final CharSequence s, final int from, final int to)
	{
		return super.append(this.hook(String.valueOf(s).substring(from, to)));
	}

	@Override
	public final PluginConsole append(final char c)
	{
		return super.append(this.hook(String.valueOf(c)));
	}

	@Override
	public final void put(final String message)
	{
		super.put(this.hook(message));
	}

	@Override
	public final void put(final String format, final Object ... args)
	{
		super.put(this.hook(String.format(format, args)));
	}

	@Override
	public final void print(final String message)
	{
		super.print(this.hook(message));
	}

	@Override
	public final void print(final String format, final Object ... args)
	{
		super.print(this.hook(String.format(format, args)));
	}
}
