package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Set;

import com.google.common.base.Supplier;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A class that can supply a set of {@link Listener} classes. More formally,
 * instances of this class supply sets of {@code Class<? extends Listener>}.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerClassesSupplier extends Supplier<Set<Class<? extends Listener>>>
{
}
