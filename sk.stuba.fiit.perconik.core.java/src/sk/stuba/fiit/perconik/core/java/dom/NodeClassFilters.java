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

public final class NodeClassFilters
{
	private static final NodeClassFilter<?, Annotation> annotations = NodeClassFilters.ofClass(Annotation.class);
	
	private static final NodeClassFilter<?, Comment> comments = NodeClassFilters.ofClass(Comment.class);

	private static final NodeClassFilter<?, Expression> expressions = NodeClassFilters.ofClass(Expression.class);

	private static final NodeClassFilter<?, Name> names = NodeClassFilters.ofClass(Name.class);

	private static final NodeClassFilter<?, QualifiedName> qualifiedNames = ofClass(QualifiedName.class);

	private static final NodeClassFilter<?, SimpleName> simpleNames = ofClass(SimpleName.class);
	
	private static final NodeClassFilter<?, Statement> statements = NodeClassFilters.ofClass(Statement.class);

	private static final NodeClassFilter<?, StringLiteral> stringLiterals = ofClass(StringLiteral.class);
	
	private NodeClassFilters()
	{
		throw new AssertionError();
	}

	private static final <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> cast(final NodeClassFilter<?, ?> filter)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		NodeClassFilter<N, F> result = (NodeClassFilter<N, F>) filter;
		
		return result;
	}
	
	public static final <N extends ASTNode> NodeClassFilter<N, Comment> annotations()
	{
		return cast(annotations);
	}
	
	public static final <N extends ASTNode> NodeClassFilter<N, Comment> comments()
	{
		return cast(comments);
	}

	public static final <N extends ASTNode> NodeClassFilter<N, Comment> expressions()
	{
		return cast(expressions);
	}
	
	public static final <N extends ASTNode> NodeClassFilter<N, Comment> names()
	{
		return cast(names);
	}

	public static final <N extends ASTNode> NodeClassFilter<N, Comment> qualifiedNames()
	{
		return cast(qualifiedNames);
	}
	
	public static final <N extends ASTNode> NodeClassFilter<N, SimpleName> simpleNames()
	{
		return cast(simpleNames);
	}

	public static final <N extends ASTNode> NodeClassFilter<N, Comment> statements()
	{
		return cast(statements);
	}

	public static final <N extends ASTNode> NodeClassFilter<N, StringLiteral> stringLiterals()
	{
		return cast(stringLiterals);
	}

	public static final <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> ofClass(final Class<? extends F> type)
	{
		return NodeClassFilter.of(type);
	}

	public static final <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> ofClass(final Class<? extends F> a, final Class<? extends F> b)
	{
		return NodeClassFilter.of(a, b);
	}

	public static final <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> ofClass(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c)
	{
		return NodeClassFilter.of(a, b, c);
	}

	public static final <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> ofClass(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d)
	{
		return NodeClassFilter.of(a, b, c, d); 
	}

	@SafeVarargs
	public static final <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> ofClass(final Class<? extends F> a, final Class<? extends F> b, final Class<? extends F> c, final Class<? extends F> d, final Class<? extends F> ... rest)
	{
		return NodeClassFilter.of(a, b, c, d, rest);
	}

	public static final <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> ofClass(final Iterable<Class<? extends F>> types)
	{
		return NodeClassFilter.of(types); 
	}

	public static final <N extends ASTNode, F extends ASTNode> NodeClassFilter<N, F> ofClass(final Iterator<Class<? extends F>> types)
	{
		return NodeClassFilter.of(types);
	}
}
