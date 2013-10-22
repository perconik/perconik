package sk.stuba.fiit.perconik.core.java.dom.difference;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.core.java.dom.AstNodes;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class AstNodeModification<N extends ASTNode> extends AstNodeDelta<N>
{
	private final N original;
	
	private final N revised;

	private AstNodeModification(@Nullable final N original, @Nullable final N revised)
	{
		this.original = original;
		this.revised  = revised;
	}

	public static final <N extends ASTNode> AstNodeModification<N> of(final N original, final N revised)
	{
		return new AstNodeModification<>(original, revised);
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
	public final N getOriginalNode()
	{
		return this.original;
	}

	@Override
	public final N getRevisedNode()
	{
		return this.revised;
	}

	@Override
	public final AstNodeDeltaType getType()
	{
		return AstNodeDeltaType.MODIFICATION;
	}
}
