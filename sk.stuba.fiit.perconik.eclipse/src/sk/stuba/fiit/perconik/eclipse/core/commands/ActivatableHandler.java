package sk.stuba.fiit.perconik.eclipse.core.commands;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import javax.annotation.Nullable;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import com.google.common.base.Optional;

public abstract class ActivatableHandler extends AbstractHandler
{
	@Nullable
	private IHandlerActivation activation;
	
	protected ActivatableHandler()
	{
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
			checkState(this.activation == null);
			
			return this.activation = service.activateHandler(identifier, this);
		}
	}
	
	public final void deactivate()
	{
		synchronized (this.activation)
		{
			checkState(this.activation != null);

			this.activation.getHandlerService().deactivateHandler(this.activation);
		}
	}

	public final Optional<IHandlerActivation> getActivation()
	{
		return Optional.fromNullable(this.activation);
	}
}
