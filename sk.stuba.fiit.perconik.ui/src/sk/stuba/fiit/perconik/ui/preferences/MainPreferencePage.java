package sk.stuba.fiit.perconik.ui.preferences;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.preferences.MainPreferences;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <i>PerConIK</i> main preference page.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MainPreferencePage extends AbstractWorkbenchPreferencePage {
  private MainPreferences preferences;

  public MainPreferencePage() {}

  @Override
  public void init(final IWorkbench workbench) {
    this.noDefaultAndApplyButton();

    this.setDescription("Expand the tree to edit preferences for a specific feature.");
  }

  @Override
  protected Control createContent(final Composite parent) {
    return parent;
  }

  public void setMainPreferences(final MainPreferences preferences) {
    this.preferences = checkNotNull(preferences);
  }

  public MainPreferences getMainPreferences() {
    return this.preferences;
  }
}
