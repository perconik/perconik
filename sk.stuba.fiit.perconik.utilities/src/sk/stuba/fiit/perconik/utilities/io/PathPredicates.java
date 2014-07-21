package sk.stuba.fiit.perconik.utilities.io;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;

import javax.annotation.Nonnull;

import com.google.common.base.Predicate;
import com.google.common.base.Throwables;

import static com.google.common.base.Preconditions.checkNotNull;

public final class PathPredicates
{
	private PathPredicates()
	{
		throw new AssertionError();
	}
	
	private static final <T extends Path> Predicate<T> cast(Predicate<? extends Path> predicate)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		Predicate<T> result = (Predicate<T>) predicate;
		
		return result;
	}

	public static final <T extends Path> Predicate<T> startsWith(T prefix)
	{
		return new StartsWithPredicate<>(prefix);
	}

	public static final <T extends Path> Predicate<T> endsWith(T suffix)
	{
		return new EndsWithPredicate<>(suffix);
	}

	static final class StartsWithPredicate<T extends Path> implements Predicate<T>, Serializable
	{
		private static final long serialVersionUID = 0;
		
		private final T prefix;
		
		StartsWithPredicate(T prefix)
		{
			this.prefix = checkNotNull(prefix);
		}
	
		public final boolean apply(@Nonnull T path)
		{
			return path != null ? path.startsWith(this.prefix) : false;
		}
		
		// TODO add equals, hashCode, toString
	}

	static final class EndsWithPredicate<T extends Path> implements Predicate<T>, Serializable
	{
		private static final long serialVersionUID = 0;
		
		private final T suffix;
		
		EndsWithPredicate(T suffix)
		{
			this.suffix = checkNotNull(suffix);
		}
	
		public final boolean apply(@Nonnull T path)
		{
			return path != null ? path.endsWith(this.suffix) : false;
		}
		
		// TODO add equals, hashCode, toString
	}

	public static final <T extends Path> Predicate<T> isDirectory()
	{
		return cast(IsDirectoryPredicate.Default.INSTANCE);
	}
	
	public static final <T extends Path> Predicate<T> isDirectory(LinkOption ... options)
	{
		return new IsDirectoryPredicate<>(options);
	}
	
	public static final <T extends Path> Predicate<T> isRegularFile()
	{
		return cast(IsRegularFilePredicate.Default.INSTANCE);
	}
	
	public static final <T extends Path> Predicate<T> isRegularFile(LinkOption ... options)
	{
		return new IsRegularFilePredicate<>(options);
	}
	
	public static final <T extends Path> Predicate<T> isExecutable()
	{
		return cast(IsExecutablePredicate.INSTANCE);
	}
	
	public static final <T extends Path> Predicate<T> isReadable()
	{
		return cast(IsReadablePredicate.INSTANCE);
	}

	public static final <T extends Path> Predicate<T> isWritable()
	{
		return cast(IsWritablePredicate.INSTANCE);
	}

	public static final <T extends Path> Predicate<T> isHidden()
	{
		return cast(IsHiddenPredicate.INSTANCE);
	}

	public static final <T extends Path> Predicate<T> isSymbolicLink()
	{
		return cast(IsSymbolicLinkPredicate.INSTANCE);
	}

	static final class IsDirectoryPredicate<T extends Path> implements Predicate<T>, Serializable
	{
		private static final long serialVersionUID = 0;
	
		private final LinkOption[] options;
		
		IsDirectoryPredicate(LinkOption ... options)
		{
			this.options = Arrays.copyOf(options, options.length);
		}
	
		public final boolean apply(@Nonnull T path)
		{
			return Files.isDirectory(path, this.options);
		}
	
		static enum Default implements Predicate<Path>
		{
			INSTANCE;
			
			public final boolean apply(@Nonnull Path path)
			{
				return Files.isDirectory(path);
			}
			
			// TODO add toString
		}
		
		// TODO add equals, hashCode, toString
	}

	static final class IsRegularFilePredicate<T extends Path> implements Predicate<T>, Serializable
	{
		private static final long serialVersionUID = 0;
	
		private final LinkOption[] options;
		
		IsRegularFilePredicate(LinkOption ... options)
		{
			this.options = Arrays.copyOf(options, options.length);
		}
	
		public final boolean apply(@Nonnull T path)
		{
			return Files.isRegularFile(path, this.options);
		}
	
		static enum Default implements Predicate<Path>
		{
			INSTANCE;
			
			public final boolean apply(@Nonnull Path path)
			{
				return Files.isRegularFile(path);
			}
			
			// TODO add toString
		}
		
		// TODO add equals, hashCode, toString
	}

	static enum IsExecutablePredicate implements Predicate<Path>
	{
		INSTANCE;
	
		public final boolean apply(@Nonnull Path path)
		{
			return Files.isExecutable(path); 
		}
		
		// TODO add toString
	}

	static enum IsReadablePredicate implements Predicate<Path>
	{
		INSTANCE;
	
		public final boolean apply(@Nonnull Path path)
		{
			return Files.isReadable(path);
		}
		
		// TODO add toString
	}

	static enum IsWritablePredicate implements Predicate<Path>
	{
		INSTANCE;
	
		public final boolean apply(@Nonnull Path path)
		{
			return Files.isWritable(path);
		}
		
		// TODO add toString
	}

	static enum IsHiddenPredicate implements Predicate<Path>
	{
		INSTANCE;
	
		public final boolean apply(@Nonnull Path path)
		{
			try
			{
				return Files.isHidden(path);
			}
			catch (IOException e)
			{
				throw Throwables.propagate(e);
			}
		}
		
		// TODO add toString
	}

	static enum IsSymbolicLinkPredicate implements Predicate<Path>
	{
		INSTANCE;
	
		public final boolean apply(@Nonnull Path path)
		{
			return Files.isSymbolicLink(path);
		}
		
		// TODO add toString
	}
}
