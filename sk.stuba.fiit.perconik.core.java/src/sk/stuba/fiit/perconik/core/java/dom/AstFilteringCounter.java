package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.base.Preconditions;

public final class AstFilteringCounter<N extends ASTNode> implements AstCounter<N>
{
	final AstFilter<ASTNode> filter;
	
	AstFilteringCounter(final AstFilter<ASTNode> filter)
	{
		this.filter = Preconditions.checkNotNull(filter);
	}
	
	public static final <N extends ASTNode> AstFilteringCounter<N> using(final AstFilter<ASTNode> filter)
	{
		return new AstFilteringCounter<>(filter);
	}

	public final int count(final N node)
	{
		if (node == null)
		{
			return 0;
		}

		return new Processor().perform(node);
	}
	
	private final class Processor extends AbstractCountingVisitor<N>
	{
		Processor()
		{
		}

		@Override
		public final void preVisit(final ASTNode node)
		{
			if (AstFilteringCounter.this.filter.accept(node))
			{
				this.count ++;
			}
		}
	}
}
