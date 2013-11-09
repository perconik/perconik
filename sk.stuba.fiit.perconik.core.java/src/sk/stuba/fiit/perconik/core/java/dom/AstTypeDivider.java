package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public final class AstTypeDivider<N extends ASTNode> implements Function<N, Multimap<AstNodeType, ASTNode>>
{
	AstTypeDivider()
	{
	}

	public static final <N extends ASTNode> AstTypeDivider<N> create()
	{
		return new AstTypeDivider<>();
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
}
