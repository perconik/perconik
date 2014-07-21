package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;

import org.eclipse.core.resources.IResource;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Resource visitor depths.
 * 
 * @see IResource
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum ResourceVisitorDepth implements IntegralConstant
{
	/**
	 * @see IResource#DEPTH_ZERO
	 */
	DEPTH_ZERO(IResource.DEPTH_ZERO),
	
	/**
	 * @see IResource#DEPTH_ONE
	 */
	DEPTH_ONE(IResource.DEPTH_ONE),
	
	/**
	 * @see IResource#DEPTH_INFINITE
	 */
	DEPTH_INFINITE(IResource.DEPTH_INFINITE);
	
	private static final IntegralConstantSupport<ResourceVisitorDepth> integers = IntegralConstantSupport.of(ResourceVisitorDepth.class);
	
	private final int value;
	
	private ResourceVisitorDepth(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final ResourceVisitorDepth valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
