package sk.stuba.fiit.perconik.core.persistence;

import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Optional;

public interface SerializedListenerRegistration extends ListenerRegistration
{
	public boolean hasSerializedListener();
	
	public Optional<Listener> getSerializedListener();
}
