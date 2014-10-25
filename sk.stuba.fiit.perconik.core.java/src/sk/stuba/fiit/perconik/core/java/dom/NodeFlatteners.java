package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import org.eclipse.jdt.core.dom.ASTNode;

public final class NodeFlatteners {
  private NodeFlatteners() {}

  private enum ToStringFlattener implements Function<ASTNode, CharSequence> {
    INSTANCE;

    public CharSequence apply(@Nullable final ASTNode node) {
      return node == null ? "" : node.toString();
    }

    @Override
    public String toString() {
      return "toString";
    }
  }

  private static <N extends ASTNode, S extends CharSequence> Function<N, S> cast(final Function<?, S> flattener) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    Function<N, S> result = (Function<N, S>) flattener;

    return result;
  }

  public static <N extends ASTNode> Function<N, CharSequence> toStringBased() {
    return cast(ToStringFlattener.INSTANCE);
  }
}
