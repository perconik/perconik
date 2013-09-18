package sk.stuba.fiit.perconik.core.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class AstNodeModification extends AstNodeDelta
{
	private final ASTNode original;
	
	private final ASTNode revised;

	private AstNodeModification(final ASTNode original, final ASTNode revised)
	{
		this.original = original;
		this.revised  = revised;
	}

	public static final AstNodeModification of(final ASTNode original, final ASTNode revised)
	{
		return new AstNodeModification(original, revised);
	}
	
	@Override
	public final String toString(final int indent)
	{
		SmartStringBuilder builder = this.toStringBuilder(indent);
		
		builder.append("original: ").appendln(AstNodes.typeAsString(this.original));
		builder.tab().lines(this.original).untab();
		builder.append("revised: ").appendln(AstNodes.typeAsString(this.revised));
		builder.tab().lines(this.revised);
		
		return builder.toString();
	}

	@Override
	public final ASTNode getOriginalNode()
	{
		return this.original;
	}

	@Override
	public final ASTNode getRevisedNode()
	{
		return this.revised;
	}

	@Override
	public final AstNodeDeltaType getType()
	{
		return AstNodeDeltaType.MODIFICATION;
	}
}
