package sk.stuba.fiit.perconik.utilities.reflect;

import java.util.List;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

final class CompositeClassResolver implements ClassResolver
{
	private final List<ClassResolver> resolvers;
	
	CompositeClassResolver(Iterable<ClassResolver> resolvers)
	{
		this.resolvers = ImmutableList.copyOf(resolvers);
		
		Preconditions.checkArgument(!this.resolvers.isEmpty());
	}
	
	public final Class<?> forName(String name) throws ClassNotFoundException
	{
		List<Throwable> suppressions = Lists.newLinkedList();
		
		for (ClassResolver resolver: this.resolvers)
		{
			try
			{
				return resolver.forName(name);
			}
			catch (Exception e)
			{
				suppressions.add(e);
			}
		}
		
		ClassNotFoundException failure = new ClassNotFoundException(name + " not found");
		
		throw MoreThrowables.initializeSuppressor(failure, Lists.reverse(suppressions));
	}
}
