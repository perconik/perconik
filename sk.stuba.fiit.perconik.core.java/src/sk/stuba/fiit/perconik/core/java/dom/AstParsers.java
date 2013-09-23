package sk.stuba.fiit.perconik.core.java.dom;

import org.eclipse.jdt.core.dom.ASTParser;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstApiLevel;

public final class AstParsers
{
	private AstParsers()
	{
		throw new AssertionError();
	}

	public static final ASTParser newParser()
	{
		return newParser(AstApiLevel.latest());
	}

	public static final ASTParser newParser(final AstApiLevel level)
	{
		return ASTParser.newParser(level.getValue());
	}
}
