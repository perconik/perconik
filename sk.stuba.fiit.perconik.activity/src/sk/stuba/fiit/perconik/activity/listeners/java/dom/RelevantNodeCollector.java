package sk.stuba.fiit.perconik.activity.listeners.java.dom;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import sk.stuba.fiit.perconik.utilities.function.Collector;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newLinkedHashSet;

final class RelevantNodeCollector implements Collector<CompilationUnit, ASTNode> {
  public RelevantNodeCollector() {}

  public final List<ASTNode> apply(@Nullable final CompilationUnit unit) {
    return new Processor().apply(unit);
  }

  private static final class Processor extends ASTVisitor implements Collector<CompilationUnit, ASTNode> {
    private final Set<ASTNode> result;

    Processor() {
      this.result = newLinkedHashSet();
    }

    public List<ASTNode> apply(@Nullable final CompilationUnit unit) {
      if (unit == null) {
        return null;
      }

      unit.accept(this);

      return newArrayList(this.result);
    }

    private void addNode(final ASTNode node) {
      if (node != null) {
        this.result.add(node);
      }
    }

    private void addNodes(final Collection<? extends ASTNode> nodes) {
      this.result.addAll(nodes);
    }

    // compilation unit

    @Override
    public boolean visit(final CompilationUnit node) {
      return true;
    }

    @Override
    public boolean visit(final PackageDeclaration node) {
      this.addNode(node);

      return true;
    }

    @Override
    public boolean visit(final ImportDeclaration node) {
      this.addNode(node);

      return true;
    }

    // abstract type declarations

    @Override
    public boolean visit(final AnnotationTypeDeclaration node) {
      this.addNode(node.getJavadoc());
      this.addNodes(node.modifiers());
      this.addNode(node.getName());

      return true;
    }

    @Override
    public boolean visit(final EnumDeclaration node) {
      this.addNode(node.getJavadoc());
      this.addNodes(node.modifiers());
      this.addNode(node.getName());
      this.addNodes(node.superInterfaceTypes());

      return true;
    }

    @Override
    public boolean visit(final TypeDeclaration node) {
      this.addNode(node.getJavadoc());
      this.addNodes(node.modifiers());
      this.addNode(node.getName());
      this.addNodes(node.typeParameters());
      this.addNode(node.getSuperclassType());
      this.addNodes(node.superInterfaceTypes());

      return true;
    }

    // abstract type body declarations

    @Override
    public boolean visit(final AnnotationTypeMemberDeclaration node) {
      this.addNode(node);

      return true;
    }

    @Override
    public boolean visit(final EnumConstantDeclaration node) {
      this.addNode(node);

      return true;
    }

    @Override
    public boolean visit(final FieldDeclaration node) {
      this.addNode(node);

      return true;
    }

    @Override
    public boolean visit(final Initializer node) {
      this.addNode(node);

      return true;
    }

    @Override
    public boolean visit(final MethodDeclaration node) {
      this.addNode(node);

      return true;
    }
  }
}
