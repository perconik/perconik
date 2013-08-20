package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jface.text.DocumentEvent;
import sk.stuba.fiit.perconik.core.listeners.DocumentChangeListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class DocumentChangeDebugListener extends AbstractDebugListener implements DocumentChangeListener
{
	public DocumentChangeDebugListener()
	{
	}
	
	public DocumentChangeDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void documentAboutToBeChanged(final DocumentEvent event)
	{
		this.printHeader("Document about to be changed");
		this.printDocumentEvent(event);
	}

	public final void documentChanged(final DocumentEvent event)
	{
		this.printHeader("Document changed");
		this.printDocumentEvent(event);
	}
	
	private final void printDocumentEvent(final DocumentEvent event)
	{
		this.put(Debug.dumpDocumentEvent(event));
	}
}
