package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;

enum StandardNodeFactory implements NodeFactory
{
	INSTANCE;
	
	private StandardNodeFactory()
	{
	}

	public final <N extends ASTNode> N newNode(final AST tree, final Class<N> implementation)
	{
		return implementation.cast(tree.createInstance(implementation));
	}
}