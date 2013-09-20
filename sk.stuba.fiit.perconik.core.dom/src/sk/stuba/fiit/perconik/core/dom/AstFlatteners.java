package sk.stuba.fiit.perconik.core.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public final class AstFlatteners
{
	private AstFlatteners()
	{
		throw new AssertionError();
	}
	
	private static enum ToString implements AstFlattener<ASTNode>
	{
		INSTANCE;
		
		public final CharSequence flatten(@Nullable final ASTNode node)
		{
			return node == null ? "" : node.toString();
		}
	}
	
	private static final <N extends ASTNode> AstFlattener<N> cast(final AstFlattener<?> flattener)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstFlattener<N> result = (AstFlattener<N>) flattener;
		
		return result;
	}

	public static final <N extends ASTNode> AstFlattener<N> toStringBased()
	{
		return cast(ToString.INSTANCE);
	}
}
