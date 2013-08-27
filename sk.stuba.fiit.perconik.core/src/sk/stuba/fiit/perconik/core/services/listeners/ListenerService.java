package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.Service;

public interface ListenerService extends Service
{
	public ListenerProvider getListenerProvider();
	
	public ListenerManager getListenerManager();

	public ListenerInitializer getListenerInitializer();
	
	public static interface Builder
	{
		public Builder provider(ListenerProvider provider);
		
		public Builder manager(ListenerManager manager);
		
		public Builder initializer(ListenerInitializer initializer);
		
		public ListenerService build(); 
	}
}
