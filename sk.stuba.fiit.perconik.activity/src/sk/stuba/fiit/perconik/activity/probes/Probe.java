package sk.stuba.fiit.perconik.activity.probes;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;

public interface Probe<T> extends Supplier<T> {
  @Override
  public boolean equals(@Nullable Object object);

  @Override
  public T get();
}
