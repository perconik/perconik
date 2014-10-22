package sk.stuba.fiit.perconik.activity.probes;

import java.util.Map;

import sk.stuba.fiit.perconik.data.content.AnyContent;

public interface Prober extends GenericProber<AnyContent, Probe<?>> {
  public void inject(AnyContent content);

  public Map<String, Probe<?>> probes();
}
