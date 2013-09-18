package sk.stuba.fiit.perconik.core.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class AstNodeAddition extends AstNodeDelta
{
	private final ASTNode node;

	private AstNodeAddition(final ASTNode node)
	{
		this.node = node;
	}

	public static final AstNodeAddition of(final ASTNode node)
	{
		return new AstNodeAddition(node);
	}
	
	@Override
	public final String toString(final int indent)
	{
		SmartStringBuilder builder = this.toStringBuilder(indent);
		
		builder.append("revised: ").appendln(AstNodes.typeAsString(this.node));
		builder.tab().lines(this.node);
		
		return builder.toString();
	}
	
	public final ASTNode getAddedNode()
	{
		return this.node;
	}

	@Override
	public final ASTNode getOriginalNode()
	{
		return null;
	}

	@Override
	public final ASTNode getRevisedNode()
	{
		return this.getAddedNode();
	}

	@Override
	public final AstNodeDeltaType getType()
	{
		return AstNodeDeltaType.ADDITION;
	}
}
