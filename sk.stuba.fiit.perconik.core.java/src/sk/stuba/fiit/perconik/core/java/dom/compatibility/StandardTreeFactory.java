package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import org.eclipse.jdt.core.dom.AST;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.TreeApiLevel;
import com.google.common.base.Preconditions;

final class StandardTreeFactory implements TreeFactory
{
	private final TreeApiLevel level;
	
	StandardTreeFactory(final TreeApiLevel level)
	{
		this.level = Preconditions.checkNotNull(level);
	}

	public final AST newTree()
	{
		return AST.newAST(this.level.getValue());
	}
}