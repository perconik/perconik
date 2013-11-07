package sk.stuba.fiit.perconik.core.java.dom.traverse;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public final class AstVisitors
{
	private AstVisitors()
	{
		throw new AssertionError();
	}
	
	public static final void accept(@Nullable final ASTNode node, final AstVisitor visitor)
	{
		if (node != null)
		{
			node.accept(visitor.asUnderlyingVisitor());
		}
	}
}
