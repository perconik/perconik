package sk.stuba.fiit.perconik.utilities.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolvers;

public final class Serialization {
  private Serialization() {}

  private static Object read(@Nullable final byte[] data, final ClassResolver resolver) throws ClassNotFoundException, IOException {
    if (data == null || data.length == 0) {
      return null;
    }

    try (ByteArrayInputStream bytes = new ByteArrayInputStream(data)) {
      try (ObjectInputStream objects = new ClassResolvingObjectInputStream(resolver, bytes)) {
        return objects.readObject();
      }
    }
  }

  public static Object fromBytes(@Nullable final byte[] data) throws ClassNotFoundException, IOException {
    return fromBytes(data, ClassResolvers.getDefault());
  }

  public static Object fromBytes(@Nullable final byte[] data, final ClassResolver resolver) throws ClassNotFoundException, IOException {
    return read(data, resolver);
  }

  public static Object fromString(@Nullable final String data) throws ClassNotFoundException, IOException {
    return fromBytes(data != null ? data.getBytes() : null);
  }

  public static Object fromString(@Nullable final String data, final Charset charset) throws ClassNotFoundException, IOException {
    return fromBytes(data != null ? data.getBytes(charset) : null);
  }

  public static Object fromString(@Nullable final String data, final Charset charset, final ClassResolver resolver) throws ClassNotFoundException, IOException {
    return fromBytes(data != null ? data.getBytes(charset) : null, resolver);
  }

  private static ByteArrayOutputStream write(final Object object) throws IOException {
    try (ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
      try (ObjectOutputStream objects = new ObjectOutputStream(bytes)) {
        objects.writeObject(object);
        objects.flush();
      }

      return bytes;
    }
  }

  public static byte[] toBytes(@Nullable final Object object) throws IOException {
    return write(object).toByteArray();
  }

  public static String toString(@Nullable final Object object) throws IOException {
    return write(object).toString();
  }
}
