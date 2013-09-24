package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Iterator;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

public final class AstCollectors
{
	private static final AstCollector<?, Comment> comments = usingFilter(AstFilters.comments());
	
	private static final AstCollector<?, SimpleName> simpleNames = usingFilter(AstFilters.simpleNames());
	
	private static final AstCollector<?, StringLiteral> stringLiterals = usingFilter(AstFilters.stringLiterals());
	
	private AstCollectors()
	{
		throw new AssertionError();
	}
	
	private static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> cast(final AstCollector<?, ?> collector)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstCollector<N, R> result = (AstCollector<N, R>) collector;
		
		return result;
	}
	
	public static final <N extends ASTNode> AstCollector<N, Comment> comments()
	{
		return cast(comments);
	}
	
	public static final <N extends ASTNode> AstCollector<N, SimpleName> simpleNames()
	{
		return cast(simpleNames);
	}

	public static final <N extends ASTNode> AstCollector<N, StringLiteral> stringLiterals()
	{
		return cast(stringLiterals);
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, N> usingFilter(final AstFilter<N> filter)
	{
		return AstFilteringCollector.using(filter);
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> usingFilter(final AstTypeFilter<N, R> filter)
	{
		return AstFilteringCollector.using(filter);
	}
	
	// TODO consider
//	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> fromTransformer(final AstTransformer<? super N, ? extends List<R>> transformer)
//	{
//		return new AstCollector<N, R>()
//		{
//			public final List<R> collect(final N node)
//			{
//				return transformer.transform(node);
//			}
//		};
//	}
	
	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> ofType(final Class<? extends R> type)
	{
		return usingFilter(AstFilters.<N, R>ofType(type));
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> ofType(final Class<? extends R> a, final Class<? extends R> b)
	{
		return usingFilter(AstFilters.<N, R>ofType(a, b));
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> ofType(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c)
	{
		return usingFilter(AstFilters.<N, R>ofType(a, b, c));
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> ofType(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d)
	{
		return usingFilter(AstFilters.<N, R>ofType(a, b, c, d));
	}

	@SafeVarargs
	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> ofType(final Class<? extends R> a, final Class<? extends R> b, final Class<? extends R> c, final Class<? extends R> d, final Class<? extends R> ... rest)
	{
		return usingFilter(AstFilters.<N, R>ofType(a, b, c, d, rest));
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> ofType(final Iterable<Class<? extends R>> types)
	{
		return usingFilter(AstFilters.<N, R>ofType(types));
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> ofType(final Iterator<Class<? extends R>> types)
	{
		return usingFilter(AstFilters.<N, R>ofType(types));
	}
	
	// TODO add children collector, ancestor, descendant collectors
}
