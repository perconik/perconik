package sk.stuba.fiit.perconik.core.java.dom;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

public abstract class NodeFilteringCollector<N extends ASTNode, R extends ASTNode> implements ListCollector<N, R>
{
	NodeFilteringCollector()
	{
	}
	
	public static final <N extends ASTNode> NodeFilteringCollector<N, N> using(final Predicate<N> filter)
	{
		return new Generic<>(filter);
	}
	
	public static final <N extends ASTNode, R extends ASTNode> NodeFilteringCollector<N, R> using(final NodeClassFilter<N, R> filter)
	{
		return new Type<>(filter);
	}
	
	private static final class Generic<N extends ASTNode> extends NodeFilteringCollector<N, N>
	{
		final Predicate<N> filter;
		
		Generic(final Predicate<N> filter)
		{
			this.filter = Preconditions.checkNotNull(filter);
		}

		@Override
		public final List<N> apply(@Nullable final N node)
		{
			return new Processor().perform(node);
		}
		
		private final class Processor extends AbstractCollectingVisitor<N, N>
		{
			Processor()
			{
			}
			
			@Override
			public final void preVisit(final ASTNode node)
			{
				// this cast is unsafe, therefore the try block
				@SuppressWarnings("unchecked")
				N casted = (N) node;
				
				try
				{
					if (Generic.this.filter.apply(casted))
					{
						this.result.add(casted);
					}
				}
				catch (ClassCastException e)
				{
				}
			}
		}

		@Override
		public final Predicate<N> getFilter()
		{
			return this.filter;
		}
	}
	
	private static final class Type<N extends ASTNode, R extends ASTNode> extends NodeFilteringCollector<N, R>
	{
		final NodeClassFilter<N, R> filter;

		Type(final NodeClassFilter<N, R> filter)
		{
			this.filter = Preconditions.checkNotNull(filter);
		}
		
		@Override
		public final List<R> apply(@Nullable final N node)
		{
			return new Processor().perform(node);
		}
		
		private final class Processor extends AbstractCollectingVisitor<N, R>
		{
			Processor()
			{
			}
			
			@Override
			public final void preVisit(final ASTNode node)
			{
				for (Class<? extends R> type: Type.this.filter.getNodeClasses())
				{
					if (type.isInstance(node))
					{
						boolean accepted = false;
						
						// this cast is unsafe, therefore the try block
						@SuppressWarnings("unchecked")
						N casted = (N) node;
						
						try
						{
							accepted = Type.this.filter.apply(casted);
						}
						catch (ClassCastException e)
						{
							continue;
						}
						
						if (accepted)
						{
							this.result.add(type.cast(node));
						}
						
						return;
					}
				}
			}
		}

		@Override
		public final Predicate<N> getFilter()
		{
			return this.filter;
		}
	}
	
	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (o instanceof NodeFilteringCollector)
		{
			NodeFilteringCollector<?, ?> other = (NodeFilteringCollector<?, ?>) o;
			
			return this.getFilter().equals(other.getFilter());
		}
		
		return false;
	}

	@Override
	public final int hashCode()
	{
		return this.getFilter().hashCode();
	}
	
	@Override
	public final String toString()
	{
		return "collector(" + this.getFilter() + ")";
	}
	
	public abstract Predicate<N> getFilter();
}
