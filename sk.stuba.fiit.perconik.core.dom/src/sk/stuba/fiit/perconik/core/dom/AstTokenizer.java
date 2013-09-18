package sk.stuba.fiit.perconik.core.dom;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public interface AstTokenizer<N extends ASTNode>
{
	public List<String> tokenize(@Nullable N node);
	
	@Override
	public boolean equals(@Nullable Object o);
}
