package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Arrays;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider.Builder;
import com.google.common.collect.Sets;

public final class ListenerProviders
{
	private ListenerProviders()
	{
		throw new AssertionError();
	}
	
	public static final ListenerProvider getSystemProvider()
	{
		return SystemListenerProvider.getInstance();
	}

	public static final Builder builder()
	{
		return StandardListenerProvider.builder();
	}
	
	public static final ListenerClassesSupplier supplier(final ListenerProvider provider)
	{
		return new ListenerClassesSupplier()
		{
			public final Set<Class<? extends Listener>> get()
			{
				return provider.classes();
			}
		};
	}
	
	public static final ListenerClassesSupplier merge(final ListenerClassesSupplier ... suppliers)
	{
		return merge(Arrays.asList(suppliers));
	}
	
	public static final ListenerClassesSupplier merge(final Iterable<ListenerClassesSupplier> suppliers)
	{
		return new ListenerClassesSupplier()
		{
			public final Set<Class<? extends Listener>> get()
			{
				Set<Class<? extends Listener>> classes = Sets.newHashSet();
				
				for (ListenerClassesSupplier supplier: suppliers)
				{
					classes.addAll(supplier.get());
				}				
				
				return classes;
			}
		};
	}
}
