package sk.stuba.fiit.perconik.core.persistence;

import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.base.Optional;

public interface SerializedResourceRegistration extends ResourceRegistration
{
	public boolean hasSerializedResource();

	public Optional<Resource<?>> getSerializedResource();
}
