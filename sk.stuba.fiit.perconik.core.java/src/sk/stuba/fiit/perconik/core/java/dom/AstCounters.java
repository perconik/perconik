package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

public final class AstCounters
{
	private AstCounters()
	{
		throw new AssertionError();
	}
	
//	private static enum NodeCounter implements AstCounter<ASTNode>
//	{
//		INSTANCE;
//
//		public final int count(final ASTNode node)
//		{
//			
//		}
//	}

	// TODO usingFilter
	
	private static final <N extends ASTNode> AstCounter<N> cast(final AstCounter<?> counter)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstCounter<N> result = (AstCounter<N>) counter;
		
		return result;
	}

	// TODO node counter, line counter, char counter?
}
