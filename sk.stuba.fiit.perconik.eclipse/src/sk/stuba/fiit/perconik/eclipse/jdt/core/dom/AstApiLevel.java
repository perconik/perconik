package sk.stuba.fiit.perconik.eclipse.jdt.core.dom;

import java.util.Set;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum AstApiLevel implements IntegralConstant
{
	/**
	 * @see org.eclipse.jdt.core.dom.AST#JLS2
	 */
	@SuppressWarnings("deprecation")
	JLS2(AST.JLS2),

	/**
	 * @see org.eclipse.jdt.core.dom.AST#JLS3
	 */
	@SuppressWarnings("deprecation")
	JLS3(AST.JLS3),

	/**
	 * @see org.eclipse.jdt.core.dom.AST#JLS4
	 */
	JLS4(AST.JLS4);

	private static final IntegralConstantSupport<AstApiLevel> integers = IntegralConstantSupport.of(AstApiLevel.class);

	static final AstApiLevel latest;
	
	static
	{
		AstApiLevel[] constants = values();
		
		latest = constants[constants.length - 1];
	}

	private final int value;
	
	private AstApiLevel(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final AstApiLevel valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public static final AstApiLevel valueOf(final AST tree)
	{
		return integers.getConstant(tree.apiLevel());
	}

	public static final AstApiLevel valueOf(final ASTNode node)
	{
		return valueOf(node.getAST());
	}
	
	public static final AstApiLevel latest()
	{
		return latest;
	}

	public final int getValue()
	{
		return this.value;
	}
}
