package sk.stuba.fiit.perconik.utilities.constant;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;

abstract class AbstractConstantSupport<P, E extends Enum<E>> implements Serializable
{
	private static final long serialVersionUID = -3235429686838483239L;

	final Class<E> type;

	final BiMap<P, E> map;
	
	AbstractConstantSupport(final Class<E> type)
	{
		if (!type.isEnum())
		{
			throw new IllegalArgumentException();
		}
		
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
	
	abstract static class AbstractSerializationProxy<P, E extends Enum<E>, S extends AbstractConstantSupport<P, E>> implements Serializable
	{
		private static final long serialVersionUID = -72790832310176072L;

		private final Class<E> type;
		
		AbstractSerializationProxy(final AbstractConstantSupport<P, E> support)
		{
			this.type = support.type;
		}
		
		abstract AbstractConstantSupport<P, E> resolve(Class<E> type);

		final Object readResolve() throws InvalidObjectException
		{
			try
			{
				return resolve(this.type);
			}
			catch (Exception e)
			{
				throw new InvalidObjectException("Unknown deserialization error");
			}
		}
	}
	
	public final Set<E> getConstants()
	{
		return this.map.values();
	}
}
