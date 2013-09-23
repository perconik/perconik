package sk.stuba.fiit.perconik.core.java.dom.difference;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.eclipse.jdt.core.dom.ASTNode;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public final class AstDifference extends ForwardingSet<AstNodeDelta>
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
	protected final Set<AstNodeDelta> delegate()
	{
		return this.deltas;
	}
}
