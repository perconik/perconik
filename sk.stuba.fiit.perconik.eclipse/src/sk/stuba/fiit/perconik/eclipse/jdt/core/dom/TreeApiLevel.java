package sk.stuba.fiit.perconik.eclipse.jdt.core.dom;

import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * AST API levels.
 *
 * @see AST
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public enum TreeApiLevel implements IntegralConstant {
  /**
   * @see AST#JLS2
   */
  @SuppressWarnings("deprecation")
  JLS2(AST.JLS2),

  /**
   * @see AST#JLS3
   */
  @SuppressWarnings("deprecation")
  JLS3(AST.JLS3),

  /**
   * @see AST#JLS4
   */
  @SuppressWarnings("deprecation")
  JLS4(AST.JLS4);

  static final TreeApiLevel latest;

  static {
    TreeApiLevel[] constants = values();

    latest = constants[constants.length - 1];
  }

  private static final IntegralConstantSupport<TreeApiLevel> integers = IntegralConstantSupport.of(TreeApiLevel.class);

  private final int value;

  private TreeApiLevel(final int value) {
    this.value = value;
  }

  public static final TreeApiLevel latest() {
    return latest;
  }

  public static final Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static final TreeApiLevel valueOf(final int value) {
    return integers.getConstant(value);
  }

  public static final TreeApiLevel valueOf(final AST tree) {
    return integers.getConstant(tree.apiLevel());
  }

  public static final TreeApiLevel valueOf(final ASTNode node) {
    return valueOf(node.getAST());
  }

  public final int getValue() {
    return this.value;
  }
}
