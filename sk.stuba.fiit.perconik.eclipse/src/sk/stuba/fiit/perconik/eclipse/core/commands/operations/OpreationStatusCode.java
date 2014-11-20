package sk.stuba.fiit.perconik.eclipse.core.commands.operations;

import java.util.Set;

import org.eclipse.core.commands.operations.OperationStatus;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Operation history event types.
 *
 * @see OperationStatus
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public enum OpreationStatusCode implements IntegralConstant {
  /**
   * @see OperationStatus#NOTHING_TO_REDO
   */
  NOTHING_TO_REDO(OperationStatus.NOTHING_TO_REDO),

  /**
   * @see OperationStatus#NOTHING_TO_UNDO
   */
  NOTHING_TO_UNDO(OperationStatus.NOTHING_TO_UNDO),

  /**
   * @see OperationStatus#OPERATION_INVALID
   */
  INVALID(OperationStatus.OPERATION_INVALID);

  private static final IntegralConstantSupport<OpreationStatusCode> integers = IntegralConstantSupport.of(OpreationStatusCode.class);

  private final int value;

  private OpreationStatusCode(final int value) {
    this.value = value;
  }

  public static Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static OpreationStatusCode valueOf(final int value) {
    return integers.getConstant(value);
  }

  public int getValue() {
    return this.value;
  }
}
