package sk.stuba.fiit.perconik.utilities.io;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.base.Function;
import com.google.common.base.Throwables;

public final class PathFunctions
{
	private PathFunctions()
	{
		throw new AssertionError();
	}
	
	private static final <T extends Path> Function<T, T> castWhole(Function<? extends Path, ? extends Path> function)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		Function<T, T> result = (Function<T, T>) function;
		
		return result;
	}
	
	private static final <T extends Path, R> Function<T, R> castInput(Function<? extends Path, R> function)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		Function<T, R> result = (Function<T, R>) function;
		
		return result;
	}
	
	public static final <T extends Path> Function<T, T> normalize()
	{
		return castWhole(NormalizeFunction.INSTANCE);
	}
	
	public static final <T extends Path> Function<T, T> relativize(T path)
	{
		return new RelativizeFunction<>(path);
	}

	public static final <T extends Path> Function<T, T> resolve(T path)
	{
		return new ResolveFunction<>(path);
	}
	
	public static final <T extends Path> Function<T, T> resolveSibling(T path)
	{
		return new ResolveSiblingFunction<>(path);
	}
	
	static enum NormalizeFunction implements Function<Path, Path>
	{
		INSTANCE;
	
		public final Path apply(@Nonnull Path path)
		{
			return path.normalize();
		}
		
		// TODO add toString
	}

	static final class RelativizeFunction<T extends Path> implements Function<T, T>, Serializable
	{
		private static final long serialVersionUID = 0;
		
		final T path;

		public RelativizeFunction(T path)
		{
			this.path = checkNotNull(path);
		}

		public final T apply(@Nullable T path)
		{
			return (T) (path != null ? path.relativize(this.path) : null);
		}
		
		// TODO add equals, hashCode, toString
	}
	
	static final class ResolveFunction<T extends Path> implements Function<T, T>, Serializable
	{
		private static final long serialVersionUID = 0;
		
		final T path;

		public ResolveFunction(T path)
		{
			this.path = checkNotNull(path);
		}

		public final T apply(@Nullable T path)
		{
			return (T) (path != null ? path.resolve(this.path) : null);
		}
		
		// TODO add equals, hashCode, toString
	}
	
	static final class ResolveSiblingFunction<T extends Path> implements Function<T, T>, Serializable
	{
		private static final long serialVersionUID = 0;
		
		final T path;

		public ResolveSiblingFunction(T path)
		{
			this.path = checkNotNull(path);
		}

		public final T apply(@Nullable T path)
		{
			return (T) (path != null ? path.resolveSibling(this.path) : null);
		}
		
		// TODO add equals, hashCode, toString
	}
	
	public static final <T extends Path> Function<T, T> name(int index)
	{
		return new NameFunction<>(index);
	}
	
	public static final <T extends Path> Function<T, Integer> nameCount()
	{
		return castInput(NameCountFunction.INSTANCE);
	}
	
	static final class NameFunction<T extends Path> implements Function<T, T>, Serializable
	{
		private static final long serialVersionUID = 0;
	
		private final int index;
		
		NameFunction(int index)
		{
			checkArgument(index >= 0);
			
			this.index = index;
		}
	
		public final T apply(@Nullable T path)
		{
			return (T) (path != null ? path.getName(this.index) : null);
		}
		
		// TODO add equals, hashCode, toString
	}

	static enum NameCountFunction implements Function<Path, Integer>
	{
		INSTANCE;
	
		public final Integer apply(@Nonnull Path path)
		{
			return path.getNameCount();
		}
		
		// TODO add toString
	}
	
	public static final <T extends Path> Function<T, T> root()
	{
		return castWhole(RootFunction.INSTANCE);
	}

	public static final <T extends Path> Function<T, T> parent()
	{
		return castWhole(ParentFunction.INSTANCE);
	}

	public static final <T extends Path> Function<T, T> fileName()
	{
		return castWhole(FileNameFunction.INSTANCE);
	}

	public static final <T extends Path> Function<T, FileSystem> fileSystem()
	{
		return castInput(FileSystemFunction.INSTANCE);
	}

	static enum RootFunction implements Function<Path, Path>
	{
		INSTANCE;
	
		public final Path apply(@Nonnull Path path)
		{
			return path.getRoot();
		}
		
		// TODO add toString
	}

	static enum ParentFunction implements Function<Path, Path>
	{
		INSTANCE;
	
		public final Path apply(@Nonnull Path path)
		{
			return path.getParent();
		}
		
		// TODO add toString
	}

	static enum FileNameFunction implements Function<Path, Path>
	{
		INSTANCE;
	
		public final Path apply(@Nonnull Path path)
		{
			return path.getFileName();
		}
		
		// TODO add toString
	}

	static enum FileSystemFunction implements Function<Path, FileSystem>
	{
		INSTANCE;
	
		public final FileSystem apply(@Nonnull Path path)
		{
			return path.getFileSystem();
		}
		
		// TODO add toString
	}

	public static final <T extends Path> Function<T, T> toAbsolutePath()
	{
		return castWhole(ToAbsolutePathFunction.INSTANCE);
	}
	
	public static final <T extends Path> Function<T, T> toRealPath()
	{
		return castWhole(ToRealPathFunction.Default.INSTANCE);
	}
	
	public static final <T extends Path> Function<T, T> toRealPath(LinkOption ... options)
	{
		return new ToRealPathFunction<>(options);
	}
	
	public static final <T extends Path> Function<T, File> toFile()
	{
		return castInput(ToFileFunction.INSTANCE);
	}
	
	public static final <T extends Path> Function<T, URI> toUri()
	{
		return castInput(ToUriFunction.INSTANCE);
	}
	
	static enum ToAbsolutePathFunction implements Function<Path, Path>
	{
		INSTANCE;
	
		public final Path apply(@Nonnull Path path)
		{
			return path.toAbsolutePath();
		}
		
		// TODO add toString
	}

	static final class ToRealPathFunction<T extends Path> implements Function<T, T>, Serializable
	{
		private static final long serialVersionUID = 0;
	
		private final LinkOption[] options;
		
		ToRealPathFunction(LinkOption ... options)
		{
			this.options = Arrays.copyOf(options, options.length);
		}
	
		public final T apply(@Nonnull T path)
		{
			try
			{
				return (T) path.toRealPath(this.options);
			}
			catch (IOException e)
			{
				throw Throwables.propagate(e);
			}
		}
	
		static enum Default implements Function<Path, Path>
		{
			INSTANCE;
			
			public final Path apply(@Nonnull Path path)
			{
				try
				{
					return path.toRealPath();
				}
				catch (IOException e)
				{
					throw Throwables.propagate(e);
				}
			}
			
			// TODO add toString
		}
		
		// TODO add equals, hashCode, toString
	}

	static enum ToFileFunction implements Function<Path, File>
	{
		INSTANCE;
	
		public final File apply(@Nonnull Path path)
		{
			return path.toFile(); 
		}
		
		// TODO add toString
	}

	static enum ToUriFunction implements Function<Path, URI>
	{
		INSTANCE;
	
		public final URI apply(@Nonnull Path path)
		{
			return path.toUri();
		}
		
		// TODO add toString
	}
}
