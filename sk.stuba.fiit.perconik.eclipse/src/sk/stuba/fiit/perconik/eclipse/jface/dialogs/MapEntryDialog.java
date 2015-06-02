package sk.stuba.fiit.perconik.eclipse.jface.dialogs;

import java.util.Map.Entry;

import javax.annotation.Nullable;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.collect.Maps.immutableEntry;

import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullOrEmpty;

public class MapEntryDialog<K, V> extends StatusDialog {
  private final EntryConverter<K, V> converter;

  private Entry<K, V> entry;

  private Label keyLabel;

  private Text keyText;

  private Label valueLabel;

  private Text valueText;

  public MapEntryDialog(final Shell parent) {
    this(parent, StringEntryConverter.<K, V>create());
  }

  public MapEntryDialog(final Shell parent, final EntryConverter<K, V> converter) {
    super(parent);

    this.converter = checkNotNull(converter);
  }

  public interface EntryConverter<K, V> {
    public Entry<K, V> toEntry(Entry<K, V> original, String key, String value);

    public Entry<String, String> toText(K key, V value);
  }

  public static abstract class AbstractEntryConverter<K, V> implements EntryConverter<K, V> {
    protected AbstractEntryConverter() {}

    public Entry<K, V> toEntry(final Entry<K, V> original, final String key, final String value) {
      return immutableEntry(this.textToKey(original.getKey(), key), this.textToValue(original.getValue(), value));
    }

    protected abstract K textToKey(@Nullable K original, @Nullable String text);

    protected abstract V textToValue(@Nullable V original, @Nullable String text);

    public Entry<String, String> toText(final K key, final V value) {
      return immutableEntry(this.keyToText(key), this.valueToText(value));
    }

    protected abstract String keyToText(@Nullable K key);

    protected abstract String valueToText(@Nullable V value);
  }

  public static final class StringEntryConverter extends AbstractEntryConverter<Object, Object> {
    private static final StringEntryConverter INSTANCE = new StringEntryConverter();

    private StringEntryConverter() {}

    public static <K, V> EntryConverter<K, V> create() {
      @SuppressWarnings("unchecked")
      EntryConverter<K, V> converter = (EntryConverter<K, V>) StringEntryConverter.INSTANCE;

      return converter;
    }

    @Override
    protected String textToKey(@Nullable final Object original, @Nullable final String text) {
      return checkNotNullOrEmpty(text);
    }

    @Override
    protected String textToValue(@Nullable final Object original, @Nullable final String text) {
      return emptyToNull(text);
    }

    private static String toText(@Nullable final Object value) {
      return value != null ? value.toString() : "";
    }

    @Override
    protected String keyToText(@Nullable final Object key) {
      return toText(key);
    }

    @Override
    protected String valueToText(@Nullable final Object value) {
      return toText(value);
    }
  }

  @Override
  protected Control createDialogArea(final Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);

    composite.setLayout(new GridLayout(4, false));
    composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

    this.keyLabel = new Label(composite, SWT.NONE);
    this.keyText = new Text(composite, SWT.BORDER);

    this.keyLabel.setText("Key");
    this.keyLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
    this.keyText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 3, 1));

    this.valueLabel = new Label(composite, SWT.NONE);
    this.valueText = new Text(composite, SWT.BORDER);

    this.valueLabel.setText("Value");
    this.valueLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
    this.valueText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 3, 1));

    ModifyListener validator = new ModifyListener() {
      public void modifyText(final ModifyEvent e) {
        update();
      }
    };

    this.keyText.addModifyListener(validator);
    this.valueText.addModifyListener(validator);

    Dialog.applyDialogFont(composite);

    this.writeToText();

    this.keyText.setFocus();

    return composite;
  }

  @Override
  protected void okPressed() {
    this.entry = this.readFromText();

    super.okPressed();
  }

  private Entry<K, V> readFromText() {
    return this.converter.toEntry(this.entry, this.keyText.getText(), this.valueText.getText());
  }

  private void writeToText() {
    Entry<String, String> text = this.converter.toText(this.entry.getKey(), this.entry.getValue());

    this.keyText.setText(text.getKey());
    this.valueText.setText(text.getValue());
  }

  void update() {
    try {
      this.readFromText();
      this.updateStatus(Status.OK_STATUS);
    } catch (RuntimeException failure) {
      this.updateStatus(new Status(IStatus.ERROR, Status.CANCEL_STATUS.getPlugin(), IStatus.OK, "Invalid option", failure));
    }
  }

  public final void setEntry(final Entry<? extends K, ? extends V> entry) {
    this.entry = immutableEntry((K) entry.getKey(), (V) entry.getValue());
  }

  public final EntryConverter<K, V> getConverter() {
    return this.converter;
  }

  public final Entry<K, V> getEntry() {
    return this.entry;
  }

  @Override
  public boolean isHelpAvailable() {
    return false;
  }

  @Override
  protected boolean isResizable() {
    return true;
  }
}
