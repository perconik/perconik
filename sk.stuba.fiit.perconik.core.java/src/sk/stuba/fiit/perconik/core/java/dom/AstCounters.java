package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.function.Numerate;
import com.google.common.base.Predicate;

public final class AstCounters
{
	private AstCounters()
	{
		throw new AssertionError();
	}
	
	private static enum Nodes implements Numerate<ASTNode>
	{
		INSTANCE;

		public final int apply(final ASTNode node)
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

	private static final <N extends ASTNode> Numerate<N> cast(final Numerate<?> numerate)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		Numerate<N> result = (Numerate<N>) numerate;
		
		return result;
	}
	
	public static final <N extends ASTNode> Numerate<N> nodes()
	{
		return cast(Nodes.INSTANCE);
	}

	public static final <N extends ASTNode> Numerate<N> usingFilter(final Predicate<ASTNode> filter)
	{
		return AstFilteringCounter.using(filter);
	}

	// TODO node counter, line counter, char counter?
}
