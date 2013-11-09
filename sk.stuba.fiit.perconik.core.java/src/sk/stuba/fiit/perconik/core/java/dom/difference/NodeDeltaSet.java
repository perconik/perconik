package sk.stuba.fiit.perconik.core.java.dom.difference;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public final class NodeDeltaSet<N extends ASTNode> extends ForwardingSet<NodeDelta<N>>
{
	private static final NodeDeltaSet<?> none = new NodeDeltaSet<>(ImmutableSet.<NodeDelta<ASTNode>>of());
	
	private final Set<NodeDelta<N>> deltas;
	
	private NodeDeltaSet(final Set<NodeDelta<N>> deltas)
	{
		assert deltas != null;
		
		this.deltas = deltas;
	}
	
	public static final <N extends ASTNode> NodeDeltaSet<N> of()
	{
		@SuppressWarnings("unchecked")
		NodeDeltaSet<N> casted = (NodeDeltaSet<N>) none;
		
		return casted;
	}

	public static final <N extends ASTNode> NodeDeltaSet<N> of(final NodeDelta<N> delta)
	{
		return new NodeDeltaSet<>(ImmutableSet.of(delta));
	}

	public static final <N extends ASTNode> NodeDeltaSet<N> of(final Iterable<? extends NodeDelta<N>> deltas)
	{
		if (Iterables.isEmpty(deltas))
		{
			return of();
		}
		
		return new NodeDeltaSet<>(ImmutableSet.copyOf(deltas));
	}

	public static final <N extends ASTNode> NodeDeltaSet<N> of(final Iterator<? extends NodeDelta<N>> deltas)
	{
		if (!deltas.hasNext())
		{
			return of();
		}
		
		return new NodeDeltaSet<>(ImmutableSet.copyOf(deltas));
	}
	
	public static final class Builder<N extends ASTNode>
	{
		private final List<NodeDelta<N>> deltas;
		
		public Builder()
		{
			this.deltas = Lists.newArrayList();
		}

		public final Builder<N> add(final N node)
		{
			this.deltas.add(NodeAddition.of(node));
			
			return this;
		}
		
		public final Builder<N> addAndDelete(final N added, final N deleted)
		{
			this.add(added);
			this.delete(deleted);
			
			return this;
		}

		public final Builder<N> delete(final N node)
		{
			this.deltas.add(NodeDeletion.of(node));
			
			return this;
		}
		
		public final Builder<N> deleteAndAdd(final N deleted, final N added)
		{
			this.delete(deleted);
			this.add(added);
			
			return this;
		}

		public final Builder<N> modify(final N original, final N revised)
		{
			this.deltas.add(NodeModification.of(original, revised));
			
			return this;
		}
		
		public final NodeDeltaSet<N> build()
		{
			return NodeDeltaSet.of(this.deltas);
		}
	}
	
	public static final <N extends ASTNode> Builder<N> builder()
	{
		return new Builder<>();
	}

	@Override
	protected final Set<NodeDelta<N>> delegate()
	{
		return this.deltas;
	}
}
