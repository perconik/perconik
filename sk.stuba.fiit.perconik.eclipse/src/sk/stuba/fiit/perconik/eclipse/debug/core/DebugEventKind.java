package sk.stuba.fiit.perconik.eclipse.debug.core;

import java.util.Set;

import org.eclipse.debug.core.DebugEvent;

import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementDeltaFlag;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Debug event kinds.
 * 
 * @see DebugEvent
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum DebugEventKind implements IntegralConstant
{
	/**
	 * @see DebugEvent#RESUME
	 */
	RESUME(DebugEvent.RESUME),

	/**
	 * @see DebugEvent#SUSPEND
	 */
	SUSPEND(DebugEvent.SUSPEND),

	/**
	 * @see DebugEvent#CREATE
	 */
	CREATE(DebugEvent.CREATE),

	/**
	 * @see DebugEvent#TERMINATE
	 */
	TERMINATE(DebugEvent.TERMINATE),

	/**
	 * @see DebugEvent#CHANGE
	 */
	CHANGE(DebugEvent.CHANGE),

	/**
	 * @see DebugEvent#MODEL_SPECIFIC
	 */
	MODEL_SPECIFIC(DebugEvent.MODEL_SPECIFIC);

	private static final IntegralConstantSupport<DebugEventKind> integers = IntegralConstantSupport.of(DebugEventKind.class);

	private final int value;
	
	private DebugEventKind(final int value)
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

	public static final int valuesAsInteger(final Set<JavaElementDeltaFlag> values)
	{
		return IntegralConstantSupport.constantsAsInteger(values);
	}

	public static final DebugEventKind valueOf(final int value)
	{
		return integers.getConstant(value);
	}
	
	public static final Set<DebugEventKind> setOf(final int values)
	{
		return integers.getConstants(values);
	}

	public final int getValue()
	{
		return this.value;
	}
}
