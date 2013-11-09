package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.base.Function;

public final class AstFlatteners
{
	private AstFlatteners()
	{
		throw new AssertionError();
	}
	
	private static enum ToString implements Function<ASTNode, CharSequence>
	{
		INSTANCE;
		
		public final CharSequence apply(@Nullable final ASTNode node)
		{
			return node == null ? "" : node.toString();
		}
	}
	
	private static final <N extends ASTNode, S extends CharSequence> Function<N, S> cast(final Function<?, S> flattener)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		Function<N, S> result = (Function<N, S>) flattener;
		
		return result;
	}

	public static final <N extends ASTNode> Function<N, CharSequence> toStringBased()
	{
		return cast(ToString.INSTANCE);
	}
}
