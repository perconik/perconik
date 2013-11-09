package sk.stuba.fiit.perconik.core.java.dom;

import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;
import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;
import com.google.common.base.Preconditions;

public final class AstIdentifierTokenizer<N extends ASTNode> implements ListCollector<N, String>
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

	public final List<String> apply(final N node)
	{
		return AstTokenizers.tokenize(this.tokenizer, node);
	}
}
