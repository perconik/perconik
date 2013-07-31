package sk.stuba.fiit.perconik.eclipse.jdt.core;

import java.util.Set;
import org.eclipse.jdt.core.IJavaElementDelta;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum JavaElementDeltaFlag implements IntegralConstant
{
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_CONTENT
	 */
	CONTENT(IJavaElementDelta.F_CONTENT),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_MODIFIERS
	 */
	MODIFIERS(IJavaElementDelta.F_MODIFIERS),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_CHILDREN
	 */
	CHILDREN(IJavaElementDelta.F_CHILDREN),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_MOVED_FROM
	 */
	MOVED_FROM(IJavaElementDelta.F_MOVED_FROM),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_MOVED_TO
	 */
	MOVED_TO(IJavaElementDelta.F_MOVED_TO),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_ADDED_TO_CLASSPATH
	 */
	ADDED_TO_CLASSPATH(IJavaElementDelta.F_ADDED_TO_CLASSPATH),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_REMOVED_FROM_CLASSPATH
	 */
	REMOVED_FROM_CLASSPATH(IJavaElementDelta.F_REMOVED_FROM_CLASSPATH),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_REORDER
	 */
	REORDER(IJavaElementDelta.F_REORDER),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_OPENED
	 */
	OPENED(IJavaElementDelta.F_OPENED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_CLOSED
	 */
	CLOSED(IJavaElementDelta.F_CLOSED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_SUPER_TYPES
	 */
	SUPER_TYPES(IJavaElementDelta.F_SUPER_TYPES),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_SOURCEATTACHED
	 */
	SOURCEATTACHED(IJavaElementDelta.F_SOURCEATTACHED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_SOURCEDETACHED
	 */
	SOURCEDETACHED(IJavaElementDelta.F_SOURCEDETACHED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_FINE_GRAINED
	 */
	FINE_GRAINED(IJavaElementDelta.F_FINE_GRAINED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_ARCHIVE_CONTENT_CHANGED
	 */
	ARCHIVE_CONTENT_CHANGED(IJavaElementDelta.F_ARCHIVE_CONTENT_CHANGED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_PRIMARY_WORKING_COPY
	 */
	PRIMARY_WORKING_COPY(IJavaElementDelta.F_PRIMARY_WORKING_COPY),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_CLASSPATH_CHANGED
	 */
	CLASSPATH_CHANGED(IJavaElementDelta.F_CLASSPATH_CHANGED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_PRIMARY_RESOURCE
	 */
	PRIMARY_RESOURCE(IJavaElementDelta.F_PRIMARY_RESOURCE),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_AST_AFFECTED
	 */
	AST_AFFECTED(IJavaElementDelta.F_AST_AFFECTED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_CATEGORIES
	 */
	CATEGORIES(IJavaElementDelta.F_CATEGORIES),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_RESOLVED_CLASSPATH_CHANGED
	 */
	RESOLVED_CLASSPATH_CHANGED(IJavaElementDelta.F_RESOLVED_CLASSPATH_CHANGED),
	
	/**
	 * @see org.eclipse.jdt.core.IJavaElementDelta#F_ANNOTATIONS
	 */
	ANNOTATIONS(IJavaElementDelta.F_ANNOTATIONS);
	
	private static final IntegralConstantSupport<JavaElementDeltaFlag> integers = IntegralConstantSupport.of(JavaElementDeltaFlag.class);

	private final int value;
	
	private JavaElementDeltaFlag(final int value)
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

	public static final JavaElementDeltaFlag valueOf(final int value)
	{
		return integers.getConstant(value);
	}
	
	public static final Set<JavaElementDeltaFlag> setOf(final int values)
	{
		return integers.getConstants(values);
	}

	public final int getValue()
	{
		return this.value;
	}
}
