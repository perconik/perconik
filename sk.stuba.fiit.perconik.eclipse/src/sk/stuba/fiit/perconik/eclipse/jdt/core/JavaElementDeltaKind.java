package sk.stuba.fiit.perconik.eclipse.jdt.core;

import java.util.Set;

import org.eclipse.jdt.core.IJavaElementDelta;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Java element delta kinds.
 * 
 * @see IJavaElementDelta
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum JavaElementDeltaKind implements IntegralConstant {
  /**
   * @see IJavaElementDelta#ADDED
   */
  ADDED(IJavaElementDelta.ADDED),

  /**
   * @see IJavaElementDelta#REMOVED
   */
  REMOVED(IJavaElementDelta.REMOVED),

  /**
   * @see IJavaElementDelta#CHANGED
   */
  CHANGED(IJavaElementDelta.CHANGED);

  private static final IntegralConstantSupport<JavaElementDeltaKind> integers = IntegralConstantSupport.of(JavaElementDeltaKind.class);

  private final int value;

  private JavaElementDeltaKind(final int value) {
    this.value = value;
  }

  public static final Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static final JavaElementDeltaKind valueOf(final int value) {
    return integers.getConstant(value);
  }

  public final int getValue() {
    return this.value;
  }
}
