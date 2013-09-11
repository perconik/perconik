package sk.stuba.fiit.perconik.core.persistence.serialization;

import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Optional;

/**
 * Interface implemented by optional holders of serialized {@link Listener}
 * data. Instances of classes implementing this interface provide optional
 * access to {@code Listener} object that can be serialized and deserialized.
 * These instances usually hold a serializable listener. A serializable
 * listener is a concrete implementation of {@code Listener} interface
 * also implementing the {@link java.io.Serializable} interface.
 * 
 * <p><b>Note:</b> Holding serialized listener data
 * or a serializable listener object is optional.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface SerializedListenerData
{
	/**
	 * Returns {@code true} if this object holds
	 * a serialized listener, {@code false} otherwise.
	 * 
	 * <p>More formally, this method returns {@code true} if and
	 * only if {@code getSerializedListener().isPresent() == true}. 
	 */
	public boolean hasSerializedListener();
	
	/**
	 * Gets the serialized listener if available.
	 */
	public Optional<Listener> getSerializedListener();
}
