package sk.stuba.fiit.perconik.core.java.dom.traverse;

import static sk.stuba.fiit.perconik.core.java.dom.traverse.NodeVisitOption.INCLUDE_JAVADOC_TAGS;
import java.util.Arrays;
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

public abstract class NodeVisitor
{
	final Internals internals;
	
	protected NodeVisitor(final NodeVisitOption ... options)
	{
		Set<NodeVisitOption> set = EnumSet.copyOf(Arrays.asList(options));
		
		this.internals = new Internals(this, set.contains(INCLUDE_JAVADOC_TAGS));
	}
	
	public final ASTVisitor asUnderlyingVisitor()
	{
		return this.internals;
	}
	
	public final void subtreeVisit(final ASTNode node)
	{
		NodeVisitors.accept(node, this);
	}
	
	@SuppressWarnings({"static-method", "unused"})
	public final boolean preVisit(ASTNode node)
	{
		return true;
	}
	
	@SuppressWarnings("unused")
	public final void postVisit(ASTNode node)
	{
	}
	
	public boolean visit(AnnotationTypeDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(AnnotationTypeMemberDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(AnonymousClassDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ArrayAccess node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ArrayCreation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ArrayInitializer node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ArrayType node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(AssertStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(Assignment node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(Block node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(BlockComment node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(BooleanLiteral node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(BreakStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(CastExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(CatchClause node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(CharacterLiteral node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ClassInstanceCreation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(CompilationUnit node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ConditionalExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ConstructorInvocation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ContinueStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(DoStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(EmptyStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(EnhancedForStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(EnumConstantDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(EnumDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ExpressionStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(FieldAccess node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(FieldDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ForStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(IfStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ImportDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(InfixExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(InstanceofExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(Initializer node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(Javadoc node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(LabeledStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(LineComment node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(MarkerAnnotation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(MemberRef node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(MemberValuePair node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(MethodRef node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(MethodRefParameter node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(MethodDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(MethodInvocation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(Modifier node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(NormalAnnotation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(NullLiteral node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(NumberLiteral node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(PackageDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ParameterizedType node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ParenthesizedExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(PostfixExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(PrefixExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(PrimitiveType node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(QualifiedName node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(QualifiedType node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ReturnStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SimpleName node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SimpleType node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SingleMemberAnnotation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SingleVariableDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(StringLiteral node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SuperConstructorInvocation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SuperFieldAccess node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SuperMethodInvocation node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SwitchCase node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SwitchStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(SynchronizedStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(TagElement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(TextElement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ThisExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(ThrowStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(TryStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(TypeDeclaration node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(TypeDeclarationStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(TypeLiteral node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(TypeParameter node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(UnionType node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(VariableDeclarationExpression node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(VariableDeclarationStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(VariableDeclarationFragment node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(WhileStatement node)
	{
		return this.internals.standardVisit(node);
	}
	
	public boolean visit(WildcardType node)
	{
		return this.internals.standardVisit(node);
	}
	
	@SuppressWarnings("unused")
	public void endVisit(AnnotationTypeDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(AnnotationTypeMemberDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(AnonymousClassDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ArrayAccess node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ArrayCreation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ArrayInitializer node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ArrayType node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(AssertStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(Assignment node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(Block node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(BlockComment node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(BooleanLiteral node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(BreakStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(CastExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(CatchClause node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(CharacterLiteral node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ClassInstanceCreation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(CompilationUnit node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ConditionalExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ConstructorInvocation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ContinueStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(DoStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(EmptyStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(EnhancedForStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(EnumConstantDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(EnumDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ExpressionStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(FieldAccess node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(FieldDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ForStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(IfStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ImportDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(InfixExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(InstanceofExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(Initializer node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(Javadoc node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(LabeledStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(LineComment node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(MarkerAnnotation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(MemberRef node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(MemberValuePair node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(MethodRef node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(MethodRefParameter node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(MethodDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(MethodInvocation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(Modifier node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(NormalAnnotation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(NullLiteral node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(NumberLiteral node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(PackageDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ParameterizedType node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ParenthesizedExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(PostfixExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(PrefixExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(PrimitiveType node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(QualifiedName node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(QualifiedType node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ReturnStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SimpleName node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SimpleType node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SingleMemberAnnotation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SingleVariableDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(StringLiteral node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SuperConstructorInvocation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SuperFieldAccess node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SuperMethodInvocation node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SwitchCase node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SwitchStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(SynchronizedStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(TagElement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(TextElement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ThisExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(ThrowStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(TryStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(TypeDeclaration node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(TypeDeclarationStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(TypeLiteral node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(TypeParameter node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(UnionType node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(VariableDeclarationExpression node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(VariableDeclarationStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(VariableDeclarationFragment node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(WhileStatement node)
	{
	}
	
	@SuppressWarnings("unused")
	public void endVisit(WildcardType node)
	{
	}
	
	static final class Internals extends ASTVisitor
	{
		final NodeVisitor visitor;
		
		Internals(final NodeVisitor visitor, final boolean includeJavadocTags)
		{
			super(includeJavadocTags);
			
			this.visitor = visitor;
		}

		@Override
		public final void preVisit(final ASTNode node)
		{
			this.visitor.preVisit(node);
		}

		@Override
		public final boolean preVisit2(final ASTNode node)
		{
			return this.visitor.preVisit(node);
		}

		@Override
		public final void postVisit(final ASTNode node)
		{
			this.visitor.postVisit(node);
		}

		@Override
		public final boolean visit(AnnotationTypeDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(AnnotationTypeMemberDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(AnonymousClassDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ArrayAccess node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ArrayCreation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ArrayInitializer node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ArrayType node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(AssertStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(Assignment node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(Block node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(BlockComment node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(BooleanLiteral node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(BreakStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(CastExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(CatchClause node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(CharacterLiteral node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ClassInstanceCreation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(CompilationUnit node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ConditionalExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ConstructorInvocation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ContinueStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(DoStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(EmptyStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(EnhancedForStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(EnumConstantDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(EnumDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ExpressionStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(FieldAccess node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(FieldDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ForStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(IfStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ImportDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(InfixExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(InstanceofExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(Initializer node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(Javadoc node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(LabeledStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(LineComment node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(MarkerAnnotation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(MemberRef node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(MemberValuePair node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(MethodRef node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(MethodRefParameter node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(MethodDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(MethodInvocation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(Modifier node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(NormalAnnotation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(NullLiteral node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(NumberLiteral node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(PackageDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ParameterizedType node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ParenthesizedExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(PostfixExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(PrefixExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(PrimitiveType node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(QualifiedName node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(QualifiedType node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ReturnStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SimpleName node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SimpleType node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SingleMemberAnnotation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SingleVariableDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(StringLiteral node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SuperConstructorInvocation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SuperFieldAccess node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SuperMethodInvocation node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SwitchCase node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SwitchStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(SynchronizedStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(TagElement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(TextElement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ThisExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(ThrowStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(TryStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(TypeDeclaration node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(TypeDeclarationStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(TypeLiteral node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(TypeParameter node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(UnionType node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(VariableDeclarationExpression node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(VariableDeclarationStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(VariableDeclarationFragment node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(WhileStatement node)
		{
			return this.visitor.visit(node);
		}

		@Override
		public final boolean visit(WildcardType node)
		{
			return this.visitor.visit(node);
		}
		final boolean standardVisit(AnnotationTypeDeclaration node)
		{
			return super.visit(node);
		}
		final boolean standardVisit(AnnotationTypeMemberDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(AnonymousClassDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ArrayAccess node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ArrayCreation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ArrayInitializer node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ArrayType node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(AssertStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(Assignment node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(Block node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(BlockComment node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(BooleanLiteral node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(BreakStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(CastExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(CatchClause node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(CharacterLiteral node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ClassInstanceCreation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(CompilationUnit node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ConditionalExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ConstructorInvocation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ContinueStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(DoStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(EmptyStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(EnhancedForStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(EnumConstantDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(EnumDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ExpressionStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(FieldAccess node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(FieldDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ForStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(IfStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ImportDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(InfixExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(InstanceofExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(Initializer node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(Javadoc node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(LabeledStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(LineComment node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(MarkerAnnotation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(MemberRef node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(MemberValuePair node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(MethodRef node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(MethodRefParameter node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(MethodDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(MethodInvocation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(Modifier node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(NormalAnnotation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(NullLiteral node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(NumberLiteral node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(PackageDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ParameterizedType node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ParenthesizedExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(PostfixExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(PrefixExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(PrimitiveType node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(QualifiedName node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(QualifiedType node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ReturnStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SimpleName node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SimpleType node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SingleMemberAnnotation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SingleVariableDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(StringLiteral node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SuperConstructorInvocation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SuperFieldAccess node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SuperMethodInvocation node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SwitchCase node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SwitchStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(SynchronizedStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(TagElement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(TextElement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ThisExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(ThrowStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(TryStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(TypeDeclaration node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(TypeDeclarationStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(TypeLiteral node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(TypeParameter node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(UnionType node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(VariableDeclarationExpression node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(VariableDeclarationStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(VariableDeclarationFragment node)
		{
			return super.visit(node);
		}
		final boolean standardVisit(WhileStatement node)
		{
			return super.visit(node);
		}
		
		final boolean standardVisit(WildcardType node)
		{
			return super.visit(node);
		}
		
		@Override
		public final void endVisit(AnnotationTypeDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(AnnotationTypeMemberDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(AnonymousClassDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ArrayAccess node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ArrayCreation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ArrayInitializer node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ArrayType node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(AssertStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(Assignment node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(Block node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(BlockComment node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(BooleanLiteral node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(BreakStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(CastExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(CatchClause node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(CharacterLiteral node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ClassInstanceCreation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(CompilationUnit node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ConditionalExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ConstructorInvocation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ContinueStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(DoStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(EmptyStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(EnhancedForStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(EnumConstantDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(EnumDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ExpressionStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(FieldAccess node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(FieldDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ForStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(IfStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ImportDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(InfixExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(InstanceofExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(Initializer node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(Javadoc node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(LabeledStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(LineComment node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(MarkerAnnotation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(MemberRef node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(MemberValuePair node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(MethodRef node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(MethodRefParameter node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(MethodDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(MethodInvocation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(Modifier node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(NormalAnnotation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(NullLiteral node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(NumberLiteral node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(PackageDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ParameterizedType node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ParenthesizedExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(PostfixExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(PrefixExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(PrimitiveType node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(QualifiedName node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(QualifiedType node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ReturnStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SimpleName node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SimpleType node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SingleMemberAnnotation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SingleVariableDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(StringLiteral node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SuperConstructorInvocation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SuperFieldAccess node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SuperMethodInvocation node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SwitchCase node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SwitchStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(SynchronizedStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(TagElement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(TextElement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ThisExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(ThrowStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(TryStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(TypeDeclaration node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(TypeDeclarationStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(TypeLiteral node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(TypeParameter node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(UnionType node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(VariableDeclarationExpression node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(VariableDeclarationStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(VariableDeclarationFragment node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(WhileStatement node)
		{
			this.visitor.endVisit(node);
		}

		@Override
		public final void endVisit(WildcardType node)
		{
			this.visitor.endVisit(node);
		}
	}
}
