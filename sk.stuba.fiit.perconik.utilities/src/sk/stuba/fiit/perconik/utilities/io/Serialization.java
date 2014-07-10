package sk.stuba.fiit.perconik.utilities.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolvers;

public final class Serialization
{
	private Serialization()
	{
		throw new AssertionError();
	}

	private static final Object read(byte[] data, ClassResolver resolver) throws ClassNotFoundException, IOException
	{
		if (data == null || data.length == 0)
		{
			return null;
		}

		try (ByteArrayInputStream bytes = new ByteArrayInputStream(data))
		{
			try (ObjectInputStream objects = new ClassResolvingObjectInputStream(resolver, bytes))
			{
				return objects.readObject();
			}
		}
	}

	public static final Object fromBytes(byte[] data) throws ClassNotFoundException, IOException
	{
		return fromBytes(data, ClassResolvers.getDefault());
	}

	public static final Object fromBytes(byte[] data, ClassResolver resolver) throws ClassNotFoundException, IOException
	{
		return read(data, resolver);
	}

	public static final Object fromString(String data) throws ClassNotFoundException, IOException
	{
		return fromBytes(data.getBytes());
	}

	public static final Object fromString(String data, Charset charset) throws ClassNotFoundException, IOException
	{
		return fromBytes(data.getBytes(charset));
	}

	public static final Object fromString(String data, Charset charset, ClassResolver resolver) throws ClassNotFoundException, IOException
	{
		return fromBytes(data.getBytes(charset), resolver);
	}

	private static final ByteArrayOutputStream write(Object object) throws IOException
	{
		try (ByteArrayOutputStream bytes = new ByteArrayOutputStream())
		{
			try (ObjectOutputStream objects = new ObjectOutputStream(bytes))
			{
				objects.writeObject(object);
				objects.flush();
			}

			return bytes;
		}
	}

	public static final byte[] toBytes(Object object) throws IOException
	{
		return write(object).toByteArray();
	}

	public static final String toString(Object object) throws IOException
	{
		return write(object).toString();
	}
}
