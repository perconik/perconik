package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import sk.stuba.fiit.perconik.utilities.function.MultimapCollector;

public final class NodeTypeDivider<N extends ASTNode> implements MultimapCollector<N, NodeType, ASTNode> {
  private static final NodeTypeDivider<ASTNode> instance = new NodeTypeDivider<>();

  NodeTypeDivider() {}

  public static <N extends ASTNode> NodeTypeDivider<N> create() {
    // stateless internal singleton is shared across all types
    @SuppressWarnings("unchecked")
    NodeTypeDivider<N> casted = (NodeTypeDivider<N>) instance;

    return casted;
  }

  public Multimap<NodeType, ASTNode> apply(final N node) {
    return new Processor().perform(node);
  }

  private final class Processor extends ASTVisitor {
    final Multimap<NodeType, ASTNode> result;

    Processor() {
      super(true);

      this.result = LinkedListMultimap.create(NodeType.count());
    }

    Multimap<NodeType, ASTNode> perform(@Nullable final N node) {
      if (node == null) {
        return ImmutableMultimap.of();
      }

      node.accept(this);

      return this.result;
    }

    @Override
    public void preVisit(final ASTNode node) {
      this.result.put(NodeType.valueOf(node), node);
    }
  }

  @Override
  public boolean equals(@Nullable final Object o) {
    return o == this;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "divider";
  }
}
