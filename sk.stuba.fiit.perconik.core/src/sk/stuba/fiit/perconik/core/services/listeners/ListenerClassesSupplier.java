package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Set;
import com.google.common.base.Supplier;
import sk.stuba.fiit.perconik.core.Listener;

public interface ListenerClassesSupplier extends Supplier<Set<Class<? extends Listener>>>
{
}
