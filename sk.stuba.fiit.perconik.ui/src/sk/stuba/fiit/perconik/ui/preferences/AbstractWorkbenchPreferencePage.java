package sk.stuba.fiit.perconik.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public abstract class AbstractWorkbenchPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
  private boolean contentCreated;

  protected AbstractWorkbenchPreferencePage() {}

  public void init(final IWorkbench workbench) {}

  @Override
  public void createControl(final Composite parent) {
    this.contentCreated = false;

    super.createControl(parent);
  }

  @Override
  protected final Control createContents(final Composite parent) {
    Control content = this.createContent(parent);

    this.contentCreated = true;

    return content;
  }

  protected abstract Control createContent(final Composite parent);

  protected boolean isContentCreated() {
    return this.isControlCreated() && this.contentCreated;
  }
}
