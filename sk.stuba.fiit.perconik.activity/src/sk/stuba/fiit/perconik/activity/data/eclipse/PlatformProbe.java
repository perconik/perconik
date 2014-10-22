package sk.stuba.fiit.perconik.activity.data.eclipse;

import sk.stuba.fiit.perconik.activity.probes.Probe;

public interface PlatformProbe extends Probe<PlatformData> {
  @Override
  public PlatformData get();
}
