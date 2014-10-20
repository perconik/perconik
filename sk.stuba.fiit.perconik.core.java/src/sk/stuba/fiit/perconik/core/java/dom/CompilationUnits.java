package sk.stuba.fiit.perconik.core.java.dom;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;

public final class CompilationUnits {
  private CompilationUnits() {}

  public static CompilationUnit valueOf(@Nullable final ASTNode node) {
    return (CompilationUnit) Nodes.firstDownToRoot(node, NodeFilters.isMatching(NodeType.COMPILATION_UNIT));
  }

  public static IJavaElement element(@Nullable final CompilationUnit unit) {
    return unit != null ? unit.getJavaElement() : null;
  }

  public static IJavaElement element(@Nullable final ASTNode node) {
    return element(valueOf(node));
  }
}
