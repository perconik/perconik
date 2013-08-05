package sk.stuba.fiit.perconik.core;

public interface Listener
{
	public void preRegister();

	public void postRegister();

	public void preUnregister();

	public void postUnregister();
}
