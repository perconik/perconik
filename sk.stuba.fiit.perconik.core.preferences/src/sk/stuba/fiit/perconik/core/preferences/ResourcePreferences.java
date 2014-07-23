package sk.stuba.fiit.perconik.core.preferences;

import java.util.Set;

import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;
import sk.stuba.fiit.perconik.core.preferences.plugin.Activator;
import sk.stuba.fiit.perconik.preferences.AbstractPreferences;

import static sk.stuba.fiit.perconik.core.preferences.ResourcePreferences.Keys.persistence;

/**
 * Resource preferences. Supports both <i>default</i>
 * and <i>instance</i> (actually used and stored) scopes.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourcePreferences extends AbstractRegistrationPreferences<ResourcePersistenceData>
{
	static final String qualifier = Activator.PLUGIN_ID + ".resources";

	private ResourcePreferences(final Scope scope)
	{
		super(scope, qualifier);
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
		public static final String persistence = qualifier + ".persistence";
	}

	/**
	 * Gets default scoped core preferences.
	 */
	public static final ResourcePreferences getDefault()
	{
		return new ResourcePreferences(Scope.DEFAULT);
	}

	/**
	 * Gets configuration scoped core preferences.
	 */
	public static final ResourcePreferences getConfiguration()
	{
		return new ResourcePreferences(Scope.CONFIGURATION);
	}

	/**
	 * Sets resource persistence data.
	 * @param data resource persistence data
	 * @throws NullPointerException if {@code data} is {@code null}
	 */
	public final void setResourcePersistenceData(final Set<ResourcePersistenceData> data)
	{
		this.setRegistrations(persistence, data);
	}

	/**
	 * Gets resource persistence data.
	 */
	public final Set<ResourcePersistenceData> getResourcePersistenceData()
	{
		return this.getRegistrations(persistence);
	}

	@Override
	final Set<ResourcePersistenceData> getDefaultRegistrations()
	{
		return ResourcePersistenceData.defaults();
	}
}
