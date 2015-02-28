package sk.stuba.fiit.perconik.activity.probes;

import javax.annotation.Nullable;

import com.google.common.collect.ForwardingObject;

public abstract class ForwardingProbe<T> extends ForwardingObject implements Probe<T> {
  protected ForwardingProbe() {}

  @Override
  protected abstract Probe<T> delegate();

  @Override
  public boolean equals(@Nullable final Object object) {
    return object == this || this.delegate().equals(object);
  }

  @Override
  public int hashCode() {
    return this.delegate().hashCode();
  }

  public T get() {
    return this.delegate().get();
  }
}
