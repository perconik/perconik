package sk.stuba.fiit.perconik.activity.probes;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.ForwardingObject;

import sk.stuba.fiit.perconik.data.content.AnyContent;

public abstract class ForwardingProber<T extends AnyContent, P extends Probe<?>> extends ForwardingObject implements Prober<T, P> {
  protected ForwardingProber() {}

  @Override
  protected abstract Prober<T, P> delegate();

  @Override
  public boolean equals(@Nullable final Object object) {
    return object == this || this.delegate().equals(object);
  }

  @Override
  public int hashCode() {
    return this.delegate().hashCode();
  }

  public void inject(final T content) {
    this.delegate().inject(content);
  }

  public Map<String, P> probes() {
    return this.delegate().probes();
  }
}
