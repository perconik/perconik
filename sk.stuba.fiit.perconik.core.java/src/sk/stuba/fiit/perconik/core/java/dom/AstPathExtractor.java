package sk.stuba.fiit.perconik.core.java.dom;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;

public final class AstPathExtractor<N extends ASTNode> implements Function<N, Path>
{
	private final Function<ASTNode, String> strategy;
	
	private AstPathExtractor(final Function<ASTNode, String> strategy)
	{
		this.strategy = Preconditions.checkNotNull(strategy);
	}
	
	public static final <N extends ASTNode> AstPathExtractor<N> using(final Function<ASTNode, String> strategy)
	{
		return new AstPathExtractor<>(strategy);
	}
	
	@Override
	public final Path apply(@Nullable final ASTNode node)
	{
		if (node == null)
		{
			return Paths.get(AstPaths.unknownPathName);
		}
		
		List<ASTNode>     branch   = AstNodes.upToRoot(node);
		Iterator<ASTNode> iterator = branch.iterator();
		
		String   first = this.strategy.apply(iterator.next());
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
				rest[i] = this.strategy.apply(other);
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
			builder.append(this.strategy.apply(iterator.next()));
			
			while (iterator.hasNext())
			{
				builder.append(AstPaths.variableSeparator);
				builder.append(this.strategy.apply(iterator.next()));
			}
		}
		
		return builder.toString();
	}
	
	@Override
	public final String toString()
	{
		return "path(" + this.strategy + ")";
	}
}
