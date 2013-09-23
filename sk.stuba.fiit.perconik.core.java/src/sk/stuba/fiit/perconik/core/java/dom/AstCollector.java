package sk.stuba.fiit.perconik.core.java.dom;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public interface AstCollector<N extends ASTNode, R extends ASTNode>
{
	public List<R> collect(@Nullable N node);
	
	@Override
	public boolean equals(@Nullable Object o);
}
