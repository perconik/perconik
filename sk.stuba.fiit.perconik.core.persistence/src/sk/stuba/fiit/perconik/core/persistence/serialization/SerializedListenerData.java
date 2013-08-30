package sk.stuba.fiit.perconik.core.persistence.serialization;

import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Optional;

public interface SerializedListenerData
{
	public boolean hasSerializedListener();
	
	public Optional<Listener> getSerializedListener();
}
