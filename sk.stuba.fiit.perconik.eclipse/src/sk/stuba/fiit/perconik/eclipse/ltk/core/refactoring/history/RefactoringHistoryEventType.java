package sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history;

import java.util.Set;

import org.eclipse.ltk.core.refactoring.history.RefactoringHistoryEvent;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Refactoring history event types.
 *
 * @see RefactoringHistoryEvent
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public enum RefactoringHistoryEventType implements RefactoringEventType {
  /**
   * @see RefactoringHistoryEvent#ADDED
   */
  ADDED(RefactoringHistoryEvent.ADDED),

  /**
   * @see RefactoringHistoryEvent#DELETED
   */
  DELETED(RefactoringHistoryEvent.DELETED),

  /**
   * @see RefactoringHistoryEvent#POPPED
   */
  POPPED(RefactoringHistoryEvent.POPPED),

  /**
   * @see RefactoringHistoryEvent#PUSHED
   */
  PUSHED(RefactoringHistoryEvent.PUSHED);

  private static final IntegralConstantSupport<RefactoringHistoryEventType> integers = IntegralConstantSupport.of(RefactoringHistoryEventType.class);

  private final int value;

  private RefactoringHistoryEventType(final int value) {
    this.value = value;
  }

  public static Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static RefactoringHistoryEventType valueOf(final int value) {
    return integers.getConstant(value);
  }

  public int getValue() {
    return this.value;
  }
}
