package sk.stuba.fiit.perconik.core.dom;

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

	private static enum IdentifierTokenizer implements AstTokenizer<ASTNode>
	{
		INSTANCE;
		
		private static final IdentifierNameTokeniserFactory factory = new IdentifierNameTokeniserFactory();
		
		@Override
		public final List<String> tokenize(@Nullable final ASTNode node)
		{
			if (node == null)
			{
				return ImmutableList.of();
			}
			
			IdentifierNameTokeniser tokenizer = factory.create();
			List<SimpleName>        names     = AstCollectors.simpleNameCollector().collect(node);
			List<String>            tokens    = Lists.newArrayListWithCapacity(names.size());
			
			for (SimpleName name: names)
			{
				tokens.addAll(Arrays.asList(tokenizer.tokenise(name.toString())));
			}
			
			return tokens;
		}
	}

	public static final <N extends ASTNode> AstTokenizer<N> identifierTokenizer()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstTokenizer<N> tokenizer = (AstTokenizer<N>) IdentifierTokenizer.INSTANCE;
		
		return tokenizer;
	}
}
