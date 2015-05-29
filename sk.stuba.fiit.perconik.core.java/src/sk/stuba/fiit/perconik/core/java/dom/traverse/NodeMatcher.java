package sk.stuba.fiit.perconik.core.java.dom.traverse;

import java.util.EnumSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTMatcher;
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

import sk.stuba.fiit.perconik.utilities.MoreLists;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.core.java.dom.traverse.NodeVisitOption.INCLUDE_JAVADOC_TAGS;

public class NodeMatcher {
  final Internals internals;

  public NodeMatcher(final NodeVisitOption ... options) {
    Set<NodeVisitOption> set = EnumSet.copyOf(asList(options));

    this.internals = new Internals(this, set.contains(INCLUDE_JAVADOC_TAGS));
  }

  public final ASTMatcher asUnderlyingMatcher() {
    return this.internals;
  }

  public final boolean subtreeMatch(final ASTNode node, final Object object) {
    return this.internals.safeSubtreeMatch(node, object);
  }

  public final boolean subtreeMatch(final Iterable<ASTNode> nodes, final Iterable<?> objects) {
    return this.internals.safeSubtreeListMatch(MoreLists.toArrayList(nodes), MoreLists.toArrayList(objects));
  }

  @SuppressWarnings("unused")
  public void preMatch(final AnnotationTypeDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final AnnotationTypeMemberDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final AnonymousClassDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ArrayAccess node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ArrayCreation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ArrayInitializer node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ArrayType node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final AssertStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final Assignment node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final Block node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final BlockComment node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final BooleanLiteral node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final BreakStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final CastExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final CatchClause node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final CharacterLiteral node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ClassInstanceCreation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final CompilationUnit node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ConditionalExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ConstructorInvocation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ContinueStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final UnionType node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final DoStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final EmptyStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final EnhancedForStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final EnumConstantDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final EnumDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ExpressionStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final FieldAccess node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final FieldDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ForStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final IfStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ImportDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final InfixExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final InstanceofExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final Initializer node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final Javadoc node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final LabeledStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final LineComment node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final MarkerAnnotation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final MemberRef node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final MemberValuePair node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final MethodRef node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final MethodRefParameter node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final MethodDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final MethodInvocation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final Modifier node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final NormalAnnotation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final NullLiteral node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final NumberLiteral node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final PackageDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ParameterizedType node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ParenthesizedExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final PostfixExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final PrefixExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final PrimitiveType node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final QualifiedName node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final QualifiedType node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ReturnStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SimpleName node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SimpleType node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SingleMemberAnnotation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SingleVariableDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final StringLiteral node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SuperConstructorInvocation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SuperFieldAccess node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SuperMethodInvocation node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SwitchCase node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SwitchStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final SynchronizedStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final TagElement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final TextElement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ThisExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final ThrowStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final TryStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final TypeDeclaration node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final TypeDeclarationStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final TypeLiteral node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final TypeParameter node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final VariableDeclarationExpression node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final VariableDeclarationFragment node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final VariableDeclarationStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final WhileStatement node, final Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(final WildcardType node, final Object other) {}

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final AnnotationTypeDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final AnnotationTypeMemberDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final AnonymousClassDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ArrayAccess node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ArrayCreation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ArrayInitializer node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ArrayType node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final AssertStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final Assignment node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final Block node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final BlockComment node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final BooleanLiteral node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final BreakStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final CastExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final CatchClause node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final CharacterLiteral node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ClassInstanceCreation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final CompilationUnit node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ConditionalExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ConstructorInvocation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ContinueStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final UnionType node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final DoStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final EmptyStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final EnhancedForStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final EnumConstantDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final EnumDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ExpressionStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final FieldAccess node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final FieldDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ForStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final IfStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ImportDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final InfixExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final InstanceofExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final Initializer node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final Javadoc node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final LabeledStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final LineComment node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final MarkerAnnotation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final MemberRef node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final MemberValuePair node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final MethodRef node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final MethodRefParameter node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final MethodDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final MethodInvocation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final Modifier node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final NormalAnnotation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final NullLiteral node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final NumberLiteral node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final PackageDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ParameterizedType node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ParenthesizedExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final PostfixExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final PrefixExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final PrimitiveType node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final QualifiedName node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final QualifiedType node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ReturnStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SimpleName node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SimpleType node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SingleMemberAnnotation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SingleVariableDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final StringLiteral node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SuperConstructorInvocation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SuperFieldAccess node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SuperMethodInvocation node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SwitchCase node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SwitchStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final SynchronizedStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final TagElement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final TextElement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ThisExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final ThrowStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final TryStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final TypeDeclaration node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final TypeDeclarationStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final TypeLiteral node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final TypeParameter node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final VariableDeclarationExpression node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final VariableDeclarationFragment node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final VariableDeclarationStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final WhileStatement node, final Object other, final boolean result) {
    return result;
  }

  @SuppressWarnings({ "static-method", "unused" })
  public boolean postMatch(final WildcardType node, final Object other, final boolean result) {
    return result;
  }

  public boolean match(final AnnotationTypeDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final AnnotationTypeMemberDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final AnonymousClassDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ArrayAccess node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ArrayCreation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ArrayInitializer node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ArrayType node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final AssertStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final Assignment node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final Block node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final BlockComment node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final BooleanLiteral node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final BreakStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final CastExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final CatchClause node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final CharacterLiteral node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ClassInstanceCreation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final CompilationUnit node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ConditionalExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ConstructorInvocation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ContinueStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final UnionType node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final DoStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final EmptyStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final EnhancedForStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final EnumConstantDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final EnumDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ExpressionStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final FieldAccess node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final FieldDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ForStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final IfStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ImportDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final InfixExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final InstanceofExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final Initializer node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final Javadoc node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final LabeledStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final LineComment node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final MarkerAnnotation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final MemberRef node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final MemberValuePair node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final MethodRef node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final MethodRefParameter node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final MethodDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final MethodInvocation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final Modifier node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final NormalAnnotation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final NullLiteral node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final NumberLiteral node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final PackageDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ParameterizedType node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ParenthesizedExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final PostfixExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final PrefixExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final PrimitiveType node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final QualifiedName node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final QualifiedType node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ReturnStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SimpleName node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SimpleType node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SingleMemberAnnotation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SingleVariableDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final StringLiteral node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SuperConstructorInvocation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SuperFieldAccess node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SuperMethodInvocation node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SwitchCase node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SwitchStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final SynchronizedStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final TagElement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final TextElement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ThisExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final ThrowStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final TryStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final TypeDeclaration node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final TypeDeclarationStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final TypeLiteral node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final TypeParameter node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final VariableDeclarationExpression node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final VariableDeclarationFragment node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final VariableDeclarationStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final WhileStatement node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(final WildcardType node, final Object other) {
    return this.internals.standardMatch(node, other);
  }

  static final class Internals extends ASTMatcher {
    final NodeMatcher matcher;

    Internals(final NodeMatcher matcher, final boolean includeJavadocTags) {
      super(includeJavadocTags);

      this.matcher = matcher;
    }

    @Override
    public boolean match(final AnnotationTypeDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final AnnotationTypeMemberDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final AnonymousClassDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ArrayAccess node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ArrayCreation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ArrayInitializer node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ArrayType node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final AssertStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final Assignment node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final Block node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final BlockComment node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final BooleanLiteral node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final BreakStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final CastExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final CatchClause node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final CharacterLiteral node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ClassInstanceCreation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final CompilationUnit node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ConditionalExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ConstructorInvocation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ContinueStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final UnionType node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final DoStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final EmptyStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final EnhancedForStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final EnumConstantDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final EnumDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ExpressionStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final FieldAccess node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final FieldDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ForStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final IfStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ImportDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final InfixExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final InstanceofExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final Initializer node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final Javadoc node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final LabeledStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final LineComment node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final MarkerAnnotation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final MemberRef node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final MemberValuePair node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final MethodRef node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final MethodRefParameter node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final MethodDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final MethodInvocation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final Modifier node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final NormalAnnotation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final NullLiteral node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final NumberLiteral node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final PackageDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ParameterizedType node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ParenthesizedExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final PostfixExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final PrefixExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final PrimitiveType node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final QualifiedName node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final QualifiedType node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ReturnStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SimpleName node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SimpleType node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SingleMemberAnnotation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SingleVariableDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final StringLiteral node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SuperConstructorInvocation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SuperFieldAccess node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SuperMethodInvocation node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SwitchCase node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SwitchStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final SynchronizedStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final TagElement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final TextElement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ThisExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final ThrowStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final TryStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final TypeDeclaration node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final TypeDeclarationStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final TypeLiteral node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final TypeParameter node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final VariableDeclarationExpression node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final VariableDeclarationFragment node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final VariableDeclarationStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final WhileStatement node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public boolean match(final WildcardType node, final Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    boolean standardMatch(final AnnotationTypeDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final AnnotationTypeMemberDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final AnonymousClassDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ArrayAccess node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ArrayCreation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ArrayInitializer node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ArrayType node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final AssertStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final Assignment node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final Block node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final BlockComment node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final BooleanLiteral node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final BreakStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final CastExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final CatchClause node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final CharacterLiteral node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ClassInstanceCreation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final CompilationUnit node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ConditionalExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ConstructorInvocation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ContinueStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final UnionType node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final DoStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final EmptyStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final EnhancedForStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final EnumConstantDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final EnumDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ExpressionStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final FieldAccess node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final FieldDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ForStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final IfStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ImportDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final InfixExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final InstanceofExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final Initializer node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final Javadoc node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final LabeledStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final LineComment node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final MarkerAnnotation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final MemberRef node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final MemberValuePair node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final MethodRef node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final MethodRefParameter node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final MethodDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final MethodInvocation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final Modifier node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final NormalAnnotation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final NullLiteral node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final NumberLiteral node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final PackageDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ParameterizedType node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ParenthesizedExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final PostfixExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final PrefixExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final PrimitiveType node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final QualifiedName node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final QualifiedType node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ReturnStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SimpleName node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SimpleType node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SingleMemberAnnotation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SingleVariableDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final StringLiteral node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SuperConstructorInvocation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SuperFieldAccess node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SuperMethodInvocation node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SwitchCase node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SwitchStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final SynchronizedStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final TagElement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final TextElement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ThisExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final ThrowStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final TryStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final TypeDeclaration node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final TypeDeclarationStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final TypeLiteral node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final TypeParameter node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final VariableDeclarationExpression node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final VariableDeclarationFragment node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final VariableDeclarationStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final WhileStatement node, final Object other) {
      return super.match(node, other);
    }

    boolean standardMatch(final WildcardType node, final Object other) {
      return super.match(node, other);
    }
  }
}
