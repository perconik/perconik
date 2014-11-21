package sk.stuba.fiit.perconik.eclipse.jdt.core;

import java.util.Set;

import org.eclipse.jdt.core.IJavaElementDelta;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Java element delta flags.
 *
 * @see IJavaElementDelta
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public enum JavaElementDeltaFlag implements IntegralConstant {
  /**
   * @see IJavaElementDelta#F_CONTENT
   */
  CONTENT(IJavaElementDelta.F_CONTENT),

  /**
   * @see IJavaElementDelta#F_MODIFIERS
   */
  MODIFIERS(IJavaElementDelta.F_MODIFIERS),

  /**
   * @see IJavaElementDelta#F_CHILDREN
   */
  CHILDREN(IJavaElementDelta.F_CHILDREN),

  /**
   * @see IJavaElementDelta#F_MOVED_FROM
   */
  MOVED_FROM(IJavaElementDelta.F_MOVED_FROM),

  /**
   * @see IJavaElementDelta#F_MOVED_TO
   */
  MOVED_TO(IJavaElementDelta.F_MOVED_TO),

  /**
   * @see IJavaElementDelta#F_ADDED_TO_CLASSPATH
   */
  ADDED_TO_CLASSPATH(IJavaElementDelta.F_ADDED_TO_CLASSPATH),

  /**
   * @see IJavaElementDelta#F_REMOVED_FROM_CLASSPATH
   */
  REMOVED_FROM_CLASSPATH(IJavaElementDelta.F_REMOVED_FROM_CLASSPATH),

  /**
   * @see IJavaElementDelta#F_REORDER
   */
  REORDER(IJavaElementDelta.F_REORDER),

  /**
   * @see IJavaElementDelta#F_OPENED
   */
  OPENED(IJavaElementDelta.F_OPENED),

  /**
   * @see IJavaElementDelta#F_CLOSED
   */
  CLOSED(IJavaElementDelta.F_CLOSED),

  /**
   * @see IJavaElementDelta#F_SUPER_TYPES
   */
  SUPER_TYPES(IJavaElementDelta.F_SUPER_TYPES),

  /**
   * @see IJavaElementDelta#F_SOURCEATTACHED
   */
  SOURCE_ATTACHED(IJavaElementDelta.F_SOURCEATTACHED),

  /**
   * @see IJavaElementDelta#F_SOURCEDETACHED
   */
  SOURCE_DETACHED(IJavaElementDelta.F_SOURCEDETACHED),

  /**
   * @see IJavaElementDelta#F_FINE_GRAINED
   */
  FINE_GRAINED(IJavaElementDelta.F_FINE_GRAINED),

  /**
   * @see IJavaElementDelta#F_ARCHIVE_CONTENT_CHANGED
   */
  ARCHIVE_CONTENT_CHANGED(IJavaElementDelta.F_ARCHIVE_CONTENT_CHANGED),

  /**
   * @see IJavaElementDelta#F_PRIMARY_WORKING_COPY
   */
  PRIMARY_WORKING_COPY(IJavaElementDelta.F_PRIMARY_WORKING_COPY),

  /**
   * @see IJavaElementDelta#F_CLASSPATH_CHANGED
   */
  CLASSPATH_CHANGED(IJavaElementDelta.F_CLASSPATH_CHANGED),

  /**
   * @see IJavaElementDelta#F_PRIMARY_RESOURCE
   */
  PRIMARY_RESOURCE(IJavaElementDelta.F_PRIMARY_RESOURCE),

  /**
   * @see IJavaElementDelta#F_AST_AFFECTED
   */
  AST_AFFECTED(IJavaElementDelta.F_AST_AFFECTED),

  /**
   * @see IJavaElementDelta#F_CATEGORIES
   */
  CATEGORIES(IJavaElementDelta.F_CATEGORIES),

  /**
   * @see IJavaElementDelta#F_RESOLVED_CLASSPATH_CHANGED
   */
  RESOLVED_CLASSPATH_CHANGED(IJavaElementDelta.F_RESOLVED_CLASSPATH_CHANGED),

  /**
   * @see IJavaElementDelta#F_ANNOTATIONS
   */
  ANNOTATIONS(IJavaElementDelta.F_ANNOTATIONS);

  private static final IntegralConstantSupport<JavaElementDeltaFlag> integers = IntegralConstantSupport.of(JavaElementDeltaFlag.class);

  private final int value;

  private JavaElementDeltaFlag(final int value) {
    this.value = value;
  }

  public static Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static int valuesAsInteger() {
    return integers.getConstantsAsInteger();
  }

  public static int valuesAsInteger(final Set<JavaElementDeltaFlag> values) {
    return IntegralConstantSupport.constantsAsInteger(values);
  }

  public static JavaElementDeltaFlag valueOf(final int value) {
    return integers.getConstant(value);
  }

  public static Set<JavaElementDeltaFlag> setOf(final int values) {
    return integers.getConstants(values);
  }

  public int getValue() {
    return this.value;
  }
}
