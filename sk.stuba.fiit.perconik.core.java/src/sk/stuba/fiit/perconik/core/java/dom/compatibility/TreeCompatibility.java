package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import java.util.Map;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.TreeApiLevel;
import com.google.common.collect.Maps;

public final class TreeCompatibility
{
	private static final Map<TreeApiLevel, TreeFactory> factories;
	
	static
	{
		Map<TreeApiLevel, TreeFactory> map = Maps.newHashMap();
		
		for (TreeApiLevel level: TreeApiLevel.values())
		{
			map.put(level, new StandardTreeFactory(level));
		}
		
		factories = Maps.immutableEnumMap(map);
	}
	
	private TreeCompatibility()
	{
		throw new AssertionError();
	}

	public static final TreeFactory getFactory()
	{
		return getFactory(TreeApiLevel.latest());
	}
	
	public static final TreeFactory getFactory(final TreeApiLevel level)
	{
		return factories.get(level);
	}
	
	public static final NodeFactory getNodeFactory()
	{
		return StandardNodeFactory.INSTANCE;
	}
}
