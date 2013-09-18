package sk.stuba.fiit.perconik.utilities.constant;

import java.util.Map;
import java.util.Set;
import com.google.common.base.Function;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;

abstract class AbstractConstantSupport<P, E extends Enum<E>>
{
	final Class<E> type;

	final BiMap<P, E> map;

	AbstractConstantSupport(final Class<E> type)
	{
		this.type = type;
		
		E[] constants = this.type.getEnumConstants();

		Map<P, E> map = Maps.newHashMapWithExpectedSize(constants.length);
		
		Function<E, P> transformation = this.transformation();
		
		for (E constant: constants)
		{
			P primitive = transformation.apply(constant);
			
			if (map.put(primitive, constant) != null)
			{
				throw new AssertionError("Primitive " + primitive + " or constant " + constant.name() + " already associated");
			}
		}

		this.map = ImmutableBiMap.copyOf(map);
	}
	
	abstract Function<E, P> transformation();
	
	public final Set<E> getConstants()
	{
		return this.map.values();
	}
}
