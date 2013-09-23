package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import com.google.common.base.Preconditions;

public final class AstCutter<N extends ASTNode> implements AstTransformer<N, N>
{
	final AstFilter<ASTNode> filter;
	
	private AstCutter(final AstFilter<ASTNode> filter)
	{
		this.filter = Preconditions.checkNotNull(filter);
	}
	
	public static final <N extends ASTNode> AstCutter<N> using(final AstFilter<ASTNode> filter)
	{
		return new AstCutter<>(filter);
	}
	
	@Override
	public final N transform(@Nullable final N node)
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
		}
		
		public final N perform(final N node)
		{
			node.accept(this);
			
			return node;
		}
		
		@Override
		public final boolean preVisit2(ASTNode node)
		{
			if (AstCutter.this.filter.accept(node))
			{
				node.delete();
				
				return false;
			}
			
			return true;
		}
	}
}
