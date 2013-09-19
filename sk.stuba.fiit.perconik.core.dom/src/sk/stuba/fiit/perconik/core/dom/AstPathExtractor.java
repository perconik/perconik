package sk.stuba.fiit.perconik.core.dom;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import com.google.common.base.Preconditions;

public final class AstPathExtractor<N extends ASTNode> implements AstTransformer<N, Path>
{
	static final String unknownPathName = "_";
	
	static final String variableSeparator = ",";
	
	private final AstTransformer<ASTNode, String> strategy;
	
	private AstPathExtractor(final AstTransformer<ASTNode, String> strategy)
	{
		this.strategy = Preconditions.checkNotNull(strategy);
	}
	
	public static final String unknownPathName()
	{
		return unknownPathName;
	}
	
	public static final String variableSeparator()
	{
		return variableSeparator;
	}

	public static final <N extends ASTNode> AstPathExtractor<N> using(final AstTransformer<ASTNode, String> strategy)
	{
		return new AstPathExtractor<>(strategy);
	}
	
	@Override
	public final Path transform(@Nullable final ASTNode node)
	{
		if (node == null)
		{
			return Paths.get(unknownPathName);
		}
		
		List<ASTNode>     branch   = AstNodes.upToRoot(node);
		Iterator<ASTNode> iterator = branch.iterator();
		
		String   first = this.strategy.transform(iterator.next());
		String[] rest  = new String[branch.size() - 1];
		
		for (int i = 0; i < rest.length; i ++)
		{
			ASTNode other = iterator.next();
			
			if (other instanceof FieldDeclaration)
			{
				rest[i] = fragments(((FieldDeclaration) other).fragments());
			}
			else
			{
				rest[i] = this.strategy.transform(other);
			}
		}
		
		return Paths.get(first, rest);
	}
	
	private final String fragments(final List<VariableDeclarationFragment> fragments)
	{
		Iterator<VariableDeclarationFragment> iterator = fragments.iterator();
		
		StringBuilder builder = new StringBuilder();
		
		if (iterator.hasNext())
		{
			builder.append(this.strategy.transform(iterator.next()));
			
			while (iterator.hasNext())
			{
				builder.append(variableSeparator);
				builder.append(this.strategy.transform(iterator.next()));
			}
		}
		
		return builder.toString();
	}
}
