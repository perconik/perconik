package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import sk.stuba.fiit.perconik.utilities.MoreStrings;
import sk.stuba.fiit.perconik.utilities.function.Numerate;

public final class NodeCounters {
  private NodeCounters() {}

  private enum NodeCounter implements Numerate<ASTNode> {
    INSTANCE;

    public int apply(@Nullable final ASTNode node) {
      final AbstractCountingVisitor<ASTNode> visitor = new AbstractCountingVisitor<ASTNode>() {
        @Override
        public void preVisit(final ASTNode node) {
          this.count ++;
        }
      };

      return visitor.perform(node);
    }

    @Override
    public String toString() {
      return "nodes";
    }
  }

  private enum LineCounter implements Numerate<ASTNode> {
    INSTANCE;

    public int apply(@Nullable final ASTNode node) {
      if (node == null || !Nodes.hasSource(node)) {
        return 0;
      }

      String source = Nodes.source(node, NodeRangeType.STANDARD);

      return source != null ? source.split("\r?\n|\r").length : 0;
    }

    @Override
    public String toString() {
      return "lines(?)";
    }
  }

  private enum CharacterCounter implements Numerate<ASTNode> {
    INSTANCE;

    public int apply(@Nullable final ASTNode node) {
      return node != null ? node.getLength() : 0;
    }

    @Override
    public String toString() {
      return "characters";
    }
  }

  private enum MemoryCounter implements Numerate<ASTNode> {
    INSTANCE;

    public int apply(@Nullable final ASTNode node) {
      return node != null ? node.subtreeBytes() : 0;
    }

    @Override
    public String toString() {
      return "memory";
    }
  }

  private static final <N extends ASTNode> Numerate<N> cast(final Numerate<?> numerate) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    Numerate<N> result = (Numerate<N>) numerate;

    return result;
  }

  public static <N extends ASTNode> Numerate<N> nodes() {
    return cast(NodeCounter.INSTANCE);
  }

  public static <N extends ASTNode> Numerate<N> lines() {
    return cast(LineCounter.INSTANCE);
  }

  public static <N extends ASTNode> Numerate<N> lines(final String source) {
    return new Numerate<N>() {
      public int apply(final N node) {
        int index;

        if (node == null || (index = node.getStartPosition()) == -1) {
          return 0;
        }

        return MoreStrings.lines(source.substring(index, index + node.getLength())).size();
      }

      @Override
      public String toString() {
        return "lines(source)";
      }
    };
  }

  public static <N extends ASTNode> Numerate<N> characters() {
    return cast(CharacterCounter.INSTANCE);
  }

  public static <N extends ASTNode> Numerate<N> memory() {
    return cast(MemoryCounter.INSTANCE);
  }

  public static <N extends ASTNode> Numerate<N> usingFilter(final Predicate<ASTNode> filter) {
    return NodeFilteringCounter.using(filter);
  }

  @SafeVarargs
  public static <N extends ASTNode> Numerate<N> ofClass(final Class<? extends ASTNode> implementation, final Class<? extends ASTNode> ... rest) {
    return usingFilter(NodeClassFilter.of(implementation, rest));
  }

  public static <N extends ASTNode> Numerate<N> ofClass(final Iterable<Class<? extends ASTNode>> implementations) {
    return usingFilter(NodeClassFilter.of(implementations));
  }

  public static <N extends ASTNode> Numerate<N> ofType(final NodeType type, final NodeType ... rest) {
    return usingFilter(NodeFilters.isMatching(type, rest));
  }

  public static <N extends ASTNode> Numerate<N> ofType(final Iterable<NodeType> types) {
    return usingFilter(NodeFilters.isMatching(types));
  }
}
