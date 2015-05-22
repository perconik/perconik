package com.gratex.perconik.uaca.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference.Preference;
import sk.stuba.fiit.perconik.eclipse.ui.Windows;

public final class UacaMessageDialogs {
  private UacaMessageDialogs() {}

  public static void openError(final String key, final String message) {
    openError(key, message, "Always display warning on service failure");
  }

  public static void openError(final String key, final String message, final String toggle) {
    final Runnable dialog = new Runnable() {
      public void run() {
        IWorkbenchWindow window = Windows.getActiveWindow();

        Shell shell = window != null ? window.getShell() : Display.getDefault().getActiveShell();

        String title = "UACA Proxy Error";

        IPreferenceStore store = UacaPreferences.getShared().getPreferenceStore();

        Preference preference = Preference.usingToggleState(store, key);

        MessageDialogWithPreference.openError(shell, title, message, toggle, preference).setBlockOnOpen(true);
      }
    };

    Display.getDefault().syncExec(dialog);
  }
}
