package sk.stuba.fiit.perconik.utilities.constant;

import java.io.Serializable;
import java.util.Set;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;

public final class StringConstantSupport<E extends Enum<E> & StringConstant> extends AbstractConstantSupport<String, E> implements Serializable
{
	private static final long serialVersionUID = -2957728433673208288L;

	private StringConstantSupport(final Class<E> type)
	{
		super(type);
	}
	
	private static enum Transformation implements Function<StringConstant, String>
	{
		INSTANCE;
	
		public final String apply(final StringConstant constant)
		{
			return constant.getValue();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	final Function<E, String> transformation()
	{
		return (Function<E, String>) Transformation.INSTANCE;
	}

	public static final <E extends Enum<E> & StringConstant> StringConstantSupport<E> of(final Class<E> type)
	{
		return new StringConstantSupport<>(type);
	}

	public final Set<String> getStrings()
	{
		return this.map.keySet();
	}

	public final E getConstant(final String value)
	{
		E constant = this.map.get(value);

		Preconditions.checkArgument(constant != null, "Constant for value %s not found", value);
		
		return constant;
	}
}
