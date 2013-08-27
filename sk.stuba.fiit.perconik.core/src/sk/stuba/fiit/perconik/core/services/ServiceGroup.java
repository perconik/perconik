package sk.stuba.fiit.perconik.core.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Service.State;

public final class ServiceGroup<S extends Service> extends ForwardingSet<S>
{
	private final Set<S> services;
	
	ServiceGroup(final Collection<S> services)
	{
		this.services = services instanceof ImmutableSet ? (Set<S>) services : ImmutableSet.copyOf(services);
	}

	public static final <S extends Service> ServiceGroup<S> of()
	{
		return new ServiceGroup<>(ImmutableSet.<S>of());
	}

	public static final <S extends Service> ServiceGroup<S> of(final S service)
	{
		return new ServiceGroup<>(ImmutableSet.of(service));
	}

	public static final <S extends Service> ServiceGroup<S> of(final S a, final S b)
	{
		return new ServiceGroup<>(ImmutableSet.of(a, b));
	}
	
	@SafeVarargs
	public static final <S extends Service> ServiceGroup<S> of(final S first, final S second, final S ... rest)
	{
		return new ServiceGroup<>(Lists.asList(first, second, rest));
	}
	
	@Override
	protected final Set<S> delegate()
	{
		return this.services;
	}

	
	public final Map<S, ListenableFuture<State>> start()
	{
		ImmutableMap.Builder<S, ListenableFuture<State>> builder = ImmutableMap.builder();
		
		for (S service: this.services)
		{
			builder.put(service, service.start());
		}
		
		return builder.build();
	}
	
	public final Map<S, State> startAndWait()
	{
		ImmutableMap.Builder<S, State> builder = ImmutableMap.builder();
		
		for (S service: this.services)
		{
			builder.put(service, service.startAndWait());
		}
		
		return builder.build();
	}
	
	public final Map<S, ListenableFuture<State>> stop()
	{
		ImmutableMap.Builder<S, ListenableFuture<State>> builder = ImmutableMap.builder();
		
		for (S service: this.services)
		{
			builder.put(service, service.stop());
		}
		
		return builder.build();
	}
	
	public final Map<S, State> stopAndWait()
	{
		ImmutableMap.Builder<S, State> builder = ImmutableMap.builder();
		
		for (S service: this.services)
		{
			builder.put(service, service.stopAndWait());
		}
		
		return builder.build();
	}
	
	public final Map<S, State> states()
	{
		ImmutableMap.Builder<S, State> builder = ImmutableMap.builder();
		
		for (S service: this.services)
		{
			builder.put(service, service.state());
		}
		
		return builder.build();
	}

	public final boolean isRunning()
	{
		return this.inState(State.RUNNING);
	}

	public final boolean inState(final State state)
	{
		for (Service service: this.services)
		{
			if (!state.equals(service.state()))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public final void waitForState(final State state)
	{
		while (!this.inState(state)) {}
	}
	
	public final <U extends S> ServiceGroup<U> narrow(final Class<U> type)
	{
		ImmutableSet.Builder<U> builder = ImmutableSet.builder();
		
		for (S service: this.services)
		{
			if (type.isInstance(service))
			{
				builder.add(type.cast(service));
			}
		}
		
		return new ServiceGroup<>(builder.build());
	}
	
	public final <U extends S> U fetch(final Class<U> type)
	{
		Iterator<U> iterator = this.narrow(type).iterator();
		
		U service = iterator.next();

		Preconditions.checkArgument(!iterator.hasNext());
		
		return service;
	}
	
	public final ServiceGroup<S> reverse()
	{
		return new ServiceGroup<>(Lists.reverse(Lists.newArrayList(this.services)));
	}
}
