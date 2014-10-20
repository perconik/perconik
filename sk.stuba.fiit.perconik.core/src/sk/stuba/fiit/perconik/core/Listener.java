package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.listeners.DefaultListeners;

/**
 * A listener is notified about events that happen in an environment. This
 * environment is usually an object or a group of objects that acts as an
 * event source. In most cases a listener listens to lifecycle events of an
 * object, property changes of an object or receives notifications about
 * operations which an object performs.
 *
 * <p>Listeners are registered to resources which handle the actual
 * listener registration to the event sources. Resources also support
 * listener unregistration as well as contain some other useful utilities
 * for specific listener type.
 *
 * <p>Listeners should be implemented as singletons or classes with controlled
 * instance creation. Such listener implementations then ease the implementing
 * of a listener provider which must always return the same listener instance
 * for specified listener implementation class. This singleton like behavior of
 * listeners is strictly recommended as it is fully compatible and works very
 * well with the default core implementation. Listener implementations with
 * a single parameterless constructor are also supported but not recommended
 * as the singleton like behavior can be easily broken in this case. See the
 * documentation of {@link DefaultListeners#getDefaultListenerProvider()} for
 * more details about supported singleton forms.
 *
 * <p>Listeners are considered equal by their implementing classes. In
 * other words it means that as long as they are implemented as singletons,
 * reference equivalence between listeners is in general the same as
 * {@link #equals(Object)} equivalence. This property of listeners is
 * essential and must be preserved by all implementations in order to work
 * property when used with the core facilities.
 *
 * @see Resource
 * @see Adapter
 * @see Listeners
 * @see DefaultListeners
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Listener extends Registrable {
  /**
   * Compares the specified object with this listener for equality.
   * Returns {@code true} if the specified object is also listener
   * and the two listeners have the same implementation classes.
   * This definition ensures that this method works properly across
   * different implementations of the listener interface.
   *
   * <p><b>Note:</b> See the documentation of this class for more
   * information regarding listener implementation classes.
   *
   * @param o an object to be compared for equality with this listener
   * @return {@code true} if the specified object is equal to
   *         this listener, {@code false} otherwise
   */
  @Override
  public boolean equals(@Nullable Object o);
}
