package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.CommandManagerChangeListener;

enum CommandManagerChangeHandler implements Handler<CommandManagerChangeListener>
{
	INSTANCE;
	
	public final void register(final CommandManagerChangeListener listener)
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

	public final void unregister(final CommandManagerChangeListener listener)
	{
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
