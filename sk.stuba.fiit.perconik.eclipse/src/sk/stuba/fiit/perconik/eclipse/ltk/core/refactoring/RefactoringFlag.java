package sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring;

import java.util.Set;

import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Refactoring descriptor flags.
 *
 * @see RefactoringDescriptor
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public enum RefactoringFlag implements IntegralConstant {
  /**
   * @see RefactoringDescriptor#BREAKING_CHANGE
   */
  BREAKING_CHANGE(RefactoringDescriptor.BREAKING_CHANGE),

  /**
   * @see RefactoringDescriptor#MULTI_CHANGE
   */
  MULTI_CHANGE(RefactoringDescriptor.MULTI_CHANGE),

  /**
   * @see RefactoringDescriptor#STRUCTURAL_CHANGE
   */
  STRUCTURAL_CHANGE(RefactoringDescriptor.STRUCTURAL_CHANGE),

  /**
   * @see RefactoringDescriptor#USER_CHANGE
   */
  USER_CHANGE(RefactoringDescriptor.USER_CHANGE);

  private static final IntegralConstantSupport<RefactoringFlag> integers = IntegralConstantSupport.of(RefactoringFlag.class);

  private final int value;

  private RefactoringFlag(final int value) {
    this.value = value;
  }

  public static Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static int valuesAsInteger() {
    return integers.getConstantsAsInteger();
  }

  public static int valuesAsInteger(final Set<RefactoringFlag> values) {
    return IntegralConstantSupport.constantsAsInteger(values);
  }

  public static RefactoringFlag valueOf(final int value) {
    return integers.getConstant(value);
  }

  public static Set<RefactoringFlag> setOf(final int values) {
    return integers.getConstants(values);
  }

  public int getValue() {
    return this.value;
  }
}
