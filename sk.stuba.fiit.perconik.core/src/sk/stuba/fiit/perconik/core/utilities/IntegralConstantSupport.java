package sk.stuba.fiit.perconik.core.utilities;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;

public final class IntegralConstantSupport<E extends Enum<E> & IntegralConstant> implements Serializable
{
	private static final long serialVersionUID = 6686975853072661262L;

	private final Class<E> type;

	private final BiMap<Integer, E> map;
	
	private IntegralConstantSupport(final Class<E> type)
	{
		this.type = type;
		
		E[] constants = this.type.getEnumConstants();
		
		Map<Integer, E> map = Maps.newHashMapWithExpectedSize(constants.length);
		
		for (E constant: constants)
		{
			if (map.put(constant.getValue(), constant) != null)
			{
				throw new AssertionError("Value " + constant.getValue() + " already set for constant " + constant.name() + ".");
			}
		}

		this.map = ImmutableBiMap.copyOf(map);
	}
	
	public static final <E extends Enum<E> & IntegralConstant> IntegralConstantSupport<E> of(final Class<E> type)
	{
		return new IntegralConstantSupport<>(type);
	}

	public static final <E extends Enum<E> & IntegralConstant> int constantsAsInteger(Set<E> constants)
	{
		int values = 0;
		
		for (E constant: constants)
		{
			values |= constant.getValue();
		}
		
		return values;
	}
	
	public final Set<Integer> getIntegers()
	{
		return this.map.keySet();
	}
	
	public final Set<E> getConstants()
	{
		return this.map.values();
	}
	
	public final int getConstantsAsInteger()
	{
		return constantsAsInteger(this.map.values());
	}

	public final Set<E> getConstants(final int values)
	{
		Set<E> flags = EnumSet.noneOf(this.type);

		for (E constant: this.map.values())
		{
			if ((values & constant.getValue()) != 0)
			{
				flags.add(constant);
			}
		}

		return flags;
	}
	
	public final E getConstant(final int value)
	{
		E constant = this.map.get(value);

		Preconditions.checkArgument(constant != null);
		
		return constant;
	}
}
