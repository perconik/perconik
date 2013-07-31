package sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history;

import java.util.Set;
import org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum RefactoringExecutionEventType implements IntegralConstant
{
	/**
	 * @see org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent#ABOUT_TO_PERFORM
	 */
	ABOUT_TO_PERFORM(RefactoringExecutionEvent.ABOUT_TO_PERFORM),

	/**
	 * @see org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent#ABOUT_TO_REDO
	 */
	ABOUT_TO_REDO(RefactoringExecutionEvent.ABOUT_TO_REDO),

	/**
	 * @see org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent#ABOUT_TO_UNDO
	 */
	ABOUT_TO_UNDO(RefactoringExecutionEvent.ABOUT_TO_UNDO),

	/**
	 * @see org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent#PERFORMED
	 */
	PERFORMED(RefactoringExecutionEvent.PERFORMED),

	/**
	 * @see org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent#REDONE
	 */
	REDONE(RefactoringExecutionEvent.REDONE),

	/**
	 * @see org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent#UNDONE
	 */
	UNDONE(RefactoringExecutionEvent.UNDONE);

	private static final IntegralConstantSupport<RefactoringExecutionEventType> integers = IntegralConstantSupport.of(RefactoringExecutionEventType.class);

	private final int value;
	
	private RefactoringExecutionEventType(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final RefactoringExecutionEventType valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
