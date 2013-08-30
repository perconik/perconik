package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Supplier;

public interface ListenerTypesSupplier extends Supplier<Set<Class<? extends Listener>>>
{
}
