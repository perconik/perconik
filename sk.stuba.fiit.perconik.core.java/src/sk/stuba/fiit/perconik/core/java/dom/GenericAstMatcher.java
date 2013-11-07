package sk.stuba.fiit.perconik.core.java.dom;

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

public class GenericAstMatcher extends AstMatcher
{
	public GenericAstMatcher(AstVisitOption ... options)
	{
		super(options);
	}
	
	@SuppressWarnings("unused")
	public void genericPreMatch(ASTNode node, Object other)
	{
	}
	
	@SuppressWarnings({"static-method", "unused"})
	public boolean genericPostMatch(ASTNode node, Object other, boolean result)
	{
		return result;
	}
	
	@Override
	public void preMatch(AnnotationTypeDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(AnnotationTypeMemberDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(AnonymousClassDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ArrayAccess node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ArrayCreation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ArrayInitializer node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ArrayType node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(AssertStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(Assignment node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(Block node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(BlockComment node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(BooleanLiteral node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(BreakStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(CastExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(CatchClause node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(CharacterLiteral node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ClassInstanceCreation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(CompilationUnit node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ConditionalExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ConstructorInvocation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ContinueStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(UnionType node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(DoStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(EmptyStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(EnhancedForStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(EnumConstantDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(EnumDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ExpressionStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(FieldAccess node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(FieldDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ForStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(IfStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ImportDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(InfixExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(InstanceofExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(Initializer node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(Javadoc node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(LabeledStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(LineComment node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(MarkerAnnotation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(MemberRef node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(MemberValuePair node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(MethodRef node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(MethodRefParameter node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(MethodDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(MethodInvocation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(Modifier node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(NormalAnnotation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(NullLiteral node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(NumberLiteral node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(PackageDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ParameterizedType node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ParenthesizedExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(PostfixExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(PrefixExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(PrimitiveType node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(QualifiedName node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(QualifiedType node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ReturnStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SimpleName node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SimpleType node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SingleMemberAnnotation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SingleVariableDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(StringLiteral node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SuperConstructorInvocation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SuperFieldAccess node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SuperMethodInvocation node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SwitchCase node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SwitchStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(SynchronizedStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(TagElement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(TextElement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ThisExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(ThrowStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(TryStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(TypeDeclaration node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(TypeDeclarationStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(TypeLiteral node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(TypeParameter node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(VariableDeclarationExpression node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(VariableDeclarationFragment node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(VariableDeclarationStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(WhileStatement node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public void preMatch(WildcardType node, Object other)
	{

		this.genericPreMatch(node, other);
	}

	@Override
	public boolean postMatch(AnnotationTypeDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(AnnotationTypeMemberDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(AnonymousClassDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ArrayAccess node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ArrayCreation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ArrayInitializer node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ArrayType node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(AssertStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(Assignment node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(Block node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(BlockComment node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(BooleanLiteral node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(BreakStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(CastExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(CatchClause node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(CharacterLiteral node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ClassInstanceCreation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(CompilationUnit node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ConditionalExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ConstructorInvocation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ContinueStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(UnionType node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(DoStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(EmptyStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(EnhancedForStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(EnumConstantDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(EnumDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ExpressionStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(FieldAccess node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(FieldDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ForStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(IfStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ImportDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(InfixExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(InstanceofExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(Initializer node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(Javadoc node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(LabeledStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(LineComment node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(MarkerAnnotation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(MemberRef node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(MemberValuePair node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(MethodRef node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(MethodRefParameter node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(MethodDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(MethodInvocation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(Modifier node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(NormalAnnotation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(NullLiteral node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(NumberLiteral node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(PackageDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ParameterizedType node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ParenthesizedExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(PostfixExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(PrefixExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(PrimitiveType node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(QualifiedName node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(QualifiedType node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ReturnStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SimpleName node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SimpleType node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SingleMemberAnnotation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SingleVariableDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(StringLiteral node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SuperConstructorInvocation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SuperFieldAccess node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SuperMethodInvocation node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SwitchCase node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SwitchStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(SynchronizedStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(TagElement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(TextElement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ThisExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(ThrowStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(TryStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(TypeDeclaration node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(TypeDeclarationStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(TypeLiteral node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(TypeParameter node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(VariableDeclarationExpression node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(VariableDeclarationFragment node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(VariableDeclarationStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(WhileStatement node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}

	@Override
	public boolean postMatch(WildcardType node, Object other, boolean result)
	{

		return this.genericPostMatch(node, other, result);
	}
}
