package sk.stuba.fiit.perconik.core.java.dom.difference;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public final class AstDifference<N extends ASTNode> extends ForwardingSet<AstNodeDelta<N>>
{
	private static final AstDifference<?> none = new AstDifference<>(ImmutableSet.<AstNodeDelta<ASTNode>>of());
	
	private final Set<AstNodeDelta<N>> deltas;
	
	private AstDifference(final Set<AstNodeDelta<N>> deltas)
	{
		assert deltas != null;
		
		this.deltas = deltas;
	}
	
	public static final <N extends ASTNode> AstDifference<N> of()
	{
		@SuppressWarnings("unchecked")
		AstDifference<N> casted = (AstDifference<N>) none;
		
		return casted;
	}

	public static final <N extends ASTNode> AstDifference<N> of(final AstNodeDelta<N> delta)
	{
		return new AstDifference<>(ImmutableSet.of(delta));
	}

	public static final <N extends ASTNode> AstDifference<N> of(final Iterable<? extends AstNodeDelta<N>> deltas)
	{
		if (Iterables.isEmpty(deltas))
		{
			return of();
		}
		
		return new AstDifference<>(ImmutableSet.copyOf(deltas));
	}

	public static final <N extends ASTNode> AstDifference<N> of(final Iterator<? extends AstNodeDelta<N>> deltas)
	{
		if (!deltas.hasNext())
		{
			return of();
		}
		
		return new AstDifference<>(ImmutableSet.copyOf(deltas));
	}
	
	public static final class Builder<N extends ASTNode>
	{
		private final List<AstNodeDelta<N>> deltas;
		
		public Builder()
		{
			this.deltas = Lists.newArrayList();
		}

		public final Builder<N> add(final N node)
		{
			this.deltas.add(AstNodeAddition.of(node));
			
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
			this.deltas.add(AstNodeDeletion.of(node));
			
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
			this.deltas.add(AstNodeModification.of(original, revised));
			
			return this;
		}
		
		public final AstDifference<N> build()
		{
			return AstDifference.of(this.deltas);
		}
	}
	
	public static final <N extends ASTNode> Builder<N> builder()
	{
		return new Builder<>();
	}

	@Override
	protected final Set<AstNodeDelta<N>> delegate()
	{
		return this.deltas;
	}
}
