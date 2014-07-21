package sk.stuba.fiit.perconik.core.java.dom.traverse;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.TreeTraverser;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.core.java.dom.Nodes;

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
