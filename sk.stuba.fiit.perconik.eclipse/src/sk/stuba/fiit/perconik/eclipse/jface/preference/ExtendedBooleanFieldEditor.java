package sk.stuba.fiit.perconik.eclipse.jface.preference;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ExtendedBooleanFieldEditor extends BooleanFieldEditor {
  private Button control;

  public ExtendedBooleanFieldEditor(String name, String label, Composite parent) {
    this(name, label, DEFAULT, parent);
  }

  public ExtendedBooleanFieldEditor(String name, String label, int style, Composite parent) {
    super(name, label, style, parent);
  }

  public Button getChangeControl() {
    if (this.control == null) {
      throw new IllegalStateException();
    }

    return this.getChangeControl(null);
  }

  @Override
  public Button getChangeControl(Composite parent) {
    if (this.control == null) {
      this.control = super.getChangeControl(parent);
    }

    return this.control;
  }
}
