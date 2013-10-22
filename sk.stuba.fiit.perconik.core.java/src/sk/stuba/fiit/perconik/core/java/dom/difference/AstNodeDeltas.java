package sk.stuba.fiit.perconik.core.java.dom.difference;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public final class AstNodeDeltas
{
	private AstNodeDeltas()
	{
		throw new AssertionError();
	}
	
	public static <N extends ASTNode> AstNodeDelta<N> create(@Nullable final N original, @Nullable final N revised)
	{
		if (original != null && revised != null)
		{
			return AstNodeModification.of(original, revised);
		}
		else if (original != null)
		{
			return AstNodeDeletion.of(original);
		}
		else if (revised != null)
		{
			return AstNodeAddition.of(revised);
		}
		
		throw new NullPointerException();
	}
}
