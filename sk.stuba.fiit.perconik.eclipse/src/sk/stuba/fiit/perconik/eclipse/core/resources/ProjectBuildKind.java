package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;

import org.eclipse.core.resources.IncrementalProjectBuilder;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Project build kinds.
 *
 * @see IncrementalProjectBuilder
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public enum ProjectBuildKind implements IntegralConstant {
  /**
   * @see IncrementalProjectBuilder#FULL_BUILD
   */
  FULL(IncrementalProjectBuilder.FULL_BUILD),

  /**
   * @see IncrementalProjectBuilder#AUTO_BUILD
   */
  AUTO(IncrementalProjectBuilder.AUTO_BUILD),

  /**
   * @see IncrementalProjectBuilder#INCREMENTAL_BUILD
   */
  INCREMENTAL(IncrementalProjectBuilder.INCREMENTAL_BUILD),

  /**
   * @see IncrementalProjectBuilder#CLEAN_BUILD
   */
  CLEAN(IncrementalProjectBuilder.CLEAN_BUILD);

  private static final IntegralConstantSupport<ProjectBuildKind> integers = IntegralConstantSupport.of(ProjectBuildKind.class);

  private final int value;

  private ProjectBuildKind(final int value) {
    this.value = value;
  }

  public static Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static ProjectBuildKind valueOf(final int value) {
    return integers.getConstant(value);
  }

  public int getValue() {
    return this.value;
  }
}
