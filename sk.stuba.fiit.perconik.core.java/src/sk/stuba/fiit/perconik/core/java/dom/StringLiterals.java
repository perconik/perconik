package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nonnull;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.StringLiteral;
import com.google.common.base.Function;

public final class StringLiterals
{
	private StringLiterals()
	{
		throw new AssertionError();
	}
	
	private static enum ToEscapedValue implements Function<StringLiteral, String>
	{
		INSTANCE;
		
		public final String apply(@Nonnull final StringLiteral literal)
		{
			return literal.getEscapedValue();
		}
	}

	private static enum ToLiteralValue implements Function<StringLiteral, String>
	{
		INSTANCE;
		
		public final String apply(@Nonnull final StringLiteral literal)
		{
			return literal.getLiteralValue();
		}
	}

	private static final <N extends ASTNode, T> Function<N, T> cast(final Function<?, T> transformer)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		Function<N, T> result = (Function<N, T>) transformer;
		
		return result;
	}
	
	public static final <N extends StringLiteral> Function<N, String> toEscapedValue()
	{
		return cast(ToEscapedValue.INSTANCE);
	}
	
	public static final <N extends StringLiteral> Function<N, String> toLiteralValue()
	{
		return cast(ToLiteralValue.INSTANCE);
	}
}
