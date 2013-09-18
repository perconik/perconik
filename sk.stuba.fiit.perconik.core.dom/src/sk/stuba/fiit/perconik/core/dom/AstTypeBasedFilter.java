package sk.stuba.fiit.perconik.core.dom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public abstract class AstTypeBasedFilter<N extends ASTNode, F extends ASTNode> implements AstFilter<N> 
{
	static final Mode defaultMode = Mode.INCLUDE;
	
	static final Strategy defaultStrategy = Strategy.INSTANCE_OF;
	
	final Mode mode;
	
	final Strategy strategy;
	
	AstTypeBasedFilter(final Mode mode, final Strategy strategy)
	{
		this.mode     = Preconditions.checkNotNull(mode);
		this.strategy = Preconditions.checkNotNull(strategy);
	}
	
	private static final <N extends ASTNode, F extends ASTNode> Single<N, F> newDefaultSingle(final Class<? extends F> type)
	{
		return new Single<>(defaultMode, defaultStrategy, type);
	}
	
	private static final <N extends ASTNode, F extends ASTNode> Multi<N, F> newDefaultMulti(final Set<Class<? extends F>> types)
	{
		return new Multi<>(defaultMode, defaultStrategy, types);
	}
	
	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> of(final Class<? extends F> type)
	{
		return newDefaultSingle(type);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> of(final Class<? extends F> a, final Class<? extends F> b)
	{
		return newDefaultMulti(ImmutableSet.of(a, b));
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> of(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c)
	{
		return newDefaultMulti(ImmutableSet.of(a, b, c));
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> of(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d)
	{
		return newDefaultMulti(ImmutableSet.of(a, b, c, d));
	}

	@SafeVarargs
	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> of(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d, final Class<? extends F> ... rest)
	{
		return newDefaultMulti(ImmutableSet.<Class<? extends F>>builder().add(a).add(b).add(c).add(d).addAll(Arrays.asList(rest)).build());
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> of(final Iterable<Class<? extends F>> types)
	{
		return newDefaultMulti(ImmutableSet.copyOf(types));
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> of(final Iterator<Class<? extends F>> types)
	{
		return newDefaultMulti(ImmutableSet.copyOf(types));
	}
	
	public static final class Builder<N extends ASTNode, F extends ASTNode>
	{
		private Mode mode;
		
		private Strategy strategy;
		
		private final Set<Class<? extends F>> types; 
		
		public Builder()
		{
			this.types = Sets.newHashSet();
		}
		
		public final Builder<N, F> include()
		{
			Preconditions.checkState(this.mode == null);

			this.mode = Mode.INCLUDE;
			
			return this;
		}

		public final Builder<N, F> exclude()
		{
			Preconditions.checkState(this.mode == null);
			
			this.mode = Mode.EXCLUDE;
			
			return this;
		}

		public final Builder<N, F> exact()
		{
			Preconditions.checkState(this.strategy == null);
			
			this.strategy = Strategy.EXACT;
			
			return this;
		}

		public final Builder<N, F> instanceOf()
		{
			Preconditions.checkState(this.strategy == null);
			
			this.strategy = Strategy.INSTANCE_OF;
			
			return this;
		}
		
		public final Builder<N, F> type(Class<? extends F> type)
		{
			this.types.add(type);
			
			return this;
		}
		
		public final Builder<N, F> types(Collection<Class<? extends F>> types)
		{
			this.types.addAll(types);
			
			return this;
		}

		public final AstTypeBasedFilter<N, F> build()
		{
			if (this.mode == null)
			{
				this.mode = defaultMode;
			}

			if (this.strategy == null)
			{
				this.strategy = defaultStrategy;
			}
			
			Set<Class<? extends F>> types = ImmutableSet.copyOf(this.types);
			
			switch (types.size())
			{
				case 0:
					throw new IllegalStateException();
				case 1:
					return new Single<>(this.mode, this.strategy, types.iterator().next());
				default:
					return new Multi<>(this.mode, this.strategy, types);
			}
		}
	}
	
	public static final <N extends ASTNode, F extends ASTNode> Builder<N, F> builder()
	{
		return new Builder<>();
	}
	
	private static enum Mode
	{
		INCLUDE
		{
			@Override
			final boolean apply(final boolean result)
			{
				return result;
			}
		},
		
		EXCLUDE
		{
			@Override
			final boolean apply(final boolean result)
			{
				return !result;
			}
		};
		
		abstract boolean apply(final boolean result);
	}
	
	private static enum Strategy
	{
		EXACT
		{
			@Override
			final boolean compute(final Class<?> type, @Nullable final Object o)
			{
				return o != null && type == o.getClass();
			}
		},
		
		INSTANCE_OF
		{
			@Override
			final boolean compute(final Class<?> type, @Nullable final Object o)
			{
				return type.isInstance(o);
			}
		};
		
		abstract boolean compute(Class<?> type, Object o);
	}

	private static final class Single<N extends ASTNode, F extends ASTNode> extends AstTypeBasedFilter<N, F>
	{
		private final Class<? extends F> type;
		
		private final Set<Class<? extends F>> typeAsSet;
		
		Single(final Mode mode, final Strategy strategy, final Class<? extends F> type)
		{
			super(mode, strategy);
			
			this.type      = Preconditions.checkNotNull(type);
			this.typeAsSet = ImmutableSet.<Class<? extends F>>of(this.type);
		}

		@Override
		public final boolean accept(@Nullable final N node)
		{
			return this.mode.apply(this.strategy.compute(this.type, node));
		}

		@Override
		public final Set<Class<? extends F>> getFilteredTypes()
		{
			return this.typeAsSet;
		}
	}

	private static final class Multi<N extends ASTNode, F extends ASTNode>  extends AstTypeBasedFilter<N, F>
	{
		private final Set<Class<? extends F>> types;
		
		Multi(final Mode mode, final Strategy strategy, final Set<Class<? extends F>> types)
		{
			super(mode, strategy);
			
			for (Class<? extends F> type: types)
			{
				Preconditions.checkNotNull(type);
			}
			
			this.types = types;
		}

		@Override
		public final boolean accept(@Nullable final N node)
		{
			for (Class<? extends F> type: this.types)
			{
				if (this.mode.apply(this.strategy.compute(type, node)))
				{
					return true;
				}
			}

			return false;
		}

		@Override
		public final Set<Class<? extends F>> getFilteredTypes()
		{
			return this.types;
		}
	}

	@Override
	public final boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof AstTypeBasedFilter))
		{
			return false;
		}
		
		return this.getFilteredTypes().equals(((AstTypeBasedFilter<?, ?>) o).getFilteredTypes());
	}

	@Override
	public final int hashCode()
	{
		return this.getFilteredTypes().hashCode();
	}
	
	public abstract Set<Class<? extends F>> getFilteredTypes();
}
