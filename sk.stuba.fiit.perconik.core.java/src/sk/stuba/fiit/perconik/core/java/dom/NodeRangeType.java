package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public enum NodeRangeType
{
	STANDARD
	{
		@Override
		public final int getOffset(final CompilationUnit unit, final ASTNode node)
		{
			return node.getStartPosition();
		}

		@Override
		public final int getLength(final CompilationUnit unit, final ASTNode node)
		{
			return node.getLength();
		}
	},
	
	EXTENDED
	{
		@Override
		public final int getOffset(final CompilationUnit unit, final ASTNode node)
		{
			return unit.getExtendedStartPosition(node);
		}

		@Override
		public final int getLength(final CompilationUnit unit, final ASTNode node)
		{
			return unit.getExtendedLength(node);
		}
	};
	
	public abstract int getOffset(CompilationUnit unit, ASTNode node);
	
	public abstract int getLength(CompilationUnit unit, ASTNode node);
}
