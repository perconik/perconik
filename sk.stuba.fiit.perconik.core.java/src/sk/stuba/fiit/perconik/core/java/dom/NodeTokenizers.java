package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;

import static sk.stuba.fiit.perconik.core.java.dom.NodeCollectors.ofType;
import static sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType.QUALIFIED_NAME;
import static sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType.SIMPLE_NAME;

public final class NodeTokenizers {
  private static final NodeTokenizer<ASTNode> qualifiedNames = NodeTokenizer.using(ofType(QUALIFIED_NAME));

  private static final NodeTokenizer<ASTNode> simpleNames = NodeTokenizer.using(ofType(SIMPLE_NAME));

  private NodeTokenizers() {}

  private static <N extends ASTNode> NodeTokenizer<N> cast(final NodeTokenizer<?> tokenizer) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    NodeTokenizer<N> result = (NodeTokenizer<N>) tokenizer;

    return result;
  }

  public static <N extends ASTNode> NodeTokenizer<N> qualifiedNames() {
    return cast(qualifiedNames);
  }

  public static <N extends ASTNode> NodeTokenizer<N> simpleNames() {
    return cast(simpleNames);
  }
}
