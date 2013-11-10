package sk.stuba.fiit.perconik.core.java.dom.traverse;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.TreeTraverser;

public final class CachedTraverser extends TreeTraverser<ASTNode>
{
	final ListMultimap<ASTNode, ASTNode> cache;
	
	private CachedTraverser(@Nullable final ASTNode node)
	{
		this.cache = LinkedListMultimap.create();
		
		if (node != null)
		{
			this.load(node);
		}
	}
	
	private final void load(final ASTNode node)
	{
		ASTVisitor visitor = new ASTVisitor(true)
		{
			@Override
			public final void preVisit(final ASTNode node)
			{
				ASTNode parent = node.getParent();
				
				if (parent != null)
				{
					CachedTraverser.this.cache.put(node.getParent(), node);
				}
			}
		};
		
		node.accept(visitor);
	}
	
	public static final CachedTraverser create(@Nullable final ASTNode node)
	{
		return new CachedTraverser(node);
	}

	@Override
	public final List<ASTNode> children(@Nullable final ASTNode node)
	{
		return this.cache.get(node);
	}
}
