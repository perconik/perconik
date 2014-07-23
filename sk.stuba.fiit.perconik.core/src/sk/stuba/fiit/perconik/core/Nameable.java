package sk.stuba.fiit.perconik.core;

/**
 * A mixin interface for an object that has a qualified name.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Nameable
{
    /**
     * Gets the fully qualified name.
     * @return the fully qualified name
     */
	public String getName();
}
