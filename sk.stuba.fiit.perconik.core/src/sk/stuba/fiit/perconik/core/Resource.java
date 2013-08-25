package sk.stuba.fiit.perconik.core;

import java.util.Collection;
import javax.annotation.Nullable;

public interface Resource<L extends Listener> extends Nameable, Registrable
{
	public void register(L listener);
	
	public void unregister(L listener);
	
	public void unregisterAll(Class<? extends Listener> type);
	
	public <U extends Listener> Collection<U> isRegistred(Class<U> type);
	
	// resources should be equal if their names are equal
	@Override
	public boolean equals(@Nullable Object o);
}
