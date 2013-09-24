package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

public final class AstTransformers
{
	private static final AstPathExtractor<?> namePathExtractor = AstPathExtractor.using(PathExtractorStrategy.NAME);
	
	private static final AstPathExtractor<?> typePathExtractor = AstPathExtractor.using(PathExtractorStrategy.TYPE);

	private AstTransformers()
	{
		throw new AssertionError();
	}

	public static final <N extends ASTNode> AstCutter<N> cutterUsingFilter(final AstFilter<ASTNode> filter)
	{
		return AstCutter.using(filter);
	}
	
	private static enum PathExtractorStrategy implements AstTransformer<ASTNode, String>
	{
		NAME
		{
			public final String transform(final ASTNode node)
			{
				if (node == null)
				{
					return AstPathExtractor.unknownPathName();
				}
				
				for (StructuralPropertyDescriptor descriptor: AstNodes.structuralProperties(node))
				{
					if (descriptor.getId().equals("name"))
					{
						return node.getStructuralProperty(descriptor).toString();
					}
				}
				
				return AstPathExtractor.unknownPathName();
			}
		},
		
		TYPE
		{
			public final String transform(final ASTNode node)
			{
				return node != null ? node.getClass().getSimpleName() : AstPathExtractor.unknownPathName();
			}
		};
	}
	
	private static final <N extends ASTNode> AstPathExtractor<N> cast(final AstPathExtractor<?> extractor)
	{
		// only for stateless internal singletons shared across all types
		@SuppressWarnings("unchecked")
		AstPathExtractor<N> result = (AstPathExtractor<N>) extractor;
		
		return result;
	}

	public static final <N extends ASTNode> AstPathExtractor<N> namePathExtractor()
	{
		return cast(namePathExtractor);
	}

	public static final <N extends ASTNode> AstPathExtractor<N> typePathExtractor()
	{
		return cast(typePathExtractor);
	}
	
	// TODO add
	
//	public static final AstTransformer<N, R> from(final AstCollector<N, R> collector)
//	{
//		
//	}
//
//	public static final AstTransformer<N, R> from(final AstFilter<N> filter)
//	{
//		
//	}
//
//	public static final AstTransformer<N, R> from(final AstFlattener<N> flattener)
//	{
//		
//	}
//
//	public static final AstTransformer<N, R> from(final AstTokenizer<N> tokenizer)
//	{
//		
//	}
	
	// TODO add toParent trnasformer, firstParentOfType, ...
}
