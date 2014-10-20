package sk.stuba.fiit.perconik.eclipse.jface.preference;

import javax.annotation.Nullable;

import com.google.common.collect.ForwardingObject;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;

/**
 * A preference store which forwards all its method calls to another preference
 * store. Subclasses should override one or more methods to modify the behavior
 * of the backing preference store as desired per the decorator pattern.
 *
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object.
 * See {@link ForwardingObject} for more details.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingPreferenceStore extends ForwardingObject implements IPreferenceStore {
  /**
   * Constructor for use by subclasses.
   */
  protected ForwardingPreferenceStore() {}

  @Override
  protected abstract IPreferenceStore delegate();

  public void addPropertyChangeListener(final IPropertyChangeListener listener) {
    this.delegate().addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(final IPropertyChangeListener listener) {
    this.delegate().removePropertyChangeListener(listener);
  }

  public void firePropertyChangeEvent(final String name, final Object oldValue, final Object newValue) {
    this.delegate().firePropertyChangeEvent(name, oldValue, newValue);
  }

  public boolean needsSaving() {
    return this.delegate().needsSaving();
  }

  public void putValue(final String name, @Nullable final String value) {
    this.delegate().putValue(name, value);
  }

  public void setToDefault(final String name) {
    this.delegate().setToDefault(name);
  }

  public boolean contains(final String name) {
    return this.delegate().contains(name);
  }

  public boolean isDefault(final String name) {
    return this.delegate().isDefault(name);
  }

  public void setDefault(final String name, final boolean value) {
    this.delegate().setDefault(name, value);
  }

  public void setDefault(final String name, final float value) {
    this.delegate().setDefault(name, value);
  }

  public void setDefault(final String name, final double value) {
    this.delegate().setDefault(name, value);
  }

  public void setDefault(final String name, final int value) {
    this.delegate().setDefault(name, value);
  }

  public void setDefault(final String name, final long value) {
    this.delegate().setDefault(name, value);
  }

  public void setDefault(final String name, @Nullable final String value) {
    this.delegate().setDefault(name, value);
  }

  public boolean getDefaultBoolean(final String name) {
    return this.delegate().getDefaultBoolean(name);
  }

  public float getDefaultFloat(final String name) {
    return this.delegate().getDefaultFloat(name);
  }

  public double getDefaultDouble(final String name) {
    return this.delegate().getDefaultDouble(name);
  }

  public int getDefaultInt(final String name) {
    return this.delegate().getDefaultInt(name);
  }

  public long getDefaultLong(final String name) {
    return this.delegate().getDefaultLong(name);
  }

  public String getDefaultString(final String name) {
    return this.delegate().getDefaultString(name);
  }

  public void setValue(final String name, final boolean value) {
    this.delegate().setValue(name, value);
  }

  public void setValue(final String name, final float value) {
    this.delegate().setValue(name, value);
  }

  public void setValue(final String name, final double value) {
    this.delegate().setValue(name, value);
  }

  public void setValue(final String name, final int value) {
    this.delegate().setValue(name, value);
  }

  public void setValue(final String name, final long value) {
    this.delegate().setValue(name, value);
  }

  public void setValue(final String name, @Nullable final String value) {
    this.delegate().setValue(name, value);
  }

  public boolean getBoolean(final String name) {
    return this.delegate().getBoolean(name);
  }

  public float getFloat(final String name) {
    return this.delegate().getFloat(name);
  }

  public double getDouble(final String name) {
    return this.delegate().getDouble(name);
  }

  public int getInt(final String name) {
    return this.delegate().getInt(name);
  }

  public long getLong(final String name) {
    return this.delegate().getLong(name);
  }

  public String getString(final String name) {
    return this.delegate().getString(name);
  }
}
