package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;

public final class AstCounters
{
	private AstCounters()
	{
		throw new AssertionError();
	}
	
	private static enum Nodes implements AstCounter<ASTNode>
	{
		INSTANCE;

		public final int count(final ASTNode node)
		{
			AbstractCountingVisitor<ASTNode> visitor = new AbstractCountingVisitor<ASTNode>()
			{
				@Override
				public final void preVisit(final ASTNode node)
				{
					this.count ++;
				}
			};
			
			return visitor.perform(node);
		}
	}

	private static final <N extends ASTNode> AstCounter<N> cast(final AstCounter<?> counter)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstCounter<N> result = (AstCounter<N>) counter;
		
		return result;
	}
	
	public static final <N extends ASTNode> AstCounter<N> nodes()
	{
		return cast(Nodes.INSTANCE);
	}

	public static final <N extends ASTNode> AstCounter<N> usingFilter(final AstFilter<ASTNode> filter)
	{
		return AstFilteringCounter.using(filter);
	}

	// TODO node counter, line counter, char counter?
}
