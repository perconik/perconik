package sk.stuba.fiit.perconik.activity.data.process;

import sk.stuba.fiit.perconik.activity.probes.Probe;

public interface ProcessProbe extends Probe<ProcessData> {
  @Override
  public ProcessData get();
}
