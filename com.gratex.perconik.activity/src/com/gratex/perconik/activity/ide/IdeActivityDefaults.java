package com.gratex.perconik.activity.ide;

import java.net.URL;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;
import com.gratex.perconik.activity.TimeSupplier;

public final class IdeActivityDefaults
{
	// TODO rm class
	
	public static final URL watcherUrl;
	
	static
	{
		watcherUrl = UniformResources.newUrl("http://localhost:16375");
	}
	
	private static final TimeSupplier timeSupplier;
	
	static
	{
		timeSupplier = Internals.timeSupplier;
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
