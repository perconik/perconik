package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;
import com.google.common.collect.Multimap;

public final class AstTypeDivider<N extends ASTNode> implements AstTransformer<N, Multimap<AstNodeType, ASTNode>>
{
	AstTypeDivider()
	{
	}

	public final Multimap<AstNodeType, ASTNode> transform(final N node)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
