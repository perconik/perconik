package sk.stuba.fiit.perconik.core.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public interface AstTransformer<N extends ASTNode, R>
{
	public R transform(@Nullable N node);
	
	@Override
	public boolean equals(@Nullable Object o);
}
