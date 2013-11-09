package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

public final class AstCutter<N extends ASTNode> implements Function<N, N>
{
	final Predicate<ASTNode> filter;
	
	private AstCutter(final Predicate<ASTNode> filter)
	{
		this.filter = Preconditions.checkNotNull(filter);
	}
	
	public static final <N extends ASTNode> AstCutter<N> using(final Predicate<ASTNode> filter)
	{
		return new AstCutter<>(filter);
	}
	
	@Override
	public final N apply(@Nullable final N node)
	{
		if (node == null)
		{
			return null;
		}
		
		return new Processor().perform(node);
	}
	
	private final class Processor extends ASTVisitor
	{
		Processor()
		{
			super(true);
		}
		
		public final N perform(final N node)
		{
			node.accept(this);
			
			return node;
		}
		
		@Override
		public final boolean preVisit2(ASTNode node)
		{
			if (AstCutter.this.filter.apply(node))
			{
				node.delete();
				
				return false;
			}
			
			return true;
		}
	}
}
