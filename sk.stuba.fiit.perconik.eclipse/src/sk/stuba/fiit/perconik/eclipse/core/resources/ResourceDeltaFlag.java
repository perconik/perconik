package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;

import org.eclipse.core.resources.IResourceDelta;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Resource delta flags.
 *
 * @see IResourceDelta
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public enum ResourceDeltaFlag implements IntegralConstant {
  /**
   * @see IResourceDelta#CONTENT
   */
  CONTENT(IResourceDelta.CONTENT),

  /**
   * @see IResourceDelta#MOVED_FROM
   */
  MOVED_FROM(IResourceDelta.MOVED_FROM),

  /**
   * @see IResourceDelta#MOVED_TO
   */
  MOVED_TO(IResourceDelta.MOVED_TO),

  /**
   * @see IResourceDelta#COPIED_FROM
   */
  COPIED_FROM(IResourceDelta.COPIED_FROM),

  /**
   * @see IResourceDelta#OPEN
   */
  OPEN(IResourceDelta.OPEN),

  /**
   * @see IResourceDelta#TYPE
   */
  TYPE(IResourceDelta.TYPE),

  /**
   * @see IResourceDelta#SYNC
   */
  SYNC(IResourceDelta.SYNC),

  /**
   * @see IResourceDelta#MARKERS
   */
  MARKERS(IResourceDelta.MARKERS),

  /**
   * @see IResourceDelta#REPLACED
   */
  REPLACED(IResourceDelta.REPLACED),

  /**
   * @see IResourceDelta#DESCRIPTION
   */
  DESCRIPTION(IResourceDelta.DESCRIPTION),

  /**
   * @see IResourceDelta#ENCODING
   */
  ENCODING(IResourceDelta.ENCODING),

  /**
   * @see IResourceDelta#LOCAL_CHANGED
   */
  LOCAL_CHANGED(IResourceDelta.LOCAL_CHANGED),

  /**
   * @see IResourceDelta#DERIVED_CHANGED
   */
  DERIVED_CHANGED(IResourceDelta.DERIVED_CHANGED);

  private static final IntegralConstantSupport<ResourceDeltaFlag> integers = IntegralConstantSupport.of(ResourceDeltaFlag.class);

  private final int value;

  private ResourceDeltaFlag(final int value) {
    this.value = value;
  }

  public static Set<Integer> valuesAsIntegers() {
    return integers.getIntegers();
  }

  public static int valuesAsInteger() {
    return integers.getConstantsAsInteger();
  }

  public static int valuesAsInteger(final Set<ResourceDeltaFlag> values) {
    return IntegralConstantSupport.constantsAsInteger(values);
  }

  public static ResourceDeltaFlag valueOf(final int value) {
    return integers.getConstant(value);
  }

  public static Set<ResourceDeltaFlag> setOf(final int values) {
    return integers.getConstants(values);
  }

  public int getValue() {
    return this.value;
  }
}
