package sk.stuba.fiit.perconik.utilities.io;

import static com.google.common.base.Preconditions.checkNotNull;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import javax.annotation.Nullable;
import com.google.common.base.Predicate;

public final class PathFilters
{
	private PathFilters()
	{
		throw new AssertionError();
	}
	
	public static final <T> DirectoryStream.Filter<T> using(Predicate<? super T> predicate)
	{
		return new PredicateFilter<>(predicate);
	}
	
	static final class PredicateFilter<T> implements DirectoryStream.Filter<T>, Serializable
	{
		private static final long serialVersionUID = 0;
		
		private final Predicate<? super T> predicate;
		
		PredicateFilter(Predicate<? super T> predicate)
		{
			this.predicate = checkNotNull(predicate);
		}

		public final boolean accept(@Nullable T entry) throws IOException
		{
			return this.predicate.apply(entry);
		}
		
		@Override
		public final boolean equals(@Nullable Object o)
		{
			if (o instanceof PredicateFilter)
			{
				PredicateFilter<?> other = (PredicateFilter<?>) o;
				
				return this.predicate.equals(other.predicate);
			}
			
			return false;
		}

		@Override
		public final int hashCode()
		{
			return this.predicate.hashCode();
		}

		@Override
		public final String toString()
		{
			return "Filters.using(" + this.predicate + ")";
		}
	}
}
