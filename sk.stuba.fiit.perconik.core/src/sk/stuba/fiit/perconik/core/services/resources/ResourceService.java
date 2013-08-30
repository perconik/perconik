package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.Service;

public interface ResourceService extends Service
{
	public ResourceProvider getResourceProvider();
	
	public ResourceManager getResourceManager();
	
	public static interface Builder
	{
		public Builder provider(ResourceProvider provider);
		
		public Builder manager(ResourceManager manager);
		
		public ResourceService build(); 
	}
}
