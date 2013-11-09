package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

public final class AstTransformers
{
	private AstTransformers()
	{
		throw new AssertionError();
	}
	
	public static final <N extends ASTNode> AstCutter<N> cutterUsingFilter(final Predicate<ASTNode> filter)
	{
		return AstCutter.using(filter);
	}

	private static enum ToRoot implements Function<ASTNode, ASTNode>
	{
		INSTANCE;

		public final ASTNode apply(@Nullable final ASTNode node)
		{
			return AstNodes.root(node);
		}
		
		@Override
		public final String toString()
		{
			return "root";
		}
	}

	private static enum ToParent implements Function<ASTNode, ASTNode>
	{
		INSTANCE;

		public final ASTNode apply(@Nullable final ASTNode node)
		{
			return AstNodes.parent(node);
		}
		
		@Override
		public final String toString()
		{
			return "parent";
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
		return cast(ToRoot.INSTANCE);
	}
	
	public static final <N extends ASTNode> Function<N, ASTNode> toParent()
	{
		return cast(ToParent.INSTANCE);
	}
	
	// TODO add
//	public static final <N extends ASTNode> Function<N, ASTNode> toFirstAncestor(final )
//	{
//		return cast(ToRoot.INSTANCE);
//	}

}
