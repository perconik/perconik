package sk.stuba.fiit.perconik.preferences;

import static sk.stuba.fiit.perconik.preferences.ResourcePreferences.Keys.persistence;
import java.util.Set;
import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;
import sk.stuba.fiit.perconik.preferences.plugin.Activator;

/**
 * Resource preferences. Supports both <i>default</i>
 * and <i>instance</i> (actually used and stored) scopes.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourcePreferences extends AbstractPreferences
{
	private ResourcePreferences(final Scope scope)
	{
		super(scope);
	}

	/**
	 * Used to aid in default resource preferences initialization.
	 * 
	 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
	 * 
	 * @author Pavol Zbell
	 * @since 1.0
	 */
	public static final class Initializer extends AbstractPreferences.Initializer
	{
		/**
		 * The constructor.
		 */
		public Initializer()
		{
		}
	
		/**
		 * Called by the preference initializer to
		 * initialize default resource preferences.
		 * 
		 * <p><b>Warning:</b> Clients should not call this method.
		 * It will be called automatically by the preference initializer
		 * when the appropriate default preference node is accessed.
		 */
		@Override
		public final void initializeDefaultPreferences()
		{
			Set<ResourcePersistenceData> data = ResourcePersistenceData.defaults();
			
			ResourcePreferences.getDefault().setResourcePersistenceData(data);
		}
	}
	
	public static final class Keys extends AbstractPreferences.Keys
	{
		static final String prefix = Activator.PLUGIN_ID + ".resources.";
		
		public static final String persistence = prefix + "persistence";
	}

	/**
	 * Gets default resource preferences.
	 */
	public static final ResourcePreferences getDefault()
	{
		return new ResourcePreferences(Scope.DEFAULT);
	}
	
	/**
	 * Gets resource preferences.
	 */
	public static final ResourcePreferences getInstance()
	{
		return new ResourcePreferences(Scope.INSTANCE);
	}
	
	/**
	 * Sets resource persistence data.
	 * @param data resource persistence data
	 * @throws NullPointerException if {@code data} is {@code null}
	 */
	public final void setResourcePersistenceData(final Set<ResourcePersistenceData> data)
	{
		this.setValue(persistence, data);
	}

	/**
	 * Gets resource persistence data.
	 */
	public final Set<ResourcePersistenceData> getResourcePersistenceData()
	{		
		try
		{
			return (Set<ResourcePersistenceData>) this.getObject(persistence);
		}
		catch (RuntimeException e)
		{
			if (this.scope != Scope.DEFAULT)
			{
				return ResourcePersistenceData.defaults();
			}
			
			throw e;
		}
	}
}
