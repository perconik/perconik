package sk.stuba.fiit.perconik.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public final class Groups {
  private Groups() {}

  public static Group create(final Composite parent, final String title) {
    GridData data = new GridData(GridData.FILL_HORIZONTAL);

    return create(parent, title, data);
  }

  public static Group create(final Composite parent, final String title, final GridData data) {
    GridLayout layout = new GridLayout();

    return create(parent, title, data, layout);
  }

  public static Group create(final Composite parent, final String title, final GridData data, final GridLayout layout) {
    Group group = new Group(parent, SWT.NONE);

    group.setText(title);
    group.setLayoutData(data);
    group.setLayout(layout);

    return group;
  }

  public static void updateMargins(final Group group) {
    GridLayout layout = (GridLayout) group.getLayout();

    layout.marginWidth = 5;
    layout.marginHeight = 5;
  }
}
