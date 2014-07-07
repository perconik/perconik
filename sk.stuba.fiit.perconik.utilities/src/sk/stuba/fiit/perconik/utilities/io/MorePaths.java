package sk.stuba.fiit.perconik.utilities.io;

import java.nio.file.Path;
import java.util.Iterator;
import com.google.common.collect.AbstractSequentialIterator;

public final class MorePaths
{
	private MorePaths()
	{
		throw new AssertionError();
	}

	private static final class DownToRootIterator<T extends Path> extends AbstractSequentialIterator<T>
	{
		protected DownToRootIterator(T path)
		{
			super(path);
		}

		@Override
		protected final T computeNext(T previous)
		{
			return (T) previous.getParent();
		}
	}

	public static final <T extends Path> Iterable<T> downToRoot(final T path)
	{
		return new Iterable<T>()
		{
			public final Iterator<T> iterator()
			{
				return new DownToRootIterator<>(path);
			}
		};
	}
}
