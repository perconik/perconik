package sk.stuba.fiit.perconik.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public final class Groups {
  private Groups() {}

  public static Group create(final Composite parent, final String title) {
    Group group = new Group(parent, SWT.NONE);

    group.setText(title);
    group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    return group;
  }
}
