package sk.stuba.fiit.perconik.eclipse.jdt.core.dom;

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;
import sk.stuba.fiit.perconik.utilities.constant.TypeConstant;
import sk.stuba.fiit.perconik.utilities.constant.TypeConstantSupport;

/**
 * AST parser construct kinds.
 * 
 * @see ASTParser
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum TreeParserConstructKind implements IntegralConstant, TypeConstant<ASTNode>
{
	/**
	 * @see ASTParser#K_COMPILATION_UNIT
	 */
	COMPILATION_UNIT(ASTParser.K_COMPILATION_UNIT, CompilationUnit.class),
	
	/**
	 * @see ASTParser#K_CLASS_BODY_DECLARATIONS
	 */
	CLASS_BODY_DECLARATIONS(ASTParser.K_CLASS_BODY_DECLARATIONS, BodyDeclaration.class),
	
	/**
	 * @see ASTParser#K_EXPRESSION
	 */
	EXPRESSION(ASTParser.K_EXPRESSION, Expression.class),
	
	/**
	 * @see ASTParser#K_STATEMENTS
	 */
	STATEMENTS(ASTParser.K_STATEMENTS, Statement.class);

	private static final IntegralConstantSupport<TreeParserConstructKind> integers = IntegralConstantSupport.of(TreeParserConstructKind.class);
	
	private static final TypeConstantSupport<TreeParserConstructKind, ASTNode> types = TypeConstantSupport.of(TreeParserConstructKind.class);

	private final int value;
	
	private final Class<? extends ASTNode> type;
	
	private TreeParserConstructKind(final int value, final Class<? extends ASTNode> type)
	{
		assert type != null;
		
		this.value = value;
		this.type  = type;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final Set<Class<? extends ASTNode>> valuesAsTypes()
	{
		return types.getTypes();
	}
	
	public static final TreeParserConstructKind valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public static final TreeParserConstructKind valueOf(final Class<? extends ASTNode> type)
	{
		return types.getConstant(type);
	}

	public static final TreeParserConstructKind valueOf(final ASTNode element)
	{
		return valueOf(element.getClass());
	}
	
	public final int getValue()
	{
		return this.value;
	}

	public final Class<? extends ASTNode> getType()
	{
		return this.type;
	}
}
