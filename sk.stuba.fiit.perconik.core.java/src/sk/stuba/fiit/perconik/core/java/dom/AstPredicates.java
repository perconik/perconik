package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Set;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

public final class AstPredicates
{
	private AstPredicates()
	{
		throw new AssertionError();
	}
	
	private static abstract class AbstractNodeTypePredicate<N extends ASTNode> implements Predicate<N>
	{
		final Set<AstNodeType> types;
		
		AbstractNodeTypePredicate(final AstNodeType type, final AstNodeType ... rest)
		{
			this.types = Sets.immutableEnumSet(type, rest);
		}
		
		AbstractNodeTypePredicate(final Iterable<AstNodeType> types)
		{
			this.types = Sets.immutableEnumSet(types);
		}
		
		@Override
		public final int hashCode()
		{
			return this.getNodeTypes().hashCode();
		}
		
		final Set<AstNodeType> getNodeTypes()
		{
			return this.types;
		}
	}

	private static final class IsInstancePredicate<N extends ASTNode> extends AbstractNodeTypePredicate<N>
	{
		IsInstancePredicate(final AstNodeType type, final AstNodeType ... rest)
		{
			super(type, rest);
		}
		
		IsInstancePredicate(final Iterable<AstNodeType> types)
		{
			super(types);
		}

		public final boolean apply(final N node)
		{
			for (AstNodeType type: this.types)
			{
				if (type.isInstance(node))
				{
					return true;
				}
			}
			
			return false;
		}

		@Override
		public final boolean equals(@Nullable final Object o)
		{
			if (o instanceof IsInstancePredicate)
			{
				IsInstancePredicate<?> other = (IsInstancePredicate<?>) o;
				
				return this.getNodeTypes().equals(other.getNodeTypes());
			}
			
			return false;
		}
		
		@Override
		public final String toString()
		{
			return "isInstance(" + this.getNodeTypes() + ")";
		}
	}

	private static final class IsMatchingPredicate<N extends ASTNode> extends AbstractNodeTypePredicate<N>
	{
		IsMatchingPredicate(final AstNodeType type, final AstNodeType ... rest)
		{
			super(type, rest);
		}
		
		IsMatchingPredicate(final Iterable<AstNodeType> types)
		{
			super(types);
		}

		public final boolean apply(final N node)
		{
			for (AstNodeType type: this.types)
			{
				if (type.isMatching(node))
				{
					return true;
				}
			}
			
			return false;
		}

		@Override
		public final boolean equals(@Nullable final Object o)
		{
			if (o instanceof IsMatchingPredicate)
			{
				IsMatchingPredicate<?> other = (IsMatchingPredicate<?>) o;
				
				return this.getNodeTypes().equals(other.getNodeTypes());
			}
			
			return false;
		}
		
		@Override
		public final String toString()
		{
			return "isMatching(" + this.getNodeTypes() + ")";
		}
	}
	
	public static final <N extends ASTNode> Predicate<N> isInstance(final AstNodeType type, final AstNodeType ... rest)
	{
		return new IsInstancePredicate<>(type, rest);
	}

	public static final <N extends ASTNode> Predicate<N> isInstance(final Iterable<AstNodeType> types)
	{
		return new IsInstancePredicate<>(types);
	}

	public static final <N extends ASTNode> Predicate<N> isMatching(final AstNodeType type, final AstNodeType ... rest)
	{
		return new IsMatchingPredicate<>(type, rest);
	}

	public static final <N extends ASTNode> Predicate<N> isMatching(final Iterable<AstNodeType> types)
	{
		return new IsMatchingPredicate<>(types);
	}
}
