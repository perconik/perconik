package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import com.google.common.base.Function;

public class AstPaths
{
	static final String unknownPathName = "_";
	
	static final String variableSeparator = ",";

	private static final AstPathExtractor<?> namePathExtractor = AstPathExtractor.using(PathExtractorStrategy.NAME);
	
	private static final AstPathExtractor<?> typePathExtractor = AstPathExtractor.using(PathExtractorStrategy.TYPE);

	private AstPaths()
	{
		throw new AssertionError();
	}
	
	public static final String unknownPathName()
	{
		return unknownPathName;
	}
	
	public static final String variableSeparator()
	{
		return variableSeparator;
	}
	
	private static enum PathExtractorStrategy implements Function<ASTNode, String>
	{
		NAME
		{
			public final String apply(final ASTNode node)
			{
				if (node == null)
				{
					return unknownPathName;
				}
				
				for (StructuralPropertyDescriptor descriptor: AstNodes.structuralProperties(node))
				{
					if (descriptor.getId().equals("name"))
					{
						return node.getStructuralProperty(descriptor).toString();
					}
				}
				
				return unknownPathName;
			}
		},
		
		TYPE
		{
			public final String apply(final ASTNode node)
			{
				return node != null ? node.getClass().getSimpleName() : unknownPathName;
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
}
