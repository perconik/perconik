package sk.stuba.fiit.perconik.activity.listeners.java.dom;

import com.google.common.base.Optional;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.core.java.dom.difference.NodeDeltaSet;
import sk.stuba.fiit.perconik.core.java.dom.difference.NodeDifferencer;

import static java.util.Objects.requireNonNull;

abstract class AbstractNodeDifferenceListener<T, N extends ASTNode, R extends ASTNode> extends AbstractCachingNodeListener<T, N> {
  final NodeDifferencer<? super N, R> differencer;

  AbstractNodeDifferenceListener(final DifferenceConfiguration<T, N, R> configuration) {
    super(configuration);

    this.differencer = requireNonNull(configuration.differencer);
  }

  static class DifferenceConfiguration<T, N extends ASTNode, R extends ASTNode> extends CachingConfiguration<T, N> {
    NodeDifferencer<? super N, R> differencer;

    DifferenceConfiguration() {}

    final void differencer(final NodeDifferencer<? super N, R> differencer) {
      this.differencer = requireNonNull(differencer);
    }
  }

  @Override
  final void process(final long time, final ElementChangedEvent event, final T key, final Optional<N> original, final N revised) {
    if (original.isPresent()) {
      N raw = original.get();

      NodeDeltaSet<R> difference = this.differencer.difference(raw, revised);

      this.process(time, event, key, raw, revised, difference);
    }
  }

  abstract void process(long time, ElementChangedEvent event, T key, N original, N revised, NodeDeltaSet<R> difference);
}
