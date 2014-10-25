package sk.stuba.fiit.perconik.activity.probes;

public interface ContextProbe<T> extends Probe<T> {
  public Object getContext();
}
