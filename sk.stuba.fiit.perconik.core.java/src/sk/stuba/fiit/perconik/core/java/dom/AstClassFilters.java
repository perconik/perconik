package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Iterator;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;

public final class AstClassFilters
{
	private static final AstClassFilter<?, Annotation> annotations = AstClassFilters.ofClass(Annotation.class);
	
	private static final AstClassFilter<?, Comment> comments = AstClassFilters.ofClass(Comment.class);

	private static final AstClassFilter<?, Expression> expressions = AstClassFilters.ofClass(Expression.class);

	private static final AstClassFilter<?, Name> names = AstClassFilters.ofClass(Name.class);

	private static final AstClassFilter<?, QualifiedName> qualifiedNames = ofClass(QualifiedName.class);

	private static final AstClassFilter<?, SimpleName> simpleNames = ofClass(SimpleName.class);
	
	private static final AstClassFilter<?, Statement> statements = AstClassFilters.ofClass(Statement.class);

	private static final AstClassFilter<?, StringLiteral> stringLiterals = ofClass(StringLiteral.class);
	
	private AstClassFilters()
	{
		throw new AssertionError();
	}

	private static final <N extends ASTNode, F extends ASTNode> AstClassFilter<N, F> cast(final AstClassFilter<?, ?> filter)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstClassFilter<N, F> result = (AstClassFilter<N, F>) filter;
		
		return result;
	}
	
	public static final <N extends ASTNode> AstClassFilter<N, Comment> annotations()
	{
		return cast(annotations);
	}
	
	public static final <N extends ASTNode> AstClassFilter<N, Comment> comments()
	{
		return cast(comments);
	}

	public static final <N extends ASTNode> AstClassFilter<N, Comment> expressions()
	{
		return cast(expressions);
	}
	
	public static final <N extends ASTNode> AstClassFilter<N, Comment> names()
	{
		return cast(names);
	}

	public static final <N extends ASTNode> AstClassFilter<N, Comment> qualifiedNames()
	{
		return cast(qualifiedNames);
	}
	
	public static final <N extends ASTNode> AstClassFilter<N, SimpleName> simpleNames()
	{
		return cast(simpleNames);
	}

	public static final <N extends ASTNode> AstClassFilter<N, Comment> statements()
	{
		return cast(statements);
	}

	public static final <N extends ASTNode> AstClassFilter<N, StringLiteral> stringLiterals()
	{
		return cast(stringLiterals);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstClassFilter<N, F> ofClass(final Class<? extends F> type)
	{
		return AstClassFilter.of(type);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstClassFilter<N, F> ofClass(final Class<? extends F> a, final Class<? extends F> b)
	{
		return AstClassFilter.of(a, b);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstClassFilter<N, F> ofClass(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c)
	{
		return AstClassFilter.of(a, b, c);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstClassFilter<N, F> ofClass(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d)
	{
		return AstClassFilter.of(a, b, c, d); 
	}

	@SafeVarargs
	public static final <N extends ASTNode, F extends ASTNode> AstClassFilter<N, F> ofClass(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d, final Class<? extends F> ... rest)
	{
		return AstClassFilter.of(a, b, c, d, rest);
	}

	public static final <N extends ASTNode, F extends ASTNode> AstClassFilter<N, F> ofClass(final Iterable<Class<? extends F>> types)
	{
		return AstClassFilter.of(types); 
	}

	public static final <N extends ASTNode, F extends ASTNode> AstClassFilter<N, F> ofClass(final Iterator<Class<? extends F>> types)
	{
		return AstClassFilter.of(types);
	}
}
