package sk.stuba.fiit.perconik.core.java.dom;

import java.util.LinkedList;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;

public final class NodeCollectors {
  private NodeCollectors() {}

  private enum ChildrenCollector implements ListCollector<ASTNode, ASTNode> {
    INSTANCE;

    public LinkedList<ASTNode> apply(@Nullable final ASTNode node) {
      return Nodes.children(node);
    }

    @Override
    public String toString() {
      return "children";
    }
  }

  private enum AncestorsCollector implements ListCollector<ASTNode, ASTNode> {
    INSTANCE;

    public LinkedList<ASTNode> apply(@Nullable final ASTNode node) {
      return Nodes.ancestors(node);
    }

    @Override
    public String toString() {
      return "ancestors";
    }
  }

  private enum DescendantsCollector implements ListCollector<ASTNode, ASTNode> {
    INSTANCE;

    public LinkedList<ASTNode> apply(@Nullable final ASTNode node) {
      return Nodes.descendants(node);
    }

    @Override
    public String toString() {
      return "descendants";
    }
  }

  private static <N extends ASTNode, R extends ASTNode> ListCollector<N, R> cast(final ListCollector<?, ?> collector) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    ListCollector<N, R> result = (ListCollector<N, R>) collector;

    return result;
  }

  public static <N extends ASTNode> ListCollector<N, ASTNode> children() {
    return cast(ChildrenCollector.INSTANCE);
  }

  public static <N extends ASTNode> ListCollector<N, ASTNode> ancestors() {
    return cast(AncestorsCollector.INSTANCE);
  }

  public static <N extends ASTNode> ListCollector<N, ASTNode> descendants() {
    return cast(DescendantsCollector.INSTANCE);
  }

  public static <N extends R, R extends ASTNode> ListCollector<N, R> usingFilter(final Predicate<N> filter) {
    return NodeFilteringCollector.using(filter);
  }

  public static <N extends ASTNode, R extends ASTNode> ListCollector<N, R> usingFilter(final NodeClassFilter<N, R> filter) {
    return NodeFilteringCollector.using(filter);
  }

  @SafeVarargs
  public static <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Class<? extends R> implementation, final Class<? extends R> ... rest) {
    return usingFilter(NodeClassFilter.<N, R>of(implementation, rest));
  }

  public static <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Iterable<Class<? extends R>> implementations) {
    return usingFilter(NodeClassFilter.<N, R>of(implementations));
  }

  public static <N extends R, R extends ASTNode> ListCollector<N, R> ofType(final NodeType type, final NodeType ... rest) {
    return usingFilter(NodeFilters.<N>isMatching(type, rest));
  }

  public static <N extends R, R extends ASTNode> ListCollector<N, R> ofType(final Iterable<NodeType> types) {
    return usingFilter(NodeFilters.<N>isMatching(types));
  }
}
