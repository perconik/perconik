package sk.stuba.fiit.perconik.eclipse.jdt.core;

import java.util.Set;

import org.eclipse.jdt.core.ElementChangedEvent;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Java element event types.
 * 
 * @see ElementChangedEvent
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum JavaElementEventType implements IntegralConstant
{
	/**
	 * @see ElementChangedEvent#POST_CHANGE
	 */
	POST_CHANGE(ElementChangedEvent.POST_CHANGE),
	
	/**
	 * @see ElementChangedEvent#POST_RECONCILE
	 */
	POST_RECONCILE(ElementChangedEvent.POST_RECONCILE),
	
	/**
	 * @see ElementChangedEvent#PRE_AUTO_BUILD
	 */
	@Deprecated
	@SuppressWarnings("deprecation")
	PRE_AUTO_BUILD(ElementChangedEvent.PRE_AUTO_BUILD);
	
	private static final IntegralConstantSupport<JavaElementEventType> integers = IntegralConstantSupport.of(JavaElementEventType.class);

	private final int value;
	
	private JavaElementEventType(final int value)
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

	public static final int valuesAsInteger(final Set<JavaElementEventType> values)
	{
		return IntegralConstantSupport.constantsAsInteger(values);
	}

	public static final JavaElementEventType valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
