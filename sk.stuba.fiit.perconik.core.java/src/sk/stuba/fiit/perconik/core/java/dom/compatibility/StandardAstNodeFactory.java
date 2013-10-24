package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;

enum StandardAstNodeFactory implements AstNodeFactory
{
	INSTANCE;
	
	private StandardAstNodeFactory()
	{
	}

	public final <N extends ASTNode> N newNode(final AST tree, final Class<N> type)
	{
		return type.cast(tree.createInstance(type));
	}
}