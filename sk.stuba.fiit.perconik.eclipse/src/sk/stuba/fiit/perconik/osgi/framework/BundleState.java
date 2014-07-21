package sk.stuba.fiit.perconik.osgi.framework;

import java.util.Set;

import org.osgi.framework.Bundle;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Bundle states.
 * 
 * @see Bundle
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum BundleState implements IntegralConstant
{
	/**
	 * @see Bundle#UNINSTALLED
	 */
	UNINSTALLED(Bundle.UNINSTALLED),

	/**
	 * @see Bundle#INSTALLED
	 */
	 INSTALLED(Bundle.INSTALLED),
	
	/**
	 * @see Bundle#RESOLVED
	 */
	 RESOLVED(Bundle.RESOLVED),
	
	/**
	 * @see Bundle#STARTING
	 */
	 STARTING(Bundle.STARTING),
	
	/**
	 * @see Bundle#STOPPING
	 */
	 STOPPING(Bundle.STOPPING),
	
	/**
	 * @see Bundle#ACTIVE
	 */
	 ACTIVE(Bundle.ACTIVE);
	
	private static final IntegralConstantSupport<BundleState> integers = IntegralConstantSupport.of(BundleState.class);

	private final int value;
	
	private BundleState(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final BundleState valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
