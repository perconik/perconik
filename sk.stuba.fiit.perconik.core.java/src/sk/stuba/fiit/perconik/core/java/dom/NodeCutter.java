package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import static com.google.common.base.Preconditions.checkNotNull;

public final class NodeCutter<N extends ASTNode> implements Function<N, N> {
  final Predicate<ASTNode> filter;

  private NodeCutter(final Predicate<ASTNode> filter) {
    this.filter = checkNotNull(filter);
  }

  public static <N extends ASTNode> NodeCutter<N> using(final Predicate<ASTNode> filter) {
    return new NodeCutter<>(filter);
  }

  @Override
  public N apply(@Nullable final N node) {
    if (node == null) {
      return null;
    }

    return new Processor().perform(node);
  }

  private final class Processor extends ASTVisitor {
    Processor() {
      super(true);
    }

    public N perform(final N node) {
      node.accept(this);

      return node;
    }

    @Override
    public boolean preVisit2(final ASTNode node) {
      if (NodeCutter.this.filter.apply(node)) {
        node.delete();

        return false;
      }

      return true;
    }
  }

  @Override
  public boolean equals(@Nullable final Object o) {
    if (o instanceof NodeCutter) {
      NodeCutter<?> other = (NodeCutter<?>) o;

      return this.filter.equals(other.filter);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return this.filter.hashCode();
  }

  @Override
  public String toString() {
    return "cutter(" + this.filter + ")";
  }
}
