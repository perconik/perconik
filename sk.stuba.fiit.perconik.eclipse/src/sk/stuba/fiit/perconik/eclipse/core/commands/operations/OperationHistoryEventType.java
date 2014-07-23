package sk.stuba.fiit.perconik.eclipse.core.commands.operations;

import java.util.Set;

import org.eclipse.core.commands.operations.OperationHistoryEvent;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Operation history event types.
 * 
 * @see OperationHistoryEvent
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum OperationHistoryEventType implements IntegralConstant {
  /**
   * @see OperationHistoryEvent#ABOUT_TO_EXECUTE
   */
  ABOUT_TO_EXECUTE(OperationHistoryEvent.ABOUT_TO_EXECUTE),

  /**
   * @see OperationHistoryEvent#ABOUT_TO_REDO
   */
  ABOUT_TO_REDO(OperationHistoryEvent.ABOUT_TO_REDO),

  /**
   * @see OperationHistoryEvent#ABOUT_TO_UNDO
   */
  ABOUT_TO_UNDO(OperationHistoryEvent.ABOUT_TO_UNDO),

  /**
   * @see OperationHistoryEvent#DONE
   */
  DONE(OperationHistoryEvent.DONE),

  /**
   * @see OperationHistoryEvent#OPERATION_ADDED
   */
  OPERATION_ADDED(OperationHistoryEvent.OPERATION_ADDED),

  /**
   * @see OperationHistoryEvent#OPERATION_CHANGED
   */
  OPERATION_CHANGED(OperationHistoryEvent.OPERATION_CHANGED),

  /**
   * @see OperationHistoryEvent#OPERATION_NOT_OK
   */
  OPERATION_NOT_OK(OperationHistoryEvent.OPERATION_NOT_OK),

  /**
   * @see OperationHistoryEvent#OPERATION_REMOVED
   */
  OPERATION_REMOVED(OperationHistoryEvent.OPERATION_REMOVED),

  /**
   * @see OperationHistoryEvent#REDONE
   */
  REDONE(OperationHistoryEvent.REDONE),

  /**
   * @see OperationHistoryEvent#UNDONE
   */
  UNDONE(OperationHistoryEvent.UNDONE);

  private static final IntegralConstantSupport<OperationHistoryEventType> integers = IntegralConstantSupport.of(OperationHistoryEventType.class);

  private final int value;

  private OperationHistoryEventType(final int value) {
    this.value = value;
  }

  public static final Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static final OperationHistoryEventType valueOf(final int value) {
    return integers.getConstant(value);
  }

  public final int getValue() {
    return this.value;
  }
}
