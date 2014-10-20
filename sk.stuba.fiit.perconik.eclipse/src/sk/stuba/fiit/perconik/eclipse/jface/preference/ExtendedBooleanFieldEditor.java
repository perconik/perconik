package sk.stuba.fiit.perconik.eclipse.jface.preference;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ExtendedBooleanFieldEditor extends BooleanFieldEditor {
  private Button control;

  public ExtendedBooleanFieldEditor(final String name, final String label, final Composite parent) {
    this(name, label, DEFAULT, parent);
  }

  public ExtendedBooleanFieldEditor(final String name, final String label, final int style, final Composite parent) {
    super(name, label, style, parent);
  }

  public Button getChangeControl() {
    if (this.control == null) {
      throw new IllegalStateException();
    }

    return this.getChangeControl(null);
  }

  @Override
  public Button getChangeControl(final Composite parent) {
    if (this.control == null) {
      this.control = super.getChangeControl(parent);
    }

    return this.control;
  }
}
