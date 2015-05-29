package sk.stuba.fiit.perconik.eclipse.jface.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;

public final class MessageDialogs {
  private MessageDialogs() {}

  public static String[] buttonLabels(final int kind) {
    switch (kind) {
      case MessageDialog.NONE:
      case MessageDialog.ERROR:
      case MessageDialog.INFORMATION:
      case MessageDialog.WARNING:
        return new String[] {IDialogConstants.OK_LABEL};

      case MessageDialog.CONFIRM:
        return new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL};

      case MessageDialog.QUESTION:
        return new String[] {IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL};

      case MessageDialog.QUESTION_WITH_CANCEL:
        return new String[] {IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL};

      default:
        throw new IllegalArgumentException();
    }
  }

  public static String buttonCodeToPreferenceValue(final int code) {
    switch (code) {
      case IDialogConstants.YES_ID:
      case IDialogConstants.YES_TO_ALL_ID:
      case IDialogConstants.PROCEED_ID:
      case IDialogConstants.OK_ID:
        return MessageDialogWithToggle.ALWAYS;

      case IDialogConstants.NO_ID:
      case IDialogConstants.NO_TO_ALL_ID:
        return MessageDialogWithToggle.NEVER;

      case IDialogConstants.ABORT_ID:
      case IDialogConstants.CANCEL_ID:
      case IDialogConstants.CLOSE_ID:
      case IDialogConstants.BACK_ID:
        return null;

      default:
        throw new IllegalArgumentException();
    }
  }
}
