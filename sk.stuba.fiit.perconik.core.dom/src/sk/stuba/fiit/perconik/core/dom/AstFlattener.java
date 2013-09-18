package sk.stuba.fiit.perconik.core.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public interface AstFlattener<N extends ASTNode>
{
	public CharSequence flatten(@Nullable N node);
	
	@Override
	public boolean equals(@Nullable Object o);
}
