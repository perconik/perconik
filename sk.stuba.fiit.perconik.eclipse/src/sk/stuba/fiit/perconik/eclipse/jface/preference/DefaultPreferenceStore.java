package sk.stuba.fiit.perconik.eclipse.jface.preference;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingObject;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * A forwarding preference store which forwards all its value accessor method
 * calls to default value accessor methods of another preference store.
 * Subclasses should override one or more methods to modify the behavior
 * of the backing preference store as desired per the decorator pattern.
 * 
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object.
 * See {@link ForwardingObject} for more details.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class DefaultPreferenceStore extends ForwardingPreferenceStore
{
	private final IPreferenceStore store;
	
	/**
	 * Constructor for use by subclasses.
	 */
	private DefaultPreferenceStore(final IPreferenceStore store)
	{
		this.store = Preconditions.checkNotNull(store);
	}

	public static final DefaultPreferenceStore of(final IPreferenceStore store)
	{
		return new DefaultPreferenceStore(store);
	}
	
	@Override
	protected final IPreferenceStore delegate()
	{
		return this.store;
	}

	@Override
	public void setValue(String name, boolean value)
	{
		this.delegate().setDefault(name, value);
	}

	@Override
	public void setValue(String name, float value)
	{
		this.delegate().setDefault(name, value);
	}

	@Override
	public void setValue(String name, double value)
	{
		this.delegate().setDefault(name, value);
	}

	@Override
	public void setValue(String name, int value)
	{
		this.delegate().setDefault(name, value);
	}

	@Override
	public void setValue(String name, long value)
	{
		this.delegate().setDefault(name, value);
	}

	@Override
	public void setValue(String name, @Nullable String value)
	{
		this.delegate().setDefault(name, value);
	}

	@Override
	public boolean getBoolean(String name)
	{
		return this.delegate().getDefaultBoolean(name);
	}

	@Override
	public float getFloat(String name)
	{
		return this.delegate().getDefaultFloat(name);
	}

	@Override
	public double getDouble(String name)
	{
		return this.delegate().getDefaultDouble(name);
	}

	@Override
	public int getInt(String name)
	{
		return this.delegate().getDefaultInt(name);
	}

	@Override
	public long getLong(String name)
	{
		return this.delegate().getDefaultLong(name);
	}

	@Override
	public String getString(String name)
	{
		return this.delegate().getDefaultString(name);
	}
}
