package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Map;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;

public final class AstTypeCounter<N extends ASTNode> implements AstCounter<N>, AstTransformer<N, Map<AstNodeType, Integer>>
{

	public Map<AstNodeType, Integer> transform(N node)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int count(N node)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
