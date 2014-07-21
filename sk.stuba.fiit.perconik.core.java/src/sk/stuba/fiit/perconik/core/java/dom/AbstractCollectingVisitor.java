package sk.stuba.fiit.perconik.core.java.dom;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

abstract class AbstractCollectingVisitor<N extends ASTNode, R extends ASTNode> extends ASTVisitor
{
	final List<R> result;
	
	AbstractCollectingVisitor()
	{
		super(true);
		
		this.result = Lists.newLinkedList(); 
	}
	
	final List<R> perform(@Nullable final N node)
	{
		if (node == null)
		{
			return Collections.emptyList();
		}
		
		node.accept(this);
		
		return this.result;
	}
}
