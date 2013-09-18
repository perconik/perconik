package sk.stuba.fiit.perconik.core.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public final class AstFlatteners
{
	private static enum DefaultFlattener implements AstFlattener<ASTNode>
	{
		INSTANCE;
		
		public final CharSequence flatten(@Nullable final ASTNode node)
		{
			return node == null ? "" : node.toString();
		}
	}
	
	private AstFlatteners()
	{
		throw new AssertionError();
	}
	
	static final <N extends ASTNode> AstFlattener<N> internalDefaultFlattener()
	{
		// internal singleton has no state and only unbounded type parameters
		// therefore it is safe to share the same instance across all types
		@SuppressWarnings("unchecked")
		AstFlattener<N> flattener = (AstFlattener<N>) DefaultFlattener.INSTANCE;
		
		return flattener;
	}
	
	public static final <N extends ASTNode> AstFlattener<N> defaultFlattener()
	{
		return internalDefaultFlattener();
	}
}
