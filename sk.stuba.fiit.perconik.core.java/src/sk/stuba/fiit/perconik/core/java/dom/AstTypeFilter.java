package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public abstract class AstTypeFilter<N extends ASTNode, R extends ASTNode> implements Predicate<N> 
{
	static final Mode defaultMode = Mode.INCLUDE;
	
	static final Strategy defaultStrategy = Strategy.IS_MATCHING;
	
	final Mode mode;
	
	final Strategy strategy;
	
	AstTypeFilter(final Mode mode, final Strategy strategy)
	{
		this.mode     = Preconditions.checkNotNull(mode);
		this.strategy = Preconditions.checkNotNull(strategy);
	}
	
	private static final <N extends ASTNode, R extends ASTNode> Single<N, R> newSingle(final Class<? extends R> type)
	{
		return new Single<>(defaultMode, defaultStrategy, type);
	}
	
	private static final <N extends ASTNode, R extends ASTNode> Multi<N, R> newMulti(final Set<Class<? extends R>> types)
	{
		return new Multi<>(defaultMode, defaultStrategy, types);
	}
	
	public static final <N extends ASTNode, R extends ASTNode> AstTypeFilter<N, R> of(final Class<? extends R> type)
	{
		return newSingle(type);
	}

	public static final <N extends ASTNode, R extends ASTNode> AstTypeFilter<N, R> of(final Class<? extends R> a, final Class<? extends R> b)
	{
		return newMulti(ImmutableSet.of(a, b));
	}

	public static final <N extends ASTNode, R extends ASTNode> AstTypeFilter<N, R> of(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c)
	{
		return newMulti(ImmutableSet.of(a, b, c));
	}

	public static final <N extends ASTNode, R extends ASTNode> AstTypeFilter<N, R> of(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d)
	{
		return newMulti(ImmutableSet.of(a, b, c, d));
	}

	@SafeVarargs
	public static final <N extends ASTNode, R extends ASTNode> AstTypeFilter<N, R> of(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d, final Class<? extends R> ... rest)
	{
		return newMulti(ImmutableSet.<Class<? extends R>>builder().add(a).add(b).add(c).add(d).addAll(Arrays.asList(rest)).build());
	}

	public static final <N extends ASTNode, R extends ASTNode> AstTypeFilter<N, R> of(final Iterable<Class<? extends R>> types)
	{
		return newMulti(ImmutableSet.copyOf(types));
	}

	public static final <N extends ASTNode, R extends ASTNode> AstTypeFilter<N, R> of(final Iterator<Class<? extends R>> types)
	{
		return newMulti(ImmutableSet.copyOf(types));
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
			Preconditions.checkState(this.mode == null);

			this.mode = Mode.INCLUDE;
			
			return this;
		}

		public final Builder<N, R> exclude()
		{
			Preconditions.checkState(this.mode == null);
			
			this.mode = Mode.EXCLUDE;
			
			return this;
		}

		public final Builder<N, R> instanceOf()
		{
			Preconditions.checkState(this.strategy == null);
			
			this.strategy = Strategy.IS_INSTANCE;
			
			return this;
		}
		
		public final Builder<N, R> matchedBy()
		{
			Preconditions.checkState(this.strategy == null);
			
			this.strategy = Strategy.IS_MATCHING;
			
			return this;
		}

		public final Builder<N, R> type(Class<? extends R> type)
		{
			this.types.add(type);
			
			return this;
		}

		public final Builder<N, R> types(Collection<Class<? extends R>> types)
		{
			this.types.addAll(types);
			
			return this;
		}

		public final AstTypeFilter<N, R> build()
		{
			if (this.mode == null)
			{
				this.mode = defaultMode;
			}

			if (this.strategy == null)
			{
				this.strategy = defaultStrategy;
			}
			
			Set<Class<? extends R>> types = ImmutableSet.copyOf(this.types);
			
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
		IS_INSTANCE
		{
			@Override
			final boolean compute(final AstNodeType type, @Nullable final ASTNode node)
			{
				return type.isInstance(node);
			}
		},
		
		IS_MATCHING
		{
			@Override
			final boolean compute(final AstNodeType type, @Nullable final ASTNode node)
			{
				return type.isMatching(node);
			}
		};
		
		abstract boolean compute(AstNodeType type, ASTNode node);
	}

	private static final class Single<N extends ASTNode, R extends ASTNode> extends AstTypeFilter<N, R>
	{
		private final Set<Class<? extends R>> classes;

		private final AstNodeType type;
		
		Single(final Mode mode, final Strategy strategy, final Class<? extends R> implementation)
		{
			super(mode, strategy);
			
			this.classes = ImmutableSet.<Class<? extends R>>of(implementation);
			this.type    = AstNodeType.valueOf(implementation);
		}

		@Override
		public final boolean apply(@Nullable final N node)
		{
			return this.mode.apply(this.strategy.compute(this.type, node));
		}

		@Override
		public final Set<Class<? extends R>> getNodeClasses()
		{
			return this.classes;
		}

		@Override
		public final Set<AstNodeType> getNodeTypes()
		{
			return EnumSet.of(this.type);
		}
	}

	private static final class Multi<N extends ASTNode, R extends ASTNode>  extends AstTypeFilter<N, R>
	{
		private final Set<Class<? extends R>> classes;

		private final Set<AstNodeType> types;
		
		Multi(final Mode mode, final Strategy strategy, final Set<Class<? extends R>> implementations)
		{
			super(mode, strategy);
			
			this.classes = implementations;
			
			Set<AstNodeType> types = EnumSet.noneOf(AstNodeType.class);
			
			for (Class<? extends R> implementation: this.classes)
			{
				types.add(AstNodeType.valueOf(implementation));
			}
			
			this.types = Sets.immutableEnumSet(types);
		}

		@Override
		public final boolean apply(@Nullable final N node)
		{
			for (AstNodeType type: this.types)
			{
				if (this.mode.apply(this.strategy.compute(type, node)))
				{
					return true;
				}
			}

			return false;
		}

		@Override
		public final Set<Class<? extends R>> getNodeClasses()
		{
			return this.classes;
		}
		
		@Override
		public final Set<AstNodeType> getNodeTypes()
		{
			return this.types;
		}
	}

	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof AstTypeFilter))
		{
			return false;
		}
		
		AstTypeFilter<?, ?> other = (AstTypeFilter<?, ?>) o;
		
		return this.mode == other.mode && this.strategy == other.strategy && this.getNodeTypes().equals(other.getNodeTypes());
	}

	@Override
	public final int hashCode()
	{
		return Objects.hash(this.mode, this.strategy, this.getNodeTypes());
	}
	
	public abstract Set<Class<? extends R>> getNodeClasses();
	
	public abstract Set<AstNodeType> getNodeTypes();
	
	@Override
	public final String toString()
	{
		return "filter(" + this.getNodeTypes() + ")";
	}
}
