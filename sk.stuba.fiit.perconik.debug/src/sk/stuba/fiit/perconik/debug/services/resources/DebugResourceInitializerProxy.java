package sk.stuba.fiit.perconik.debug.services.resources;

import sk.stuba.fiit.perconik.core.services.resources.ResourceInitializer;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;
import com.google.common.base.Preconditions;

public final class DebugResourceInitializerProxy extends DebugObjectProxy implements DebugResourceInitializer
{
	private final ResourceInitializer initializer;
	
	private DebugResourceInitializerProxy(final ResourceInitializer initializer, final DebugConsole console)
	{
		super(console);
		
		this.initializer = Preconditions.checkNotNull(initializer);
	}
	
	public static final DebugResourceInitializerProxy wrap(final ResourceInitializer initializer)
	{
		return wrap(initializer, Debug.getDefaultConsole());
	}

	public static final DebugResourceInitializerProxy wrap(final ResourceInitializer initializer, final DebugConsole console)
	{
		if (initializer instanceof DebugResourceInitializerProxy)
		{
			return (DebugResourceInitializerProxy) initializer;
		}
		
		return new DebugResourceInitializerProxy(initializer, console);
	}
	
	public static final ResourceInitializer unwrap(final ResourceInitializer initializer)
	{
		if (initializer instanceof DebugResourceInitializerProxy)
		{
			return ((DebugResourceInitializerProxy) initializer).delegate();
		}
		
		return initializer;
	}

	@Override
	protected final ResourceInitializer delegate()
	{
		return this.initializer;
	}

	public final void run()
	{
		this.print("Registering resources:");
		this.tab();
		
		this.delegate().run();

		this.untab();
		this.print("Resources registered");
		
		DebugResources.printRegistrations(this.getDebugConsole());
	}
}
