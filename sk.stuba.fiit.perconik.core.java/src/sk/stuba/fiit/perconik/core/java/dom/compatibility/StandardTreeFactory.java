package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import org.eclipse.jdt.core.dom.AST;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.TreeApiLevel;

final class StandardTreeFactory implements TreeFactory
{
	static final Map<TreeApiLevel, TreeFactory> INSTANCES;
	
	private final TreeApiLevel level;

	static
	{
		Map<TreeApiLevel, TreeFactory> map = Maps.newHashMap();
		
		for (TreeApiLevel level: TreeApiLevel.values())
		{
			map.put(level, new StandardTreeFactory(level));
		}
		
		INSTANCES = Maps.immutableEnumMap(map);
	}
	
	private StandardTreeFactory(final TreeApiLevel level)
	{
		this.level = Preconditions.checkNotNull(level);
	}

	public final AST newTree()
	{
		return AST.newAST(this.level.getValue());
	}
}