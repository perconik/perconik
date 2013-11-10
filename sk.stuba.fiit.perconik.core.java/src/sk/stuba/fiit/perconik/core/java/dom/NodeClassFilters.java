package sk.stuba.fiit.perconik.core.java.dom;

import static sk.stuba.fiit.perconik.core.java.dom.NodeClassFilter.of;
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
	private static final NodeClassFilter<?, Annotation> annotations = of(Annotation.class);
	
	private static final NodeClassFilter<?, Comment> comments = of(Comment.class);

	private static final NodeClassFilter<?, Expression> expressions = of(Expression.class);

	private static final NodeClassFilter<?, Name> names = of(Name.class);

	private static final NodeClassFilter<?, QualifiedName> qualifiedNames = of(QualifiedName.class);

	private static final NodeClassFilter<?, SimpleName> simpleNames = of(SimpleName.class);
	
	private static final NodeClassFilter<?, Statement> statements = of(Statement.class);

	private static final NodeClassFilter<?, StringLiteral> stringLiterals = of(StringLiteral.class);
	
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
}
