package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;
import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public abstract class Tokenizer implements ListCollector<String, String>
{
	private final IdentifierNameTokeniser tokenizer;
	
	Tokenizer(final IdentifierNameTokeniser tokenizer)
	{
		this.tokenizer = Preconditions.checkNotNull(tokenizer);
	}

	public static final Tokenizer create(final IdentifierNameTokeniser tokenizer)
	{
		return new Unknown(tokenizer);
	}

	public static final Tokenizer create(final IdentifierNameTokeniserFactory factory)
	{
		return new Known(factory);
	}
	
	private static final class Known extends Tokenizer
	{
		private final String settings;
		
		Known(final IdentifierNameTokeniserFactory factory)
		{
			this(factory.toString(), factory.create());
		}
		
		Known(final String settings, final IdentifierNameTokeniser tokenizer)
		{
			super(tokenizer);
			
			Preconditions.checkArgument(!Strings.isNullOrEmpty(settings));
			
			this.settings = settings;
		}
		
		@Override
		public final String toString()
		{
			return "tokenizer(" + this.settings + ")";
		}
	}

	private static final class Unknown extends Tokenizer
	{
		Unknown(final IdentifierNameTokeniser tokenizer)
		{
			super(tokenizer);
		}
		
		@Override
		public final String toString()
		{
			return "tokenizer(?)";
		}
	}
	
	public final List<String> apply(final String input)
	{
		return Arrays.asList(this.tokenizer.tokenise(input));
	}
	
	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (o instanceof Tokenizer)
		{
			Tokenizer other = (Tokenizer) o;
			
			return this.tokenizer.equals(other.tokenizer);
		}
		
		return false;
	}

	@Override
	public final int hashCode()
	{
		return this.tokenizer.hashCode();
	}
	
	@Override
	public abstract String toString();
}
