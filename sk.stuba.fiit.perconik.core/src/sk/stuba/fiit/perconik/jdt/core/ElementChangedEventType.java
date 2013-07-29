package sk.stuba.fiit.perconik.jdt.core;

import java.util.Set;
import org.eclipse.jdt.core.ElementChangedEvent;
import sk.stuba.fiit.perconik.core.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.core.utilities.IntegralConstantSupport;

public enum ElementChangedEventType implements IntegralConstant
{
	/**
	 * @see org.eclipse.jdt.core.ElementChangedEvent#POST_CHANGE
	 */
	POST_CHANGE(ElementChangedEvent.POST_CHANGE),
	
	/**
	 * @see org.eclipse.jdt.core.ElementChangedEvent#POST_RECONCILE
	 */
	POST_RECONCILE(ElementChangedEvent.POST_RECONCILE),
	
	/**
	 * @see org.eclipse.jdt.core.ElementChangedEvent#PRE_AUTO_BUILD
	 */
	@Deprecated
	@SuppressWarnings("deprecation")
	PRE_AUTO_BUILD(ElementChangedEvent.PRE_AUTO_BUILD);
	
	private static final IntegralConstantSupport<ElementChangedEventType> integers = IntegralConstantSupport.of(ElementChangedEventType.class);

	private final int value;
	
	private ElementChangedEventType(final int value)
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

	public static final int valuesAsInteger(Set<ElementChangedEventType> values)
	{
		return IntegralConstantSupport.constantsAsInteger(values);
	}

	public static final ElementChangedEventType valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
