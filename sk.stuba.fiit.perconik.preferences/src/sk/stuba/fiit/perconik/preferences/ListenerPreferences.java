package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;

public final class ListenerPreferences extends AbstractPreferences
{
	private static final String persistence = "persistence";
	
	private ListenerPreferences(final Scope scope)
	{
		super(scope, "listeners");
	}

	public static final ListenerPreferences getDefault()
	{
		return new ListenerPreferences(Scope.DEFAULT);
	}
	
	public static final ListenerPreferences getInstance()
	{
		return new ListenerPreferences(Scope.INSTANCE);
	}
	
	public final void setListenerPersistenceData(final Set<ListenerPersistenceData> data)
	{
		this.setValue(this.key(persistence), data);
	}
	
	public final Set<ListenerPersistenceData> getListenerPersistenceData()
	{
		return (Set<ListenerPersistenceData>) this.getObject(this.key(persistence));
	}
}
