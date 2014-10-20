package sk.stuba.fiit.perconik.eclipse.jface.preference;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class UrlFieldEditor extends StringFieldEditor {
  public UrlFieldEditor(final String name, final String label, final Composite parent) {
    super(name, label, parent);
  }

  public UrlFieldEditor(final String name, final String label, final int width, final Composite parent) {
    super(name, label, width, parent);
  }

  public UrlFieldEditor(final String name, final String label, final int width, final int strategy, final Composite parent) {
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
