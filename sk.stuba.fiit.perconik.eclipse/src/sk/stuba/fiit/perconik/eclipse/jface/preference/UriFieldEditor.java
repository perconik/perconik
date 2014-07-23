package sk.stuba.fiit.perconik.eclipse.jface.preference;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class UriFieldEditor extends StringFieldEditor {
  public UriFieldEditor(String name, String label, Composite parent) {
    super(name, label, parent);
  }

  public UriFieldEditor(String name, String label, int width, Composite parent) {
    super(name, label, width, parent);
  }

  public UriFieldEditor(String name, String label, int width, int strategy, Composite parent) {
    super(name, label, width, strategy, parent);
  }

  @Override
  protected boolean doCheckState() {
    return this.getUriValue() != null;
  }

  public URI getUriValue() {
    try {
      return new URI(this.getStringValue());
    } catch (URISyntaxException e) {
      return null;
    }
  }
}
