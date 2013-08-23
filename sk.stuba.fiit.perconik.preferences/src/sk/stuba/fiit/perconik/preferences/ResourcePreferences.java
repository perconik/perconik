package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.preferences.persistence.ResourcePersistenceData;

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
	
	public final void setResourcePersistenceData(final Set<ResourcePersistenceData> data)
	{
		this.setValue(this.key(persistence), data);
	}
	
	public final Set<ResourcePersistenceData> getResourcePersistenceData()
	{
		return (Set<ResourcePersistenceData>) this.getObject(this.key(persistence));
	}
}
