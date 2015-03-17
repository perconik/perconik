package sk.stuba.fiit.perconik.activity.ui.preferences;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import sk.stuba.fiit.perconik.ui.preferences.AbstractWorkbenchPreferencePage;

public final class ActivityPreferencePage extends AbstractWorkbenchPreferencePage {
  public ActivityPreferencePage() {}

  @Override
  public final void createControl(final Composite parent) {
    super.createControl(parent);

    //TODO this.updatePage();
  }

  @Override
  protected Control createContents(final Composite parent) {
    this.initializeDialogUnits(parent);
    this.noDefaultAndApplyButton();

    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginWidth = 0;
    layout.marginHeight = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
    layout.horizontalSpacing = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    layout.verticalSpacing = this.convertVerticalDLUsToPixels(10);
    composite.setLayout(layout);

    // TODO

    Dialog.applyDialogFont(composite);

    return composite;
  }
}
