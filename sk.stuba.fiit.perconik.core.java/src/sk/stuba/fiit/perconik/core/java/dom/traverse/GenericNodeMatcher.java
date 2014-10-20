package sk.stuba.fiit.perconik.core.java.dom.traverse;

import org.eclipse.jdt.core.dom.ASTNode;
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

public class GenericNodeMatcher extends NodeMatcher {
  public GenericNodeMatcher(final NodeVisitOption ... options) {
    super(options);
  }

  @SuppressWarnings("unused")
  protected void genericPreMatch(final ASTNode node, final Object other) {}

  @SuppressWarnings({"static-method", "unused"})
  protected boolean genericPostMatch(final ASTNode node, final Object other, final boolean result) {
    return result;
  }

  @Override
  public void preMatch(final AnnotationTypeDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final AnnotationTypeMemberDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final AnonymousClassDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ArrayAccess node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ArrayCreation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ArrayInitializer node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ArrayType node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final AssertStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final Assignment node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final Block node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final BlockComment node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final BooleanLiteral node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final BreakStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final CastExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final CatchClause node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final CharacterLiteral node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ClassInstanceCreation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final CompilationUnit node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ConditionalExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ConstructorInvocation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ContinueStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final UnionType node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final DoStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final EmptyStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final EnhancedForStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final EnumConstantDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final EnumDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ExpressionStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final FieldAccess node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final FieldDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ForStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final IfStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ImportDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final InfixExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final InstanceofExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final Initializer node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final Javadoc node, final Object other) {
    if (this.internals.standardMatch(node, other)) {
      this.genericPreMatch(node, other);
    }
  }

  @Override
  public void preMatch(final LabeledStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final LineComment node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final MarkerAnnotation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final MemberRef node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final MemberValuePair node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final MethodRef node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final MethodRefParameter node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final MethodDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final MethodInvocation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final Modifier node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final NormalAnnotation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final NullLiteral node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final NumberLiteral node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final PackageDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ParameterizedType node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ParenthesizedExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final PostfixExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final PrefixExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final PrimitiveType node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final QualifiedName node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final QualifiedType node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ReturnStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SimpleName node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SimpleType node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SingleMemberAnnotation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SingleVariableDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final StringLiteral node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SuperConstructorInvocation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SuperFieldAccess node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SuperMethodInvocation node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SwitchCase node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SwitchStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final SynchronizedStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final TagElement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final TextElement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ThisExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final ThrowStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final TryStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final TypeDeclaration node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final TypeDeclarationStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final TypeLiteral node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final TypeParameter node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final VariableDeclarationExpression node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final VariableDeclarationFragment node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final VariableDeclarationStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final WhileStatement node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public void preMatch(final WildcardType node, final Object other) {
    this.genericPreMatch(node, other);
  }

  @Override
  public boolean postMatch(final AnnotationTypeDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final AnnotationTypeMemberDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final AnonymousClassDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ArrayAccess node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ArrayCreation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ArrayInitializer node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ArrayType node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final AssertStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final Assignment node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final Block node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final BlockComment node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final BooleanLiteral node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final BreakStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final CastExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final CatchClause node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final CharacterLiteral node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ClassInstanceCreation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final CompilationUnit node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ConditionalExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ConstructorInvocation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ContinueStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final UnionType node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final DoStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final EmptyStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final EnhancedForStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final EnumConstantDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final EnumDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ExpressionStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final FieldAccess node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final FieldDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ForStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final IfStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ImportDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final InfixExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final InstanceofExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final Initializer node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final Javadoc node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final LabeledStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final LineComment node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final MarkerAnnotation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final MemberRef node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final MemberValuePair node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final MethodRef node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final MethodRefParameter node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final MethodDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final MethodInvocation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final Modifier node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final NormalAnnotation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final NullLiteral node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final NumberLiteral node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final PackageDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ParameterizedType node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ParenthesizedExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final PostfixExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final PrefixExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final PrimitiveType node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final QualifiedName node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final QualifiedType node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ReturnStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SimpleName node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SimpleType node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SingleMemberAnnotation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SingleVariableDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final StringLiteral node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SuperConstructorInvocation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SuperFieldAccess node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SuperMethodInvocation node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SwitchCase node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SwitchStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final SynchronizedStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final TagElement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final TextElement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ThisExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final ThrowStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final TryStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final TypeDeclaration node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final TypeDeclarationStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final TypeLiteral node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final TypeParameter node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final VariableDeclarationExpression node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final VariableDeclarationFragment node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final VariableDeclarationStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final WhileStatement node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }

  @Override
  public boolean postMatch(final WildcardType node, final Object other, final boolean result) {
    return this.genericPostMatch(node, other, result);
  }
}
