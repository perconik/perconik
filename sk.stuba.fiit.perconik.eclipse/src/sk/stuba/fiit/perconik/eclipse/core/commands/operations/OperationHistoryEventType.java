package sk.stuba.fiit.perconik.eclipse.core.commands.operations;

import java.util.Set;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum OperationHistoryEventType implements IntegralConstant
{
	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#ABOUT_TO_EXECUTE
	 */
	ABOUT_TO_EXECUTE(OperationHistoryEvent.ABOUT_TO_EXECUTE),
	
	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#ABOUT_TO_REDO
	 */
	ABOUT_TO_REDO(OperationHistoryEvent.ABOUT_TO_REDO),
	
	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#ABOUT_TO_UNDO
	 */
	ABOUT_TO_UNDO(OperationHistoryEvent.ABOUT_TO_UNDO),
	
	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#DONE
	 */
	DONE(OperationHistoryEvent.DONE),
	
	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#OPERATION_ADDED
	 */
	OPERATION_ADDED(OperationHistoryEvent.OPERATION_ADDED),

	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#OPERATION_CHANGED
	 */
	OPERATION_CHANGED(OperationHistoryEvent.OPERATION_CHANGED),

	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#OPERATION_NOT_OK
	 */
	OPERATION_NOT_OK(OperationHistoryEvent.OPERATION_NOT_OK),

	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#OPERATION_REMOVED
	 */
	OPERATION_REMOVED(OperationHistoryEvent.OPERATION_REMOVED),

	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#REDONE
	 */
	REDONE(OperationHistoryEvent.REDONE),

	/**
	 * @see org.eclipse.core.commands.operations.OperationHistoryEvent#UNDONE
	 */
	UNDONE(OperationHistoryEvent.UNDONE);

	private static final IntegralConstantSupport<OperationHistoryEventType> integers = IntegralConstantSupport.of(OperationHistoryEventType.class);

	private final int value;
	
	private OperationHistoryEventType(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final OperationHistoryEventType valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
