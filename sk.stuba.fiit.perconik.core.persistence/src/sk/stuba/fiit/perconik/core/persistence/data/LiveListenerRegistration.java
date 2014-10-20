package sk.stuba.fiit.perconik.core.persistence.data;

import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Standard listener registration with lively updated registration status.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class LiveListenerRegistration extends AbstractListenerRegistration {
  private final Class<? extends Listener> type;

  private final Listener listener;

  private LiveListenerRegistration(final Class<? extends Listener> type, final Listener listener) {
    this.type = type;
    this.listener = listener;
  }

  static LiveListenerRegistration construct(final Class<? extends Listener> type, final Listener listener) {
    Utilities.checkListenerClass(type);
    Utilities.checkListenerImplementation(type, listener);

    return copy(type, listener);
  }

  static LiveListenerRegistration copy(final Class<? extends Listener> type, final Listener listener) {
    return new LiveListenerRegistration(type, listener);
  }

  public static LiveListenerRegistration of(final Listener listener) {
    return construct(listener.getClass(), listener);
  }

  public static Set<LiveListenerRegistration> snapshot() {
    Set<LiveListenerRegistration> data = newHashSet();

    for (Listener listener: Listeners.registrations().values()) {
      data.add(of(listener));
    }

    return data;
  }

  public ListenerPersistenceData toPersistenceData() {
    return ListenerPersistenceData.copy(this.isRegistered(), this.type, Utilities.serializableOrNull(this.listener));
  }

  public Listener getListener() {
    return this.listener;
  }

  public Class<? extends Listener> getListenerClass() {
    return this.type;
  }
}
