package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import sk.stuba.fiit.perconik.utilities.MoreStrings;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;

public final class NodeTokenizer<N extends ASTNode> implements ListCollector<N, String> {
  private final Function<N, ? extends Iterable<ASTNode>> collector;

  private final Function<ASTNode, String> transformer;

  private final Tokenizer tokenizer;

  NodeTokenizer(final Builder<N> builder) {
    this.collector = firstNonNull(builder.collector, NodeCollectors.<N, ASTNode>ofType(NodeType.SIMPLE_NAME));
    this.transformer = firstNonNull(builder.transformer, MoreStrings.<ASTNode>toStringFunction());
    this.tokenizer = firstNonNull(builder.tokenizer, Tokenizers.shared);
  }

  public static <N extends ASTNode> NodeTokenizer<N> using(final Function<N, ? extends Iterable<ASTNode>> collector) {
    return new Builder<N>().collector(collector).build();
  }

  public static final class Builder<N extends ASTNode> {
    Function<N, ? extends Iterable<ASTNode>> collector;

    Function<ASTNode, String> transformer;

    Tokenizer tokenizer;

    public Builder() {}

    public Builder<N> collector(final Function<N, ? extends Iterable<ASTNode>> collector) {
      checkState(this.collector == null);

      this.collector = checkNotNull(collector);

      return this;
    }

    public Builder<N> transformer(final Function<ASTNode, String> transformer) {
      checkState(this.transformer == null);

      this.transformer = checkNotNull(transformer);

      return this;
    }

    public Builder<N> tokenizer(final Tokenizer tokenizer) {
      checkState(this.tokenizer == null);

      this.tokenizer = checkNotNull(tokenizer);

      return this;
    }

    public NodeTokenizer<N> build() {
      return new NodeTokenizer<>(this);
    }
  }

  public static <N extends ASTNode> Builder<N> builder() {
    return new Builder<>();
  }

  public List<String> apply(final N node) {
    if (node == null) {
      return Collections.emptyList();
    }

    Iterable<ASTNode> nodes = this.collector.apply(node);
    List<String> tokens = newArrayList();

    for (ASTNode other: nodes) {
      String input = this.transformer.apply(other);

      tokens.addAll(this.tokenizer.apply(input));
    }

    return tokens;
  }

  @Override
  public boolean equals(@Nullable final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof NodeTokenizer)) {
      return false;
    }

    NodeTokenizer<?> other = (NodeTokenizer<?>) o;

    return this.collector.equals(other.collector) && this.transformer.equals(other.transformer) && this.tokenizer.equals(other.tokenizer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.collector, this.transformer, this.tokenizer);
  }

  @Override
  public String toString() {
    return "tokenizer(" + Joiner.on(",").join(this.collector, this.transformer, this.tokenizer) + ")";
  }

  public Function<N, ? extends Iterable<ASTNode>> getCollector() {
    return this.collector;
  }

  public Function<ASTNode, String> getTransformer() {
    return this.transformer;
  }

  public Tokenizer getTokenizer() {
    return this.tokenizer;
  }
}
