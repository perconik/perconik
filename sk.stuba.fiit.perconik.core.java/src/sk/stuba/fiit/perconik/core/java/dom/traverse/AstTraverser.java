package sk.stuba.fiit.perconik.core.java.dom.traverse;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.core.java.dom.AstNodes;
import com.google.common.collect.TreeTraverser;

public final class AstTraverser extends TreeTraverser<ASTNode>
{
	public AstTraverser()
	{
	}

	@Override
	public final Iterable<ASTNode> children(@Nullable final ASTNode node)
	{
		return AstNodes.children(node);
	}
}
