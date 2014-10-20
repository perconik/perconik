package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Statement;

import static sk.stuba.fiit.perconik.core.java.dom.NodeClassFilter.of;

public final class NodeClassFilters {
  private static final NodeClassFilter<?, Annotation> annotations = of(Annotation.class);

  private static final NodeClassFilter<?, Comment> comments = of(Comment.class);

  private static final NodeClassFilter<?, Expression> expressions = of(Expression.class);

  private static final NodeClassFilter<?, Name> names = of(Name.class);

  private static final NodeClassFilter<?, Statement> statements = of(Statement.class);

  private NodeClassFilters() {}

  private static <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> cast(final NodeClassFilter<?, ?> filter) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    NodeClassFilter<N, F> result = (NodeClassFilter<N, F>) filter;

    return result;
  }

  public static <N extends ASTNode> NodeClassFilter<N, Annotation> annotations() {
    return cast(annotations);
  }

  public static <N extends ASTNode> NodeClassFilter<N, Comment> comments() {
    return cast(comments);
  }

  public static <N extends ASTNode> NodeClassFilter<N, Expression> expressions() {
    return cast(expressions);
  }

  public static <N extends ASTNode> NodeClassFilter<N, Name> names() {
    return cast(names);
  }

  public static <N extends ASTNode> NodeClassFilter<N, Statement> statements() {
    return cast(statements);
  }
}
