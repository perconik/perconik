package sk.stuba.fiit.perconik.activity.probes;

import sk.stuba.fiit.perconik.data.content.AnyContent;

public abstract class AbstractProber extends AbstractGenericProber<AnyContent, Probe<?>> implements Prober {
  protected AbstractProber() {}
}
