package sk.stuba.fiit.perconik.eclipse.core.commands;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public abstract class ActivatableHandler extends AbstractHandler
{
	private Map<String, IHandlerActivation> activations;
	
	protected ActivatableHandler()
	{
		this.activations = Maps.newLinkedHashMap();
	}
	
	public final IHandlerActivation activate(final String identifier)
	{
		return activate(identifier, Handlers.getHandlerService());
	}
	
	public final IHandlerActivation activate(final String identifier, final IHandlerService service)
	{
		checkNotNull(service);

		synchronized (this.activations)
		{
			return this.activateInternal(identifier, service);
		}
	}
	
	private final IHandlerActivation activateInternal(final String identifier, final IHandlerService service)
	{
		checkState(!this.activations.containsKey(identifier));
		
		IHandlerActivation activation = service.activateHandler(identifier, this);
		
		this.activations.put(identifier, activation);
		
		return activation;
	}

	public final void deactivate()
	{
		synchronized (this.activations)
		{
			for (String identifier: this.activations.keySet())
			{
				this.deactivateInternal(identifier);
			}
		}
	}

	public final void deactivate(final String identifier)
	{
		synchronized (this.activations)
		{
			this.deactivateInternal(identifier);
		}
	}
	
	private final void deactivateInternal(final String identifier)
	{
		IHandlerActivation activation = this.activations.get(identifier);
		
		checkState(identifier != null);
		
		IHandlerService service = activation.getHandlerService();

		if (service != null)
		{
			service.deactivateHandler(activation);
		}
	}

	public final Collection<IHandlerActivation> getActivations()
	{
		return Lists.newArrayList(this.activations.values());
	}
}
