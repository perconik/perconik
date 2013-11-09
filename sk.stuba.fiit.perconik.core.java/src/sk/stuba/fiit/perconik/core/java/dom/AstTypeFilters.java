package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Iterator;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

public final class AstTypeFilters
{
	private static final AstTypeFilter<?, Comment> comments = AstTypeFilters.ofType(BlockComment.class, Javadoc.class, LineComment.class);
	
	private static final AstTypeFilter<?, SimpleName> simpleNames = ofType(SimpleName.class);
	
	private static final AstTypeFilter<?, StringLiteral> stringLiterals = ofType(StringLiteral.class);
	
	private AstTypeFilters()
	{
		throw new AssertionError();
	}

	private static final <N extends ASTNode, F extends ASTNode> AstTypeFilter<N, F> cast(final AstTypeFilter<?, ?> filter)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstTypeFilter<N, F> result = (AstTypeFilter<N, F>) filter;
		
		return result;
	}
	
	public static final <N extends ASTNode> AstTypeFilter<N, Comment> comments()
	{
		return cast(comments);
	}
	
	public static final <N extends ASTNode> AstTypeFilter<N, SimpleName> simpleNames()
	{
		return cast(simpleNames);
	}

	public static final <N extends ASTNode> AstTypeFilter<N, StringLiteral> stringLiterals()
	{
		return cast(stringLiterals);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeFilter<N, F> ofType(final Class<? extends F> type)
	{
		return AstTypeFilter.of(type);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeFilter<N, F> ofType(final Class<? extends F> a, final Class<? extends F> b)
	{
		return AstTypeFilter.of(a, b);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeFilter<N, F> ofType(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c)
	{
		return AstTypeFilter.of(a, b, c);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeFilter<N, F> ofType(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d)
	{
		return AstTypeFilter.of(a, b, c, d); 
	}

	@SafeVarargs
	public static final <N extends ASTNode, F extends ASTNode> AstTypeFilter<N, F> ofType(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d, final Class<? extends F> ... rest)
	{
		return AstTypeFilter.of(a, b, c, d, rest);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeFilter<N, F> ofType(final Iterable<Class<? extends F>> types)
	{
		return AstTypeFilter.of(types); 
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeFilter<N, F> ofType(final Iterator<Class<? extends F>> types)
	{
		return AstTypeFilter.of(types);
	}
}
