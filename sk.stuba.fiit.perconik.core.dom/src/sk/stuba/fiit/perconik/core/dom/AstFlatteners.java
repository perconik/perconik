package sk.stuba.fiit.perconik.core.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public final class AstFlatteners
{
	private AstFlatteners()
	{
		throw new AssertionError();
	}
	
	private static enum DefaultFlattener implements AstFlattener<ASTNode>
	{
		INSTANCE;
		
		public final CharSequence flatten(@Nullable final ASTNode node)
		{
			return node == null ? "" : node.toString();
		}
	}

	public static final <N extends ASTNode> AstFlattener<N> defaultFlattener()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstFlattener<N> flattener = (AstFlattener<N>) DefaultFlattener.INSTANCE;
		
		return flattener;
	}
}
