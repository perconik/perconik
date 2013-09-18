package sk.stuba.fiit.perconik.utilities.constant;

import java.io.Serializable;
import java.util.Set;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;

public final class TypeConstantSupport<E extends Enum<E> & TypeConstant<T>, T> extends AbstractConstantSupport<Class<? extends T>, E> implements Serializable
{
	private static final long serialVersionUID = -2957728433673208288L;

	private TypeConstantSupport(final Class<E> type)
	{
		super(type);
	}
	
	private static enum Transformation implements Function<TypeConstant<?>, Class<?>>
	{
		INSTANCE;
	
		public final Class<?> apply(final TypeConstant<?> constant)
		{
			return constant.getType();
		}
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	final Function<E, Class<? extends T>> transformation()
	{
		return (Function) Transformation.INSTANCE;
	}

	public static final <E extends Enum<E> & TypeConstant<T>, T> TypeConstantSupport<E, T> of(final Class<E> type)
	{
		return new TypeConstantSupport<>(type);
	}

	public final Set<Class<? extends T>> getTypes()
	{
		return this.map.keySet();
	}

	public final E getConstant(final Class<? extends T> type)
	{
		E constant = this.map.get(type);

		Preconditions.checkArgument(constant != null, "Constant for type %s not found", type.getName());
		
		return constant;
	}
}
