package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.function.Numerate;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

public final class AstFilteringCounter<N extends ASTNode> implements Numerate<N>
{
	final Predicate<ASTNode> filter;
	
	AstFilteringCounter(final Predicate<ASTNode> filter)
	{
		this.filter = Preconditions.checkNotNull(filter);
	}
	
	public static final <N extends ASTNode> AstFilteringCounter<N> using(final Predicate<ASTNode> filter)
	{
		return new AstFilteringCounter<>(filter);
	}

	public final int apply(final N node)
	{
		return new Processor().perform(node);
	}
	
	@Override
	public final String toString()
	{
		return "counter(" + this.filter.toString() + ")";
	}
	
	private final class Processor extends AbstractCountingVisitor<N>
	{
		Processor()
		{
		}

		@Override
		public final void preVisit(final ASTNode node)
		{
			if (AstFilteringCounter.this.filter.apply(node))
			{
				this.count ++;
			}
		}
	}
}
