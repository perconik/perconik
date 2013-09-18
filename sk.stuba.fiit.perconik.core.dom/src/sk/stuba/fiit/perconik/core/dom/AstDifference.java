package sk.stuba.fiit.perconik.core.dom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public final class AstDifference implements Iterable<AstNodeDelta>
{
	private static final AstDifference none = new AstDifference(ImmutableSet.<AstNodeDelta>of());
	
	private final Set<AstNodeDelta> deltas;
	
	private AstDifference(final Set<AstNodeDelta> deltas)
	{
		assert deltas != null;
		
		this.deltas = deltas;
	}
	
	public static final AstDifference of()
	{
		return none;
	}

	public static final AstDifference of(final AstNodeDelta delta)
	{
		return new AstDifference(ImmutableSet.of(delta));
	}

	public static final AstDifference of(final AstNodeDelta delta, final AstNodeDelta ... others)
	{
		if (others.length == 0)
		{
			return of(delta);
		}
		
		ImmutableSet.Builder<AstNodeDelta> builder = ImmutableSet.builder();
		
		builder.add(delta).addAll(Arrays.asList(others));
		
		return new AstDifference(builder.build());
	}

	public static final AstDifference of(final Iterable<? extends AstNodeDelta> deltas)
	{
		if (Iterables.isEmpty(deltas))
		{
			return none;
		}
		
		return new AstDifference(ImmutableSet.copyOf(deltas));
	}

	public static final AstDifference of(final Iterator<? extends AstNodeDelta> deltas)
	{
		if (!deltas.hasNext())
		{
			return none;
		}
		
		return new AstDifference(ImmutableSet.copyOf(deltas));
	}
	
	public static final class Builder
	{
		private final List<AstNodeDelta> deltas;
		
		public Builder()
		{
			this.deltas = Lists.newArrayList();
		}

		public final Builder add(final ASTNode node)
		{
			this.deltas.add(AstNodeAddition.of(node));
			
			return this;
		}
		
		public final Builder addAndDelete(final ASTNode added, final ASTNode deleted)
		{
			this.add(added);
			this.delete(deleted);
			
			return this;
		}

		public final Builder delete(final ASTNode node)
		{
			this.deltas.add(AstNodeDeletion.of(node));
			
			return this;
		}
		
		public final Builder deleteAndAdd(final ASTNode deleted, final ASTNode added)
		{
			this.delete(deleted);
			this.add(added);
			
			return this;
		}

		public final Builder modify(final ASTNode original, final ASTNode revised)
		{
			this.deltas.add(AstNodeModification.of(original, revised));
			
			return this;
		}
		
		public final AstDifference build()
		{
			return AstDifference.of(this.deltas);
		}
	}
	
	public static final Builder builder()
	{
		return new Builder();
	}

	@Override
	public final boolean equals(@Nullable Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof AstDifference))
		{
			return false;
		}
		
		return this.deltas.equals(((AstDifference) o).deltas);
	}

	@Override
	public final int hashCode()
	{
		return this.deltas.hashCode();
	}

	@Override
	public final String toString()
	{
		return this.deltas.toString();
	}

	@Override
	public final Iterator<AstNodeDelta> iterator()
	{
		return this.deltas.iterator();
	}

	public final boolean hasNodeDeltas()
	{
		return this.deltas.size() != 0;
	}

	public final Set<AstNodeDelta> getNodeDeltas()
	{
		return this.deltas;
	}
	
	public final int getNodeDeltasSize()
	{
		return this.deltas.size();
	}
}
