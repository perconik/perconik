package sk.stuba.fiit.perconik.activity.data.process;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class ProcessData extends AnyStructuredData {
  protected Integer identifier;

  public ProcessData() {}

  public void setIdentifier(final Integer identifier) {
    this.identifier = identifier;
  }

  public Integer getIdentifier() {
    return this.identifier;
  }
}
