package sk.stuba.fiit.perconik.eclipse.jdt.core.dom;

import java.util.Set;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

public enum AstNodeFlag implements IntegralConstant
{
	/**
	 * @see org.eclipse.jdt.core.dom.ASTNode#MALFORMED
	 */
	MALFORMED(ASTNode.MALFORMED),
	
	/**
	 * @see org.eclipse.jdt.core.dom.ASTNode#ORIGINAL
	 */
	ORIGINAL(ASTNode.ORIGINAL),
	
	/**
	 * @see org.eclipse.jdt.core.dom.ASTNode#PROTECT
	 */
	PROTECT(ASTNode.PROTECT),
	
	/**
	 * @see org.eclipse.jdt.core.dom.ASTNode#RECOVERED
	 */
	RECOVERED(ASTNode.RECOVERED);
	
	private static final IntegralConstantSupport<AstNodeFlag> integers = IntegralConstantSupport.of(AstNodeFlag.class);

	private final int value;
	
	private AstNodeFlag(int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}

	public static final int valuesAsInteger()
	{
		return integers.getConstantsAsInteger();
	}

	public static final int valuesAsInteger(Set<AstNodeFlag> values)
	{
		return IntegralConstantSupport.constantsAsInteger(values);
	}

	public static final AstNodeFlag valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public static final Set<AstNodeFlag> setOf(final int values)
	{
		return integers.getConstants(values);
	}
	
	public final int getValue()
	{
		return this.value;
	}
}
