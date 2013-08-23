package sk.stuba.fiit.perconik.core;

public interface Listener extends Registrable
{
	// listeners should be equal if their implementing classes are equal
	@Override
	public boolean equals(Object o);
}
