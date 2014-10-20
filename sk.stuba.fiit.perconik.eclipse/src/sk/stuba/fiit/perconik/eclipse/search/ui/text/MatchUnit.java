package sk.stuba.fiit.perconik.eclipse.search.ui.text;

import java.util.Set;

import org.eclipse.search.ui.text.Match;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Match units.
 *
 * @see Match
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public enum MatchUnit implements IntegralConstant {
  /**
   * @see Match#UNIT_CHARACTER
   */
  CHARACTER(Match.UNIT_CHARACTER),

  /**
   * @see Match#UNIT_LINE
   */
  LINE(Match.UNIT_LINE);

  private static final IntegralConstantSupport<MatchUnit> integers = IntegralConstantSupport.of(MatchUnit.class);

  private final int value;

  private MatchUnit(final int value) {
    this.value = value;
  }

  public static Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static MatchUnit valueOf(final int value) {
    return integers.getConstant(value);
  }

  public int getValue() {
    return this.value;
  }
}
