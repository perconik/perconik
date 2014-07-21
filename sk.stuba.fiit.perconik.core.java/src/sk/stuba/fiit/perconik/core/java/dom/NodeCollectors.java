package sk.stuba.fiit.perconik.core.java.dom;

import java.util.LinkedList;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;

public final class NodeCollectors
{
	private NodeCollectors()
	{
		throw new AssertionError();
	}
	
	private static enum ChildrenCollector implements ListCollector<ASTNode, ASTNode>
	{
		INSTANCE;

		public final LinkedList<ASTNode> apply(@Nullable final ASTNode node)
		{
			return Nodes.children(node);
		}

		@Override
		public final String toString()
		{
			return "children";
		}
	}

	private static enum AncestorsCollector implements ListCollector<ASTNode, ASTNode>
	{
		INSTANCE;

		public final LinkedList<ASTNode> apply(@Nullable final ASTNode node)
		{
			return Nodes.ancestors(node);
		}
		
		@Override
		public final String toString()
		{
			return "ancestors";
		}
	}

	private static enum DescendantsCollector implements ListCollector<ASTNode, ASTNode>
	{
		INSTANCE;

		public final LinkedList<ASTNode> apply(@Nullable final ASTNode node)
		{
			return Nodes.descendants(node);
		}
		
		@Override
		public final String toString()
		{
			return "descendants";
		}
	}

	private static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> cast(final ListCollector<?, ?> collector)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		ListCollector<N, R> result = (ListCollector<N, R>) collector;
		
		return result;
	}
	
	public static final <N extends ASTNode> ListCollector<N, ASTNode> children()
	{
		return cast(ChildrenCollector.INSTANCE);
	}
	
	public static final <N extends ASTNode> ListCollector<N, ASTNode> ancestors()
	{
		return cast(AncestorsCollector.INSTANCE);
	}
	
	public static final <N extends ASTNode> ListCollector<N, ASTNode> descendants()
	{
		return cast(DescendantsCollector.INSTANCE);
	}

	public static final <N extends R, R extends ASTNode> ListCollector<N, R> usingFilter(final Predicate<N> filter)
	{
		return NodeFilteringCollector.using(filter);
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> usingFilter(final NodeClassFilter<N, R> filter)
	{
		return NodeFilteringCollector.using(filter);
	}
	
	@SafeVarargs
	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Class<? extends R> implementation, final Class<? extends R> ... rest)
	{
		return usingFilter(NodeClassFilter.<N, R>of(implementation, rest));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Iterable<Class<? extends R>> types)
	{
		return usingFilter(NodeClassFilter.<N, R>of(types));
	}

	public static final <N extends R, R extends ASTNode> ListCollector<N, R> ofType(final NodeType type, final NodeType ... rest)
	{
		return usingFilter(NodeFilters.<N>isMatching(type, rest));
	}

	public static final <N extends R, R extends ASTNode> ListCollector<N, R> ofType(final Iterable<NodeType> types)
	{
		return usingFilter(NodeFilters.<N>isMatching(types));
	}
}
