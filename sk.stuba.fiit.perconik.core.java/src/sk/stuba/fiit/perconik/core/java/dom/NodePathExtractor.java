package sk.stuba.fiit.perconik.core.java.dom;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;

public final class NodePathExtractor<N extends ASTNode> implements Function<N, Path>
{
	private final Function<ASTNode, String> strategy;
	
	private NodePathExtractor(final Function<ASTNode, String> strategy)
	{
		this.strategy = Preconditions.checkNotNull(strategy);
	}
	
	public static final <N extends ASTNode> NodePathExtractor<N> using(final Function<ASTNode, String> strategy)
	{
		return new NodePathExtractor<>(strategy);
	}
	
	@Override
	public final Path apply(@Nullable final ASTNode node)
	{
		if (node == null)
		{
			return Paths.get(NodePaths.unknownPathName);
		}
		
		LinkedList<ASTNode> branch = Nodes.upToRoot(node);
		
		if (NodeType.valueOf(branch.getLast()) == NodeType.COMPILATION_UNIT)
		{
			branch.removeLast();
		}

		Iterator<ASTNode> iterator = branch.descendingIterator();
		
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
				builder.append(NodePaths.variableSeparator);
				builder.append(this.strategy.apply(iterator.next()));
			}
		}
		
		return builder.toString();
	}
	
	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (o instanceof NodePathExtractor)
		{
			NodePathExtractor<?> other = (NodePathExtractor<?>) o;
			
			return this.strategy.equals(other.strategy);
		}
		
		return false;
	}

	@Override
	public final int hashCode()
	{
		return this.strategy.hashCode();
	}
	
	@Override
	public final String toString()
	{
		return "path(" + this.strategy + ")";
	}

	public final Function<ASTNode, String> getStrategy()
	{
		return this.strategy;
	}
}
