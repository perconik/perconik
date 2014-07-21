package sk.stuba.fiit.perconik.core.java.dom.difference;

import java.util.Set;

import sk.stuba.fiit.perconik.utilities.constant.TypeConstant;
import sk.stuba.fiit.perconik.utilities.constant.TypeConstantSupport;

public enum NodeDeltaType implements TypeConstant<NodeDelta<?>>
{
	DELETION(NodeDeletion.class),
	
	ADDITION(NodeAddition.class),
	
	MODIFICATION(NodeModification.class);

	private static final TypeConstantSupport<NodeDeltaType, NodeDelta<?>> types = TypeConstantSupport.of(NodeDeltaType.class);

	private final Class<? extends NodeDelta<?>> type;
	
	private <T extends NodeDelta<?>> NodeDeltaType(final Class<? extends T> type)
	{
		assert type != null;
		
		this.type = type;
	}
	
	public static final Set<Class<? extends NodeDelta<?>>> valuesAsTypes()
	{
		return types.getTypes();
	}

	public static final NodeDeltaType valueOf(final Class<? extends NodeDelta<?>> type)
	{
		return types.getConstant(type);
	}

	public final Class<? extends NodeDelta<?>> getType()
	{
		return this.type;
	}
}
