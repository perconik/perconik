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
import sk.stuba.fiit.perconik.core.java.dom.AstVisitOption;

public abstract class GenericAstVisitor extends AstVisitor
{
	protected GenericAstVisitor(final AstVisitOption ... options)
	{
		super(options);
	}
	
	@SuppressWarnings({"unused", "static-method"})
	protected boolean genericVisit(ASTNode node)
	{
		return true;
	}
	
	@SuppressWarnings("unused")
	protected void genericEndVisit(ASTNode node)
	{
	}

	@Override
	public boolean visit(AnnotationTypeDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(AnnotationTypeMemberDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ArrayAccess node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ArrayCreation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ArrayInitializer node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ArrayType node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(AssertStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(Assignment node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(Block node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(BlockComment node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(BooleanLiteral node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(BreakStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(CastExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(CatchClause node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(CharacterLiteral node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(CompilationUnit node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ConditionalExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ConstructorInvocation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ContinueStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(DoStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(EmptyStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(EnhancedForStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(EnumConstantDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(EnumDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ExpressionStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(FieldAccess node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ForStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(IfStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ImportDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(InfixExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(InstanceofExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(Initializer node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(Javadoc node)
	{
		if (this.internals.standardVisit(node))
		{
			return this.genericVisit(node);
		}
		
		return false;
	}

	@Override
	public boolean visit(LabeledStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(LineComment node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(MarkerAnnotation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(MemberRef node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(MemberValuePair node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(MethodInvocation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(MethodRef node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(MethodRefParameter node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(Modifier node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(NormalAnnotation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(NullLiteral node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(NumberLiteral node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(PackageDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ParameterizedType node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ParenthesizedExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(PostfixExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(PrefixExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(PrimitiveType node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(QualifiedName node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(QualifiedType node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ReturnStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SimpleName node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SimpleType node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SingleMemberAnnotation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SingleVariableDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(StringLiteral node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SuperFieldAccess node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SwitchCase node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SwitchStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(SynchronizedStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(TagElement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(TextElement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ThisExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(ThrowStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(TryStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(TypeDeclaration node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(TypeDeclarationStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(TypeLiteral node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(TypeParameter node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(UnionType node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(VariableDeclarationExpression node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(VariableDeclarationStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(VariableDeclarationFragment node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(WhileStatement node)
	{
		return this.genericVisit(node);
	}

	@Override
	public boolean visit(WildcardType node)
	{
		return this.genericVisit(node);
	}

	@Override
	public void endVisit(AnnotationTypeDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(AnnotationTypeMemberDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(AnonymousClassDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ArrayAccess node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ArrayCreation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ArrayInitializer node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ArrayType node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(AssertStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(Assignment node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(Block node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(BlockComment node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(BooleanLiteral node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(BreakStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(CastExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(CatchClause node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(CharacterLiteral node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ClassInstanceCreation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(CompilationUnit node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ConditionalExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ConstructorInvocation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ContinueStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(DoStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(EmptyStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(EnhancedForStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(EnumConstantDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(EnumDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ExpressionStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(FieldAccess node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(FieldDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ForStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(IfStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ImportDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(InfixExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(InstanceofExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(Initializer node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(Javadoc node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(LabeledStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(LineComment node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(MarkerAnnotation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(MemberRef node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(MemberValuePair node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(MethodDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(MethodInvocation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(MethodRef node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(MethodRefParameter node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(Modifier node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(NormalAnnotation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(NullLiteral node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(NumberLiteral node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(PackageDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ParameterizedType node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ParenthesizedExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(PostfixExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(PrefixExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(PrimitiveType node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(QualifiedName node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(QualifiedType node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ReturnStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SimpleName node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SimpleType node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SingleMemberAnnotation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SingleVariableDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(StringLiteral node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SuperConstructorInvocation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SuperFieldAccess node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SuperMethodInvocation node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SwitchCase node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SwitchStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(SynchronizedStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(TagElement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(TextElement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ThisExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(ThrowStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(TryStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(TypeDeclaration node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(TypeDeclarationStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(TypeLiteral node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(TypeParameter node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(UnionType node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationExpression node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationFragment node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(WhileStatement node)
	{
		this.genericEndVisit(node);
	}

	@Override
	public void endVisit(WildcardType node)
	{
		this.genericEndVisit(node);
	}
}
