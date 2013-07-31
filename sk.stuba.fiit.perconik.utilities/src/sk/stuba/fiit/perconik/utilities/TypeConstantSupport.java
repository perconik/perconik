package sk.stuba.fiit.perconik.utilities;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;

public final class TypeConstantSupport<E extends Enum<E> & TypeConstant<T>, T> implements Serializable
{
	private static final long serialVersionUID = -2957728433673208288L;

	private final Class<E> type;

	private final BiMap<Class<? extends T>, E> map;

	private TypeConstantSupport(final Class<E> type)
	{
		this.type = type;
		
		E[] constants = this.type.getEnumConstants();

		Map<Class<? extends T>, E> map = Maps.newHashMapWithExpectedSize(constants.length);
		
		for (E constant: constants)
		{
			if (map.put(constant.getType(), constant) != null)
			{
				throw new AssertionError("Type " + constant.getType() + " already set for constant " + constant.name() + ".");
			}
		}

		this.map = ImmutableBiMap.copyOf(map);
	}
	
	public static final <E extends Enum<E> & TypeConstant<T>, T> TypeConstantSupport<E, T> of(final Class<E> type)
	{
		return new TypeConstantSupport<>(type);
	}

	public final Set<Class<? extends T>> getTypes()
	{
		return this.map.keySet();
	}
	
	public final Set<E> getConstants()
	{
		return this.map.values();
	}

	public final E getConstant(final Class<? extends T> type)
	{
		E constant = this.map.get(type);

		Preconditions.checkArgument(constant != null);
		
		return constant;
	}
}
