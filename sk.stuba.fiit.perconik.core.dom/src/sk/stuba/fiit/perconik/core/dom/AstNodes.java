package sk.stuba.fiit.perconik.core.dom;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstApiLevel;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;
import com.google.common.collect.Lists;

public final class AstNodes
{
	private AstNodes()
	{
		throw new AssertionError();
	}
	
	public static final AST newTree()
	{
		return newTree(AstApiLevel.latest());
	}

	public static final AST newTree(final AstApiLevel level)
	{
		return AST.newAST(level.getValue());
	}
	
	public static final <N extends ASTNode> N newNode(final AST tree, final Class<N> type)
	{
		return type.cast(tree.createInstance(type));
	}

	public static final ASTNode create(final ASTParser parser, final byte source[], final Charset charset)
	{
		return create(parser, new String(source, charset));
	}
	
	public static final ASTNode create(final ASTParser parser, final char[] source)
	{
		parser.setSource(source);
		
		return parser.createAST(null);
	}
	
	public static final ASTNode create(final ASTParser parser, final CharSequence source)
	{
		return create(parser, source.toString().toCharArray());
	}

	public static final <N extends ASTNode> N copyOf(final N node)
	{
		if (node == null)
		{
			return null;
		}
		
		return copyOf(node, AstApiLevel.valueOf(node.getAST().apiLevel()));
	}
	
	public static final <N extends ASTNode> N copyOf(final N node, final AstApiLevel level)
	{
		return copyOf(node, newTree(level));
	}

	public static final <N extends ASTNode> N copyOf(final N node, final AST tree)
	{
		return (N) ASTNode.copySubtree(tree, node);
	}
	
	// TODO check null handling in the whole package API
	
	// TODO public
	static final ASTNode root(final ASTNode node)
	{
		return node != null ? node.getRoot() : null;
	}

	static final ASTNode parent(final ASTNode node)
	{
		return node != null ? node.getParent() : null;
	}

	static final ASTNode parent(ASTNode node, final Collection<Class<? extends ASTNode>> types)
	{
		while (node != null)
		{
			for (Class<?> type: types)
			{
				if (type.isInstance(node))
				{
					return node;
				}
			}

			node = node.getParent();
		}

		return null;
	}

	static final AstNodeType type(final ASTNode node)
	{
		return node != null ? AstNodeType.valueOf(node) : null;
	}

	static final Class<? extends ASTNode> typeAsClass(final ASTNode node)
	{
		return node != null ? node.getClass() : null;
	}

	static final String typeAsString(final ASTNode node)
	{
		return node != null ? node.getClass().getSimpleName() : null;
	}

	public static final List<ASTNode> ancestors(ASTNode node)
	{
		List<ASTNode> ancestors = Lists.newLinkedList();
	
		while ((node = node.getParent()) != null)
		{
			ancestors.add(node);
		}
		
		return ancestors;
	}

	public static final List<ASTNode> branch(ASTNode node)
	{
		LinkedList<ASTNode> branch = Lists.newLinkedList();
		
		do
		{
			branch.addFirst(node);
		}
		while ((node = node.getParent()) != null);
		
		return branch;
	}
	
	public static final Map<String, Object> genericProperties(final ASTNode node)
	{
		return node.properties();
	}
	
	public static final List<StructuralPropertyDescriptor> structuralProperties(final ASTNode node)
	{
		return node.structuralPropertiesForType();
	}
	
	// TODO public
	static final boolean isProblematicTree(final ASTNode node)
	{
		if (isRecoveredOrMalformed(node))
		{
			return true;
		}
		
		for (ASTNode child: children(node))
		{
			if (isRecoveredOrMalformed(child))
			{
				return true;
			}
		}

		return false;
	}

	// TODO public
	static final boolean isRecoveredOrMalformed(final ASTNode node)
	{
		return (node.getFlags() & ASTNode.RECOVERED) != 0 || (node.getFlags() & ASTNode.MALFORMED) != 0;
	}
	
	// TODO consider
//	public static final boolean isChild(ASTNode child, @Nullable ASTNode parent)
//	{
//		Preconditions.checkNotNull(child);
//		
//		do
//		{
//			if (child == parent)
//			{
//				return true;
//			}
//		}
//		while ((child = child.getParent()) != null); 
//		
//		return false;
//	}

	public static final Set<ASTNode> children(final ASTNode node)
	{
		final Set<ASTNode> children = new HashSet<>();

		ASTVisitor visitor = new ASTVisitor()
		{
			@Override
			public final void preVisit(final ASTNode child)
			{
				children.add(child);
			}
		};
		
		node.accept(visitor);
		
		return children;
	}
}
