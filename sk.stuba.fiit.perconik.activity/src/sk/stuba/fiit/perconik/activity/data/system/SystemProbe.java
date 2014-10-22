package sk.stuba.fiit.perconik.activity.data.system;

import sk.stuba.fiit.perconik.activity.probes.Probe;

public interface SystemProbe extends Probe<SystemData> {
  @Override
  public SystemData get();
}
