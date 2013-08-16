package sk.stuba.fiit.perconik.core;

public interface Registrable
{
	public void preRegister();

	public void postRegister();

	public void preUnregister();

	public void postUnregister();
}
