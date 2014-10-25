package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import org.eclipse.jdt.core.dom.ASTNode;

import static com.google.common.base.Preconditions.checkNotNull;

public final class NodeTransformers {
  private NodeTransformers() {}

  public static <N extends ASTNode> NodeCutter<N> cutterUsingFilter(final Predicate<ASTNode> filter) {
    return NodeCutter.using(filter);
  }

  private enum ToRootFunction implements Function<ASTNode, ASTNode> {
    INSTANCE;

    public ASTNode apply(@Nullable final ASTNode node) {
      return Nodes.root(node);
    }

    @Override
    public String toString() {
      return "root";
    }
  }

  private enum ToParentFunction implements Function<ASTNode, ASTNode> {
    INSTANCE;

    public ASTNode apply(@Nullable final ASTNode node) {
      return Nodes.parent(node);
    }

    @Override
    public String toString() {
      return "parent";
    }
  }

  private static abstract class InternalFunction<N extends ASTNode> implements Function<N, ASTNode> {
    final Predicate<ASTNode> predicate;

    InternalFunction(final Predicate<ASTNode> predicate) {
      this.predicate = checkNotNull(predicate);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      return null != o && this.getClass() == o.getClass() && this.predicate.equals(((InternalFunction<?>) o).predicate);
    }

    @Override
    public int hashCode() {
      return this.predicate.hashCode();
    }
  }

  private static final class ToFirstDownToRootFunction<N extends ASTNode> extends InternalFunction<N> {
    ToFirstDownToRootFunction(final Predicate<ASTNode> predicate) {
      super(predicate);
    }

    public ASTNode apply(final N node) {
      return Nodes.firstDownToRoot(node, this.predicate);
    }

    @Override
    public String toString() {
      return "firstDownToRoot";
    }
  }

  private static final class ToFirstUpToLeavesFunction<N extends ASTNode> extends InternalFunction<N> {
    ToFirstUpToLeavesFunction(final Predicate<ASTNode> predicate) {
      super(predicate);
    }

    public ASTNode apply(final N node) {
      return Nodes.firstUpToLeaves(node, this.predicate);
    }

    @Override
    public String toString() {
      return "firstUpToLeaves";
    }
  }

  private static final class ToFirstAncestorFunction<N extends ASTNode> extends InternalFunction<N> {
    ToFirstAncestorFunction(final Predicate<ASTNode> predicate) {
      super(predicate);
    }

    public ASTNode apply(final N node) {
      return Nodes.firstAncestor(node, this.predicate);
    }

    @Override
    public String toString() {
      return "firstAncestor";
    }
  }

  private static final class ToFirstDescendantFunction<N extends ASTNode> extends InternalFunction<N> {
    ToFirstDescendantFunction(final Predicate<ASTNode> predicate) {
      super(predicate);
    }

    public ASTNode apply(final N node) {
      return Nodes.firstDescendant(node, this.predicate);
    }

    @Override
    public String toString() {
      return "firstDescendant";
    }
  }

  private static <N extends ASTNode, T> Function<N, T> cast(final Function<?, T> transformer) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    Function<N, T> result = (Function<N, T>) transformer;

    return result;
  }

  public static <N extends ASTNode> Function<N, ASTNode> toRoot() {
    return cast(ToRootFunction.INSTANCE);
  }

  public static <N extends ASTNode> Function<N, ASTNode> toParent() {
    return cast(ToParentFunction.INSTANCE);
  }

  public static <N extends ASTNode> Function<N, ASTNode> toFirstDownToRoot(final Predicate<ASTNode> predicate) {
    return new ToFirstDownToRootFunction<>(predicate);
  }

  public static <N extends ASTNode> Function<N, ASTNode> toFirstUpToLeaves(final Predicate<ASTNode> predicate) {
    return new ToFirstUpToLeavesFunction<>(predicate);
  }

  public static <N extends ASTNode> Function<N, ASTNode> toFirstAncestor(final Predicate<ASTNode> predicate) {
    return new ToFirstAncestorFunction<>(predicate);
  }

  public static <N extends ASTNode> Function<N, ASTNode> toFirstDescendant(final Predicate<ASTNode> predicate) {
    return new ToFirstDescendantFunction<>(predicate);
  }
}
