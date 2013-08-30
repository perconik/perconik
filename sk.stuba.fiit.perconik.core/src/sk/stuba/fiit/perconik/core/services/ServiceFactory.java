package sk.stuba.fiit.perconik.core.services;

public interface ServiceFactory
{
	public Service create();
	
	@Override
	public boolean equals(Object o);
}
