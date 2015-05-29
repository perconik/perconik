package sk.stuba.fiit.perconik.eclipse.jface.dialogs;

import javax.annotation.Nullable;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import static com.google.common.base.Strings.nullToEmpty;

import static sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogs.buttonLabels;

public class MessageDialogWithTextArea extends MessageDialog {
  private String text;

  private Text area;

  public MessageDialogWithTextArea(final Shell shell, @Nullable final String title, @Nullable final Image image, final String message, final int type, final String[] labels, final int index) {
    super(shell, title, image, message, type, labels, index);
  }

  public static MessageDialogWithTextArea open(final int kind, final Shell shell, @Nullable final String title, final String message, final String text, int style) {
    MessageDialogWithTextArea dialog = new MessageDialogWithTextArea(shell, title, null, message, kind, buttonLabels(kind), 0);

    style &= SWT.SHEET;

    dialog.setShellStyle(dialog.getShellStyle() | style);
    dialog.setText(text);
    dialog.open();

    return dialog;
  }

  public static MessageDialogWithTextArea openError(final Shell shell, @Nullable final String title, final String message, final String text) {
    return open(ERROR, shell, title, message, text, SWT.NONE);
  }

  public static MessageDialogWithTextArea openInformation(final Shell shell, @Nullable final String title, final String message, final String text) {
    return open(INFORMATION, shell, title, message, text, SWT.NONE);
  }

  public static MessageDialogWithTextArea openOkCancelConfirm(final Shell shell, @Nullable final String title, final String message, final String text) {
    return open(CONFIRM, shell, title, message, text, SWT.NONE);
  }

  public static MessageDialogWithTextArea openWarning(final Shell shell, @Nullable final String title, final String message, final String text) {
    return open(WARNING, shell, title, message, text, SWT.NONE);
  }

  public static MessageDialogWithTextArea openYesNoCancelQuestion(final Shell shell, @Nullable final String title, final String message, final String text) {
    return open(QUESTION_WITH_CANCEL, shell, title, message, text, SWT.NONE);
  }

  public static MessageDialogWithTextArea openYesNoQuestion(final Shell shell, @Nullable final String title, final String message, final String text) {
    return open(QUESTION, shell, title, message, text, SWT.NONE);
  }

  @Override
  protected Control createCustomArea(final Composite parent) {
    super.createCustomArea(parent);

    this.area = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.READ_ONLY);

    GridData gridData = new GridData(GridData.FILL_BOTH);
    gridData.horizontalSpan = 2;
    gridData.heightHint = 200;

    this.area.setBackground(this.getShell().getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
    this.area.setLayoutData(gridData);
    this.area.setText(nullToEmpty(this.getText()));

    return this.area;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  @Override
  protected boolean isResizable() {
    return true;
  }
}
