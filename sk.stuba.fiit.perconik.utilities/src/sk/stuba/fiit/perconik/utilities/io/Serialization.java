package sk.stuba.fiit.perconik.utilities.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.google.common.base.Strings;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolvers;

public final class Serialization
{
	private Serialization()
	{
		throw new AssertionError();
	}
	
	public static final Object readFromString(String data) throws ClassNotFoundException, IOException
	{
		return readFromString(data, ClassResolvers.getDefault());
	}
	
	public static final Object readFromString(String data, ClassResolver resolver) throws ClassNotFoundException, IOException
	{
		if (Strings.isNullOrEmpty(data))
		{
			return null;
		}
		
		try (ByteArrayInputStream bytes = new ByteArrayInputStream(data.getBytes()))
		{
			try (ObjectInputStream objects = new ClassResolvingObjectInputStream(resolver, bytes))
			{
				return objects.readObject();
			}
		}
	}
	
	public static final String writeToString(Object object) throws IOException
	{
		try (ByteArrayOutputStream bytes = new ByteArrayOutputStream())
		{
			try (ObjectOutputStream objects = new ObjectOutputStream(bytes))
			{
				objects.writeObject(object);
				objects.flush();
			}
			
			return bytes.toString();
		}
	}
}
