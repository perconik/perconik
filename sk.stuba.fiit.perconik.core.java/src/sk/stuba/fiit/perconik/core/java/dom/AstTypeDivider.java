package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public final class AstTypeDivider<N extends ASTNode> implements Function<N, Multimap<AstNodeType, ASTNode>>
{
	private static final AstTypeDivider<ASTNode> instance = new AstTypeDivider<>();
	
	AstTypeDivider()
	{
	}

	public static final <N extends ASTNode> AstTypeDivider<N> create()
	{
		// stateless internal singleton is shared across all types
		@SuppressWarnings("unchecked")
		AstTypeDivider<N> casted = (AstTypeDivider<N>) instance;
		
		return casted;
	}
	
	public final Multimap<AstNodeType, ASTNode> apply(final N node)
	{
		return new Processor().perform(node);
	}

	private final class Processor extends ASTVisitor
	{
		final Multimap<AstNodeType, ASTNode> result;
		
		Processor()
		{
			super(true);
			
			this.result = LinkedListMultimap.create(AstNodeType.count());
		}
		
		final Multimap<AstNodeType, ASTNode> perform(final N node)
		{
			if (node == null)
			{
				return ImmutableMultimap.of();
			}
			
			node.accept(this);
			
			return this.result;
		}

		@Override
		public final void preVisit(final ASTNode node)
		{
			this.result.put(AstNodeType.valueOf(node), node);
		}
	}
	
	@Override
	public final boolean equals(@Nullable Object o)
	{
		return o == this;
	}

	@Override
	public final int hashCode()
	{
		return this.hashCode();
	}
	
	@Override
	public final String toString()
	{
		return "divider";
	}
}
