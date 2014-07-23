package sk.stuba.fiit.perconik.core.java.dom.traverse;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeTraverser;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

public final class CachedTraverser extends TreeTraverser<ASTNode> {
  final Set<ASTNode> nodes;

  final ListMultimap<ASTNode, ASTNode> children;

  private CachedTraverser(@Nullable final ASTNode node) {
    this.nodes = Sets.newHashSet();
    this.children = LinkedListMultimap.create();

    if (node != null) {
      this.load(node);
    }
  }

  private final void load(final ASTNode node) {
    ASTVisitor visitor = new ASTVisitor(true) {
      @Override
      public final void preVisit(final ASTNode node) {
        CachedTraverser.this.nodes.add(node);

        ASTNode parent = node.getParent();

        if (parent != null) {
          CachedTraverser.this.children.put(node.getParent(), node);
        }
      }
    };

    node.accept(visitor);
  }

  public static final CachedTraverser create(@Nullable final ASTNode node) {
    return new CachedTraverser(node);
  }

  @Override
  public final List<ASTNode> children(@Nullable final ASTNode node) {
    Preconditions.checkArgument(this.nodes.contains(node));

    return this.children.get(node);
  }
}
