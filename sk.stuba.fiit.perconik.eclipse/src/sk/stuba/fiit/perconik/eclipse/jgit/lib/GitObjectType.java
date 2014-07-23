package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import java.util.Set;

import org.eclipse.jgit.lib.Constants;

import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Git object types.
 * 
 * @see Constants
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum GitObjectType implements IntegralConstant
{
	/**
	 * @see Constants#OBJ_BAD
	 */
	UNKNOWN(Constants.OBJ_BAD),

	/**
	 * @see Constants#OBJ_BLOB
	 */
	BLOB(Constants.OBJ_BLOB),

	/**
	 * @see Constants#OBJ_COMMIT
	 */
	COMMIT(Constants.OBJ_COMMIT),

	/**
	 * @see Constants#OBJ_EXT
	 */
	EXTENDED(Constants.OBJ_EXT),

	/**
	 * @see Constants#OBJ_OFS_DELTA
	 */
	OFFSET_DELTA(Constants.OBJ_OFS_DELTA),

	/**
	 * @see Constants#OBJ_REF_DELTA
	 */
	REFERENCE_DELTA(Constants.OBJ_REF_DELTA),

	/**
	 * @see Constants#OBJ_TAG
	 */
	TAG(Constants.OBJ_TAG),

	/**
	 * @see Constants#OBJ_TREE
	 */
	TREE(Constants.OBJ_TREE),

	/**
	 * @see Constants#OBJ_TYPE_5
	 */
	RESERVED(Constants.OBJ_TYPE_5);

	private static final IntegralConstantSupport<GitObjectType> integers = IntegralConstantSupport.of(GitObjectType.class);

	private final int value;
	
	private GitObjectType(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final GitObjectType valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
