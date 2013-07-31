package sk.stuba.fiit.perconik.core.listeners;

// TODO consider adding
/*

add

IExecutionListener
IExecutionListenerWithChecks
ICommandListener
ICommandManagerListener

IDocumentListener
ICompletionListener

IDebugEventSetListener

add experimental

StyledTextListener extends org.eclipse.swt.widgets.Listener

add custom

MarkSelectionListener
StructuredSelectionListener
TextSelectionListener

 */

class KnownResources
{
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
	
	private KnownResources()
	{
		throw new AssertionError();
	}
	
	private static final <T extends Listener> Resource<T> build(final Class<T> type, Handler<T> handler)
	{
		Resource<T> resource = Resources.create(handler);
		
		Resources.register(type, resource);
		
		return resource;
	}
}
