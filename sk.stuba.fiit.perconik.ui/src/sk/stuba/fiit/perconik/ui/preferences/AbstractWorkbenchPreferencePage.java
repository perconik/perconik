package sk.stuba.fiit.perconik.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public abstract class AbstractWorkbenchPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
  protected AbstractWorkbenchPreferencePage() {}

  public void init(final IWorkbench workbench) {}
}
