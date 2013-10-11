package sk.stuba.fiit.perconik.osgi.framework;

import org.osgi.framework.Bundle;
import sk.stuba.fiit.perconik.utilities.reflect.ClassResolver;
import com.google.common.base.Preconditions;

final class BundleClassResolver implements ClassResolver
{
	private Bundle bundle;
	
	BundleClassResolver(Bundle loader)
	{
		this.bundle = Preconditions.checkNotNull(loader);
	}

	public final Class<?> forName(String name) throws ClassNotFoundException
	{
		return this.bundle.loadClass(name);
	}
}
