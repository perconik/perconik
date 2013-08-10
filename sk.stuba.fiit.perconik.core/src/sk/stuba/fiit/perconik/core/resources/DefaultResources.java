package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.ResourceService;
import sk.stuba.fiit.perconik.core.listeners.CommandChangeListener;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.core.listeners.CommandManagerChangeListener;
import sk.stuba.fiit.perconik.core.listeners.CompletionListener;
import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;
import sk.stuba.fiit.perconik.core.listeners.DocumentChangeListener;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.core.listeners.JavaElementChangeListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;
import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;
import sk.stuba.fiit.perconik.core.listeners.RefactoringExecutionListener;
import sk.stuba.fiit.perconik.core.listeners.RefactoringHistoryListener;
import sk.stuba.fiit.perconik.core.listeners.ResourceChangeListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;

// TODO consider adding
/*

add experimental

StyledTextListener extends org.eclipse.swt.widgets.Listener

add custom

EditorListener

MarkSelectionListener
StructuredSelectionListener
TextSelectionListener

 */

public class DefaultResources
{
	private static final ResourceService service = new GenericResourceService();
	
	static final Resource<CommandChangeListener> commandChange;

	static final Resource<CommandExecutionListener> commandExecution;

	static final Resource<CommandManagerChangeListener> commandManagerChange;

	static final Resource<CompletionListener> completion;

	static final Resource<DebugEventsListener> debugEvents;

	static final Resource<DocumentChangeListener> documentChange;

	static final Resource<FileBufferListener> fileBuffer;

	static final Resource<JavaElementChangeListener> javaElementChange;

	static final Resource<LaunchListener> launch;
	
	static final Resource<LaunchesListener> launches;

	static final Resource<LaunchConfigurationListener> launchConfiguration;
	
	static final Resource<OperationHistoryListener> operationHistory;
	
	static final Resource<PageListener> page;
	
	static final Resource<PartListener> part;
	
	static final Resource<PerspectiveListener> perspective;

	static final Resource<RefactoringExecutionListener> refactoringExecution;

	static final Resource<RefactoringHistoryListener> refactoringHistory;

	static final Resource<ResourceChangeListener> resourceChange;

	static final Resource<SelectionListener> selection;
	
	static final Resource<TestRunListener> testRun;

	static final Resource<WindowListener> window;

	static final Resource<WorkbenchListener> workbench;

	static
	{
		commandChange        = build(CommandChangeListener.class, CommandChangeHandler.INSTANCE);
		commandExecution     = build(CommandExecutionListener.class, CommandExecutionHandler.INSTANCE);
		commandManagerChange = build(CommandManagerChangeListener.class, CommandManagerChangeHandler.INSTANCE);
		completion           = build(CompletionListener.class, CompletionHandler.INSTANCE);
		debugEvents          = build(DebugEventsListener.class, DebugEventsHandler.INSTANCE);
		documentChange       = build(DocumentChangeListener.class, DocumentChangeHandler.INSTANCE);
		fileBuffer           = build(FileBufferListener.class, FileBufferHandler.INSTANCE);
		javaElementChange    = build(JavaElementChangeListener.class, JavaElementChangeHandler.INSTANCE);
		launch               = build(LaunchListener.class, LaunchHandler.INSTANCE);
		launchConfiguration  = build(LaunchConfigurationListener.class, LaunchConfigurationHandler.INSTANCE);
		launches             = build(LaunchesListener.class, LaunchesHandler.INSTANCE);
		operationHistory     = build(OperationHistoryListener.class, OperationHistoryHandler.INSTANCE);
		page                 = build(PageListener.class, PageHandler.INSTANCE);
		part                 = build(PartListener.class, PartHandler.INSTANCE);
		perspective          = build(PerspectiveListener.class, PerspectiveHandler.INSTANCE);
		refactoringExecution = build(RefactoringExecutionListener.class, RefactoringExecutionHandler.INSTANCE);
		refactoringHistory   = build(RefactoringHistoryListener.class, RefactoringHistoryHandler.INSTANCE);
		resourceChange       = build(ResourceChangeListener.class, ResourceChangeHandler.INSTANCE);
		selection            = build(SelectionListener.class, SelectionHandler.INSTANCE);
		testRun              = build(TestRunListener.class, TestRunHandler.INSTANCE);
		window               = build(WindowListener.class, WindowHandler.INSTANCE);
		workbench            = build(WorkbenchListener.class, WorkbenchHandler.INSTANCE);
	}
	
	private DefaultResources()
	{
		throw new AssertionError();
	}
	
	public static final ResourceService getDefaultResourceService()
	{
		return DefaultResources.service;
	}

	private static final <T extends Listener> Resource<T> build(final Class<T> type, final Handler<T> handler)
	{
		Resource<T> resource = new GenericResource<>(Pools.getListenerPoolFactory().create(handler));
		
		DefaultResources.getDefaultResourceService().register(type, resource);
		
		return resource;
	}

	public static final Resource<CommandChangeListener> getCommandChangeResource()
	{
		return DefaultResources.commandChange;
	}

	public static final Resource<CommandExecutionListener> getCommandExecutionResource()
	{
		return DefaultResources.commandExecution;
	}

	public static final Resource<CommandManagerChangeListener> getCommandManagerChangeResource()
	{
		return DefaultResources.commandManagerChange;
	}

	public static final Resource<CompletionListener> getCompletionResource()
	{
		return DefaultResources.completion;
	}

	public static final Resource<DebugEventsListener> getDebugEventsResource()
	{
		return DefaultResources.debugEvents;
	}

	public static final Resource<DocumentChangeListener> getDocumentChangeResource()
	{
		return DefaultResources.documentChange;
	}

	public static final Resource<FileBufferListener> getFileBufferResource()
	{
		return DefaultResources.fileBuffer;
	}

	public static final Resource<JavaElementChangeListener> getJavaElementChangeResource()
	{
		return DefaultResources.javaElementChange;
	}

	public static final Resource<LaunchListener> getLaunchResource()
	{
		return DefaultResources.launch;
	}
	
	public static final Resource<LaunchesListener> getLaunchesResource()
	{
		return DefaultResources.launches;
	}

	public static final Resource<LaunchConfigurationListener> getLaunchConfigurationResource()
	{
		return DefaultResources.launchConfiguration;
	}

	public static final Resource<OperationHistoryListener> getOperationHistoryResource()
	{
		return DefaultResources.operationHistory;
	}

	public static final Resource<PageListener> getPageResource()
	{
		return DefaultResources.page;
	}

	public static final Resource<PartListener> getPartResource()
	{
		return DefaultResources.part;
	}

	public static final Resource<PerspectiveListener> getPerspectiveResource()
	{
		return DefaultResources.perspective;
	}

	public static final Resource<RefactoringExecutionListener> getRefactoringExecutionResource()
	{
		return DefaultResources.refactoringExecution;
	}

	public static final Resource<RefactoringHistoryListener> getRefactoringHistoryResource()
	{
		return DefaultResources.refactoringHistory;
	}

	public static final Resource<ResourceChangeListener> getResourceChangeResource()
	{
		return DefaultResources.resourceChange;
	}

	public static final Resource<SelectionListener> getSelectionResource()
	{
		return DefaultResources.selection;
	}

	public static final Resource<TestRunListener> getTestRunResource()
	{
		return DefaultResources.testRun;
	}

	public static final Resource<WindowListener> getWindowResource()
	{
		return DefaultResources.window;
	}

	public static final Resource<WorkbenchListener> getWorkbenchResource()
	{
		return DefaultResources.workbench;
	}
}
