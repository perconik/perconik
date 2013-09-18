package sk.stuba.fiit.perconik.eclipse.debug.core;

import java.util.Set;
import org.eclipse.debug.core.DebugEvent;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementDeltaFlag;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Debug event details.
 * 
 * @see DebugEvent
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum DebugEventDetail implements IntegralConstant
{
	/**
	 * @see DebugEvent#STEP_INTO
	 */
	STEP_INTO(DebugEvent.STEP_INTO),

	/**
	 * @see DebugEvent#STEP_OVER
	 */
	STEP_OVER(DebugEvent.STEP_OVER),
	
	/**
	 * @see DebugEvent#STEP_RETURN
	 */
	STEP_RETURN(DebugEvent.STEP_RETURN),
	
	/**
	 * @see DebugEvent#STEP_END
	 */
	STEP_END(DebugEvent.STEP_END),
	
	/**
	 * @see DebugEvent#BREAKPOINT
	 */
	BREAKPOINT(DebugEvent.BREAKPOINT),
	
	/**
	 * @see DebugEvent#CLIENT_REQUEST
	 */
	CLIENT_REQUEST(DebugEvent.CLIENT_REQUEST),
	
	/**
	 * @see DebugEvent#EVALUATION
	 */
	EVALUATION(DebugEvent.EVALUATION),
	
	/**
	 * @see DebugEvent#EVALUATION_IMPLICIT
	 */
	EVALUATION_IMPLICIT(DebugEvent.EVALUATION_IMPLICIT),
	
	/**
	 * @see DebugEvent#STATE
	 */
	STATE(DebugEvent.STATE),
	
	/**
	 * @see DebugEvent#CONTENT
	 */
	CONTENT(DebugEvent.CONTENT);
	
	private static final IntegralConstantSupport<DebugEventDetail> integers = IntegralConstantSupport.of(DebugEventDetail.class);

	private final int value;
	
	private DebugEventDetail(final int value)
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

	public static final DebugEventDetail valueOf(final int value)
	{
		return integers.getConstant(value);
	}
	
	public static final Set<DebugEventDetail> setOf(final int values)
	{
		return integers.getConstants(values);
	}

	public final int getValue()
	{
		return this.value;
	}
}
