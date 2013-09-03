package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Supplier;
import com.google.common.collect.SetMultimap;

public interface ResourceNamesSupplier extends Supplier<SetMultimap<Class<? extends Listener>, String>>
{
}
