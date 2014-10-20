package sk.stuba.fiit.perconik.core.java.dom;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

import sk.stuba.fiit.perconik.core.java.dom.compatibility.TreeCompatibility;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.TreeApiLevel;

import static com.google.common.collect.Lists.newLinkedList;

// TODO add more helpers from org.eclipse.jdt.internal.corext.dom.ASTNodes

public final class Nodes {
  private Nodes() {}

  public static AST newTree() {
    return newTree(TreeApiLevel.latest());
  }

  public static AST newTree(final TreeApiLevel level) {
    return TreeCompatibility.getTreeFactory(level).newTree();
  }

  public static <N extends ASTNode> N newNode(final AST tree, final Class<N> type) {
    return TreeCompatibility.getNodeFactory().newNode(tree, type);
  }

  public static ASTNode create(final ASTParser parser, final byte source[], final Charset charset) {
    return create(parser, new String(source, charset));
  }

  public static ASTNode create(final ASTParser parser, final char[] source) {
    parser.setSource(source);

    return parser.createAST(null);
  }

  public static ASTNode create(final ASTParser parser, final CharSequence source) {
    return create(parser, source.toString().toCharArray());
  }

  public static ASTNode create(final ASTParser parser, final ICompilationUnit source) {
    return create(parser, source, false);
  }

  public static ASTNode create(final ASTParser parser, final ICompilationUnit source, final boolean resolveBindings) {
    parser.setSource(source);
    parser.setResolveBindings(resolveBindings);

    return parser.createAST(null);
  }

  // TODO consider
  //	public static ASTNode create(final ASTParser parser, final IClassFile source)
  //	{
  //		return create(parser, source.toString().toCharArray());
  //	}

  public static <N extends ASTNode> N copyOf(final N node) {
    if (node == null) {
      return null;
    }

    return copyOf(node, TreeApiLevel.valueOf(node.getAST().apiLevel()));
  }

  public static <N extends ASTNode> N copyOf(final N node, final TreeApiLevel level) {
    return copyOf(node, newTree(level));
  }

  public static <N extends ASTNode> N copyOf(final N node, final AST tree) {
    @SuppressWarnings("unchecked")
    N result = (N) ASTNode.copySubtree(tree, node);

    return result;
  }

  public static ASTNode root(@Nullable final ASTNode node) {
    return node != null ? node.getRoot() : null;
  }

  public static ASTNode parent(@Nullable final ASTNode node) {
    return node != null ? node.getParent() : null;
  }

  public static LinkedList<ASTNode> children(@Nullable final ASTNode node) {
    final LinkedList<ASTNode> children = newLinkedList();

    final ASTVisitor visitor = new ASTVisitor(true) {
      @Override
      public boolean preVisit2(final ASTNode child) {
        if (isChild(node, child)) {
          children.add(child);

          return false;
        }

        return true;
      }
    };

    if (node != null) {
      node.accept(visitor);
    }

    return children;
  }

  public static LinkedList<ASTNode> ancestors(@Nullable ASTNode node) {
    final LinkedList<ASTNode> ancestors = newLinkedList();

    while (node != null) {
      ancestors.add(node = node.getParent());
    }

    return ancestors;
  }

  public static LinkedList<ASTNode> descendants(@Nullable final ASTNode node) {
    final LinkedList<ASTNode> descendants = newLinkedList();

    if (node == null) {
      return descendants;
    }

    final ASTVisitor visitor = new ASTVisitor(true) {
      @Override
      public void preVisit(final ASTNode descendant) {
        descendants.add(descendant);
      }
    };

    node.accept(visitor);

    return descendants;
  }

  public static LinkedList<ASTNode> downToRoot(@Nullable ASTNode node) {
    LinkedList<ASTNode> branch = newLinkedList();

    if (node != null) {
      do {
        branch.add(node);
      } while ((node = node.getParent()) != null);
    }

    return branch;
  }

  public static LinkedList<ASTNode> upToLeaves(@Nullable final ASTNode node) {
    LinkedList<ASTNode> branch = descendants(node);

    if (node != null) {
      branch.addFirst(node);
    }

    return branch;
  }

  public static ASTNode firstDownToRoot(@Nullable ASTNode node, final Predicate<ASTNode> predicate) {
    if (node != null) {
      do {
        if (predicate.apply(node)) {
          return node;
        }
      } while ((node = node.getParent()) != null);
    }

    return null;
  }

  public static ASTNode firstUpToLeaves(@Nullable final ASTNode node, final Predicate<ASTNode> predicate) {
    for (ASTNode other: upToLeaves(node)) {
      if (predicate.apply(other)) {
        return node;
      }
    }

    return null;
  }

  public static ASTNode firstAncestor(@Nullable ASTNode node, final Predicate<ASTNode> predicate) {
    while (node != null) {
      if (predicate.apply(node = node.getParent())) {
        return node;
      }
    }

    return null;
  }

  public static ASTNode firstDescendant(@Nullable final ASTNode node, final Predicate<ASTNode> predicate) {
    final MutableReference<ASTNode> descendant = new MutableReference<>();

    final ASTVisitor visitor = new ASTVisitor(true) {
      @Override
      public boolean preVisit2(final ASTNode other) {
        if (predicate.apply(other)) {
          descendant.value = other;
        }

        return descendant.value == null;
      }
    };

    if (node != null) {
      node.accept(visitor);
    }

    return descendant.value;
  }

  public static Map<String, Object> genericProperties(final ASTNode node) {
    return node.properties();
  }

  public static List<StructuralPropertyDescriptor> structuralProperties(final ASTNode node) {
    return node.structuralPropertiesForType();
  }

  public static String source(final ASTNode node, final NodeRangeType range) {
    ASTNode root = node.getRoot();

    if (root instanceof CompilationUnit) {
      CompilationUnit unit = (CompilationUnit) root;
      ITypeRoot type = unit.getTypeRoot();

      try {
        if (type != null && type.getBuffer() != null) {
          int offset = range.getOffset(unit, node);
          int length = range.getLength(unit, node);

          return type.getBuffer().getText(offset, length);
        }
      } catch (JavaModelException e) {
        return null;
      }
    }

    return null;
  }

  public static boolean hasSource(final ASTNode node) {
    return node.getStartPosition() != -1;
  }

  public static boolean isRoot(@Nullable final ASTNode node) {
    return node != null && node == node.getRoot();
  }

  public static boolean isParent(@Nullable final ASTNode node, @Nullable final ASTNode parent) {
    return node != null && parent == node.getParent();
  }

  public static boolean isChild(@Nullable final ASTNode node, @Nullable final ASTNode child) {
    return child != null && child.getParent() == node;
  }

  public static boolean isAncestor(@Nullable ASTNode node, @Nullable final ASTNode ancestor) {
    while (node != null) {
      if (ancestor == (node = node.getParent())) {
        return true;
      }
    }

    return false;
  }

  public static boolean isDescendant(@Nullable final ASTNode node, @Nullable final ASTNode descendant) {
    if (node == null) {
      return false;
    }

    final MutableBoolean visit = new MutableBoolean(true);

    final ASTVisitor visitor = new ASTVisitor(true) {
      @Override
      public boolean preVisit2(final ASTNode other) {
        if (other == descendant) {
          visit.value = false;
        }

        return visit.value;
      }
    };

    node.accept(visitor);

    return !visit.value;
  }

  public static boolean isProblematic(final ASTNode node) {
    int flags = node.getFlags();

    return (flags & ASTNode.RECOVERED) != 0 || (flags & ASTNode.MALFORMED) != 0;
  }

  public static boolean isProblematicTree(final ASTNode node) {
    if (isProblematic(node)) {
      return true;
    }

    for (ASTNode descendant: descendants(node)) {
      if (isProblematic(descendant)) {
        return true;
      }
    }

    return false;
  }

  static class MutableReference<T> {
    T value;

    MutableReference() {}

    MutableReference(final T value) {
      this.value = value;
    }
  }

  static class MutableBoolean {
    boolean value;

    MutableBoolean(final boolean value) {
      this.value = value;
    }
  }

  public static Class<? extends ASTNode> toClass(@Nullable final ASTNode node) {
    return node != null ? node.getClass() : null;
  }

  public static NodeType toType(@Nullable final ASTNode node) {
    return node != null ? NodeType.valueOf(node) : null;
  }

  public static String toTypeString(@Nullable final ASTNode node) {
    return node != null ? NodeType.valueOf(node).getName() : null;
  }
}
