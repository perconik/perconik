package sk.stuba.fiit.perconik.eclipse.jface.preference;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class UriFieldEditor extends StringFieldEditor {
  public UriFieldEditor(final String name, final String label, final Composite parent) {
    super(name, label, parent);
  }

  public UriFieldEditor(final String name, final String label, final int width, final Composite parent) {
    super(name, label, width, parent);
  }

  public UriFieldEditor(final String name, final String label, final int width, final int strategy, final Composite parent) {
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
