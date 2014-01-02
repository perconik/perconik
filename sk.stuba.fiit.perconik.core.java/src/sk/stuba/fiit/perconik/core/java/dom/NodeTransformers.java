package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

public final class NodeTransformers
{
	private NodeTransformers()
	{
		throw new AssertionError();
	}
	
	public static final <N extends ASTNode> NodeCutter<N> cutterUsingFilter(final Predicate<ASTNode> filter)
	{
		return NodeCutter.using(filter);
	}

	private static enum ToRootFunction implements Function<ASTNode, ASTNode>
	{
		INSTANCE;

		public final ASTNode apply(@Nullable final ASTNode node)
		{
			return Nodes.root(node);
		}
		
		@Override
		public final String toString()
		{
			return "root";
		}
	}

	private static enum ToParentFunction implements Function<ASTNode, ASTNode>
	{
		INSTANCE;

		public final ASTNode apply(@Nullable final ASTNode node)
		{
			return Nodes.parent(node);
		}
		
		@Override
		public final String toString()
		{
			return "parent";
		}
	}
	
	private static abstract class InternalFunction<N extends ASTNode> implements Function<N, ASTNode>
	{
		final Predicate<ASTNode> predicate;
		
		InternalFunction(final Predicate<ASTNode> predicate)
		{
			this.predicate = Preconditions.checkNotNull(predicate);
		}

		@Override
		public final boolean equals(@Nullable final Object o)
		{
			return null != o && this.getClass() == o.getClass() && this.predicate.equals(((InternalFunction<?>) o).predicate);
		}

		@Override
		public final int hashCode()
		{
			return this.predicate.hashCode();
		}
	}
	
	private static final class ToFirstUpToRootFunction<N extends ASTNode> extends InternalFunction<N>
	{
		ToFirstUpToRootFunction(final Predicate<ASTNode> predicate)
		{
			super(predicate);
		}

		public final ASTNode apply(final N node)
		{
			return Nodes.firstUpToRoot(node, this.predicate);
		}
		
		@Override
		public final String toString()
		{
			return "firstUpToRoot";
		}
	}
	
	private static final class ToFirstDownToLeavesFunction<N extends ASTNode> extends InternalFunction<N>
	{
		ToFirstDownToLeavesFunction(final Predicate<ASTNode> predicate)
		{
			super(predicate);
		}

		public final ASTNode apply(final N node)
		{
			return Nodes.firstDownToLeaves(node, this.predicate);
		}
		
		@Override
		public final String toString()
		{
			return "firstDownToLeaves";
		}
	}
	
	private static final class ToFirstAncestorFunction<N extends ASTNode> extends InternalFunction<N>
	{
		ToFirstAncestorFunction(final Predicate<ASTNode> predicate)
		{
			super(predicate);
		}

		public final ASTNode apply(final N node)
		{
			return Nodes.firstAncestor(node, this.predicate);
		}
		
		@Override
		public final String toString()
		{
			return "firstAncestor";
		}
	}
	
	private static final class ToFirstDescendantFunction<N extends ASTNode> extends InternalFunction<N>
	{
		ToFirstDescendantFunction(final Predicate<ASTNode> predicate)
		{
			super(predicate);
		}
		
		public final ASTNode apply(final N node)
		{
			return Nodes.firstDescendant(node, this.predicate);
		}
		
		@Override
		public final String toString()
		{
			return "firstDescendant";
		}
	}
	
	private static final <N extends ASTNode, T> Function<N, T> cast(final Function<?, T> transformer)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		Function<N, T> result = (Function<N, T>) transformer;
		
		return result;
	}

	public static final <N extends ASTNode> Function<N, ASTNode> toRoot()
	{
		return cast(ToRootFunction.INSTANCE);
	}
	
	public static final <N extends ASTNode> Function<N, ASTNode> toParent()
	{
		return cast(ToParentFunction.INSTANCE);
	}
	
	public static final <N extends ASTNode> Function<N, ASTNode> toFirstUpToRoot(final Predicate<ASTNode> predicate)
	{
		return new ToFirstUpToRootFunction<>(predicate);
	}
	
	public static final <N extends ASTNode> Function<N, ASTNode> toFirstDownToLeaves(final Predicate<ASTNode> predicate)
	{
		return new ToFirstDownToLeavesFunction<>(predicate);
	}
	
	public static final <N extends ASTNode> Function<N, ASTNode> toFirstAncestor(final Predicate<ASTNode> predicate)
	{
		return new ToFirstAncestorFunction<>(predicate);
	}
	
	public static final <N extends ASTNode> Function<N, ASTNode> toFirstDescendant(final Predicate<ASTNode> predicate)
	{
		return new ToFirstDescendantFunction<>(predicate);
	}
}
