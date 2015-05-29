package sk.stuba.fiit.perconik.core.persistence.data;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.persistence.InvalidListenerException;
import sk.stuba.fiit.perconik.core.persistence.MarkableRegistration;
import sk.stuba.fiit.perconik.core.persistence.RegistrationMarker;
import sk.stuba.fiit.perconik.core.persistence.serialization.SerializedListenerData;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Markable listener registration with lively updated registration status.
 *
 * <p><b>Note:</b> This implementation is truly serializable if and only
 * if the underlying listener is serializable. Otherwise this implementation
 * serializes listener's data necessary to obtain the listener from the core
 * listener provider after deserialization at runtime.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenerPersistenceData extends AbstractListenerRegistration implements MarkableRegistration, RegistrationMarker<ListenerPersistenceData>, Serializable, SerializedListenerData {
  private static final long serialVersionUID = -1672202405264953995L;

  private final transient boolean registered;

  private final transient Class<? extends Listener> implementation;

  private final transient Optional<Listener> listener;

  private ListenerPersistenceData(final boolean registered, final Class<? extends Listener> implementation, final Optional<Listener> listener) {
    this.registered = registered;
    this.implementation = implementation;
    this.listener = listener;
  }

  static ListenerPersistenceData construct(final boolean registered, final Class<? extends Listener> implementation, @Nullable final Listener listener) {
    Utilities.checkListenerClass(implementation);
    Utilities.checkListenerImplementation(implementation, listener);

    return copy(registered, implementation, listener);
  }

  static ListenerPersistenceData copy(final boolean registered, final Class<? extends Listener> implementation, @Nullable final Listener listener) {
    return new ListenerPersistenceData(registered, implementation, Utilities.serializableOrNullAsOptional(listener));
  }

  public static ListenerPersistenceData of(final Listener listener) {
    return construct(Listeners.isRegistered(listener), listener.getClass(), listener);
  }

  public static Set<ListenerPersistenceData> defaults() {
    ListenerProvider provider = Services.getListenerService().getListenerProvider();

    Set<ListenerPersistenceData> data = newHashSet();

    for (Class<? extends Listener> implementation: provider.classes()) {
      data.add(construct(Utilities.registeredByDefault(implementation), implementation, null));
    }

    return data;
  }

  public static Set<ListenerPersistenceData> snapshot() {
    ListenerProvider provider = Services.getListenerService().getListenerProvider();

    Set<ListenerPersistenceData> data = newHashSet();

    Collection<Listener> listeners = Listeners.registrations().values();

    for (Class<? extends Listener> implementation: provider.classes()) {
      for (Listener listener: listeners) {
        data.add(construct(implementation == listener.getClass(), implementation, listener));
      }
    }

    return data;
  }

  private static final class SerializationProxy implements Serializable {
    private static final long serialVersionUID = -6638506142325802066L;

    private final boolean registered;

    private final String implementation;

    private final Optional<Listener> listener;

    private SerializationProxy(final ListenerPersistenceData data) {
      this.registered = data.hasRegistredMark();
      this.implementation = data.getListenerClass().getName();
      this.listener = data.getSerializedListener();
    }

    static SerializationProxy of(final ListenerPersistenceData data) {
      return new SerializationProxy(data);
    }

    private Object readResolve() throws InvalidObjectException {
      try {
        return construct(this.registered, Utilities.resolveClassAsSubclass(this.implementation, Listener.class), this.listener.orNull());
      } catch (Exception e) {
        throw new InvalidListenerException("Unknown deserialization error", e);
      }
    }
  }

  @SuppressWarnings({ "static-method", "unused" })
  private void readObject(final ObjectInputStream in) throws InvalidObjectException {
    throw new InvalidListenerException("Serialization proxy required");
  }

  private Object writeReplace() {
    return SerializationProxy.of(this);
  }

  public ListenerPersistenceData applyRegisteredMark() {
    Listener listener = this.getListener();

    if (listener == null) {
      return this;
    }

    boolean status = Listeners.isRegistered(listener);

    if (this.registered == status) {
      return this;
    }

    if (this.registered) {
      Listeners.register(listener);
    } else {
      Listeners.unregister(listener);
    }

    return new ListenerPersistenceData(status, this.implementation, this.listener);
  }

  public ListenerPersistenceData updateRegisteredMark() {
    return this.markRegistered(this.isRegistered());
  }

  public ListenerPersistenceData markRegistered(final boolean status) {
    if (this.registered == status) {
      return this;
    }

    return new ListenerPersistenceData(status, this.implementation, this.listener);
  }

  public boolean hasRegistredMark() {
    return this.registered;
  }

  public boolean hasSerializedListener() {
    return this.listener.isPresent();
  }

  public Listener getListener() {
    if (this.hasSerializedListener()) {
      return this.listener.get();
    }

    return Listeners.forClass(this.implementation);
  }

  public Class<? extends Listener> getListenerClass() {
    return this.implementation;
  }

  public Optional<Listener> getSerializedListener() {
    return this.listener;
  }
}
