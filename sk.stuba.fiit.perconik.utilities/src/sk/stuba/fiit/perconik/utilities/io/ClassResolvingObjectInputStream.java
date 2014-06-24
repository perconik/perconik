package sk.stuba.fiit.perconik.utilities.io;

import static com.google.common.base.Preconditions.checkNotNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

public class ClassResolvingObjectInputStream extends ObjectInputStream
{
	private final ClassResolver resolver;

	public ClassResolvingObjectInputStream(ClassResolver resolver, InputStream in) throws IOException
	{
		super(in);
		
		this.resolver = checkNotNull(resolver);
	}

	@Override
	protected Class<?> resolveClass(final ObjectStreamClass type) throws ClassNotFoundException, IOException
	{
		try
		{
			return this.resolver.forName(type.getName());
		}
		catch (ClassNotFoundException cnfe)
		{
			return super.resolveClass(type);
		}
	}
}
