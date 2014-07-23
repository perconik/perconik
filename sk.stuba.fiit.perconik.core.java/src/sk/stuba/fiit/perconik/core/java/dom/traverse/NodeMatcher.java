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
  public void preMatch(AnnotationTypeDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(AnnotationTypeMemberDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(AnonymousClassDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ArrayAccess node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ArrayCreation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ArrayInitializer node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ArrayType node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(AssertStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(Assignment node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(Block node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(BlockComment node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(BooleanLiteral node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(BreakStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(CastExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(CatchClause node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(CharacterLiteral node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ClassInstanceCreation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(CompilationUnit node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ConditionalExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ConstructorInvocation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ContinueStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(UnionType node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(DoStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(EmptyStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(EnhancedForStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(EnumConstantDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(EnumDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ExpressionStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(FieldAccess node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(FieldDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ForStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(IfStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ImportDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(InfixExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(InstanceofExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(Initializer node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(Javadoc node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(LabeledStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(LineComment node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(MarkerAnnotation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(MemberRef node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(MemberValuePair node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(MethodRef node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(MethodRefParameter node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(MethodDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(MethodInvocation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(Modifier node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(NormalAnnotation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(NullLiteral node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(NumberLiteral node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(PackageDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ParameterizedType node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ParenthesizedExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(PostfixExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(PrefixExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(PrimitiveType node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(QualifiedName node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(QualifiedType node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ReturnStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SimpleName node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SimpleType node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SingleMemberAnnotation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SingleVariableDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(StringLiteral node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SuperConstructorInvocation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SuperFieldAccess node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SuperMethodInvocation node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SwitchCase node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SwitchStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(SynchronizedStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(TagElement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(TextElement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ThisExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(ThrowStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(TryStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(TypeDeclaration node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(TypeDeclarationStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(TypeLiteral node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(TypeParameter node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(VariableDeclarationExpression node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(VariableDeclarationFragment node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(VariableDeclarationStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(WhileStatement node, Object other) {}

  @SuppressWarnings("unused")
  public void preMatch(WildcardType node, Object other) {}

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(AnnotationTypeDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(AnnotationTypeMemberDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(AnonymousClassDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ArrayAccess node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ArrayCreation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ArrayInitializer node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ArrayType node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(AssertStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(Assignment node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(Block node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(BlockComment node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(BooleanLiteral node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(BreakStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(CastExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(CatchClause node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(CharacterLiteral node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ClassInstanceCreation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(CompilationUnit node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ConditionalExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ConstructorInvocation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ContinueStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(UnionType node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(DoStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(EmptyStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(EnhancedForStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(EnumConstantDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(EnumDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ExpressionStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(FieldAccess node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(FieldDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ForStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(IfStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ImportDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(InfixExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(InstanceofExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(Initializer node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(Javadoc node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(LabeledStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(LineComment node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(MarkerAnnotation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(MemberRef node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(MemberValuePair node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(MethodRef node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(MethodRefParameter node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(MethodDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(MethodInvocation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(Modifier node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(NormalAnnotation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(NullLiteral node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(NumberLiteral node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(PackageDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ParameterizedType node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ParenthesizedExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(PostfixExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(PrefixExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(PrimitiveType node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(QualifiedName node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(QualifiedType node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ReturnStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SimpleName node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SimpleType node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SingleMemberAnnotation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SingleVariableDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(StringLiteral node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SuperConstructorInvocation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SuperFieldAccess node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SuperMethodInvocation node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SwitchCase node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SwitchStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(SynchronizedStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(TagElement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(TextElement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ThisExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(ThrowStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(TryStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(TypeDeclaration node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(TypeDeclarationStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(TypeLiteral node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(TypeParameter node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(VariableDeclarationExpression node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(VariableDeclarationFragment node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(VariableDeclarationStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(WhileStatement node, Object other, boolean result) {
    return result;
  }

  @SuppressWarnings({"static-method", "unused"})
  public boolean postMatch(WildcardType node, Object other, boolean result) {
    return result;
  }

  public boolean match(AnnotationTypeDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(AnnotationTypeMemberDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(AnonymousClassDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ArrayAccess node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ArrayCreation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ArrayInitializer node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ArrayType node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(AssertStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(Assignment node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(Block node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(BlockComment node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(BooleanLiteral node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(BreakStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(CastExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(CatchClause node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(CharacterLiteral node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ClassInstanceCreation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(CompilationUnit node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ConditionalExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ConstructorInvocation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ContinueStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(UnionType node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(DoStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(EmptyStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(EnhancedForStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(EnumConstantDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(EnumDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ExpressionStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(FieldAccess node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(FieldDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ForStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(IfStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ImportDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(InfixExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(InstanceofExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(Initializer node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(Javadoc node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(LabeledStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(LineComment node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(MarkerAnnotation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(MemberRef node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(MemberValuePair node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(MethodRef node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(MethodRefParameter node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(MethodDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(MethodInvocation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(Modifier node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(NormalAnnotation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(NullLiteral node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(NumberLiteral node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(PackageDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ParameterizedType node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ParenthesizedExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(PostfixExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(PrefixExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(PrimitiveType node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(QualifiedName node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(QualifiedType node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ReturnStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SimpleName node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SimpleType node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SingleMemberAnnotation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SingleVariableDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(StringLiteral node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SuperConstructorInvocation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SuperFieldAccess node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SuperMethodInvocation node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SwitchCase node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SwitchStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(SynchronizedStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(TagElement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(TextElement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ThisExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(ThrowStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(TryStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(TypeDeclaration node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(TypeDeclarationStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(TypeLiteral node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(TypeParameter node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(VariableDeclarationExpression node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(VariableDeclarationFragment node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(VariableDeclarationStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(WhileStatement node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  public boolean match(WildcardType node, Object other) {
    return this.internals.standardMatch(node, other);
  }

  static final class Internals extends ASTMatcher {
    final NodeMatcher matcher;

    Internals(final NodeMatcher matcher, final boolean includeJavadocTags) {
      super(includeJavadocTags);

      this.matcher = matcher;
    }

    @Override
    public final boolean match(AnnotationTypeDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(AnnotationTypeMemberDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(AnonymousClassDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ArrayAccess node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ArrayCreation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ArrayInitializer node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ArrayType node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(AssertStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(Assignment node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(Block node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(BlockComment node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(BooleanLiteral node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(BreakStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(CastExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(CatchClause node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(CharacterLiteral node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ClassInstanceCreation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(CompilationUnit node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ConditionalExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ConstructorInvocation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ContinueStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(UnionType node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(DoStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(EmptyStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(EnhancedForStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(EnumConstantDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(EnumDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ExpressionStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(FieldAccess node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(FieldDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ForStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(IfStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ImportDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(InfixExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(InstanceofExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(Initializer node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(Javadoc node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(LabeledStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(LineComment node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(MarkerAnnotation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(MemberRef node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(MemberValuePair node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(MethodRef node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(MethodRefParameter node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(MethodDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(MethodInvocation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(Modifier node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(NormalAnnotation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(NullLiteral node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(NumberLiteral node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(PackageDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ParameterizedType node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ParenthesizedExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(PostfixExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(PrefixExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(PrimitiveType node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(QualifiedName node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(QualifiedType node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ReturnStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SimpleName node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SimpleType node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SingleMemberAnnotation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SingleVariableDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(StringLiteral node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SuperConstructorInvocation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SuperFieldAccess node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SuperMethodInvocation node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SwitchCase node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SwitchStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(SynchronizedStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(TagElement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(TextElement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ThisExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(ThrowStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(TryStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(TypeDeclaration node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(TypeDeclarationStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(TypeLiteral node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(TypeParameter node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(VariableDeclarationExpression node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(VariableDeclarationFragment node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(VariableDeclarationStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(WhileStatement node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    @Override
    public final boolean match(WildcardType node, Object object) {
      this.matcher.preMatch(node, object);

      return this.matcher.postMatch(node, object, this.matcher.match(node, object));
    }

    final boolean standardMatch(AnnotationTypeDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(AnnotationTypeMemberDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(AnonymousClassDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ArrayAccess node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ArrayCreation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ArrayInitializer node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ArrayType node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(AssertStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(Assignment node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(Block node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(BlockComment node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(BooleanLiteral node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(BreakStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(CastExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(CatchClause node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(CharacterLiteral node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ClassInstanceCreation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(CompilationUnit node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ConditionalExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ConstructorInvocation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ContinueStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(UnionType node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(DoStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(EmptyStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(EnhancedForStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(EnumConstantDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(EnumDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ExpressionStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(FieldAccess node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(FieldDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ForStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(IfStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ImportDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(InfixExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(InstanceofExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(Initializer node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(Javadoc node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(LabeledStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(LineComment node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(MarkerAnnotation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(MemberRef node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(MemberValuePair node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(MethodRef node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(MethodRefParameter node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(MethodDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(MethodInvocation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(Modifier node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(NormalAnnotation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(NullLiteral node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(NumberLiteral node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(PackageDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ParameterizedType node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ParenthesizedExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(PostfixExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(PrefixExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(PrimitiveType node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(QualifiedName node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(QualifiedType node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ReturnStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SimpleName node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SimpleType node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SingleMemberAnnotation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SingleVariableDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(StringLiteral node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SuperConstructorInvocation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SuperFieldAccess node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SuperMethodInvocation node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SwitchCase node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SwitchStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(SynchronizedStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(TagElement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(TextElement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ThisExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(ThrowStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(TryStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(TypeDeclaration node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(TypeDeclarationStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(TypeLiteral node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(TypeParameter node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(VariableDeclarationExpression node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(VariableDeclarationFragment node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(VariableDeclarationStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(WhileStatement node, Object other) {
      return super.match(node, other);
    }

    final boolean standardMatch(WildcardType node, Object other) {
      return super.match(node, other);
    }
  }
}
