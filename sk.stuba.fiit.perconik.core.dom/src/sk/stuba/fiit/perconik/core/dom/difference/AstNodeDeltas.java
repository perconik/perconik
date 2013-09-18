package sk.stuba.fiit.perconik.core.dom.difference;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;

public final class AstNodeDeltas
{
	private AstNodeDeltas()
	{
		throw new AssertionError();
	}
	
	public static AstNodeDelta create(@Nullable final ASTNode original, @Nullable final ASTNode revised)
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
