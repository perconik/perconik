package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;

/**
 * The {@code ManagerFactory} creates {@link Manager} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ManagerFactory
{
	/**
	 * Creates a manager object.
	 * @return manager object
	 */
	public Manager create();

	/**
	 * Indicates whether some other object is equal to this
	 * factory. This method must obey the general contract of
	 * {@link Object#equals(Object)}. Additionally, this method
	 * can return {@code true} <i>only</i> if the specified object
	 * is also a factory and it creates instances the same way as
	 * this factory.
	 * 
	 * <p><b>Note:</b> that it is always safe <i>not</i> to override
	 * {@code Object.equals(Object)}. However, overriding this method may,
	 * in some cases, improve performance by allowing programs to determine
	 * that two distinct factories work the same way.
	 * 
	 * @param o the reference object with which to compare
	 * @return {@code true} only if the specified object is also a factory
	 *         and it creates instances the same way as this factory
	 */
	@Override
	public boolean equals(@Nullable Object o);
}
