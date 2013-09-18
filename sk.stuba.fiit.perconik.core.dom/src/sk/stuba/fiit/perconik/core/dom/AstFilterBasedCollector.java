package sk.stuba.fiit.perconik.core.dom;

import java.util.List;
import javax.annotation.Nullable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class AstFilterBasedCollector<N extends ASTNode, R extends ASTNode> implements AstCollector<N, R>
{
	final AstTypeBasedFilter<N, R> filter;
	
	private AstFilterBasedCollector(final AstTypeBasedFilter<N, R> filter)
	{
		this.filter = Preconditions.checkNotNull(filter);
	}
	
	public static final <N extends ASTNode, R extends ASTNode> AstFilterBasedCollector<N, R> using(final AstTypeBasedFilter<N, R> filter)
	{
		return new AstFilterBasedCollector<>(filter);
	}
	
	@Override
	public final List<R> collect(@Nullable final N node)
	{
		if (node == null)
		{
			return null;
		}

		return new Processor().perform(node);
	}
	
	private final class Processor extends ASTVisitor
	{
		final List<R> result;
		
		Processor()
		{
			this.result = Lists.newLinkedList(); 
		}
		
		final List<R> perform(final ASTNode node)
		{
			node.accept(this);
			
			return this.result;
		}
		
		@Override
		public final void preVisit(final ASTNode node)
		{
			for (Class<? extends R> type: AstFilterBasedCollector.this.filter.getFilteredTypes())
			{
				if (type.isInstance(node))
				{
					boolean accepted = false;
					
					// this cast is unsafe, therefore the try block
					@SuppressWarnings("unchecked")
					N casted = (N) node;
					
					try
					{
						accepted = AstFilterBasedCollector.this.filter.accept(casted);
					}
					catch (ClassCastException e)
					{
						continue;
					}
					
					if (accepted)
					{
						// safe cast as long as node is an instance of type
						@SuppressWarnings("unchecked")
						R result = (R) node;

						this.result.add(result);
					}
					
					return;
				}
			}
		}
	}
}
