package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;
import org.eclipse.core.resources.IResourceChangeEvent;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum ResourceEventType implements IntegralConstant
{
	/**
	 * @see org.eclipse.core.resources.IResourceChangeEvent#POST_CHANGE
	 */
	POST_CHANGE(IResourceChangeEvent.POST_CHANGE),
	
	/**
	 * @see org.eclipse.core.resources.IResourceChangeEvent#PRE_CLOSE
	 */
	PRE_CLOSE(IResourceChangeEvent.PRE_CLOSE),

	/**
	 * @see org.eclipse.core.resources.IResourceChangeEvent#PRE_DELETE
	 */
	PRE_DELETE(IResourceChangeEvent.PRE_DELETE),
	
	/**
	 * @see org.eclipse.core.resources.IResourceChangeEvent#PRE_BUILD
	 */
	PRE_BUILD(IResourceChangeEvent.PRE_BUILD),
	
	/**
	 * @see org.eclipse.core.resources.IResourceChangeEvent#POST_BUILD
	 */
	POST_BUILD(IResourceChangeEvent.POST_BUILD),
	
	/**
	 * @see org.eclipse.core.resources.IResourceChangeEvent#PRE_REFRESH
	 */
	PRE_REFRESH(IResourceChangeEvent.PRE_REFRESH);

	private static final IntegralConstantSupport<ResourceEventType> integers = IntegralConstantSupport.of(ResourceEventType.class);

	private final int value;
	
	private ResourceEventType(final int value)
	{
		this.value = value;
	}
		
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}

	public static final ResourceEventType valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
