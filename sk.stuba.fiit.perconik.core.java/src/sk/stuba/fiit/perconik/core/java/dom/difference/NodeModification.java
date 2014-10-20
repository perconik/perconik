package sk.stuba.fiit.perconik.core.java.dom.difference;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.core.java.dom.Nodes;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class NodeModification<N extends ASTNode> extends NodeDelta<N> {
  private final N original;

  private final N revised;

  private NodeModification(@Nullable final N original, @Nullable final N revised) {
    this.original = original;
    this.revised = revised;
  }

  public static <N extends ASTNode> NodeModification<N> of(final N original, final N revised) {
    return new NodeModification<>(original, revised);
  }

  @Override
  public String toString(final int indent) {
    SmartStringBuilder builder = this.toStringBuilder(indent);

    builder.append("original: ").appendln(Nodes.toTypeString(this.original));
    builder.tab().lines(this.original).untab();
    builder.append("revised: ").appendln(Nodes.toTypeString(this.revised));
    builder.tab().lines(this.revised);

    return builder.toString();
  }

  @Override
  public N getOriginalNode() {
    return this.original;
  }

  @Override
  public N getRevisedNode() {
    return this.revised;
  }

  @Override
  public NodeDeltaType getType() {
    return NodeDeltaType.MODIFICATION;
  }
}
