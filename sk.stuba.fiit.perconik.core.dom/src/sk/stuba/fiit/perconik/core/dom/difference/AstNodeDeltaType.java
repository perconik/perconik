package sk.stuba.fiit.perconik.core.dom.difference;

import java.util.Set;
import sk.stuba.fiit.perconik.utilities.constant.TypeConstant;
import sk.stuba.fiit.perconik.utilities.constant.TypeConstantSupport;

public enum AstNodeDeltaType implements TypeConstant<AstNodeDelta>
{
	DELETION(AstNodeDeletion.class),
	
	ADDITION(AstNodeAddition.class),
	
	MODIFICATION(AstNodeModification.class);

	private static final TypeConstantSupport<AstNodeDeltaType, AstNodeDelta> types = TypeConstantSupport.of(AstNodeDeltaType.class);

	private final Class<? extends AstNodeDelta> type;
	
	private AstNodeDeltaType(final Class<? extends AstNodeDelta> type)
	{
		assert type != null;
		
		this.type = type;
	}
	
	public static final Set<Class<? extends AstNodeDelta>> valuesAsTypes()
	{
		return types.getTypes();
	}

	public static final AstNodeDeltaType valueOf(final Class<? extends AstNodeDelta> type)
	{
		return types.getConstant(type);
	}

	public final Class<? extends AstNodeDelta> getType()
	{
		return this.type;
	}
}
