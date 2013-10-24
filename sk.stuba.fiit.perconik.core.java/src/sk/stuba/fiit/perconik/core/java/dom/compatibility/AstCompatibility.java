package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import java.util.Map;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstApiLevel;
import com.google.common.collect.Maps;

public final class AstCompatibility
{
	private static final Map<AstApiLevel, AstFactory> factories;
	
	static
	{
		Map<AstApiLevel, AstFactory> map = Maps.newHashMap();
		
		for (AstApiLevel level: AstApiLevel.values())
		{
			map.put(level, new StandardAstFactory(level));
		}
		
		factories = Maps.immutableEnumMap(map);
	}
	
	private AstCompatibility()
	{
		throw new AssertionError();
	}

	public static final AstFactory getFactory()
	{
		return getFactory(AstApiLevel.latest());
	}
	
	public static final AstFactory getFactory(final AstApiLevel level)
	{
		return factories.get(level);
	}
	
	public static final AstNodeFactory getNodeFactory()
	{
		return StandardAstNodeFactory.INSTANCE;
	}
}
