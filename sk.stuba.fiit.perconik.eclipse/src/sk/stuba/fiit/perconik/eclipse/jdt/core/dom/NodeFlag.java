package sk.stuba.fiit.perconik.eclipse.jdt.core.dom;

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * AST node flags.
 * 
 * @see ASTNode
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum NodeFlag implements IntegralConstant {
  /**
   * @see ASTNode#MALFORMED
   */
  MALFORMED(ASTNode.MALFORMED),

  /**
   * @see ASTNode#ORIGINAL
   */
  ORIGINAL(ASTNode.ORIGINAL),

  /**
   * @see ASTNode#PROTECT
   */
  PROTECT(ASTNode.PROTECT),

  /**
   * @see ASTNode#RECOVERED
   */
  RECOVERED(ASTNode.RECOVERED);

  private static final IntegralConstantSupport<NodeFlag> integers = IntegralConstantSupport.of(NodeFlag.class);

  private final int value;

  private NodeFlag(int value) {
    this.value = value;
  }

  public static final Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static final int valuesAsInteger() {
    return integers.getConstantsAsInteger();
  }

  public static final int valuesAsInteger(final Set<NodeFlag> values) {
    return IntegralConstantSupport.constantsAsInteger(values);
  }

  public static final NodeFlag valueOf(final int value) {
    return integers.getConstant(value);
  }

  public static final Set<NodeFlag> setOf(final int values) {
    return integers.getConstants(values);
  }

  public final int getValue() {
    return this.value;
  }
}
