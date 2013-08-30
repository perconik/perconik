package sk.stuba.fiit.perconik.core.persistence.serialization;

import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.persistence.ResourceRegistration;
import com.google.common.base.Optional;

public interface SerializedResourceData extends ResourceRegistration
{
	public boolean hasSerializedResource();

	public Optional<Resource<?>> getSerializedResource();
}
