package sk.stuba.fiit.perconik.core.java.dom;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public abstract class NodeClassFilter<N extends ASTNode, R extends ASTNode> implements Predicate<N> 
{
	static final Mode defaultMode = Mode.INCLUDE;
	
	static final Strategy defaultStrategy = Strategy.INSTANCE_OF;
	
	final Mode mode;
	
	final Strategy strategy;
	
	NodeClassFilter(final Mode mode, final Strategy strategy)
	{
		this.mode     = checkNotNull(mode);
		this.strategy = checkNotNull(strategy);
	}
	
	private static final <N extends ASTNode, R extends ASTNode> Single<N, R> newSingle(final Class<? extends R> implementation)
	{
		return new Single<>(defaultMode, defaultStrategy, implementation);
	}
	
	private static final <N extends ASTNode, R extends ASTNode> Multi<N, R> newMulti(final Set<Class<? extends R>> implementations)
	{
		return new Multi<>(defaultMode, defaultStrategy, implementations);
	}
	
	public static final <N extends ASTNode, R extends ASTNode> NodeClassFilter<N, R> of(final Class<? extends R> implementation)
	{
		return newSingle(checkNotNull(implementation));
	}

	public static final <N extends ASTNode, R extends ASTNode> NodeClassFilter<N, R> of(final Class<? extends R> a, final Class<? extends R> b)
	{
		return newMulti(ImmutableSet.of(a, b));
	}

	public static final <N extends ASTNode, R extends ASTNode> NodeClassFilter<N, R> of(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c)
	{
		return newMulti(ImmutableSet.of(a, b, c));
	}

	public static final <N extends ASTNode, R extends ASTNode> NodeClassFilter<N, R> of(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d)
	{
		return newMulti(ImmutableSet.of(a, b, c, d));
	}

	@SafeVarargs
	public static final <N extends ASTNode, R extends ASTNode> NodeClassFilter<N, R> of(final Class<? extends R> implementation, final Class<? extends R> ... rest)
	{
		return newMulti(ImmutableSet.<Class<? extends R>>builder().add(implementation).addAll(Arrays.asList(rest)).build());
	}

	public static final <N extends ASTNode, R extends ASTNode> NodeClassFilter<N, R> of(final Iterable<Class<? extends R>> implementations)
	{
		return newMulti(ImmutableSet.copyOf(implementations));
	}

	public static final <N extends ASTNode, R extends ASTNode> NodeClassFilter<N, R> of(final Iterator<Class<? extends R>> implementations)
	{
		return newMulti(ImmutableSet.copyOf(implementations));
	}
	
	public static final class Builder<N extends ASTNode, R extends ASTNode>
	{
		private Mode mode;
		
		private Strategy strategy;
		
		private final Set<Class<? extends R>> types; 
		
		public Builder()
		{
			this.types = Sets.newHashSet();
		}
		
		public final Builder<N, R> include()
		{
			checkState(this.mode == null);

			this.mode = Mode.INCLUDE;
			
			return this;
		}

		public final Builder<N, R> exclude()
		{
			checkState(this.mode == null);
			
			this.mode = Mode.EXCLUDE;
			
			return this;
		}

		public final Builder<N, R> instanceOf()
		{
			checkState(this.strategy == null);
			
			this.strategy = Strategy.INSTANCE_OF;
			
			return this;
		}
		
		public final Builder<N, R> matchedBy()
		{
			checkState(this.strategy == null);
			
			this.strategy = Strategy.MATCHED_BY;
			
			return this;
		}

		public final Builder<N, R> type(final Class<? extends R> type)
		{
			this.types.add(type);
			
			return this;
		}

		public final Builder<N, R> types(final Collection<Class<? extends R>> types)
		{
			this.types.addAll(types);
			
			return this;
		}

		public final NodeClassFilter<N, R> build()
		{
			Mode     mode     = firstNonNull(this.mode, defaultMode);
			Strategy strategy = firstNonNull(this.strategy, defaultStrategy);
			
			Set<Class<? extends R>> types = ImmutableSet.copyOf(this.types);
			
			switch (types.size())
			{
				case 0:
					throw new IllegalStateException();
				case 1:
					return new Single<>(mode, strategy, types.iterator().next());
				default:
					return new Multi<>(mode, strategy, types);
			}
		}
	}
	
	public static final <N extends ASTNode, R extends ASTNode> Builder<N, R> builder()
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
		INSTANCE_OF
		{
			@Override
			final boolean compute(final Class<? extends ASTNode> implementation, @Nullable final ASTNode node)
			{
				return node != null && implementation.isInstance(node);
			}
		},
		
		MATCHED_BY
		{
			@Override
			final boolean compute(final Class<? extends ASTNode> implementation, @Nullable final ASTNode node)
			{
				return node != null && implementation == node.getClass();
			}
		};
		
		abstract boolean compute(Class<? extends ASTNode> implementation, @Nullable ASTNode node);
	}

	private static final class Single<N extends ASTNode, R extends ASTNode> extends NodeClassFilter<N, R>
	{
		private final Class<? extends R> implementation;
		
		Single(final Mode mode, final Strategy strategy, final Class<? extends R> implementation)
		{
			super(mode, strategy);
			
			this.implementation = implementation;
		}

		@Override
		public final boolean apply(@Nullable final N node)
		{
			return this.mode.apply(this.strategy.compute(this.implementation, node));
		}

		@Override
		public final Set<Class<? extends R>> getNodeClasses()
		{
			return ImmutableSet.<Class<? extends R>>of(this.implementation);
		}
	}

	private static final class Multi<N extends ASTNode, R extends ASTNode>  extends NodeClassFilter<N, R>
	{
		private final Set<Class<? extends R>> implementations;
		
		Multi(final Mode mode, final Strategy strategy, final Set<Class<? extends R>> implementations)
		{
			super(mode, strategy);
			
			this.implementations = implementations;
		}

		@Override
		public final boolean apply(@Nullable final N node)
		{
			for (Class<? extends R> implementation: this.implementations)
			{
				if (this.mode.apply(this.strategy.compute(implementation, node)))
				{
					return true;
				}
			}

			return false;
		}

		@Override
		public final Set<Class<? extends R>> getNodeClasses()
		{
			return this.implementations;
		}
	}

	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof NodeClassFilter))
		{
			return false;
		}
		
		NodeClassFilter<?, ?> other = (NodeClassFilter<?, ?>) o;
		
		return this.mode == other.mode && this.strategy == other.strategy && this.getNodeClasses().equals(other.getNodeClasses());
	}

	@Override
	public final int hashCode()
	{
		return Objects.hashCode(this.mode, this.strategy, this.getNodeClasses());
	}
	
	public abstract Set<Class<? extends R>> getNodeClasses();
	
	@Override
	public final String toString()
	{
		return "filter(" + Joiner.on(',').join(this.getNodeClasses()) + ")";
	}
}
