package sk.stuba.fiit.perconik.utilities.reflection;

import java.util.List;
import sk.stuba.fiit.perconik.utilities.FallbackException;
import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

@Beta
public final class StaticLookup<T> implements Supplier<T>
{
	private final List<Accessor<? extends T>> accessors;
	
	StaticLookup(Builder<T> builder)
	{
		Preconditions.checkArgument(!builder.accessors.isEmpty());
		
		this.accessors = ImmutableList.copyOf(builder.accessors);
	}
	
	public static final class Builder<T>
	{
		List<Accessor<? extends T>> accessors;
		
		public Builder()
		{
			this.accessors = Lists.newArrayListWithExpectedSize(16);
		}
		
		public final Builder<T> classConstant(Class<?> implementation, TypeToken<T> type, String name) throws IllegalAccessException, NoSuchFieldException
		{
			this.accessors.add(StaticAccessor.ofClassConstant(implementation, type, name));
			
			return this;
		}

		public final Builder<T> classField(Class<?> implementation, TypeToken<T> type, String name) throws NoSuchFieldException
		{
			this.accessors.add(StaticAccessor.ofClassField(implementation, type, name));
			
			return this;
		}

		public final Builder<T> classConstructor(Class<T> type) throws NoSuchMethodException
		{
			this.accessors.add(StaticAccessor.ofClassConstructor(type));
			
			return this;
		}

		public final Builder<T> classMethod(Class<?> implementation, TypeToken<T> type, String name) throws NoSuchMethodException
		{
			this.accessors.add(StaticAccessor.ofClassMethod(implementation, type, name));
			
			return this;
		}
		
		public final Builder<T> enumConstant(Class<T> type, String name)
		{
			this.accessors.add(StaticAccessor.ofEnumConstant(type, name));
			
			return this;
		}

		public final StaticLookup<T> build()
		{
			return new StaticLookup<>(this);
		}
	}
	
	public static final <T> Builder<T> builder()
	{
		return new Builder<>();
	}

	public final T get()
	{
		RuntimeException failure = new IllegalStateException("Unable to get value");
		
		for (Accessor<? extends T> accessor: this.accessors)
		{
			try
			{
				return accessor.get();
			}
			catch (RuntimeException cause)
			{
				failure = new FallbackException(failure, cause);
			}
		}
		
		throw failure;
	}
}
