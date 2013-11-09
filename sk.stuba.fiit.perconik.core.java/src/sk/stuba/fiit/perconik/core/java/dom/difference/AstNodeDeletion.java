package sk.stuba.fiit.perconik.core.java.dom.difference;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.core.java.dom.AstNodes;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class AstNodeDeletion<N extends ASTNode> extends AstNodeDelta<N>
{
	private final N node;

	private AstNodeDeletion(final N node)
	{
		this.node = node;
	}

	public static final <N extends ASTNode> AstNodeDeletion<N> of(@Nullable final N node)
	{
		return new AstNodeDeletion<>(node);
	}

	@Override
	public final String toString(final int indent)
	{
		SmartStringBuilder builder = this.toStringBuilder(indent);

		builder.append("original: ").appendln(AstNodes.toTypeString(this.node));
		builder.tab().lines(this.node);
		
		return builder.toString();
	}

	public final N getDeletedNode()
	{
		return this.node;
	}

	@Override
	public final N getOriginalNode()
	{
		return this.getDeletedNode();
	}

	@Override
	public final N getRevisedNode()
	{
		return null;
	}
	
	@Override
	public final AstNodeDeltaType getType()
	{
		return AstNodeDeltaType.DELETION;
	}
}
