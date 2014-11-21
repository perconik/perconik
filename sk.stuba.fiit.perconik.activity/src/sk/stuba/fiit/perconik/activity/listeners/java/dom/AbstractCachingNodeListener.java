package sk.stuba.fiit.perconik.activity.listeners.java.dom;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import static java.util.Objects.requireNonNull;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;

import static sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementDeltaFlag.AST_AFFECTED;
import static sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementDeltaFlag.setOf;

abstract class AbstractCachingNodeListener<T, N extends ASTNode> extends AbstractJavaElementListener {
  final Function<? super CompilationUnit, ? extends Iterable<N>> collector;

  final Function<? super N, ? extends Optional<T>> keyer;

  final KeyerFailureBehavior behavior;

  final Cache<T, N> cache;

  AbstractCachingNodeListener(final CachingConfiguration<T, N> configuration) {
    this.collector = requireNonNull(configuration.collector);
    this.keyer = requireNonNull(configuration.keyer);
    this.behavior = requireNonNull(configuration.behavior);

    this.cache = configuration.build();
  }

  static class CachingConfiguration<T, N extends ASTNode> {
    Function<? super CompilationUnit, ? extends Iterable<N>> collector;

    Function<? super N, ? extends Optional<T>> keyer;

    KeyerFailureBehavior behavior;

    CacheBuilder<Object, Object> builder;

    CacheLoader<? super T, N> loader;

    CachingConfiguration() {}

    final void collector(final Function<? super CompilationUnit, ? extends Iterable<N>> collector) {
      this.collector = requireNonNull(collector);
    }

    final void keyer(final Function<? super N, ? extends Optional<T>> keyer) {
      this.keyer = requireNonNull(keyer);
    }

    final void behavior(final KeyerFailureBehavior behavior) {
      this.behavior = requireNonNull(behavior);
    }

    final void builder(final CacheBuilder<Object, Object> builder) {
      this.builder = requireNonNull(builder);
    }

    final void loader(final CacheLoader<? super T, N> loader) {
      this.loader = requireNonNull(loader);
    }

    final Cache<T, N> build() {
      if (this.loader == null) {
        return this.builder.build();
      }

      return this.builder.build(this.loader);
    }
  }

  enum KeyerFailureBehavior {
    REJECT {
      @Override
      <T, N extends ASTNode> Optional<? extends T> handle(final Function<? super N, ? extends Optional<T>> keyer, final N revised) {
        try {
          return requireNonNull(keyer.apply(revised));
        } catch (Exception ignore) {
          return absent();
        }
      }
    },

    RAISE {
      @Override
      <T, N extends ASTNode> Optional<? extends T> handle(final Function<? super N, ? extends Optional<T>> keyer, final N revised) {
        try {
          return requireNonNull(keyer.apply(revised));
        } catch (Exception failure) {
          throw new KeyComputationException(failure);
        }
      }
    };

    abstract <T, N extends ASTNode> Optional<? extends T> handle(Function<? super N, ? extends Optional<T>> keyer, N revised);
  }

  static class KeyComputationException extends RuntimeException {
    private static final long serialVersionUID = 0L;

    KeyComputationException(final Throwable cause) {
      super(cause);
    }
  }

  private Iterable<N> collect(final IJavaElementDelta delta) {
    return this.collector.apply(delta.getCompilationUnitAST());
  }

  private Optional<? extends T> key(final N revised) {
    return this.behavior.handle(this.keyer, revised);
  }

  private N update(final T key, final N revised) {
    return this.cache.asMap().put(key, revised);
  }

  @Override
  final void process(final long time, final ElementChangedEvent event) {
    IJavaElementDelta delta = event.getDelta();

    if (!setOf(delta.getFlags()).contains(AST_AFFECTED)) {
      return;
    }

    for (N revised: this.collect(delta)) {
      Optional<? extends T> option = this.key(revised);

      if (option.isPresent()) {
        T key = option.get();
        N original = this.update(key, revised);

        this.process(time, event, key, fromNullable(original), revised);
      }
    }
  }

  abstract void process(long time, ElementChangedEvent event, T key, Optional<N> original, N revised);
}
