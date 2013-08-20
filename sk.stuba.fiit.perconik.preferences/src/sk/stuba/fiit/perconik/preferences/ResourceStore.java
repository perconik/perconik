package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import com.google.common.collect.Sets;

public final class ResourceStore extends AbstractStore<Set<String>>
{
	ResourceStore(final IPreferenceStore store, final String key)
	{
		super(store, key);
	}

	public static final ResourceStore getDefaultStore()
	{
		return ResourceInitializer.store;
	}
	
	final void setDefault(final Iterable<String> names)
	{
		this.store.setDefault(this.key, toString(Sets.newHashSet(names)));
	}
	
	public final void set(final ResourceProvider provider)
	{
		this.set(Sets.newHashSet(provider.names()));
	}
	
	public final void set(final Iterable<String> names)
	{
		this.store.setValue(this.key, toString(Sets.newHashSet(names)));
	}
	
	public final Set<String> get()
	{
		return fromString(this.store.getString(this.key)); 
	}
}
