package com.gratex.perconik.activity.ide.listeners;

import static com.google.common.base.Preconditions.checkState;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import sk.stuba.fiit.perconik.eclipse.core.commands.Handlers;

// TODO refactor as public
abstract class AbstractCommandHandler extends AbstractHandler
{
	private IHandlerActivation activation;
	
	AbstractCommandHandler()
	{
	}
	
	void activate(final String identifier)
	{
		IHandlerService service = Handlers.getHandlerService();
		
		if (service != null)
		{
			synchronized (this.activation)
			{
				checkState(this.activation == null);
				
				this.activation = service.activateHandler(identifier, this);				
			}
		}
	}
	
	void deactivate()
	{
		IHandlerService service = Handlers.getHandlerService();
		
		if (service != null)
		{
			synchronized (this.activation)
			{
				checkState(this.activation != null);
				
				service.deactivateHandler(this.activation);
			}
		}
	}
}
