package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Map.Entry;

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

import sk.stuba.fiit.perconik.core.ui.plugin.Activator;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Maps.immutableEntry;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.requireNonNullOrEmpty;

final class MapEntryDialog<K, V> extends StatusDialog {
  private final MapEntryDialog.EntryConverter<K, V> converter;

  private Entry<K, V> entry;

  private Label keyLabel;

  private Text keyText;

  private Label valueLabel;

  private Text valueText;

  MapEntryDialog(final Shell parent, final MapEntryDialog.EntryConverter<K, V> converter) {
    super(parent);

    this.setTitle("Option Dialog");

    this.converter = requireNonNull(converter);
  }

  static <K, V> MapEntryDialog<K, V> forObjectEntry(final Shell parent) {
    return new MapEntryDialog<>(parent, StringEntryConverter.<K, V>create());
  }

  static MapEntryDialog<String, String> forStringEntry(final Shell parent) {
    return forObjectEntry(parent);
  }

  interface EntryConverter<K, V> {
    public Entry<K, V> convert(Entry<K, V> original, String key, String value);
  }

  static abstract class AbstractEntryConverter<K, V> implements MapEntryDialog.EntryConverter<K, V> {
    AbstractEntryConverter() {}

    public Entry<K, V> convert(final Entry<K, V> original, final String key, final String value) {
      return immutableEntry(this.key(original.getKey(), key), this.value(original.getValue(), value));
    }

    abstract K key(K original, String text);

    abstract V value(V original, String text);
  }

  static final class StringEntryConverter extends MapEntryDialog.AbstractEntryConverter<Object, Object> {
    private static final MapEntryDialog.StringEntryConverter INSTANCE = new StringEntryConverter();

    private StringEntryConverter() {}

    static <K, V> MapEntryDialog.EntryConverter<K, V> create() {
      @SuppressWarnings("unchecked")
      MapEntryDialog.EntryConverter<K, V> converter = (MapEntryDialog.EntryConverter<K, V>) StringEntryConverter.INSTANCE;

      return converter;
    }

    @Override
    String key(final Object original, final String text) {
      return requireNonNullOrEmpty(text);
    }

    @Override
    String value(final Object original, final String text) {
      return requireNonNull(text);
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

    this.load();

    this.keyText.setFocus();

    return composite;
  }

  @Override
  protected void okPressed() {
    this.entry = this.read();

    super.okPressed();
  }

  Entry<K, V> read() {
    return this.converter.convert(this.entry, this.keyText.getText(), this.valueText.getText());
  }

  void load() {
    this.keyText.setText(toText(this.entry.getKey()));
    this.valueText.setText(toText(this.entry.getValue()));
  }

  void update() {
    try {
      this.read();
      this.updateStatus(Status.OK_STATUS);
    } catch (RuntimeException failure) {
      this.updateStatus(new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Invalid option", failure));
    }
  }

  private static String toText(final Object value) {
    return value != null ? value.toString() : "";
  }

  public void setEntry(final Entry<K, V> entry) {
    this.entry = immutableEntry(entry.getKey(), entry.getValue());
  }

  public Entry<K, V> getEntry() {
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
