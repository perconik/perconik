package sk.stuba.fiit.perconik.utilities.function;

import java.util.Collection;

import javax.annotation.Nullable;

import com.google.common.base.Function;

public interface Collector<T, E> extends Function<T, Collection<E>> {
  @Override
  public Collection<E> apply(@Nullable T input);
}
