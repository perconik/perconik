package sk.stuba.fiit.perconik.eclipse.jdt.core;

import java.util.Set;
import org.eclipse.jdt.core.IJavaElementDelta;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum JavaElementDeltaKind implements IntegralConstant
{
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#ADDED
	 */
	ADDED(IJavaElementDelta.ADDED),

	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#REMOVED
	 */
	REMOVED(IJavaElementDelta.REMOVED),

	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#CHANGED
	 */
	CHANGED(IJavaElementDelta.CHANGED);

	private static final IntegralConstantSupport<JavaElementDeltaKind> integers = IntegralConstantSupport.of(JavaElementDeltaKind.class);

	private final int value;
	
	private JavaElementDeltaKind(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final JavaElementDeltaKind valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
