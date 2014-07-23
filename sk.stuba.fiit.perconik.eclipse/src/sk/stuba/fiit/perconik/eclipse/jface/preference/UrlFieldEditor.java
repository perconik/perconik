package sk.stuba.fiit.perconik.eclipse.jface.preference;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class UrlFieldEditor extends StringFieldEditor {
  public UrlFieldEditor(String name, String label, Composite parent) {
    super(name, label, parent);
  }

  public UrlFieldEditor(String name, String label, int width, Composite parent) {
    super(name, label, width, parent);
  }

  public UrlFieldEditor(String name, String label, int width, int strategy, Composite parent) {
    super(name, label, width, strategy, parent);
  }

  @Override
  protected boolean doCheckState() {
    return this.getUrlValue() != null;
  }

  public URL getUrlValue() {
    try {
      return new URL(this.getStringValue());
    } catch (MalformedURLException e) {
      return null;
    }
  }
}
