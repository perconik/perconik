package sk.stuba.fiit.perconik.core.persistence.serialization;

import com.google.common.base.Optional;

import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.persistence.ResourceRegistration;

/**
 * Interface implemented by optional holders of serialized {@link Resource}
 * data. Instances of classes implementing this interface provide optional
 * access to {@code Resource} object that can be serialized and deserialized.
 * These instances usually hold a serializable resource. A serializable
 * resource is a concrete implementation of {@code Resource} interface
 * also implementing the {@link java.io.Serializable} interface.
 * 
 * <p><b>Note:</b> Holding serialized resource data
 * or a serializable resource object is optional.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface SerializedResourceData extends ResourceRegistration
{
	/**
	 * Returns {@code true} if this object holds
	 * a serialized resource, {@code false} otherwise.
	 * 
	 * <p>More formally, this method returns {@code true} if and
	 * only if {@code getSerializedResource().isPresent() == true}. 
	 */
	public boolean hasSerializedResource();

	/**
	 * Gets the serialized resource if available.
	 */
	public Optional<Resource<?>> getSerializedResource();
}
