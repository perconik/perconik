package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.core.utilities.SmartStringBuilder;

public final class PartDebugListener extends AbstractDebugListener implements PartListener
{
	public PartDebugListener()
	{
	}
	
	public PartDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void partOpened(final IWorkbenchPart part)
	{
		this.print("Part opened:");
		this.printPage(part);
	}

	public final void partClosed(final IWorkbenchPart part)
	{
		this.print("Part closed:");
		this.printPage(part);
	}

	public final void partActivated(final IWorkbenchPart part)
	{
		this.print("Part activated:");
		this.printPage(part);
	}

	public final void partDeactivated(final IWorkbenchPart part)
	{
		this.print("Part deactivated:");
		this.printPage(part);
	}

	public final void partBroughtToTop(final IWorkbenchPart part)
	{
		this.print("Part brought to top:");
		this.printPage(part);
	}
	
	private final void printPage(final IWorkbenchPart part)
	{
		this.put(dumpPart(part));
	}
	
	static final String dumpPart(final IWorkbenchPart part)
	{
		SmartStringBuilder builder = new SmartStringBuilder().tab();
		
		String title   = part.getTitle();
		String tooltip = part.getTitleToolTip();

		builder.append("title: ").appendln(title);
		builder.append("tooltip: ").appendln(tooltip);
		
		return builder.toString();
	}
}
