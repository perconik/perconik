package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public final class CompilationUnits
{
	private CompilationUnits()
	{
		throw new AssertionError();
	}
	
	public static final CompilationUnit valueOf(@Nullable final ASTNode node)
	{
		return AstNodes.firstUpToRoot(node, CompilationUnit.class);
	}
	
	public static final IJavaElement element(@Nullable final CompilationUnit unit)
	{
		return unit != null ? unit.getJavaElement() : null; 
	}

	public static final IJavaElement element(@Nullable final ASTNode node)
	{
		return element(valueOf(node));
	}
}
