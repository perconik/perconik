package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTMatcher;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;

public final class AstMatchingNode<N extends ASTNode>
{
	private static final ASTMatcher matcher = new ASTMatcher(true);
	
	private final N node;
	
	private int hash;
	
	private AstMatchingNode(@Nullable final N node)
	{
		this.node = node;
	}
	
	public static final <N extends ASTNode> AstMatchingNode<N> of(@Nullable final N node)
	{
		return new AstMatchingNode<>(node);
	}

	@Override
	public final boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		
		if (!(o instanceof AstMatchingNode))
		{
			return false;
		}
		
		AstMatchingNode<?> other = (AstMatchingNode<?>) o;
		
		return matcher.safeSubtreeMatch(this.node, other.node);
	}

	@Override
	public final int hashCode()
	{
		int hash = this.hash;
		
		if (hash == 0 && this.node != null)
		{
			synchronized (this)
			{
				hash = this.hash;
				
				if (hash == 0)
				{
					hash = this.hash = this.node.toString().hashCode();
				}
			}
		}
		
		return hash;
	}
	
	@Override
	public final String toString()
	{
		return this.node != null ? this.node.toString() : null;
	}

	public final ASTNode getRoot()
	{
		return AstNodes.root(this.node);
	}

	public final ASTNode getParent()
	{
		return AstNodes.parent(this.node);
	}

	public final N getNode()
	{
		return this.node;
	}
	
	public final AstNodeType getType()
	{
		return AstNodes.typeAsConstant(this.node);
	}
}
