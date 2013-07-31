package sk.stuba.fiit.perconik.debug;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptorProxy;
import org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent;
import org.eclipse.ltk.core.refactoring.history.RefactoringHistoryEvent;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.debug.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.core.commands.operations.OperationHistoryEventType;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.StatusSeverity;
import sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringExecutionEventType;
import sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringHistoryEventType;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import com.google.common.collect.ImmutableList;

public final class Debug
{
	private Debug()
	{
		throw new AssertionError();
	}
	
	private static final class ConsoleHolder
	{
		static final PluginConsole console = PluginConsole.of(Activator.getDefault());
		
		private ConsoleHolder()
		{
			throw new AssertionError();
		}
	}
	
	public static final PluginConsole getDefaultConsole()
	{
		return ConsoleHolder.console;
	}
	
	private static final PluginConsole console()
	{
		return getDefaultConsole();
	}

	public static final void put(final String message)
	{
		console().put(message);
	}
	
	public static final void put(final String format, final Object ... args)
	{
		console().put(format, args);
	}
	
	public static final void print(final String message)
	{
		console().print(message);
	}
	
	public static final void print(final String format, final Object ... args)
	{
		console().print(format, args);
	}

	public static final void notice(final String message)
	{
		console().notice(message);
	}
	
	public static final void notice(final String format, Object ... args)
	{
		console().notice(format, args);
	}
	
	public static final void warning(final String message)
	{
		console().warning(message);
	}

	public static final void warning(final String format, Object ... args)
	{
		console().warning(format, args);
	}

	public static final void error(final String message, final Exception e)
	{
		console().error(message, e);
	}
	
	private static final SmartStringBuilder builder()
	{
		return new SmartStringBuilder().tab();
	}
	
	public static final String dumpHeader(final String title)
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.appendln().appendln(dumpTime()).appendln();
		builder.format("%s:", title).appendln();
		
		return builder.toString();
	}
	
	public static final String dumpClass(final Class<?> type)
	{
		String name = type.getCanonicalName();
		
		if (name != null)
		{
			return name;
		}
		
		return type.getName();
	}
	
	public static final String dumpBlock(final Object key, final Object value)
	{
		SmartStringBuilder builder = builder();
	
		return builder.append(key).appendln(':').lines(value).toString();
	}

	public static final String dumpLine(final Object key, final Object value)
	{
		SmartStringBuilder builder = builder();
	
		return builder.append(key).append(": ").appendln(value).toString();
	}
	
	public static final String dumpTime()
	{
		return dumpTime(new Date());
	}

	public static final String dumpTime(final Date date)
	{
		return TimeUtilities.format(date);
	}

	private static final class TimeUtilities
	{
		private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		static final String format(final Date date)
		{
			return formatter.format(date);
		}
	}

	public static final String dumpFileBuffer(final IFileBuffer buffer)
	{
		SmartStringBuilder builder = builder();
		
		IPath   location = buffer.getLocation();
		IStatus status   = buffer.getStatus();
		
		long modificationStamp = buffer.getModificationStamp();
		
		boolean commitable     = buffer.isCommitable();
		boolean dirty          = buffer.isDirty();
		boolean shared         = buffer.isShared();
		boolean stateValidated = buffer.isStateValidated();
		
		boolean synchronizationContextRequested = buffer.isSynchronizationContextRequested();
		boolean synchronizedWithFileSystem      = buffer.isSynchronized();

		builder.append("location: ").appendln(location);
		
		if (status != null)
		{
			builder.appendln("status:").lines(dumpStatus(status));
		}
		
		builder.append("modification stamp: ").appendln(modificationStamp < 0 ? "unknown" : modificationStamp);
		
		builder.append("commitable: ").appendln(commitable);
		builder.append("dirty: ").appendln(dirty);
		builder.append("shared: ").appendln(shared);
		builder.append("state validated: ").appendln(stateValidated);
		
		builder.append("synchronization context requested: ").appendln(synchronizationContextRequested);
		builder.append("synchronized with file system: ").appendln(synchronizedWithFileSystem);
		
		return builder.toString();
	}
	
	public static final String dumpLaunch(final ILaunch launch) throws CoreException
	{
		SmartStringBuilder builder = builder();
		
		ILaunchConfiguration configuration = launch.getLaunchConfiguration();
		
		String  mode = launch.getLaunchMode();
		
		builder.append("mode: ").appendln(mode);
		builder.appendln("configuration:").lines(dumpLaunchConfiguration(configuration));
	
		return builder.toString();
	}

	public static final String dumpLaunches(final ILaunch[] launches) throws CoreException
	{
		SmartStringBuilder builder = builder();

		if (launches.length != 0)
		{
			for (int i = 0; i < launches.length; i ++)
			{
				builder.format("launch %d:", i);
				builder.lines(dumpLaunch(launches[i]));
			}
		}
		else
		{
			builder.append("none");
		}
		
		return builder.toString();
	}
	
	public static final String dumpLaunchConfiguration(final ILaunchConfiguration configuration) throws CoreException
	{
		SmartStringBuilder builder = builder();
		
		ILaunchConfigurationType type = configuration.getType();
		IFile file = configuration.getFile();
		
		String      name     = configuration.getName();
		String      category = configuration.getCategory();
		Set<String> modes    = configuration.getModes();
		
		String  application = configuration.getAttribute("application", "?");
		String  product     = configuration.getAttribute("product", "?");
		boolean useProduct  = configuration.getAttribute("useProduct", false);
		
		builder.append("name: ").appendln(name);
		builder.append("category: ").appendln(category);
		builder.appendln("type:").lines(dumpLaunchConfigurationType(type));
		builder.append("modes: ").list(modes.isEmpty() ? ImmutableList.of("none") : modes).appendln();
		
		if (file != null)
		{
			builder.append("full path: ").appendln(file.getFullPath());
			builder.append("location: ").appendln(file.getLocation());
			builder.append("URI: ").appendln(file.getLocationURI());
		}
		
		builder.append("application: ").appendln(application);
		builder.append("product: ").append(product).append(" (use ").append(useProduct).appendln(")");
		
//		Map<Object, Object> attributes = configuration.getAttributes();
//		
//		for (Entry<Object, Object> entry: attributes.entrySet())
//		{
//			builder.append(entry.getKey()).append(": ").appendln(entry.getValue());
//		}

		return builder.toString();
	}

	public static final String dumpLaunchConfigurationType(final ILaunchConfigurationType type)
	{
		SmartStringBuilder builder = builder();
		
		String name             = type.getName();
		String category         = type.getCategory();
		String identifier       = type.getIdentifier();
		String pluginIdentifier = type.getPluginIdentifier();
		String contributorName  = type.getContributorName();

		builder.append("name: ").appendln(name);
		builder.append("category: ").appendln(category);
		builder.append("identifier: ").appendln(identifier);
		builder.append("plugin identifier: ").appendln(pluginIdentifier);
		builder.append("contributor name: ").appendln(contributorName);
		
		return builder.toString();
	}	
	
	public static final String dumpMarkSelection(final IMarkSelection selection)
	{
		SmartStringBuilder builder = builder();
	
		boolean empty = selection.isEmpty();
		
		int offset = selection.getOffset();
		int length = selection.getLength();
	
		builder.appendln("category: mark");
		
		builder.append("empty: ").appendln(empty);
		
		builder.append("offset: ").appendln(offset);
		builder.append("length: ").appendln(length);
		
		return builder.toString();
	}

	public static final String dumpOperationHistoryEvent(final OperationHistoryEvent event)
	{
		SmartStringBuilder builder = builder();

		IUndoableOperation operation = event.getOperation();
		IStatus            status    = event.getStatus();

		OperationHistoryEventType type = OperationHistoryEventType.valueOf(event.getEventType());
		
		builder.format("type: %s (%d)", type, type.getValue()).appendln();
		
		builder.appendln("operation:").lines(dumpUndoableOperation(operation));
		
		if (status != null)
		{
			builder.appendln("status:").lines(dumpStatus(status));
		}
		
		return builder.toString();
	}

	public static final String dumpPage(final IWorkbenchPage page)
	{
		SmartStringBuilder builder = builder();
		
		Class<?> type  = page.getClass();
		String   label = page.getLabel();
	
		builder.append("class: ").appendln(dumpClass(type));
		builder.append("label: ").appendln(label);
		
		return builder.toString();
	}

	public static final String dumpPart(final IWorkbenchPart part)
	{
		SmartStringBuilder builder = builder();
		
		Class<?> type    = part.getClass();
		String   title   = part.getTitle();
		String   tooltip = part.getTitleToolTip();
		
		builder.append("class: ").appendln(dumpClass(type));
		builder.append("title: ").appendln(title);
		builder.append("tooltip: ").appendln(tooltip);
		
		return builder.toString();
	}
	
	public static final String dumpPartReference(final IWorkbenchPartReference reference)
	{
		SmartStringBuilder builder = builder();
		
		Class<?> type = reference.getClass();
		String   id   = reference.getId();
		
		String   name    = reference.getPartName();
		String   title   = reference.getTitle();
		String   tooltip = reference.getTitleToolTip();
		
		boolean dirty = reference.isDirty();

		builder.append("class: ").appendln(dumpClass(type));
		builder.append("id: ").appendln(id);
		
		builder.append("name: ").appendln(name);
		builder.append("title: ").appendln(title);
		builder.append("tooltip: ").appendln(tooltip);
		
		builder.append("dirty: ").appendln(dirty);
		
		return builder.toString();
	}
	
	public static final String dumpPerspectiveDescriptor(final IPerspectiveDescriptor descriptor)
	{
		SmartStringBuilder builder = builder();
		
		Class<?> type = descriptor.getClass();
		String   id   = descriptor.getId();

		String   label       = descriptor.getLabel();
		String   description = descriptor.getDescription();

		builder.append("class: ").appendln(dumpClass(type));
		builder.append("id: ").appendln(id);
		
		builder.append("label: ").appendln(label);
		builder.append("description: ").appendln(description);
		
		return builder.toString();
	}

	public static final String dumpRefactoringDescriptorProxy(final RefactoringDescriptorProxy proxy)
	{
		SmartStringBuilder builder = builder();
		
		String project     = proxy.getProject();
		String description = proxy.getDescription();

		long timestamp = proxy.getTimeStamp();
	
		builder.append("project: ").appendln(project);
		builder.append("description: ").appendln(description);
		builder.append("timestamp: ").appendln(timestamp);
		
		return builder.toString();
	}

	public static final String dumpRefactoringExecutionEvent(final RefactoringExecutionEvent event)
	{
		SmartStringBuilder builder = builder();
		
		RefactoringExecutionEventType type = RefactoringExecutionEventType.valueOf(event.getEventType());
		
		RefactoringDescriptorProxy descriptor = event.getDescriptor();
		
		builder.format("type: %s (%d)", type, type.getValue()).appendln();
		builder.appendln("descriptor:").lines(dumpRefactoringDescriptorProxy(descriptor));
		
		return builder.toString();
	}
	
	public static final String dumpRefactoringHistoryEvent(final RefactoringHistoryEvent event)
	{
		SmartStringBuilder builder = builder();
		
		RefactoringHistoryEventType type = RefactoringHistoryEventType.valueOf(event.getEventType());
		
		RefactoringDescriptorProxy descriptor = event.getDescriptor();
		
		builder.format("type: %s (%d)", type, type.getValue()).appendln();
		builder.appendln("descriptor:").lines(dumpRefactoringDescriptorProxy(descriptor));
		
		return builder.toString();
	}

	public static final String dumpSelection(final ISelection selection)
	{
		if (selection instanceof IMarkSelection)
		{
			return dumpMarkSelection((IMarkSelection) selection);
		}
		else if (selection instanceof IStructuredSelection)
		{
			return dumpStructuredSelection((IStructuredSelection) selection);
		}
		else if (selection instanceof ITextSelection)
		{
			return dumpTextSelection((ITextSelection) selection);
		}
		else
		{
			SmartStringBuilder builder = builder();

			Class<?> type = selection.getClass();
			
			boolean empty = selection.isEmpty();
			
			builder.append("class: ").appendln(dumpClass(type));
			builder.append("empty: ").appendln(empty);
			
			return builder.toString();
		}
	}

	public static final String dumpStatus(final IStatus status)
	{
		SmartStringBuilder builder = builder();
	
		int code = status.getCode();
		
		StatusSeverity severity = StatusSeverity.valueOf(status.getSeverity());
		
		String plugin  = status.getPlugin();
		String message = status.getMessage();
		
		boolean ok    = status.isOK();
		boolean multi = status.isMultiStatus();
	
		Throwable throwable = status.getException();
	
		builder.append("code: ").appendln(code);
		builder.format("severity: %s (%d)", severity, severity.getValue()).appendln();
		
		builder.append("plugin: ").appendln(plugin);
		builder.append("message: ").appendln(message);
		
		builder.append("ok: ").appendln(ok);
		builder.append("multi: ").appendln(multi);
	
		if (throwable != null)
		{
			builder.appendln("exception:").lines(dumpThrowable(throwable));
		}
		
		return builder.toString();
	}

	public static final String dumpStructuredSelection(final IStructuredSelection selection)
	{
		SmartStringBuilder builder = builder();

		boolean empty = selection.isEmpty();
		
		int size = selection.size();

		builder.appendln("category: structured");
		
		builder.append("empty: ").appendln(empty);
		
		builder.append("size: ").appendln(size);
		
		return builder.toString();
	}

	public static final String dumpTextSelection(final ITextSelection selection)
	{
		SmartStringBuilder builder = builder();

		boolean empty = selection.isEmpty();
		
		int start = selection.getStartLine();
		int end   = selection.getEndLine();

		int offset = selection.getOffset();
		int length = selection.getLength();

		String text = selection.getText();

		builder.appendln("category: text");
		
		builder.append("empty: ").appendln(empty);
		
		builder.append("start line: ").appendln(start);
		builder.append("end line: ").appendln(end);

		builder.append("offset: ").appendln(offset);
		builder.append("length: ").appendln(length);

		builder.append("text: \"").append(text).appendln('"');

		return builder.toString();
	}
	
	public static final String dumpThrowable(final Throwable throwable)
	{
		SmartStringBuilder builder = builder();

		Class<?> type    = throwable.getClass();
		String   message = throwable.getMessage();

		builder.append("class: ").appendln(dumpClass(type));
		builder.append("message: ").appendln(message);
		
		return builder.toString();
	}
	
	public static final String dumpUndoableOperation(final IUndoableOperation operation)
	{
		SmartStringBuilder builder = builder();

		String label = operation.getLabel();

		boolean execute = operation.canExecute();
		boolean redo    = operation.canRedo();
		boolean undo    = operation.canUndo();
		
		builder.append("label: ").appendln(label);
		
		builder.append("execute: ").appendln(execute);
		builder.append("redo: ").appendln(redo);
		builder.append("undo: ").appendln(undo);
		
		return builder.toString();		
	}
	
	public static final String dumpWindow(final IWorkbenchWindow window)
	{
		SmartStringBuilder builder = builder();
	
		Class<?> type       = window.getClass();
		int      pagesCount = window.getPages().length;
	
		IWorkbenchPage activePage = window.getActivePage();
		
		builder.append("class: ").appendln(dumpClass(type));
		builder.append("pages: ").appendln(pagesCount);
		
		builder.appendln("active page:").lines(dumpPage(activePage));
		
		return builder.toString();
	}

	public static final String dumpWorkbench(final IWorkbench workbench)
	{
		SmartStringBuilder builder = builder();

		Class<?> type        = workbench.getClass();
		int      windowCount = workbench.getWorkbenchWindowCount();
		
		boolean starting = workbench.isStarting();
		boolean closing  = workbench.isClosing();

		builder.append("class: ").appendln(dumpClass(type));
		builder.append("windows: ").appendln(windowCount);
		
		builder.append("starting: ").appendln(starting);
		builder.append("closing: ").appendln(closing);
		
		return builder.toString();
	}
}
