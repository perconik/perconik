package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import org.eclipse.jdt.core.dom.AST;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstApiLevel;
import com.google.common.base.Preconditions;

final class StandardAstFactory implements AstFactory
{
	private final AstApiLevel level;
	
	StandardAstFactory(final AstApiLevel level)
	{
		this.level = Preconditions.checkNotNull(level);
	}

	public final AST newTree()
	{
		return AST.newAST(this.level.getValue());
	}
}