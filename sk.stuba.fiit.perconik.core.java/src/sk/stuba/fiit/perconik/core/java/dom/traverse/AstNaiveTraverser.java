package sk.stuba.fiit.perconik.core.java.dom.traverse;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.core.java.dom.AstNodes;
import com.google.common.collect.TreeTraverser;

public final class AstNaiveTraverser extends TreeTraverser<ASTNode>
{
	private AstNaiveTraverser()
	{
	}
	
	public static final AstNaiveTraverser create()
	{
		return new AstNaiveTraverser();
	}

	@Override
	public final List<ASTNode> children(@Nullable final ASTNode node)
	{
		return AstNodes.children(node);
	}
}
