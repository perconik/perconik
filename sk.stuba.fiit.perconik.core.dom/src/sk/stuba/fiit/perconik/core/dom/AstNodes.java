package sk.stuba.fiit.perconik.core.dom;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
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
	
	public static final ASTNode root(@Nullable final ASTNode node)
	{
		return node != null ? node.getRoot() : null;
	}

	public static final ASTNode parent(@Nullable final ASTNode node)
	{
		return node != null ? node.getParent() : null;
	}

	public static final ASTNode parent(@Nullable ASTNode node, final Set<Class<? extends ASTNode>> types)
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
	
	// TODO consider @Nullable on all methods below
	
	// TODO add
	// public static final List<ASTNode> children(ASTNode node)

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

	public static final List<ASTNode> ancestors(ASTNode node)
	{
		List<ASTNode> ancestors = Lists.newLinkedList();
	
		while ((node = node.getParent()) != null)
		{
			ancestors.add(node);
		}
		
		return ancestors;
	}

	public static final Set<ASTNode> descendants(ASTNode node)
	{
		final Set<ASTNode> descendants = new HashSet<>();

		ASTVisitor visitor = new ASTVisitor()
		{
			@Override
			public final void preVisit(final ASTNode child)
			{
				descendants.add(child);
			}
		};
		
		node.accept(visitor);
		
		return descendants;
	}

	public static final Class<? extends ASTNode> typeAsClass(@Nullable final ASTNode node)
	{
		return node != null ? node.getClass() : null;
	}

	public static final AstNodeType typeAsConstant(@Nullable final ASTNode node)
	{
		return node != null ? AstNodeType.valueOf(node) : null;
	}

	public static final String typeAsString(@Nullable final ASTNode node)
	{
		return node != null ? node.getClass().getSimpleName() : null;
	}

	public static final Map<String, Object> genericProperties(final ASTNode node)
	{
		return node.properties();
	}

	public static final List<StructuralPropertyDescriptor> structuralProperties(final ASTNode node)
	{
		return node.structuralPropertiesForType();
	}

	// TODO add predicates:
	
	// public static final boolean isRoot
	// public static final boolean isParent
	// public static final boolean isChild
	// public static final boolean isAncestor
	// public static final boolean isDescendant
	
//	public static final boolean isChild(@Nullable ASTNode parent, ASTNode child)
//	{
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

	// TODO consider
//	public static final boolean isProblematicTree(final ASTNode node)
//	{
//		if (isRecoveredOrMalformed(node))
//		{
//			return true;
//		}
//		
//		for (ASTNode child: children(node)) // <-- descendants?
//		{
//			if (isRecoveredOrMalformed(child))
//			{
//				return true;
//			}
//		}
//
//		return false;
//	}
//
//	public static final boolean isRecoveredOrMalformed(final ASTNode node)
//	{
//		return (node.getFlags() & ASTNode.RECOVERED) != 0 || (node.getFlags() & ASTNode.MALFORMED) != 0;
//	}
}
