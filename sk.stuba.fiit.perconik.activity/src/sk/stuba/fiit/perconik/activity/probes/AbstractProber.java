package sk.stuba.fiit.perconik.activity.probes;

import java.util.Map.Entry;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

import sk.stuba.fiit.perconik.data.content.AnyContent;

public abstract class AbstractProber<T extends AnyContent, P extends Probe<?>> implements Prober<T, P> {
  protected AbstractProber() {}

  @Override
  public void inject(final T content) {
    for (Entry<String, P> entry: this.probes().entrySet()) {
      this.probeAndPut(entry.getValue(), entry.getKey(), content);
    }
  }

  protected void probeAndPut(final P probe, final String key, final T content) {
    Object data = null;

    try {
      data = probe.get();
    } catch (RuntimeException failure) {
      this.onProbeFailure(probe, key, failure);
    }

    content.put(key, data);
  }

  protected void onProbeFailure(final P probe, final String key, final RuntimeException failure) {
    String message = probe + " failed on " + key + " with " + failure.getMessage();

    throw new ProbingException(message, failure);
  }

  @Override
  public String toString() {
    return this.toStringHelper().toString();
  }

  protected ToStringHelper toStringHelper() {
    ToStringHelper helper = MoreObjects.toStringHelper(this);

    helper.add("probes", this.probes());

    return helper;
  }
}
