package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;
import com.google.common.base.Predicate;

public final class AstCollectors
{
	private static final ListCollector<?, Comment> comments = usingFilter(AstTypeFilters.comments());
	
	private static final ListCollector<?, SimpleName> simpleNames = usingFilter(AstTypeFilters.simpleNames());
	
	private static final ListCollector<?, StringLiteral> stringLiterals = usingFilter(AstTypeFilters.stringLiterals());
	
	private AstCollectors()
	{
		throw new AssertionError();
	}
	
	private static enum Children implements ListCollector<ASTNode, ASTNode>
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

	private static enum Ancestors implements ListCollector<ASTNode, ASTNode>
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

	private static enum Descendants implements ListCollector<ASTNode, ASTNode>
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
		return cast(Children.INSTANCE);
	}
	
	public static final <N extends ASTNode> ListCollector<N, ASTNode> ancestors()
	{
		return cast(Ancestors.INSTANCE);
	}
	
	public static final <N extends ASTNode> ListCollector<N, ASTNode> descendants()
	{
		return cast(Descendants.INSTANCE);
	}
	
	public static final <N extends ASTNode> ListCollector<N, Comment> comments()
	{
		return cast(comments);
	}
	
	public static final <N extends ASTNode> ListCollector<N, SimpleName> simpleNames()
	{
		return cast(simpleNames);
	}

	public static final <N extends ASTNode> ListCollector<N, StringLiteral> stringLiterals()
	{
		return cast(stringLiterals);
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, N> usingFilter(final Predicate<N> filter)
	{
		return AstFilteringCollector.using(filter);
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> usingFilter(final AstTypeFilter<N, R> filter)
	{
		return AstFilteringCollector.using(filter);
	}
	
	// TODO consider
//	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> fromTransformer(final AstTransformer<? super N, ? extends List<R>> transformer)
//	{
//		return new ListCollector<N, R>()
//		{
//			public final List<R> collect(final N node)
//			{
//				return transformer.transform(node);
//			}
//		};
//	}
	
	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofType(final Class<? extends R> type)
	{
		return usingFilter(AstTypeFilters.<N, R>ofType(type));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofType(final Class<? extends R> a, final Class<? extends R> b)
	{
		return usingFilter(AstTypeFilters.<N, R>ofType(a, b));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofType(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c)
	{
		return usingFilter(AstTypeFilters.<N, R>ofType(a, b, c));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofType(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d)
	{
		return usingFilter(AstTypeFilters.<N, R>ofType(a, b, c, d));
	}

	@SafeVarargs
	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofType(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d, final Class<? extends R> ... rest)
	{
		return usingFilter(AstTypeFilters.<N, R>ofType(a, b, c, d, rest));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofType(final Iterable<Class<? extends R>> types)
	{
		return usingFilter(AstTypeFilters.<N, R>ofType(types));
	}

	public static final <N extends ASTNode, R extends ASTNode> ListCollector<N, R> ofType(final Iterator<Class<? extends R>> types)
	{
		return usingFilter(AstTypeFilters.<N, R>ofType(types));
	}
}
