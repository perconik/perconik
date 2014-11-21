package sk.stuba.fiit.perconik.eclipse.jface.dialogs;

import javax.annotation.Nullable;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

import static com.google.common.base.Preconditions.checkNotNull;

public class MessageDialogWithPreference extends MessageDialogWithToggle {
  protected Preference preference;

  public MessageDialogWithPreference(final Shell shell, @Nullable final String title, @Nullable final Image image, final String message, final int type, final String[] labels, final int index, final String toggle, final Preference preference) {
    super(shell, title, image, message, type, labels, index, toggle, preference.getAsState());

    this.setPreference(preference);
  }

  public static final class Preference {
    final IPreferenceStore store;

    final String key;

    final Strategy strategy;

    Preference(final IPreferenceStore store, final String key, final Strategy strategy) {
      this.store = checkNotNull(store);
      this.key = checkNotNull(key);
      this.strategy = checkNotNull(strategy);
    }

    public static Preference of(final IPreferenceStore store, final String key, final Strategy strategy) {
      return new Preference(store, key, strategy);
    }

    public static Preference usingReturnCode(final IPreferenceStore store, final String key) {
      return new Preference(store, key, ReturnCodeStrategy.INSTANCE);
    }

    public static Preference usingToggleState(final IPreferenceStore store, final String key) {
      return new Preference(store, key, ToggleStateStrategy.INSTANCE);
    }

    public interface Strategy {
      public void set(IPreferenceStore store, String key, int code, boolean state);

      public Object get(IPreferenceStore store, String key);

      public boolean getAsState(IPreferenceStore store, String key);
    }

    enum ReturnCodeStrategy implements Strategy {
      INSTANCE;

      public void set(final IPreferenceStore store, final String key, final int code, final boolean state) {
        try {
          store.setValue(key, buttonCodeToValue(code));
        } catch (IllegalStateException | NullPointerException e) {}
      }

      public Object get(final IPreferenceStore store, final String key) {
        return store.getString(key);
      }

      public boolean getAsState(final IPreferenceStore store, final String key) {
        return this.get(store, key).equals(ALWAYS);
      }
    }

    enum ToggleStateStrategy implements Strategy {
      INSTANCE;

      public void set(final IPreferenceStore store, final String key, final int code, final boolean state) {
        store.setValue(key, state);
      }

      public Object get(final IPreferenceStore store, final String key) {
        return this.getAsState(store, key);
      }

      public boolean getAsState(final IPreferenceStore store, final String key) {
        return store.getBoolean(key);
      }
    }

    public void set(final int code, final boolean state) {
      this.strategy.set(this.store, this.key, code, state);
    }

    public Object get() {
      return this.strategy.get(this.store, this.key);
    }

    public boolean getAsState() {
      return this.strategy.getAsState(this.store, this.key);
    }

    public String getKey() {
      return this.key;
    }

    public IPreferenceStore getStore() {
      return this.store;
    }

    public Strategy getStrategy() {
      return this.strategy;
    }
  }

  public static MessageDialogWithPreference open(final int kind, final Shell shell, @Nullable final String title, final String message, final String toggle, final Preference preference, int style) {
    MessageDialogWithPreference dialog = new MessageDialogWithPreference(shell, title, null, message, kind, buttonLabels(kind), 0, toggle, preference);

    style &= SWT.SHEET;

    dialog.setShellStyle(dialog.getShellStyle() | style);
    dialog.open();

    return dialog;
  }

  public static MessageDialogWithPreference openError(final Shell shell, @Nullable final String title, final String message, final String toggle, final Preference preference) {
    return open(ERROR, shell, title, message, toggle, preference, SWT.NONE);
  }

  public static MessageDialogWithPreference openInformation(final Shell shell, @Nullable final String title, final String message, final String toggle, final Preference preference) {
    return open(INFORMATION, shell, title, message, toggle, preference, SWT.NONE);
  }

  public static MessageDialogWithPreference openOkCancelConfirm(final Shell shell, @Nullable final String title, final String message, final String toggle, final Preference preference) {
    return open(CONFIRM, shell, title, message, toggle, preference, SWT.NONE);
  }

  public static MessageDialogWithPreference openWarning(final Shell shell, @Nullable final String title, final String message, final String toggle, final Preference preference) {
    return open(WARNING, shell, title, message, toggle, preference, SWT.NONE);
  }

  public static MessageDialogWithPreference openYesNoCancelQuestion(final Shell shell, @Nullable final String title, final String message, final String toggle, final Preference preference) {
    return open(QUESTION_WITH_CANCEL, shell, title, message, toggle, preference, SWT.NONE);
  }

  public static MessageDialogWithPreference openYesNoQuestion(final Shell shell, @Nullable final String title, final String message, final String toggle, final Preference preference) {
    return open(QUESTION, shell, title, message, toggle, preference, SWT.NONE);
  }

  protected static String buttonCodeToValue(final int code) {
    switch (code) {
      case IDialogConstants.YES_ID:
      case IDialogConstants.YES_TO_ALL_ID:
      case IDialogConstants.PROCEED_ID:
      case IDialogConstants.OK_ID:
        return ALWAYS;

      case IDialogConstants.NO_ID:
      case IDialogConstants.NO_TO_ALL_ID:
        return NEVER;

      case IDialogConstants.ABORT_ID:
      case IDialogConstants.CANCEL_ID:
      case IDialogConstants.CLOSE_ID:
      case IDialogConstants.BACK_ID:
        return null;

      default:
    }

    throw new IllegalStateException();
  }

  protected static String[] buttonLabels(final int kind) {
    switch (kind) {
      case ERROR:
      case INFORMATION:
      case WARNING:
        return new String[] {IDialogConstants.OK_LABEL};

      case CONFIRM:
        return new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL};

      case QUESTION:
        return new String[] {IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL};

      case QUESTION_WITH_CANCEL:
        return new String[] {IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL};

      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  protected void buttonPressed(final int code) {
    this.setReturnCode(code);
    this.close();
  }

  @Override
  public int open() {
    int code = super.open();

    this.preference.set(this.getReturnCode(), this.getToggleState());

    return code;
  }

  public void setPreference(final Preference preference) {
    this.preference = preference;

    super.setPrefStore(preference.store);
    super.setPrefKey(preference.key);
  }

  public Preference getPreference() {
    return this.preference;
  }

  /**
   * @deprecated Use {@link #setPreference(Preference)} instead.
   */
  @Deprecated
  @Override
  public void setPrefStore(final IPreferenceStore store) {
    super.setPrefStore(store);

    this.setPreference(Preference.of(store, this.preference.key, this.preference.strategy));
  }

  /**
   * @deprecated Use {@link #setPreference(Preference)} instead.
   */
  @Deprecated
  @Override
  public void setPrefKey(final String key) {
    super.setPrefKey(key);

    this.setPreference(Preference.of(this.preference.store, key, this.preference.strategy));
  }

  /**
   * @deprecated Use {@link #getPreference()}{@code .}{@link Preference#getStore() getStore()} instead.
   */
  @Deprecated
  @Override
  public IPreferenceStore getPrefStore() {
    return super.getPrefStore();
  }

  /**
   * @deprecated Use {@link #getPreference()}{@code .}{@link Preference#getKey() getKey()} instead.
   */
  @Deprecated
  @Override
  public String getPrefKey() {
    return super.getPrefKey();
  }
}
