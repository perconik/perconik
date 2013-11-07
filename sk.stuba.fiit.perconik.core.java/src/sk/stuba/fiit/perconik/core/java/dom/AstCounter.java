package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public interface AstCounter<N extends ASTNode>
{
	public int count(@Nullable N node);
	
	@Override
	public boolean equals(@Nullable Object o);
}
