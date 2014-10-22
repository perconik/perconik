package sk.stuba.fiit.perconik.activity.probes;

import com.google.common.base.Supplier;

public interface Probe<T> extends Supplier<T> {
  @Override
  public T get();
}
