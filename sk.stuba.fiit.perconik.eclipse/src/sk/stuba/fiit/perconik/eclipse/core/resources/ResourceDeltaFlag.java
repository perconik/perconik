package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;
import org.eclipse.core.resources.IResourceDelta;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum ResourceDeltaFlag implements IntegralConstant
{
	/**
	 * @see org.eclipse.core.resources.IResourceDelta#CONTENT
	 */
	CONTENT(IResourceDelta.CONTENT),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#MOVED_FROM
	 */
	MOVED_FROM(IResourceDelta.MOVED_FROM),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#MOVED_TO
	 */
	MOVED_TO(IResourceDelta.MOVED_TO),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#COPIED_FROM
	 */
	COPIED_FROM(IResourceDelta.COPIED_FROM),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#OPEN
	 */
	OPEN(IResourceDelta.OPEN),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#TYPE
	 */
	TYPE(IResourceDelta.TYPE),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#SYNC
	 */
	SYNC(IResourceDelta.SYNC),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#MARKERS
	 */
	MARKERS(IResourceDelta.MARKERS),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#REPLACED
	 */
	REPLACED(IResourceDelta.REPLACED),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#DESCRIPTION
	 */
	DESCRIPTION(IResourceDelta.DESCRIPTION),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#ENCODING
	 */
	ENCODING(IResourceDelta.ENCODING),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#LOCAL_CHANGED
	 */
	LOCAL_CHANGED(IResourceDelta.LOCAL_CHANGED),

	/**
	 * @see org.eclipse.core.resources.IResourceDelta#DERIVED_CHANGED
	 */
	DERIVED_CHANGED(IResourceDelta.DERIVED_CHANGED);
	
	private static final IntegralConstantSupport<ResourceDeltaFlag> integers = IntegralConstantSupport.of(ResourceDeltaFlag.class);

	private final int value;
	
	private ResourceDeltaFlag(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final int valuesAsInteger()
	{
		return integers.getConstantsAsInteger();
	}

	public static final int valuesAsInteger(Set<ResourceDeltaFlag> values)
	{
		return IntegralConstantSupport.constantsAsInteger(values);
	}

	public static final ResourceDeltaFlag valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public static final Set<ResourceDeltaFlag> setOf(final int values)
	{
		return integers.getConstants(values);
	}

	public final int getValue()
	{
		return this.value;
	}
}
