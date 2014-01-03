package sk.stuba.fiit.perconik.eclipse.search.ui.text;

import java.util.Set;
import org.eclipse.search.ui.text.MatchEvent;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

/**
 * Match event kinds.
 * 
 * @see MatchEvent
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum MatchEventKind implements IntegralConstant
{
	/**
	 * @see MatchEvent#ADDED
	 */
	ADDED(MatchEvent.ADDED),

	/**
	 * @see MatchEvent#REMOVED
	 */
	REMOVED(MatchEvent.REMOVED);

	private static final IntegralConstantSupport<MatchEventKind> integers = IntegralConstantSupport.of(MatchEventKind.class);

	private final int value;
	
	private MatchEventKind(final int value)
	{
		this.value = value;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final MatchEventKind valueOf(final int value)
	{
		return integers.getConstant(value);
	}

	public final int getValue()
	{
		return this.value;
	}
}
