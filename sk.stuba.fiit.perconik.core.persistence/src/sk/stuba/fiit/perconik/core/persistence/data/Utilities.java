package sk.stuba.fiit.perconik.core.persistence.data;

import java.io.Serializable;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Registrable;
import sk.stuba.fiit.perconik.core.Registrables;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.annotations.Experimental;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.persistence.ListenerRegistration;
import sk.stuba.fiit.perconik.core.persistence.MarkableRegistration;
import sk.stuba.fiit.perconik.core.persistence.Registration;
import sk.stuba.fiit.perconik.core.persistence.ResourceRegistration;
import sk.stuba.fiit.perconik.core.persistence.serialization.SerializedListenerData;
import sk.stuba.fiit.perconik.core.persistence.serialization.SerializedResourceData;
import sk.stuba.fiit.perconik.core.plugin.Activator;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotable;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

import static com.google.common.base.Preconditions.checkArgument;

final class Utilities {
  private static final ClassResolver resolver = Activator.classResolver();

  private Utilities() {}

  static Class<? extends Listener> checkListenerClass(final Class<? extends Listener> type) {
    try {
      return Services.getListenerService().getListenerProvider().loadClass(type.getName());
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  static Listener checkListenerImplementation(final Class<? extends Listener> type, @Nullable final Listener listener) {
    if (listener != null && type != listener.getClass()) {
      throw new IllegalArgumentException("Listener " + listener + " is not implemented by " + type.getName());
    }

    return listener;
  }

  static Class<? extends Listener> checkListenerType(final Class<? extends Listener> type) {
    checkArgument(Listener.class.isAssignableFrom(type), "Class " + type.getName() + " is not assignable to " + Listener.class.getName());

    return type;
  }

  static String checkResourceName(final String name) {
    checkArgument(!name.isEmpty(), "Resource name can not be empty");

    return name;
  }

  static Resource<?> checkResourceImplementation(final String name, @Nullable final Resource<?> resource) {
    if (resource != null && !name.equals(resource.getName())) {
      throw new IllegalArgumentException("Resource " + resource + " has not name " + name);
    }

    return resource;
  }

  static boolean registeredByDefault(final Class<? extends Registrable> type) {
    Annotable annotable = Registrables.toAnnotable(type);

    return !annotable.hasAnnotation(Experimental.class) && !annotable.hasAnnotation(Unsupported.class);
  }

  static Class<?> resolveClass(final String name) throws ClassNotFoundException {
    return resolver.forName(name);
  }

  static <T> Class<? extends T> resolveClassAsSubclass(final String name, final Class<T> subclass) throws ClassNotFoundException {
    return resolveClass(name).asSubclass(subclass);
  }

  static <T> T serializableOrNull(@Nullable final T object) {
    return object instanceof Serializable ? object : null;
  }

  static <T> Optional<T> serializableOrNullAsOptional(@Nullable final T object) {
    return Optional.fromNullable(serializableOrNull(object));
  }

  private static ToStringHelper toStringHelperFor(final Registration registration) {
    ToStringHelper helper = MoreObjects.toStringHelper(registration);

    helper.add("registered", registration.isRegistered());

    if (registration instanceof MarkableRegistration) {
      helper.add("registered-mark", ((MarkableRegistration) registration).hasRegistredMark());
    }

    helper.add("serializable", registration instanceof Serializable);

    return helper;
  }

  static String toString(final ListenerRegistration registration) {
    ToStringHelper helper = toStringHelperFor(registration);

    helper.add("listener-class", registration.getListenerClass().getName());
    helper.add("listener", registration.getListener());

    if (registration instanceof SerializedListenerData) {
      helper.add("serialized-listener", ((SerializedListenerData) registration).hasSerializedListener());
    }

    return helper.toString();
  }

  static String toString(final ResourceRegistration registration) {
    ToStringHelper helper = toStringHelperFor(registration);

    helper.add("listener-type", registration.getListenerType().getName());
    helper.add("resource-name", registration.getResourceName());
    helper.add("resource", registration.getResource());

    if (registration instanceof SerializedResourceData) {
      helper.add("serialized-resource", ((SerializedResourceData) registration).hasSerializedResource());
    }

    return helper.toString();
  }
}
