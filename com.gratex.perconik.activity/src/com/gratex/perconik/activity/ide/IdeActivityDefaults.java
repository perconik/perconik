package com.gratex.perconik.activity.ide;

import static com.gratex.perconik.activity.ide.Internals.*;
import java.net.URL;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;
import com.gratex.perconik.activity.TimeSupplier;

public final class IdeActivityDefaults
{
	public static final URL watcherUrl;
	
	static
	{
		watcherUrl  = UniformResources.newUrl("http://localhost:16375");
	}
	
	private static final TimeSupplier timeSupplier;
	
	static
	{
		timeSupplier      = options.containsKey("fixed-year")       ? Debug.timeSupplier      : Internals.timeSupplier;
	}

	private IdeActivityDefaults()
	{
		throw new AssertionError();
	}
	
	public static final TimeSupplier getTimeSupplier()
	{
		return timeSupplier;
	}
}
