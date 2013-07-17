package sk.stuba.fiit.perconik.listeners;

class KnownResources
{
	static final Resource<FileBufferListener> fileBuffer;

	static final Resource<LaunchListener> launch;
	
	static final Resource<LaunchConfigurationListener> launchConfiguration;
	
	static final Resource<OperationHistoryListener> operationHistory;
	
	static final Resource<PartListener> part;
	
	static final Resource<RefactoringExecutionListener> refactoringExecution;

	static final Resource<SelectionListener> selection;
	
	static
	{
		fileBuffer           = build(FileBufferListener.class, FileBufferHandler.INSTANCE);
		launch               = build(LaunchListener.class, LaunchHandler.INSTANCE);
		launchConfiguration  = build(LaunchConfigurationListener.class, LaunchConfigurationHandler.INSTANCE);
		operationHistory     = build(OperationHistoryListener.class, OperationHistoryHandler.INSTANCE);
		part                 = build(PartListener.class, PartHandler.INSTANCE);
		refactoringExecution = build(RefactoringExecutionListener.class, RefactoringExecutionHandler.INSTANCE);
		selection            = build(SelectionListener.class, SelectionHandler.INSTANCE);
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
