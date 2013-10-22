package sk.stuba.fiit.perconik.core.java.dom.difference;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.core.java.dom.AstNodes;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class AstNodeAddition<N extends ASTNode> extends AstNodeDelta<N>
{
	private final N node;

	private AstNodeAddition(@Nullable final N node)
	{
		this.node = node;
	}

	public static final <N extends ASTNode> AstNodeAddition<N> of(@Nullable final N node)
	{
		return new AstNodeAddition<>(node);
	}
	
	@Override
	public final String toString(final int indent)
	{
		SmartStringBuilder builder = this.toStringBuilder(indent);
		
		builder.append("revised: ").appendln(AstNodes.typeAsString(this.node));
		builder.tab().lines(this.node);
		
		return builder.toString();
	}
	
	public final N getAddedNode()
	{
		return this.node;
	}

	@Override
	public final N getOriginalNode()
	{
		return null;
	}

	@Override
	public final N getRevisedNode()
	{
		return this.getAddedNode();
	}

	@Override
	public final AstNodeDeltaType getType()
	{
		return AstNodeDeltaType.ADDITION;
	}
}
