package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.utilities.FallbackException;

public class ProviderFallbackException extends FallbackException
{
	private static final long serialVersionUID = 4422720325649677707L;

	public ProviderFallbackException(Throwable cause, Throwable parent)
	{
		super(parent, cause);
	}
}
