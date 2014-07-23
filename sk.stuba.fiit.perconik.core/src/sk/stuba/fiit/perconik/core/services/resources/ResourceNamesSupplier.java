package sk.stuba.fiit.perconik.core.services.resources;

import com.google.common.base.Supplier;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A class that can supply a set multimap of {@link Listener} classes
 * to {@link sk.stuba.fiit.perconik.core.Resource Resource} names.
 * More formally, instances of this class supply set multimaps with
 * {@code Class<? extends Listener>} as keys and {@code String} as values.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceNamesSupplier extends Supplier<SetMultimap<Class<? extends Listener>, String>>
{
}
