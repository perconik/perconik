package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Nameable;

public interface Initializer extends Nameable
{
	public void preInitialize();
	
	public void initialize();
	
	public void postInitialize();
}
