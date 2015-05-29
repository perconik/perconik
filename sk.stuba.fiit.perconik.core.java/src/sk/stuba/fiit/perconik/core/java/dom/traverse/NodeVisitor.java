package sk.stuba.fiit.perconik.core.java.dom.traverse;

import java.util.EnumSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EmptyStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.UnionType;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.WildcardType;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.core.java.dom.traverse.NodeVisitOption.INCLUDE_JAVADOC_TAGS;

public abstract class NodeVisitor {
  final Internals internals;

  protected NodeVisitor(final NodeVisitOption ... options) {
    Set<NodeVisitOption> set = EnumSet.copyOf(asList(options));

    this.internals = new Internals(this, set.contains(INCLUDE_JAVADOC_TAGS));
  }

  public final ASTVisitor asUnderlyingVisitor() {
    return this.internals;
  }

  public final void subtreeVisit(final ASTNode node) {
    NodeVisitors.accept(node, this);
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean preVisit(final ASTNode node) {
    return true;
  }

  @SuppressWarnings("unused")
  public void postVisit(final ASTNode node) {}

  public boolean visit(final AnnotationTypeDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final AnnotationTypeMemberDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final AnonymousClassDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ArrayAccess node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ArrayCreation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ArrayInitializer node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ArrayType node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final AssertStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final Assignment node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final Block node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final BlockComment node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final BooleanLiteral node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final BreakStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final CastExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final CatchClause node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final CharacterLiteral node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ClassInstanceCreation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final CompilationUnit node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ConditionalExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ConstructorInvocation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ContinueStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final DoStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final EmptyStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final EnhancedForStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final EnumConstantDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final EnumDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ExpressionStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final FieldAccess node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final FieldDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ForStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final IfStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ImportDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final InfixExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final InstanceofExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final Initializer node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final Javadoc node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final LabeledStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final LineComment node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final MarkerAnnotation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final MemberRef node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final MemberValuePair node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final MethodRef node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final MethodRefParameter node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final MethodDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final MethodInvocation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final Modifier node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final NormalAnnotation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final NullLiteral node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final NumberLiteral node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final PackageDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ParameterizedType node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ParenthesizedExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final PostfixExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final PrefixExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final PrimitiveType node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final QualifiedName node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final QualifiedType node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ReturnStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SimpleName node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SimpleType node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SingleMemberAnnotation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SingleVariableDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final StringLiteral node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SuperConstructorInvocation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SuperFieldAccess node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SuperMethodInvocation node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SwitchCase node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SwitchStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final SynchronizedStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final TagElement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final TextElement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ThisExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final ThrowStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final TryStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final TypeDeclaration node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final TypeDeclarationStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final TypeLiteral node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final TypeParameter node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final UnionType node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final VariableDeclarationExpression node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final VariableDeclarationStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final VariableDeclarationFragment node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final WhileStatement node) {
    return this.internals.standardVisit(node);
  }

  public boolean visit(final WildcardType node) {
    return this.internals.standardVisit(node);
  }

  @SuppressWarnings("unused")
  public void endVisit(final AnnotationTypeDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final AnnotationTypeMemberDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final AnonymousClassDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ArrayAccess node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ArrayCreation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ArrayInitializer node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ArrayType node) {}

  @SuppressWarnings("unused")
  public void endVisit(final AssertStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final Assignment node) {}

  @SuppressWarnings("unused")
  public void endVisit(final Block node) {}

  @SuppressWarnings("unused")
  public void endVisit(final BlockComment node) {}

  @SuppressWarnings("unused")
  public void endVisit(final BooleanLiteral node) {}

  @SuppressWarnings("unused")
  public void endVisit(final BreakStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final CastExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final CatchClause node) {}

  @SuppressWarnings("unused")
  public void endVisit(final CharacterLiteral node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ClassInstanceCreation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final CompilationUnit node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ConditionalExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ConstructorInvocation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ContinueStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final DoStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final EmptyStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final EnhancedForStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final EnumConstantDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final EnumDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ExpressionStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final FieldAccess node) {}

  @SuppressWarnings("unused")
  public void endVisit(final FieldDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ForStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final IfStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ImportDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final InfixExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final InstanceofExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final Initializer node) {}

  @SuppressWarnings("unused")
  public void endVisit(final Javadoc node) {}

  @SuppressWarnings("unused")
  public void endVisit(final LabeledStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final LineComment node) {}

  @SuppressWarnings("unused")
  public void endVisit(final MarkerAnnotation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final MemberRef node) {}

  @SuppressWarnings("unused")
  public void endVisit(final MemberValuePair node) {}

  @SuppressWarnings("unused")
  public void endVisit(final MethodRef node) {}

  @SuppressWarnings("unused")
  public void endVisit(final MethodRefParameter node) {}

  @SuppressWarnings("unused")
  public void endVisit(final MethodDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final MethodInvocation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final Modifier node) {}

  @SuppressWarnings("unused")
  public void endVisit(final NormalAnnotation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final NullLiteral node) {}

  @SuppressWarnings("unused")
  public void endVisit(final NumberLiteral node) {}

  @SuppressWarnings("unused")
  public void endVisit(final PackageDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ParameterizedType node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ParenthesizedExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final PostfixExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final PrefixExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final PrimitiveType node) {}

  @SuppressWarnings("unused")
  public void endVisit(final QualifiedName node) {}

  @SuppressWarnings("unused")
  public void endVisit(final QualifiedType node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ReturnStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SimpleName node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SimpleType node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SingleMemberAnnotation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SingleVariableDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final StringLiteral node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SuperConstructorInvocation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SuperFieldAccess node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SuperMethodInvocation node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SwitchCase node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SwitchStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final SynchronizedStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final TagElement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final TextElement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ThisExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final ThrowStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final TryStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final TypeDeclaration node) {}

  @SuppressWarnings("unused")
  public void endVisit(final TypeDeclarationStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final TypeLiteral node) {}

  @SuppressWarnings("unused")
  public void endVisit(final TypeParameter node) {}

  @SuppressWarnings("unused")
  public void endVisit(final UnionType node) {}

  @SuppressWarnings("unused")
  public void endVisit(final VariableDeclarationExpression node) {}

  @SuppressWarnings("unused")
  public void endVisit(final VariableDeclarationStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final VariableDeclarationFragment node) {}

  @SuppressWarnings("unused")
  public void endVisit(final WhileStatement node) {}

  @SuppressWarnings("unused")
  public void endVisit(final WildcardType node) {}

  static final class Internals extends ASTVisitor {
    final NodeVisitor visitor;

    Internals(final NodeVisitor visitor, final boolean includeJavadocTags) {
      super(includeJavadocTags);

      this.visitor = visitor;
    }

    @Override
    public void preVisit(final ASTNode node) {
      this.visitor.preVisit(node);
    }

    @Override
    public boolean preVisit2(final ASTNode node) {
      return this.visitor.preVisit(node);
    }

    @Override
    public void postVisit(final ASTNode node) {
      this.visitor.postVisit(node);
    }

    @Override
    public boolean visit(final AnnotationTypeDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final AnnotationTypeMemberDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final AnonymousClassDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ArrayAccess node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ArrayCreation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ArrayInitializer node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ArrayType node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final AssertStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final Assignment node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final Block node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final BlockComment node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final BooleanLiteral node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final BreakStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final CastExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final CatchClause node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final CharacterLiteral node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ClassInstanceCreation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final CompilationUnit node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ConditionalExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ConstructorInvocation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ContinueStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final DoStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final EmptyStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final EnhancedForStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final EnumConstantDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final EnumDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ExpressionStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final FieldAccess node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final FieldDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ForStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final IfStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ImportDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final InfixExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final InstanceofExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final Initializer node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final Javadoc node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final LabeledStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final LineComment node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final MarkerAnnotation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final MemberRef node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final MemberValuePair node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final MethodRef node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final MethodRefParameter node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final MethodDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final MethodInvocation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final Modifier node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final NormalAnnotation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final NullLiteral node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final NumberLiteral node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final PackageDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ParameterizedType node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ParenthesizedExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final PostfixExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final PrefixExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final PrimitiveType node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final QualifiedName node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final QualifiedType node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ReturnStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SimpleName node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SimpleType node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SingleMemberAnnotation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SingleVariableDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final StringLiteral node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SuperConstructorInvocation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SuperFieldAccess node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SuperMethodInvocation node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SwitchCase node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SwitchStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final SynchronizedStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final TagElement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final TextElement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ThisExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final ThrowStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final TryStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final TypeDeclaration node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final TypeDeclarationStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final TypeLiteral node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final TypeParameter node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final UnionType node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final VariableDeclarationExpression node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final VariableDeclarationStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final VariableDeclarationFragment node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final WhileStatement node) {
      return this.visitor.visit(node);
    }

    @Override
    public boolean visit(final WildcardType node) {
      return this.visitor.visit(node);
    }

    boolean standardVisit(final AnnotationTypeDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final AnnotationTypeMemberDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final AnonymousClassDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final ArrayAccess node) {
      return super.visit(node);
    }

    boolean standardVisit(final ArrayCreation node) {
      return super.visit(node);
    }

    boolean standardVisit(final ArrayInitializer node) {
      return super.visit(node);
    }

    boolean standardVisit(final ArrayType node) {
      return super.visit(node);
    }

    boolean standardVisit(final AssertStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final Assignment node) {
      return super.visit(node);
    }

    boolean standardVisit(final Block node) {
      return super.visit(node);
    }

    boolean standardVisit(final BlockComment node) {
      return super.visit(node);
    }

    boolean standardVisit(final BooleanLiteral node) {
      return super.visit(node);
    }

    boolean standardVisit(final BreakStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final CastExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final CatchClause node) {
      return super.visit(node);
    }

    boolean standardVisit(final CharacterLiteral node) {
      return super.visit(node);
    }

    boolean standardVisit(final ClassInstanceCreation node) {
      return super.visit(node);
    }

    boolean standardVisit(final CompilationUnit node) {
      return super.visit(node);
    }

    boolean standardVisit(final ConditionalExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final ConstructorInvocation node) {
      return super.visit(node);
    }

    boolean standardVisit(final ContinueStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final DoStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final EmptyStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final EnhancedForStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final EnumConstantDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final EnumDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final ExpressionStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final FieldAccess node) {
      return super.visit(node);
    }

    boolean standardVisit(final FieldDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final ForStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final IfStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final ImportDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final InfixExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final InstanceofExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final Initializer node) {
      return super.visit(node);
    }

    boolean standardVisit(final Javadoc node) {
      return super.visit(node);
    }

    boolean standardVisit(final LabeledStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final LineComment node) {
      return super.visit(node);
    }

    boolean standardVisit(final MarkerAnnotation node) {
      return super.visit(node);
    }

    boolean standardVisit(final MemberRef node) {
      return super.visit(node);
    }

    boolean standardVisit(final MemberValuePair node) {
      return super.visit(node);
    }

    boolean standardVisit(final MethodRef node) {
      return super.visit(node);
    }

    boolean standardVisit(final MethodRefParameter node) {
      return super.visit(node);
    }

    boolean standardVisit(final MethodDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final MethodInvocation node) {
      return super.visit(node);
    }

    boolean standardVisit(final Modifier node) {
      return super.visit(node);
    }

    boolean standardVisit(final NormalAnnotation node) {
      return super.visit(node);
    }

    boolean standardVisit(final NullLiteral node) {
      return super.visit(node);
    }

    boolean standardVisit(final NumberLiteral node) {
      return super.visit(node);
    }

    boolean standardVisit(final PackageDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final ParameterizedType node) {
      return super.visit(node);
    }

    boolean standardVisit(final ParenthesizedExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final PostfixExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final PrefixExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final PrimitiveType node) {
      return super.visit(node);
    }

    boolean standardVisit(final QualifiedName node) {
      return super.visit(node);
    }

    boolean standardVisit(final QualifiedType node) {
      return super.visit(node);
    }

    boolean standardVisit(final ReturnStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final SimpleName node) {
      return super.visit(node);
    }

    boolean standardVisit(final SimpleType node) {
      return super.visit(node);
    }

    boolean standardVisit(final SingleMemberAnnotation node) {
      return super.visit(node);
    }

    boolean standardVisit(final SingleVariableDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final StringLiteral node) {
      return super.visit(node);
    }

    boolean standardVisit(final SuperConstructorInvocation node) {
      return super.visit(node);
    }

    boolean standardVisit(final SuperFieldAccess node) {
      return super.visit(node);
    }

    boolean standardVisit(final SuperMethodInvocation node) {
      return super.visit(node);
    }

    boolean standardVisit(final SwitchCase node) {
      return super.visit(node);
    }

    boolean standardVisit(final SwitchStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final SynchronizedStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final TagElement node) {
      return super.visit(node);
    }

    boolean standardVisit(final TextElement node) {
      return super.visit(node);
    }

    boolean standardVisit(final ThisExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final ThrowStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final TryStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final TypeDeclaration node) {
      return super.visit(node);
    }

    boolean standardVisit(final TypeDeclarationStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final TypeLiteral node) {
      return super.visit(node);
    }

    boolean standardVisit(final TypeParameter node) {
      return super.visit(node);
    }

    boolean standardVisit(final UnionType node) {
      return super.visit(node);
    }

    boolean standardVisit(final VariableDeclarationExpression node) {
      return super.visit(node);
    }

    boolean standardVisit(final VariableDeclarationStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final VariableDeclarationFragment node) {
      return super.visit(node);
    }

    boolean standardVisit(final WhileStatement node) {
      return super.visit(node);
    }

    boolean standardVisit(final WildcardType node) {
      return super.visit(node);
    }

    @Override
    public void endVisit(final AnnotationTypeDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final AnnotationTypeMemberDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final AnonymousClassDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ArrayAccess node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ArrayCreation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ArrayInitializer node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ArrayType node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final AssertStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final Assignment node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final Block node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final BlockComment node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final BooleanLiteral node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final BreakStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final CastExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final CatchClause node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final CharacterLiteral node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ClassInstanceCreation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final CompilationUnit node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ConditionalExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ConstructorInvocation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ContinueStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final DoStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final EmptyStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final EnhancedForStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final EnumConstantDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final EnumDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ExpressionStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final FieldAccess node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final FieldDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ForStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final IfStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ImportDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final InfixExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final InstanceofExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final Initializer node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final Javadoc node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final LabeledStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final LineComment node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final MarkerAnnotation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final MemberRef node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final MemberValuePair node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final MethodRef node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final MethodRefParameter node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final MethodDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final MethodInvocation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final Modifier node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final NormalAnnotation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final NullLiteral node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final NumberLiteral node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final PackageDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ParameterizedType node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ParenthesizedExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final PostfixExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final PrefixExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final PrimitiveType node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final QualifiedName node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final QualifiedType node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ReturnStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SimpleName node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SimpleType node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SingleMemberAnnotation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SingleVariableDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final StringLiteral node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SuperConstructorInvocation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SuperFieldAccess node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SuperMethodInvocation node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SwitchCase node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SwitchStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final SynchronizedStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final TagElement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final TextElement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ThisExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final ThrowStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final TryStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final TypeDeclaration node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final TypeDeclarationStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final TypeLiteral node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final TypeParameter node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final UnionType node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final VariableDeclarationExpression node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final VariableDeclarationStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final VariableDeclarationFragment node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final WhileStatement node) {
      this.visitor.endVisit(node);
    }

    @Override
    public void endVisit(final WildcardType node) {
      this.visitor.endVisit(node);
    }
  }
}
