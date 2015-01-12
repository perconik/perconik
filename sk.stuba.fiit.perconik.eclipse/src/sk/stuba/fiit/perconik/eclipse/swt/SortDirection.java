package sk.stuba.fiit.perconik.eclipse.swt;

import org.eclipse.swt.SWT;

public enum SortDirection {
  UP(SWT.UP),

  DOWN(SWT.DOWN);

  private final int value;

  private SortDirection(final int value) {
    this.value = value;
  }

  public SortDirection opposite() {
    return this == UP ? DOWN : UP;
  }

  public int getValue() {
    return this.value;
  }
}
