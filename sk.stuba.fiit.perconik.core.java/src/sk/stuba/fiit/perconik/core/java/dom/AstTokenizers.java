package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SimpleName;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;
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

	private static enum IdentifierNameTokenizer implements ListCollector<ASTNode, String>
	{
		INSTANCE;
		
		private static final IdentifierNameTokeniserFactory factory = new IdentifierNameTokeniserFactory();
		
		private static final IdentifierNameTokeniser tokenizer = factory.create();
		
		@Override
		public final List<String> apply(@Nullable final ASTNode node)
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
		
		List<SimpleName> names  = AstCollectors.simpleNames().apply(node);
		List<String>     tokens = Lists.newArrayListWithCapacity(names.size());
		
		for (SimpleName name: names)
		{
			tokens.addAll(Arrays.asList(tokenizer.tokenise(name.toString())));
		}
		
		return tokens;
	}

	private static final <N extends ASTNode> ListCollector<N, String> cast(final ListCollector<?, String> tokenizer)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		ListCollector<N, String> result = (ListCollector<N, String>) tokenizer;
		
		return result;
	}
	
	public static final <N extends ASTNode> ListCollector<N, String> identifierNames()
	{
		return cast(IdentifierNameTokenizer.INSTANCE);
	}
}
