
package sk.stuba.fiit.perconik.ui.utilities;

import org.eclipse.swt.graphics.GC;

public final class Tables
{
	private Tables()
	{
		throw new AssertionError();
	}

	public final static int getMinimumColumnWidth(final GC gc, final String s)
	{
		return gc.stringExtent(s).x + 10;
	}
}
