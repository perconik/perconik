package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SimpleName;
import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class AstTokenizers
{
	private AstTokenizers()
	{
		throw new AssertionError();
	}

	private static enum IdentifierNameTokenizer implements AstTokenizer<ASTNode>
	{
		INSTANCE;
		
		private static final IdentifierNameTokeniserFactory factory = new IdentifierNameTokeniserFactory();
		
		private static final IdentifierNameTokeniser tokenizer = factory.create();
		
		@Override
		public final List<String> tokenize(@Nullable final ASTNode node)
		{
			return AstTokenizers.tokenize(tokenizer, node);
		}
	}
	
	static final List<String> tokenize(final IdentifierNameTokeniser tokenizer, @Nullable final ASTNode node)
	{
		if (node == null)
		{
			return ImmutableList.of();
		}
		
		List<SimpleName> names  = AstCollectors.simpleNames().collect(node);
		List<String>     tokens = Lists.newArrayListWithCapacity(names.size());
		
		for (SimpleName name: names)
		{
			tokens.addAll(Arrays.asList(tokenizer.tokenise(name.toString())));
		}
		
		return tokens;
	}

	private static final <N extends ASTNode> AstTokenizer<N> cast(final AstTokenizer<?> tokenizer)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstTokenizer<N> result = (AstTokenizer<N>) tokenizer;
		
		return result;
	}
	
	public static final <N extends ASTNode> AstTokenizer<N> identifierNames()
	{
		return cast(IdentifierNameTokenizer.INSTANCE);
	}
}
