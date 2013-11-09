package sk.stuba.fiit.perconik.core.java.dom.traverse;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.core.java.dom.Nodes;
import com.google.common.collect.TreeTraverser;

public final class NaiveTraverser extends TreeTraverser<ASTNode>
{
	private NaiveTraverser()
	{
	}
	
	public static final NaiveTraverser create()
	{
		return new NaiveTraverser();
	}

	@Override
	public final List<ASTNode> children(@Nullable final ASTNode node)
	{
		return Nodes.children(node);
	}
}
