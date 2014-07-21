package sk.stuba.fiit.perconik.utilities.function;

import java.io.Serializable;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Numerates
{
	private Numerates()
	{
		throw new AssertionError();
	}
	
	private static final class NumerateFunction<T> implements Function<T, Integer>, Serializable
	{
		private static final long serialVersionUID = 0;
		
		private final Numerate<T> numerate;

		NumerateFunction(final Numerate<T> numerate)
		{
			this.numerate = checkNotNull(numerate);
		}

		@Override
		public final Integer apply(@Nullable final T input)
		{
			return this.numerate.apply(input);
		}

		@Override
		public final boolean equals(@Nullable final Object o)
		{
			if (o instanceof NumerateFunction)
			{
				NumerateFunction<?> other = (NumerateFunction<?>) o;
				
				return this.numerate.equals(other.numerate);
			}
			
			return false;
		}

		@Override
		public final int hashCode()
		{
			return this.numerate.hashCode();
		}

		@Override
		public final String toString()
		{
			return "forNumerate(" + this.numerate + ")";
		}
	}
	
	public static final <T> Function<T, Integer> asFunction(final Numerate<T> numerate)
	{
		return new NumerateFunction<>(numerate);
	}
}
