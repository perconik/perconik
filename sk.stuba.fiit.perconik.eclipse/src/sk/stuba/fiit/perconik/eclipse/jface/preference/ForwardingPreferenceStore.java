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
public abstract class ForwardingPreferenceStore extends ForwardingObject implements IPreferenceStore
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ForwardingPreferenceStore()
	{
	}

	@Override
	protected abstract IPreferenceStore delegate();

	public void addPropertyChangeListener(IPropertyChangeListener listener)
	{
		this.delegate().addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(IPropertyChangeListener listener)
	{
		this.delegate().removePropertyChangeListener(listener);
	}

	public void firePropertyChangeEvent(String name, Object oldValue, Object newValue)
	{
		this.delegate().firePropertyChangeEvent(name, oldValue, newValue);
	}

	public boolean needsSaving()
	{
		return this.delegate().needsSaving();
	}

	public void putValue(String name, @Nullable String value)
	{
		this.delegate().putValue(name, value);
	}

	public void setToDefault(String name)
	{
		this.delegate().setToDefault(name);
	}

	public boolean contains(String name)
	{
		return this.delegate().contains(name);
	}

	public boolean isDefault(String name)
	{
		return this.delegate().isDefault(name);
	}

	public void setDefault(String name, boolean value)
	{
		this.delegate().setDefault(name, value);
	}

	public void setDefault(String name, float value)
	{
		this.delegate().setDefault(name, value);
	}

	public void setDefault(String name, double value)
	{
		this.delegate().setDefault(name, value);
	}

	public void setDefault(String name, int value)
	{
		this.delegate().setDefault(name, value);
	}

	public void setDefault(String name, long value)
	{
		this.delegate().setDefault(name, value);
	}

	public void setDefault(String name, @Nullable String value)
	{
		this.delegate().setDefault(name, value);
	}

	public boolean getDefaultBoolean(String name)
	{
		return this.delegate().getDefaultBoolean(name);
	}

	public float getDefaultFloat(String name)
	{
		return this.delegate().getDefaultFloat(name);
	}

	public double getDefaultDouble(String name)
	{
		return this.delegate().getDefaultDouble(name);
	}

	public int getDefaultInt(String name)
	{
		return this.delegate().getDefaultInt(name);
	}

	public long getDefaultLong(String name)
	{
		return this.delegate().getDefaultLong(name);
	}

	public String getDefaultString(String name)
	{
		return this.delegate().getDefaultString(name);
	}

	public void setValue(String name, boolean value)
	{
		this.delegate().setValue(name, value);
	}

	public void setValue(String name, float value)
	{
		this.delegate().setValue(name, value);
	}

	public void setValue(String name, double value)
	{
		this.delegate().setValue(name, value);
	}

	public void setValue(String name, int value)
	{
		this.delegate().setValue(name, value);
	}

	public void setValue(String name, long value)
	{
		this.delegate().setValue(name, value);
	}

	public void setValue(String name, @Nullable String value)
	{
		this.delegate().setValue(name, value);
	}

	public boolean getBoolean(String name)
	{
		return this.delegate().getBoolean(name);
	}

	public float getFloat(String name)
	{
		return this.delegate().getFloat(name);
	}

	public double getDouble(String name)
	{
		return this.delegate().getDouble(name);
	}

	public int getInt(String name)
	{
		return this.delegate().getInt(name);
	}

	public long getLong(String name)
	{
		return this.delegate().getLong(name);
	}

	public String getString(String name)
	{
		return this.delegate().getString(name);
	}
}
