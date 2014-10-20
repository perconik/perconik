package sk.stuba.fiit.perconik.core.java.dom.difference;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.core.java.dom.Nodes;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class NodeDeletion<N extends ASTNode> extends NodeDelta<N> {
  private final N node;

  private NodeDeletion(final N node) {
    this.node = node;
  }

  public static <N extends ASTNode> NodeDeletion<N> of(@Nullable final N node) {
    return new NodeDeletion<>(node);
  }

  @Override
  public String toString(final int indent) {
    SmartStringBuilder builder = this.toStringBuilder(indent);

    builder.append("original: ").appendln(Nodes.toTypeString(this.node));
    builder.tab().lines(this.node);

    return builder.toString();
  }

  public N getDeletedNode() {
    return this.node;
  }

  @Override
  public N getOriginalNode() {
    return this.getDeletedNode();
  }

  @Override
  public N getRevisedNode() {
    return null;
  }

  @Override
  public NodeDeltaType getType() {
    return NodeDeltaType.DELETION;
  }
}
