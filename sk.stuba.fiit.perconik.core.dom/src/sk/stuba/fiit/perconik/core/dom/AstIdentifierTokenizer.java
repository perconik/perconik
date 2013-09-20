package sk.stuba.fiit.perconik.core.dom;

import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;
import com.google.common.base.Preconditions;

public final class AstIdentifierTokenizer<N extends ASTNode> implements AstTokenizer<N>
{
	private final IdentifierNameTokeniser tokenizer;
	
	private AstIdentifierTokenizer(final IdentifierNameTokeniser tokenizer)
	{
		this.tokenizer = Preconditions.checkNotNull(tokenizer);
	}

	public static final <N extends ASTNode> AstIdentifierTokenizer<N> create(final IdentifierNameTokeniser tokenizer)
	{
		return new AstIdentifierTokenizer<>(tokenizer);
	}

	public static final <N extends ASTNode> AstIdentifierTokenizer<N> create(final IdentifierNameTokeniserFactory factory)
	{
		return new AstIdentifierTokenizer<>(factory.create());
	}

	public final List<String> tokenize(final N node)
	{
		return AstTokenizers.tokenize(this.tokenizer, node);
	}
}
