package sk.stuba.fiit.perconik.core.services;

public interface ProviderFactory<P extends Provider>
{
	public P create(P parent);
	
	@Override
	public boolean equals(Object o);
}
