package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

abstract class AbstractCountingVisitor<N extends ASTNode> extends ASTVisitor {
  int count;

  AbstractCountingVisitor() {
    super(true);
  }

  final int perform(@Nullable final N node) {
    if (node == null) {
      return 0;
    }

    node.accept(this);

    return this.count;
  }
}
