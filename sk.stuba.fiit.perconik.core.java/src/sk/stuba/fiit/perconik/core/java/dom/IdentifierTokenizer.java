package sk.stuba.fiit.perconik.core.java.dom;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;
import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public abstract class IdentifierTokenizer<N extends ASTNode> implements ListCollector<N, String>
{
	private final IdentifierNameTokeniser tokenizer;
	
	IdentifierTokenizer(final IdentifierNameTokeniser tokenizer)
	{
		this.tokenizer = Preconditions.checkNotNull(tokenizer);
	}

	public static final <N extends ASTNode> IdentifierTokenizer<N> create(final IdentifierNameTokeniser tokenizer)
	{
		return new Unknown<>(tokenizer);
	}

	public static final <N extends ASTNode> IdentifierTokenizer<N> create(final IdentifierNameTokeniserFactory factory)
	{
		return new Known<>(factory);
	}
	
	private static final class Known<N extends ASTNode> extends IdentifierTokenizer<N>
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

	private static final class Unknown<N extends ASTNode> extends IdentifierTokenizer<N>
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
	
	public final List<String> apply(final N node)
	{
		return NodeTokenizers.tokenize(this.tokenizer, node);
	}
	
	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (o instanceof IdentifierTokenizer)
		{
			IdentifierTokenizer<?> other = (IdentifierTokenizer<?>) o;
			
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
