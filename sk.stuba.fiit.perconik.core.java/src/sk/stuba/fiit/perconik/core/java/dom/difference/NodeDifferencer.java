package sk.stuba.fiit.perconik.core.java.dom.difference;

import org.eclipse.jdt.core.dom.ASTNode;

public interface NodeDifferencer<N extends ASTNode, R extends ASTNode> {
  public NodeDeltaSet<R> difference(N original, N revised);
}
