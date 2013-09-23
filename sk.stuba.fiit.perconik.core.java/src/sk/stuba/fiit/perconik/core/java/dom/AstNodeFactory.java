package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;

// TODO impls

public interface AstNodeFactory
{
	public <N extends ASTNode> N newNode(AST tree, Class<N> type);
}
