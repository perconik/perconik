package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;

public final class ResourcePreferences extends AbstractPreferences
{
	private static final String persistence = "persistence";
	
	private ResourcePreferences(final Scope scope)
	{
		super(scope, "resources");
	}

	public static final ResourcePreferences getDefault()
	{
		return new ResourcePreferences(Scope.DEFAULT);
	}
	
	public static final ResourcePreferences getInstance()
	{
		return new ResourcePreferences(Scope.INSTANCE);
	}
	
	// TODO beta
//	@Override
//	final Object restore(final String key, final Exception cause)
//	{
//		switch (key)
//		{
//			case persistence:
//				Set<ResourcePersistenceData> data = ResourcePersistenceData.defaults();
//				
//				this.setValue(key, data);
//				
//				return data;
//				
//			default:
//				throw new UnsupportedOperationException(cause);
//		}
//		
//	}

	public final void setResourcePersistenceData(final Set<ResourcePersistenceData> data)
	{
		this.setValue(this.key(persistence), data);
	}
	
	public final Set<ResourcePersistenceData> getResourcePersistenceData()
	{
		return (Set<ResourcePersistenceData>) this.getObject(this.key(persistence));
	}
}
