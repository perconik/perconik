package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;

public interface NodeFactory {
  public <N extends ASTNode> N newNode(AST tree, Class<N> implementation);
}
