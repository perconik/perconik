package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;
import com.google.common.base.Predicate;

public final class AstCollectors
{
	private AstCollectors()
	{
		throw new AssertionError();
	}
	
	private static enum ChildrenCollector implements ListCollector<ASTNode, ASTNode>
	{
		INSTANCE;

		public final LinkedList<ASTNode> apply(@Nullable final ASTNode node)
		{
			return AstNodes.children(node);
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
			return AstNodes.ancestors(node);
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
			return AstNodes.descendants(node);
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

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, N> usingFilter(final Predicate<N> filter)
	{
		return AstFilteringCollector.using(filter);
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> usingFilter(final AstClassFilter<N, R> filter)
	{
		return AstFilteringCollector.using(filter);
	}
	
	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Class<? extends R> type)
	{
		return usingFilter(AstClassFilters.<N, R>ofClass(type));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Class<? extends R> a, final Class<? extends R> b)
	{
		return usingFilter(AstClassFilters.<N, R>ofClass(a, b));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c)
	{
		return usingFilter(AstClassFilters.<N, R>ofClass(a, b, c));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d)
	{
		return usingFilter(AstClassFilters.<N, R>ofClass(a, b, c, d));
	}

	@SafeVarargs
	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d, final Class<? extends R> ... rest)
	{
		return usingFilter(AstClassFilters.<N, R>ofClass(a, b, c, d, rest));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Iterable<Class<? extends R>> types)
	{
		return usingFilter(AstClassFilters.<N, R>ofClass(types));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofClass(final Iterator<Class<? extends R>> types)
	{
		return usingFilter(AstClassFilters.<N, R>ofClass(types));
	}
}
