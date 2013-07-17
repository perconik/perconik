package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.core.commands.operations.OperationHistoryFactory;

enum OperationHistoryHandler implements Handler<OperationHistoryListener>
{
	INSTANCE;
	
	public final void add(final OperationHistoryListener listener)
	{
		OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(listener);
	}

	public final void remove(final OperationHistoryListener listener)
	{
		OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(listener);
	}
}
