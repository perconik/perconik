package sk.stuba.fiit.perconik.utilities.net;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public final class NtlmAuthenticator extends Authenticator
{
	private final String username;
	
	private final char[] password;

	public NtlmAuthenticator(final String username, final String password)
	{
		this.username = username;
		this.password = password.toCharArray();
	}

	@Override
	public final PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(this.username, this.password);
	}
}
