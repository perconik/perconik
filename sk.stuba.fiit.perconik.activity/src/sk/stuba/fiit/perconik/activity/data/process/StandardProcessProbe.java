package sk.stuba.fiit.perconik.activity.data.process;

import sk.stuba.fiit.perconik.environment.Environment;

public class StandardProcessProbe implements ProcessProbe {
  public StandardProcessProbe() {}

  public ProcessData get() {
    ProcessData data = new ProcessData();

    try {
      data.setIdentifier(Environment.getProcessIdentifier());
    } catch (RuntimeException failure) {
      // ignore
    }

    return data;
  }
}
