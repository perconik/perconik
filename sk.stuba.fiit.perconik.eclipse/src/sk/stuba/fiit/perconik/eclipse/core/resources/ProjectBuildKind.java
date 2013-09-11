package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;

/**
 * Project build kinds.
 * 
 * @see IncrementalProjectBuilder
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum ProjectBuildKind implements IntegralConstant
{
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
	
	private ProjectBuildKind(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final ProjectBuildKind valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
