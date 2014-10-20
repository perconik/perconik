package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.dom.ASTMatcher;
import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;

public final class MatchingNode<N extends ASTNode> {
  private static final ASTMatcher matcher = new ASTMatcher(true);

  @Nullable
  private final N node;

  private volatile int hash;

  private MatchingNode(@Nullable final N node) {
    this.node = node;
  }

  public static <N extends ASTNode> MatchingNode<N> from(@Nullable final N node) {
    return new MatchingNode<>(node);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof MatchingNode)) {
      return false;
    }

    MatchingNode<?> other = (MatchingNode<?>) o;

    return matcher.safeSubtreeMatch(this.node, other.node);
  }

  @Override
  public int hashCode() {
    int hash = this.hash;

    if (hash == 0 && this.node != null) {
      synchronized (this) {
        hash = this.hash;

        if (hash == 0) {
          hash = this.hash = this.node.toString().hashCode();
        }
      }
    }

    return hash;
  }

  @Override
  public String toString() {
    return this.node != null ? this.node.toString() : null;
  }

  public N asNode() {
    return this.node;
  }

  public MatchingNode<?> getRoot() {
    return from(Nodes.root(this.node));
  }

  public MatchingNode<?> getParent() {
    return from(Nodes.parent(this.node));
  }

  public NodeType getType() {
    return Nodes.toType(this.node);
  }
}
