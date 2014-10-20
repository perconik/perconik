package sk.stuba.fiit.perconik.core.java.dom.difference;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.core.java.dom.Nodes;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class NodeAddition<N extends ASTNode> extends NodeDelta<N> {
  private final N node;

  private NodeAddition(@Nullable final N node) {
    this.node = node;
  }

  public static <N extends ASTNode> NodeAddition<N> of(@Nullable final N node) {
    return new NodeAddition<>(node);
  }

  @Override
  public String toString(final int indent) {
    SmartStringBuilder builder = this.toStringBuilder(indent);

    builder.append("revised: ").appendln(Nodes.toTypeString(this.node));
    builder.tab().lines(this.node);

    return builder.toString();
  }

  public N getAddedNode() {
    return this.node;
  }

  @Override
  public N getOriginalNode() {
    return null;
  }

  @Override
  public N getRevisedNode() {
    return this.getAddedNode();
  }

  @Override
  public NodeDeltaType getType() {
    return NodeDeltaType.ADDITION;
  }
}
