package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.ForwardingLoadingCache;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import org.eclipse.core.resources.IProject;
import org.eclipse.jgit.api.Git;

@Deprecated
public final class GitProjectCache extends ForwardingLoadingCache<IProject, Git>
{
	private final LoadingCache<IProject, Git> cache;

	public GitProjectCache()
	{
		this.cache = CacheBuilder.newBuilder().weakKeys().softValues().removalListener(Listener.INSTANCE).build(Loader.INSTANCE);
	}

	private static final class Loader extends CacheLoader<IProject, Git>
	{
		static final Loader INSTANCE = new Loader();

		private Loader()
		{
		}

		@Override
		public final Git load(final IProject project)
		{
			Git git = GitCommands.open(project);

			return checkNotNull(git);
		}
	}

	private static final class Listener implements RemovalListener<IProject, Git>
	{
		static final Listener INSTANCE = new Listener();

		private Listener()
		{
		}

		public final void onRemoval(final RemovalNotification<IProject, Git> notification)
		{
			try
			{
				notification.getValue().close();
			}
			catch (Exception e)
			{
			}
		}
	}

	@Override
	protected final LoadingCache<IProject, Git> delegate()
	{
		return this.cache;
	}
}
