package sk.stuba.fiit.perconik.eclipse.core.commands;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import com.google.common.base.Optional;

public abstract class ActivatableHandler extends AbstractHandler
{
	private Optional<IHandlerActivation> activation;
	
	protected ActivatableHandler()
	{
		this.activation = Optional.absent();
	}
	
	public final IHandlerActivation activate(final String identifier)
	{
		return activate(identifier, Handlers.getHandlerService());
	}
	
	public final IHandlerActivation activate(final String identifier, final IHandlerService service)
	{
		checkNotNull(service);

		synchronized (this.activation)
		{
			checkState(!this.activation.isPresent());
			
			this.activation = Optional.of(service.activateHandler(identifier, this));
			
			return this.activation.get();
		}
	}
	
	public final void deactivate()
	{
		synchronized (this.activation)
		{
			IHandlerActivation activation = this.activation.get();
			IHandlerService    service    = activation.getHandlerService();

			if (service != null)
			{
				service.deactivateHandler(activation);
			}
		}
	}

	public final Optional<IHandlerActivation> getActivation()
	{
		return this.activation;
	}
}
