package sk.stuba.fiit.perconik.activity.data.core;

import sk.stuba.fiit.perconik.activity.probes.Probe;

public interface CoreProbe extends Probe<CoreData> {
  @Override
  public CoreData get();
}
