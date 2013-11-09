package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTParser;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.TreeApiLevel;

public final class TreeParsers
{
	private TreeParsers()
	{
		throw new AssertionError();
	}

	public static final ASTParser newParser()
	{
		return newParser(TreeApiLevel.latest());
	}

	public static final ASTParser newParser(final TreeApiLevel level)
	{
		return ASTParser.newParser(level.getValue());
	}
}
