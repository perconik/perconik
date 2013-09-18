package sk.stuba.fiit.perconik.core.dom.difference;

import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.core.dom.AstNodes;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class AstNodeDeletion extends AstNodeDelta
{
	private final ASTNode node;

	private AstNodeDeletion(final ASTNode node)
	{
		this.node = node;
	}

	public static final AstNodeDeletion of(final ASTNode node)
	{
		return new AstNodeDeletion(node);
	}

	@Override
	public final String toString(final int indent)
	{
		SmartStringBuilder builder = this.toStringBuilder(indent);

		builder.append("original: ").appendln(AstNodes.typeAsString(this.node));
		builder.tab().lines(this.node);
		
		return builder.toString();
	}

	public final ASTNode getDeletedNode()
	{
		return this.node;
	}

	@Override
	public final ASTNode getOriginalNode()
	{
		return this.getDeletedNode();
	}

	@Override
	public final ASTNode getRevisedNode()
	{
		return null;
	}
	
	@Override
	public final AstNodeDeltaType getType()
	{
		return AstNodeDeltaType.DELETION;
	}
}
