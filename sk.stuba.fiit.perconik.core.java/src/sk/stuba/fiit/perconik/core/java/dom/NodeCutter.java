package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

public final class NodeCutter<N extends ASTNode> implements Function<N, N>
{
	final Predicate<ASTNode> filter;
	
	private NodeCutter(final Predicate<ASTNode> filter)
	{
		this.filter = Preconditions.checkNotNull(filter);
	}
	
	public static final <N extends ASTNode> NodeCutter<N> using(final Predicate<ASTNode> filter)
	{
		return new NodeCutter<>(filter);
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
	
	@Override
	public final boolean equals(@Nullable Object o)
	{
		if (o instanceof NodeCutter)
		{
			NodeCutter<?> other = (NodeCutter<?>) o;
			
			return this.filter.equals(other.filter);
		}
		
		return false;
	}

	@Override
	public final int hashCode()
	{
		return this.filter.hashCode();
	}
	
	@Override
	public final String toString()
	{
		return "cutter(" + this.filter + ")";
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
			if (NodeCutter.this.filter.apply(node))
			{
				node.delete();
				
				return false;
			}
			
			return true;
		}
	}
}
