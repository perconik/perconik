package sk.stuba.fiit.perconik.preferences.persistence;

public interface RegistrationMarker<R extends MarkableRegistration>
{
	public R applyRegisteredMark();
	
	public R updateRegisteredMark();
	
	public R markRegistered(boolean status);
}
