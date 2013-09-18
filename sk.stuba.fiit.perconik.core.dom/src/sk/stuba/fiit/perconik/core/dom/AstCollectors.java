package sk.stuba.fiit.perconik.core.dom;

import java.util.Iterator;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

public final class AstCollectors
{
	private static final AstCollector<?, Comment> commentCollector = usingFilter(AstFilters.internalCommentFilter());
	
	private static final AstCollector<?, SimpleName> simpleNameCollector = usingFilter(AstFilters.internalSimpleNameFilter());
	
	private static final AstCollector<?, StringLiteral> stringLiteralCollector = usingFilter(AstFilters.internalStringLiteralFilter());
	
	private AstCollectors()
	{
		throw new AssertionError();
	}

	static final <N extends ASTNode> AstCollector<N, Comment> internalCommentCollector()
	{
		// internal singleton has no state and only unbounded type parameters
		// therefore it is safe to share the same instance across all types
		@SuppressWarnings("unchecked")
		AstCollector<N, Comment> collector = (AstCollector<N, Comment>) commentCollector;
		
		return collector;
	}

	static final <N extends ASTNode> AstCollector<N, SimpleName> internalSimpleNameCollector()
	{
		// internal singleton has no state and only unbounded type parameters
		// therefore it is safe to share the same instance across all types
		@SuppressWarnings("unchecked")
		AstCollector<N, SimpleName> collector = (AstCollector<N, SimpleName>) simpleNameCollector;
		
		return collector;
	}

	static final <N extends ASTNode> AstCollector<N, StringLiteral> internalStringLiteralCollector()
	{
		// internal singleton has no state and only unbounded type parameters
		// therefore it is safe to share the same instance across all types
		@SuppressWarnings("unchecked")
		AstCollector<N, StringLiteral> collector = (AstCollector<N, StringLiteral>) stringLiteralCollector;
		
		return collector;
	}
	
	public static final <N extends ASTNode> AstCollector<N, Comment> commentCollector()
	{
		return internalCommentCollector();
	}
	
	public static final <N extends ASTNode> AstCollector<N, SimpleName> simpleNameCollector()
	{
		return internalSimpleNameCollector();
	}

	public static final <N extends ASTNode> AstCollector<N, StringLiteral> stringLiteralCollector()
	{
		return internalStringLiteralCollector();
	}

	public static final <N extends ASTNode, R extends ASTNode> AstCollector<N, R> usingFilter(final AstTypeBasedFilter<N, R> filter)
	{
		return AstFilterBasedCollector.using(filter);
	}
	
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
