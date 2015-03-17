package sk.stuba.fiit.perconik.activity.ui.preferences;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Shell;

import sk.stuba.fiit.perconik.activity.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MapEntryDialog;

final class CustomMapEntryDialog<K, V> extends MapEntryDialog<K, V> {
  CustomMapEntryDialog(final Shell parent) {
    super(parent);
  }

  @Override
  protected IDialogSettings getDialogBoundsSettings() {
    return DialogSettings.getOrCreateSection(Activator.defaultInstance().getDialogSettings(), this.getClass().getName());
  }
}
