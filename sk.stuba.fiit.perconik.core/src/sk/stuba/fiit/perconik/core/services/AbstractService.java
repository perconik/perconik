package sk.stuba.fiit.perconik.core.services;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Service;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.Strings;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

public abstract class AbstractService extends com.google.common.util.concurrent.AbstractService implements Service
{
	protected AbstractService()
	{
	}
	
	protected final void checkState(final State first, final State ... rest)
	{
		Set<State> states = EnumSet.of(first, rest);
		
		if (!states.contains(this.state()))
		{
			SmartStringBuilder builder = new SmartStringBuilder();
			
			builder.append(this.toString()).append(" must be in state ");

			Function<State, String> toLowerCase = new Function<State, String>() {
				public final String apply(final State state)
				{
					return state.toString().toLowerCase();
				}
			};
			
			List<State >list = Lists.newArrayList(states);
			
			Collections.sort(list, Strings.toStringComparator());

			builder.list(Lists.transform(list, toLowerCase));
			
			throw new IllegalStateException(builder.toString());
		}
	}
	
	protected final void checkRunning()
	{
		this.checkState(State.RUNNING);
	}
	
	@Override
	public final String toString()
	{
		return this.getName() + " [" + this.state().toString().toLowerCase() + "]";
	}

	public final String getName()
	{
		return this.getClass().getName();
	}
}
