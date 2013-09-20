package sk.stuba.fiit.perconik.core.dom;

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

// TODO refactor:
// override all methods as final, use custom named methods
// add R and P as in javax.lang.model.element.ElementVisitor

public abstract class AstVisitor extends ASTVisitor
{
	protected AstVisitor()
	{
	}
	
	// TODO resolve javadoc tags
	public static enum Flag
	{
		JAVADOC_TAGS;
	}
	
	public abstract boolean pre(ASTNode node);
	
	public abstract void post(ASTNode node);
	
	@Override
	public final void preVisit(final ASTNode node)
	{
		this.pre(node);
	}

	@Override
	public final boolean preVisit2(final ASTNode node)
	{
		return this.pre(node);
	}

	@Override
	public final void postVisit(final ASTNode node)
	{
		this.post(node);
	}

	@Override
	public boolean visit(AnnotationTypeDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(AnnotationTypeMemberDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ArrayAccess node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ArrayCreation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ArrayInitializer node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ArrayType node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(AssertStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(Assignment node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(Block node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(BlockComment node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(BooleanLiteral node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(BreakStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(CastExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(CatchClause node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(CharacterLiteral node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(CompilationUnit node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ConditionalExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ConstructorInvocation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ContinueStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(DoStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(EmptyStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(EnhancedForStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(EnumConstantDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(EnumDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ExpressionStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldAccess node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ForStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(IfStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ImportDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(InfixExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(InstanceofExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(Initializer node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(Javadoc node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(LabeledStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(LineComment node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(MarkerAnnotation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(MemberRef node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(MemberValuePair node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodRef node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodRefParameter node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodInvocation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(Modifier node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(NormalAnnotation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(NullLiteral node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(NumberLiteral node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(PackageDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ParameterizedType node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ParenthesizedExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(PostfixExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(PrefixExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(PrimitiveType node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(QualifiedName node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(QualifiedType node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ReturnStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SimpleName node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SimpleType node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SingleMemberAnnotation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SingleVariableDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(StringLiteral node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperFieldAccess node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchCase node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(SynchronizedStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(TagElement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(TextElement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ThisExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(ThrowStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(TryStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(TypeDeclaration node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(TypeDeclarationStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(TypeLiteral node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(TypeParameter node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(UnionType node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationExpression node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationFragment node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(WhileStatement node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(WildcardType node)
	{
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public void endVisit(AnnotationTypeDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(AnnotationTypeMemberDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(AnonymousClassDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ArrayAccess node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ArrayCreation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ArrayInitializer node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ArrayType node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(AssertStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(Assignment node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(Block node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(BlockComment node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(BooleanLiteral node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(BreakStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(CastExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(CatchClause node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(CharacterLiteral node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ClassInstanceCreation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(CompilationUnit node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ConditionalExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ConstructorInvocation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ContinueStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(DoStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(EmptyStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(EnhancedForStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(EnumConstantDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(EnumDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ExpressionStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(FieldAccess node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(FieldDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ForStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(IfStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ImportDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(InfixExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(InstanceofExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(Initializer node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(Javadoc node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(LabeledStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(LineComment node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(MarkerAnnotation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(MemberRef node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(MemberValuePair node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(MethodRef node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(MethodRefParameter node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(MethodDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(MethodInvocation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(Modifier node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(NormalAnnotation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(NullLiteral node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(NumberLiteral node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(PackageDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ParameterizedType node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ParenthesizedExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(PostfixExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(PrefixExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(PrimitiveType node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(QualifiedName node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(QualifiedType node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ReturnStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SimpleName node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SimpleType node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SingleMemberAnnotation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SingleVariableDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(StringLiteral node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SuperConstructorInvocation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SuperFieldAccess node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SuperMethodInvocation node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SwitchCase node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SwitchStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(SynchronizedStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(TagElement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(TextElement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ThisExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(ThrowStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(TryStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(TypeDeclaration node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(TypeDeclarationStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(TypeLiteral node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(TypeParameter node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(UnionType node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationExpression node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationFragment node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(WhileStatement node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}

	@Override
	public void endVisit(WildcardType node)
	{
		// TODO Auto-generated method stub
		super.endVisit(node);
	}
}
