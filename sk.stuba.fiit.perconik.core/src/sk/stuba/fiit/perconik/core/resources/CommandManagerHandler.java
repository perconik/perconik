package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.CommandManagerListener;

enum CommandManagerHandler implements Handler<CommandManagerListener>
{
	INSTANCE;
	
	public final void register(final CommandManagerListener listener)
	{
		throw new UnsupportedOperationException("Not implemented yet");
		
//		final Runnable addListener = new Runnable()
//		{
//			@Override
//			public final void run()
//			{
//				// TODO
//			}
//		};
//	
//		Display.getDefault().asyncExec(addListener);
	}

	public final void unregister(final CommandManagerListener listener)
	{
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
