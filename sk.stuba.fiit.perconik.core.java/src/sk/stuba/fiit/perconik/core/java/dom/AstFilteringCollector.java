package sk.stuba.fiit.perconik.core.java.dom;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

public abstract class AstFilteringCollector<N extends ASTNode, R extends ASTNode> implements ListCollector<N, R>
{
	AstFilteringCollector()
	{
	}
	
	public static final <N extends ASTNode> AstFilteringCollector<N, N> using(final Predicate<N> filter)
	{
		return new Generic<>(filter);
	}
	
	public static final <N extends ASTNode, R extends ASTNode> AstFilteringCollector<N, R> using(final AstTypeFilter<N, R> filter)
	{
		return new Type<>(filter);
	}
	
	private static final class Generic<N extends ASTNode> extends AstFilteringCollector<N, N>
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
	}
	
	private static final class Type<N extends ASTNode, R extends ASTNode> extends AstFilteringCollector<N, R>
	{
		final AstTypeFilter<N, R> filter;

		Type(final AstTypeFilter<N, R> filter)
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
	}
}
