package sk.stuba.fiit.perconik.core.dom;

import java.util.Iterator;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

public final class AstFilters
{
	private static final AstTypeFilter<?, Comment> comments = ofType(Comment.class);
	
	private static final AstTypeFilter<?, SimpleName> simpleNames = ofType(SimpleName.class);
	
	private static final AstTypeFilter<?, StringLiteral> stringLiterals = ofType(StringLiteral.class);
	
	private AstFilters()
	{
		throw new AssertionError();
	}
	
	private static enum AlwaysAccept implements AstFilter<ASTNode>
	{
		INSTANCE;
	
		public final boolean accept(@Nullable final ASTNode node)
		{
			return true;
		}
	}

	private static enum AlwaysReject implements AstFilter<ASTNode>
	{
		INSTANCE;
	
		public final boolean accept(@Nullable final ASTNode node)
		{
			return false;
		}
	}
	
	private static enum IsNull implements AstFilter<ASTNode>
	{
		INSTANCE;
	
		public final boolean accept(@Nullable final ASTNode node)
		{
			return node == null;
		}
	}
	
	private static enum NotNull implements AstFilter<ASTNode>
	{
		INSTANCE;
	
		public final boolean accept(@Nullable final ASTNode node)
		{
			return node != null;
		}
	}

	private static final <N extends ASTNode> AstFilter<N> cast(final AstFilter<?> filter)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstFilter<N> result = (AstFilter<N>) filter;
		
		return result;
	}

	public static final <N extends ASTNode> AstFilter<N> alwaysAccept()
	{
		return cast(AlwaysAccept.INSTANCE);
	}
	
	public static final <N extends ASTNode> AstFilter<N> alwaysReject()
	{
		return cast(AlwaysReject.INSTANCE);
	}
	
	public static final <N extends ASTNode> AstFilter<N> isNull()
	{
		return cast(IsNull.INSTANCE);
	}
	
	public static final <N extends ASTNode> AstFilter<N> notNull()
	{
		return cast(NotNull.INSTANCE);
	}
	
	// TODO add: and, or, not, asPredicate, fromPredicate

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
