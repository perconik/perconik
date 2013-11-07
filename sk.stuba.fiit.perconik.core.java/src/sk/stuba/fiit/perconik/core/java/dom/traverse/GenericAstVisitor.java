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
import com.google.common.annotations.Beta;

@Beta
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
		return genericVisit(node);
	}

	@Override
	public boolean visit(AnnotationTypeMemberDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ArrayAccess node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ArrayCreation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ArrayInitializer node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ArrayType node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(AssertStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(Assignment node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(Block node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(BlockComment node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(BooleanLiteral node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(BreakStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(CastExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(CatchClause node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(CharacterLiteral node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(CompilationUnit node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ConditionalExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ConstructorInvocation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ContinueStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(DoStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(EmptyStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(EnhancedForStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(EnumConstantDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(EnumDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ExpressionStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(FieldAccess node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ForStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(IfStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ImportDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(InfixExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(InstanceofExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(Initializer node)
	{
		return genericVisit(node);
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
		return genericVisit(node);
	}

	@Override
	public boolean visit(LineComment node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(MarkerAnnotation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(MemberRef node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(MemberValuePair node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(MethodInvocation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(MethodRef node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(MethodRefParameter node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(Modifier node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(NormalAnnotation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(NullLiteral node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(NumberLiteral node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(PackageDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ParameterizedType node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ParenthesizedExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(PostfixExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(PrefixExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(PrimitiveType node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(QualifiedName node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(QualifiedType node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ReturnStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SimpleName node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SimpleType node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SingleMemberAnnotation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SingleVariableDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(StringLiteral node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SuperFieldAccess node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SwitchCase node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SwitchStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(SynchronizedStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(TagElement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(TextElement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ThisExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(ThrowStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(TryStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(TypeDeclaration node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(TypeDeclarationStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(TypeLiteral node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(TypeParameter node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(UnionType node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(VariableDeclarationExpression node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(VariableDeclarationStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(VariableDeclarationFragment node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(WhileStatement node)
	{
		return genericVisit(node);
	}

	@Override
	public boolean visit(WildcardType node)
	{
		return genericVisit(node);
	}

	@Override
	public void endVisit(AnnotationTypeDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(AnnotationTypeMemberDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(AnonymousClassDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ArrayAccess node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ArrayCreation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ArrayInitializer node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ArrayType node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(AssertStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(Assignment node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(Block node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(BlockComment node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(BooleanLiteral node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(BreakStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(CastExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(CatchClause node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(CharacterLiteral node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ClassInstanceCreation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(CompilationUnit node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ConditionalExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ConstructorInvocation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ContinueStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(DoStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(EmptyStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(EnhancedForStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(EnumConstantDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(EnumDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ExpressionStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(FieldAccess node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(FieldDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ForStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(IfStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ImportDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(InfixExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(InstanceofExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(Initializer node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(Javadoc node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(LabeledStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(LineComment node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(MarkerAnnotation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(MemberRef node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(MemberValuePair node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(MethodDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(MethodInvocation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(MethodRef node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(MethodRefParameter node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(Modifier node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(NormalAnnotation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(NullLiteral node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(NumberLiteral node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(PackageDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ParameterizedType node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ParenthesizedExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(PostfixExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(PrefixExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(PrimitiveType node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(QualifiedName node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(QualifiedType node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ReturnStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SimpleName node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SimpleType node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SingleMemberAnnotation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SingleVariableDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(StringLiteral node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SuperConstructorInvocation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SuperFieldAccess node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SuperMethodInvocation node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SwitchCase node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SwitchStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(SynchronizedStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(TagElement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(TextElement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ThisExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(ThrowStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(TryStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(TypeDeclaration node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(TypeDeclarationStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(TypeLiteral node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(TypeParameter node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(UnionType node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationExpression node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(VariableDeclarationFragment node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(WhileStatement node)
	{
		genericEndVisit(node);
	}

	@Override
	public void endVisit(WildcardType node)
	{
		genericEndVisit(node);
	}
}
