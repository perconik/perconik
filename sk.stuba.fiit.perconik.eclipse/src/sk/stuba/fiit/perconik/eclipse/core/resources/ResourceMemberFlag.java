package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;

import org.eclipse.core.resources.IContainer;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Resource member flags.
 * 
 * @see IContainer
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum ResourceMemberFlag implements IntegralConstant {
  /**
   * @see IContainer#INCLUDE_HIDDEN
   */
  INCLUDE_HIDDEN(IContainer.INCLUDE_HIDDEN),

  /**
   * @see IContainer#INCLUDE_PHANTOMS
   */
  INCLUDE_PHANTOMS(IContainer.INCLUDE_PHANTOMS),

  /**
   * @see IContainer#INCLUDE_TEAM_PRIVATE_MEMBERS
   */
  INCLUDE_TEAM_PRIVATE_MEMBERS(IContainer.INCLUDE_TEAM_PRIVATE_MEMBERS),

  /**
   * @see IContainer#EXCLUDE_DERIVED
   */
  EXCLUDE_DERIVED(IContainer.EXCLUDE_DERIVED),

  /**
   * @see IContainer#DO_NOT_CHECK_EXISTENCE
   */
  DO_NOT_CHECK_EXISTENCE(IContainer.DO_NOT_CHECK_EXISTENCE);

  private static final IntegralConstantSupport<ResourceMemberFlag> integers = IntegralConstantSupport.of(ResourceMemberFlag.class);

  private final int value;

  private ResourceMemberFlag(final int value) {
    this.value = value;
  }

  public static final Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static final int valuesAsInteger() {
    return integers.getConstantsAsInteger();
  }

  public static final int valuesAsInteger(final Set<ResourceMemberFlag> values) {
    return IntegralConstantSupport.constantsAsInteger(values);
  }

  public static final ResourceMemberFlag valueOf(final int value) {
    return integers.getConstant(value);
  }

  public static final Set<ResourceMemberFlag> setOf(final int values) {
    return integers.getConstants(values);
  }

  public final int getValue() {
    return this.value;
  }
}
