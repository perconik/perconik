package sk.stuba.fiit.perconik.core.dom;

import java.util.Iterator;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

public final class AstFilters
{
	private static final AstTypeBasedFilter<?, Comment> commentFilter = ofType(Comment.class);
	
	private static final AstTypeBasedFilter<?, SimpleName> simpleNameFilter = ofType(SimpleName.class);
	
	private static final AstTypeBasedFilter<?, StringLiteral> stringLiteralFilter = ofType(StringLiteral.class);
	
	private AstFilters()
	{
		throw new AssertionError();
	}
	
	private static enum AcceptFilter implements AstFilter<ASTNode>
	{
		INSTANCE;
	
		public final boolean accept(@Nullable final ASTNode node)
		{
			return true;
		}
	}

	private static enum RejectFilter implements AstFilter<ASTNode>
	{
		INSTANCE;
	
		public final boolean accept(@Nullable final ASTNode node)
		{
			return false;
		}
	}

	public static final <N extends ASTNode> AstFilter<N> acceptFilter()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstFilter<N> filter = (AstFilter<N>) AcceptFilter.INSTANCE;
		
		return filter;
	}
	
	public static final <N extends ASTNode> AstFilter<N> rejectFilter()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstFilter<N> filter = (AstFilter<N>) RejectFilter.INSTANCE;
		
		return filter;
	}
	
	public static final <N extends ASTNode> AstTypeBasedFilter<N, Comment> commentFilter()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstTypeBasedFilter<N, Comment> filter = (AstTypeBasedFilter<N, Comment>) commentFilter;
		
		return filter;
	}
	
	public static final <N extends ASTNode> AstTypeBasedFilter<N, SimpleName> simpleNameFilter()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstTypeBasedFilter<N, SimpleName> filter = (AstTypeBasedFilter<N, SimpleName>) simpleNameFilter;
		
		return filter;
	}

	public static final <N extends ASTNode> AstTypeBasedFilter<N, StringLiteral> stringLiteralFilter()
	{
		// internal singleton is stateless and safe to share across all types
		@SuppressWarnings("unchecked")
		AstTypeBasedFilter<N, StringLiteral> filter = (AstTypeBasedFilter<N, StringLiteral>) stringLiteralFilter;
		
		return filter;
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> ofType(final Class<? extends F> type)
	{
		return AstTypeBasedFilter.of(type);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> ofType(final Class<? extends F> a, final Class<? extends F> b)
	{
		return AstTypeBasedFilter.of(a, b);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> ofType(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c)
	{
		return AstTypeBasedFilter.of(a, b, c);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> ofType(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d)
	{
		return AstTypeBasedFilter.of(a, b, c, d); 
	}

	@SafeVarargs
	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> ofType(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d, final Class<? extends F> ... rest)
	{
		return AstTypeBasedFilter.of(a, b, c, d, rest);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> ofType(final Iterable<Class<? extends F>> types)
	{
		return AstTypeBasedFilter.of(types); 
	}

	public static final <N extends ASTNode, F extends ASTNode> AstTypeBasedFilter<N, F> ofType(final Iterator<Class<? extends F>> types)
	{
		return AstTypeBasedFilter.of(types);
	}
}
