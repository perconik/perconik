package sk.stuba.fiit.perconik.eclipse.core.runtime;

import java.util.Set;
import org.eclipse.core.runtime.IStatus;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementDeltaFlag;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

/**
 * Status severities.
 * 
 * @see IStatus
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum StatusSeverity implements IntegralConstant
{
	/**
	 * @see IStatus#OK
	 */
	OK(IStatus.OK),

	/**
	 * @see IStatus#INFO
	 */
	INFO(IStatus.INFO),

	/**
	 * @see IStatus#WARNING
	 */
	WARNING(IStatus.WARNING),

	/**
	 * @see IStatus#ERROR
	 */
	ERROR(IStatus.ERROR),

	/**
	 * @see IStatus#CANCEL
	 */
	CANCEL(IStatus.CANCEL);
	
	private static final IntegralConstantSupport<StatusSeverity> integers = IntegralConstantSupport.of(StatusSeverity.class);

	private final int value;
	
	private StatusSeverity(final int value)
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

	public static final StatusSeverity valueOf(final int value)
	{
		return integers.getConstant(value);
	}
	
	public static final Set<StatusSeverity> setOf(final int values)
	{
		return integers.getConstants(values);
	}

	public final int getValue()
	{
		return this.value;
	}
}
