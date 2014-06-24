package sk.stuba.fiit.perconik.preferences;

import static com.google.common.base.Preconditions.checkNotNull;
import sk.stuba.fiit.perconik.utilities.io.Serialization;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

public abstract class AbstractObjectPreferences extends AbstractPreferences
{
	final ClassResolver resolver;
	
	public AbstractObjectPreferences(final Scope scope, final ClassResolver resolver)
	{
		super(scope);
		
		this.resolver = checkNotNull(resolver);
	}
	
	static final Object fromStringOrFailure(final String key, final String value, final ClassResolver resolver)
	{
		try
		{
			return Serialization.readFromString(value, resolver);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Unable to read object under key " + key + " from string", e);
		}
	}

	static final String toStringOrFailure(final String key, final Object value)
	{
		try
		{
			return Serialization.writeToString(value);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Unable to write object under key " + key + " to string", e);
		}
	}

	protected final void setDefault(final String key, final Object value)
	{
		this.store.setDefault(key, toStringOrFailure(key, value));
	}

	protected final Object getDefaultObject(final String key)
	{
		return fromStringOrFailure(key, this.store.getDefaultString(key), this.resolver);
	}

	protected final void setValue(final String key, final Object value)
	{
		this.store.setValue(key, toStringOrFailure(key, value));
	}

	protected final Object getObject(final String key)
	{
		return fromStringOrFailure(key, this.store.getString(key), this.resolver);
	}
}
