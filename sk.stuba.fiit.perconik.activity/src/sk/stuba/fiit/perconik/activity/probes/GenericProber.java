package sk.stuba.fiit.perconik.activity.probes;

import java.util.Map;

import sk.stuba.fiit.perconik.data.content.AnyContent;

public interface GenericProber<T extends AnyContent, P extends Probe<?>> {
  public void inject(T content);

  public Map<String, P> probes();
}
