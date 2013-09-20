package sk.stuba.fiit.perconik.core.dom;

import java.util.Iterator;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

public final class AstCollectors
{
	private static final AstCollector<?, Comment> commentCollector = usingFilter(AstFilters.commentFilter());
	
	private static final AstCollector<?, SimpleName> simpleNameCollector = usingFilter(AstFilters.simpleNameFilter());
	
	private static final AstCollector<?, StringLiteral> stringLiteralCollector = usingFilter(AstFilters.stringLiteralFilter());
	
	private AstCollectors()
	{
		throw new AssertionError();
	}
	
	public static final <N extends ASTNode> AstCollector<N, Comment> commentCollector()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstCollector<N, Comment> collector = (AstCollector<N, Comment>) commentCollector;
		
		return collector;
	}
	
	public static final <N extends ASTNode> AstCollector<N, SimpleName> simpleNameCollector()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstCollector<N, SimpleName> collector = (AstCollector<N, SimpleName>) simpleNameCollector;
		
		return collector;
	}

	public static final <N extends ASTNode> AstCollector<N, StringLiteral> stringLiteralCollector()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstCollector<N, StringLiteral> collector = (AstCollector<N, StringLiteral>) stringLiteralCollector;
		
		return collector;
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> usingFilter(final AstTypeBasedFilter<N, R> filter)
	{
		return AstFilterBasedCollector.using(filter);

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
}
