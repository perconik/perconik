package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import com.google.common.collect.Sets;

public final class ListenerStore extends AbstractStore<Set<Class<? extends Listener>>>
{
	ListenerStore(final IPreferenceStore store, final String key)
	{
		super(store, key);
	}
	
	public static final ListenerStore getDefaultStore()
	{
		return ListenerInitializer.store;
	}
	
	final void setDefault(final Iterable<Class<? extends Listener>> types)
	{
		this.store.setDefault(this.key, toString(Sets.newHashSet(types)));
	}
	
	public final void set(final ListenerProvider provider)
	{
		this.set(Sets.newHashSet(provider.classes()));
	}
	
	public final void set(final Iterable<Class<? extends Listener>> types)
	{
		this.store.setValue(this.key, toString(Sets.newHashSet(types)));
	}
	
	public final Set<Class<? extends Listener>> get()
	{
		return fromString(this.store.getString(this.key)); 
	}
}
