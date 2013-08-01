package sk.stuba.fiit.perconik.eclipse.debug.core;

import java.util.Set;
import org.eclipse.debug.core.DebugEvent;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementDeltaFlag;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum DebugEventDetail implements IntegralConstant
{
	/**
	 * @see org.eclipse.debug.core.DebugEvent#STEP_INTO
	 */
	STEP_INTO(DebugEvent.STEP_INTO),

	/**
	 * @see org.eclipse.debug.core.DebugEvent#STEP_OVER
	 */
	STEP_OVER(DebugEvent.STEP_OVER),
	
	/**
	 * @see org.eclipse.debug.core.DebugEvent#STEP_RETURN
	 */
	STEP_RETURN(DebugEvent.STEP_RETURN),
	
	/**
	 * @see org.eclipse.debug.core.DebugEvent#STEP_END
	 */
	STEP_END(DebugEvent.STEP_END),
	
	/**
	 * @see org.eclipse.debug.core.DebugEvent#BREAKPOINT
	 */
	BREAKPOINT(DebugEvent.BREAKPOINT),
	
	/**
	 * @see org.eclipse.debug.core.DebugEvent#CLIENT_REQUEST
	 */
	CLIENT_REQUEST(DebugEvent.CLIENT_REQUEST),
	
	/**
	 * @see org.eclipse.debug.core.DebugEvent#EVALUATION
	 */
	EVALUATION(DebugEvent.EVALUATION),
	
	/**
	 * @see org.eclipse.debug.core.DebugEvent#EVALUATION_IMPLICIT
	 */
	EVALUATION_IMPLICIT(DebugEvent.EVALUATION_IMPLICIT),
	
	/**
	 * @see org.eclipse.debug.core.DebugEvent#STATE
	 */
	STATE(DebugEvent.STATE),
	
	/**
	 * @see org.eclipse.debug.core.DebugEvent#CONTENT
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

	public static final int valuesAsInteger(Set<JavaElementDeltaFlag> values)
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
