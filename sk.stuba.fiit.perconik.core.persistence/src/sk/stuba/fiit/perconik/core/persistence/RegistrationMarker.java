package sk.stuba.fiit.perconik.core.persistence;

public interface RegistrationMarker<R extends MarkableRegistration>
{
	public R applyRegisteredMark();
	
	public R updateRegisteredMark();
	
	public R markRegistered(boolean status);
}
