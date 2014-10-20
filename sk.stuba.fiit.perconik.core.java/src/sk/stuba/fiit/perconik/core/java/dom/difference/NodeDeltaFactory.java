package sk.stuba.fiit.perconik.core.java.dom.difference;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.dom.ASTNode;

public final class NodeDeltaFactory {
  private NodeDeltaFactory() {}

  public static <N extends ASTNode> NodeDelta<N> create(@Nullable final N original, @Nullable final N revised) {
    if (original != null && revised != null) {
      return NodeModification.of(original, revised);
    } else if (original != null) {
      return NodeDeletion.of(original);
    } else if (revised != null) {
      return NodeAddition.of(revised);
    }

    throw new NullPointerException();
  }
}
