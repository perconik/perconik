package sk.stuba.fiit.perconik.environment;

import javax.annotation.Nullable;
import org.osgi.framework.Version;

public class JavaVerificationException extends Exception
{
	private static final long serialVersionUID = 0;

	private final Version required;

	private final Version detected;
	
	public JavaVerificationException(Version required)
	{
		this(required, Environment.getJavaVersion());
	}
	
	public JavaVerificationException(Version required, Version detected)
	{
		this(required, detected, null);
	}
	
	public JavaVerificationException(Version required, Version detected, @Nullable Throwable cause)
	{
		super("Java " + required + " required but Java " + detected + " detected", cause);
		
		this.detected = detected;
		this.required = required;
	}
	
	public final Version getDetectedJavaVersion()
	{
		return this.detected;
	}

	public final Version getRequiredJavaVersion()
	{
		return this.required;
	} 
}
