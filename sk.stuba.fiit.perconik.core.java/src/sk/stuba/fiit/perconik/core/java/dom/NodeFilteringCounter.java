package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.utilities.function.Numerate;

import static com.google.common.base.Preconditions.checkNotNull;

public final class NodeFilteringCounter<N extends ASTNode> implements Numerate<N> {
  final Predicate<ASTNode> filter;

  NodeFilteringCounter(final Predicate<ASTNode> filter) {
    this.filter = checkNotNull(filter);
  }

  public static final <N extends ASTNode> NodeFilteringCounter<N> using(final Predicate<ASTNode> filter) {
    return new NodeFilteringCounter<>(filter);
  }

  public final int apply(final N node) {
    return new Processor().perform(node);
  }

  private final class Processor extends AbstractCountingVisitor<N> {
    Processor() {}

    @Override
    public final void preVisit(final ASTNode node) {
      if (NodeFilteringCounter.this.filter.apply(node)) {
        this.count++;
      }
    }
  }

  @Override
  public final boolean equals(@Nullable final Object o) {
    if (o instanceof NodeFilteringCounter) {
      NodeFilteringCounter<?> other = (NodeFilteringCounter<?>) o;

      return this.filter.equals(other.filter);
    }

    return false;
  }

  @Override
  public final int hashCode() {
    return this.filter.hashCode();
  }

  @Override
  public final String toString() {
    return "counter(" + this.filter + ")";
  }
}
