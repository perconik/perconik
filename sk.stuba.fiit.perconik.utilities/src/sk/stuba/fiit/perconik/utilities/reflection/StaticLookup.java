package sk.stuba.fiit.perconik.utilities.reflection;

import java.util.List;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;
import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

@Beta
public final class StaticLookup<T> implements Supplier<T>
{
	private final List<Accessor<? extends T>> accessors;
	
	private final Optional<RuntimeException> suppressed;
	
	StaticLookup(Builder<T> builder)
	{
		this.accessors = ImmutableList.copyOf(builder.accessors);
		
		if (!builder.suppressions.isEmpty())
		{
			RuntimeException suppressor = new ReflectionException("Static accessor construction");
			
			this.suppressed = Optional.of(MoreThrowables.initializeSuppressor(suppressor, Lists.reverse(builder.suppressions)));
		}
		else
		{
			this.suppressed = Optional.absent();
		}
	}
	
	public static final class Builder<T>
	{
		List<Accessor<? extends T>> accessors;
		
		List<Throwable> suppressions;
		
		public Builder()
		{
			this.accessors    = Lists.newArrayListWithExpectedSize(8);
			this.suppressions = Lists.newArrayListWithExpectedSize(8);
		}
		
		public final Builder<T> classConstant(Class<?> implementation, TypeToken<? extends T> type, String name)
		{
			try
			{
				this.accessors.add(StaticAccessor.ofClassConstant(implementation, type, name));
			}
			catch (Exception cause)
			{
				this.suppressions.add(cause);
			}
			
			return this;
		}

		public final Builder<T> classField(Class<?> implementation, TypeToken<? extends T> type, String name)
		{
			try
			{
				this.accessors.add(StaticAccessor.ofClassField(implementation, type, name));
			}
			catch (Exception cause)
			{
				this.suppressions.add(cause);
			}
			
			return this;
		}

		public final Builder<T> classConstructor(Class<? extends T> type)
		{
			return this.classConstructor(TypeToken.of(type));
		}

		public final Builder<T> classConstructor(TypeToken<? extends T> type)
		{
			try
			{
				this.accessors.add(StaticAccessor.ofClassConstructor(type));
			}
			catch (Exception cause)
			{
				this.suppressions.add(cause);
			}
			
			return this;
		}

		public final Builder<T> classMethod(Class<?> implementation, TypeToken<? extends T> type, String name)
		{
			try
			{
				this.accessors.add(StaticAccessor.ofClassMethod(implementation, type, name));
			}
			catch (Exception cause)
			{
				this.suppressions.add(cause);
			}
			
			return this;
		}
		
		public final Builder<T> enumConstant(Class<? extends T> type, String name)
		{
			return this.enumConstant(TypeToken.of(type), name);
		}

		public final Builder<T> enumConstant(TypeToken<? extends T> type, String name)
		{
			try
			{
				this.accessors.add(StaticAccessor.ofEnumConstant(type, name));
			}
			catch (Exception cause)
			{
				this.suppressions.add(cause);
			}
			
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
		List<Throwable> suppressions = Lists.newLinkedList();
		
		for (Accessor<? extends T> accessor: this.accessors)
		{
			try
			{
				return accessor.get();
			}
			catch (Throwable cause)
			{
				if (cause.getClass() == ReflectionException.class)
				{
					cause = cause.getCause();
				}
				
				suppressions.add(cause);
			}
		}

		RuntimeException failure = new ReflectionException("Static access failed");
		
		suppressions = Lists.reverse(suppressions);
		
		if (this.suppressed.isPresent())
		{
			suppressions.add(this.suppressed.get());
		}
		
		throw MoreThrowables.initializeSuppressor(failure, suppressions);
	}
}
