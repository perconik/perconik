package sk.stuba.fiit.perconik.core.java.dom;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import org.eclipse.jdt.core.dom.ASTMatcher;
import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;

import static com.google.common.base.Optional.fromNullable;

import static sk.stuba.fiit.perconik.core.java.dom.Nodes.toType;
import static sk.stuba.fiit.perconik.utilities.MoreLists.newArrayListSuitableFor;

public final class MatchingNode<N extends ASTNode> {
  private static final ASTMatcher matcher = new ASTMatcher(true);

  @Nullable
  private final N node;

  private volatile int hash;

  private MatchingNode(@Nullable final N node) {
    this.node = node;
  }

  public static <N extends ASTNode> MatchingNode<N> of(@Nullable final N node) {
    return new MatchingNode<>(node);
  }

  public static <N extends ASTNode> List<MatchingNode<N>> wrap(final Iterable<? extends N> nodes) {
    List<MatchingNode<N>> wrapped = newArrayListSuitableFor(nodes);

    for (N node: nodes) {
      wrapped.add(of(node));
    }

    return wrapped;
  }

  public static <N extends ASTNode> List<N> unwrap(final Iterable<? extends MatchingNode<N>> nodes) {
    List<N> unwrapped = newArrayListSuitableFor(nodes);

    for (MatchingNode<? extends N> node: nodes) {
      unwrapped.add(node.node);
    }

    return unwrapped;
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

  @Nullable
  public N asNode() {
    return this.node;
  }

  public Optional<N> asOption() {
    return fromNullable(this.node);
  }

  public MatchingNode<?> getRoot() {
    return of(Nodes.root(this.node));
  }

  public MatchingNode<?> getParent() {
    return of(Nodes.parent(this.node));
  }

  public NodeType getType() {
    return toType(this.node);
  }
}
