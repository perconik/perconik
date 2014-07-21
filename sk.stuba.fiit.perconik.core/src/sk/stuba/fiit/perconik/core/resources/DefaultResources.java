package sk.stuba.fiit.perconik.core.resources;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.ListenerRegistrationException;
import sk.stuba.fiit.perconik.core.ListenerUnregistrationException;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.listeners.CommandCategoryListener;
import sk.stuba.fiit.perconik.core.listeners.CommandContextListener;
import sk.stuba.fiit.perconik.core.listeners.CommandContextManagerListener;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.core.listeners.CommandHandlerListener;
import sk.stuba.fiit.perconik.core.listeners.CommandListener;
import sk.stuba.fiit.perconik.core.listeners.CommandManagerListener;
import sk.stuba.fiit.perconik.core.listeners.CompletionListener;
import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.core.listeners.GitConfigurationListener;
import sk.stuba.fiit.perconik.core.listeners.GitIndexListener;
import sk.stuba.fiit.perconik.core.listeners.GitReferenceListener;
import sk.stuba.fiit.perconik.core.listeners.GitRepositoryListener;
import sk.stuba.fiit.perconik.core.listeners.JavaElementListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;
import sk.stuba.fiit.perconik.core.listeners.MarkSelectionListener;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;
import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;
import sk.stuba.fiit.perconik.core.listeners.RefactoringExecutionListener;
import sk.stuba.fiit.perconik.core.listeners.RefactoringHistoryListener;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;
import sk.stuba.fiit.perconik.core.listeners.SearchResultListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManagers;
import sk.stuba.fiit.perconik.core.services.resources.ResourceNamesSupplier;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider.Builder;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProviders;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceServices;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;

import static sk.stuba.fiit.perconik.core.utilities.LogHelper.log;

/**
 * Static accessor methods pertaining to default resource core implementation.
 *
 * <p>The core implementation includes default {@code Resource}
 * instances along with respective {@code ResourceNamesSupplier}
 * as well as default {@code ResourceService}, {@code ResourceProvider}
 * and {@code ResourceManager}.
 *
 * <p>The default implementations of resources as well as resource provider,
 * manager and service are still available by this class even if the respective
 * resources are unregistered from the core or the services are switched or
 * stopped.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class DefaultResources
{
	static final Resource<CommandListener> command;

	static final Resource<CommandCategoryListener> commandCategory;

	static final Resource<CommandContextListener> commandContext;

	static final Resource<CommandContextManagerListener> commandContextManager;

	static final Resource<CommandExecutionListener> commandExecution;

	static final Resource<CommandHandlerListener> commandHandler;

	static final Resource<CommandManagerListener> commandManager;

	static final Resource<CompletionListener> completion;

	static final Resource<DebugEventsListener> debugEvents;

	static final Resource<DocumentListener> document;

	static final Resource<EditorListener> editor;

	static final Resource<FileBufferListener> fileBuffer;

	static final Resource<GitConfigurationListener> gitConfiguration;

	static final Resource<GitIndexListener> gitIndex;

	static final Resource<GitReferenceListener> gitReference;

	static final Resource<GitRepositoryListener> gitRepository;

	static final Resource<JavaElementListener> javaElement;

	static final Resource<LaunchListener> launch;

	static final Resource<LaunchesListener> launches;

	static final Resource<LaunchConfigurationListener> launchConfiguration;

	static final Resource<MarkSelectionListener> markSelection;

	static final Resource<OperationHistoryListener> operationHistory;

	static final Resource<PageListener> page;

	static final Resource<PartListener> part;

	static final Resource<PerspectiveListener> perspective;

	static final Resource<RefactoringExecutionListener> refactoringExecution;

	static final Resource<RefactoringHistoryListener> refactoringHistory;

	static final Resource<ResourceListener> resource;

	static final Resource<SearchQueryListener> searchQuery;

	static final Resource<SearchResultListener> searchResult;

	static final Resource<SelectionListener> selection;

	static final Resource<StructuredSelectionListener> structuredSelection;

	static final Resource<TestRunListener> testRun;

	static final Resource<TextSelectionListener> textSelection;

	static final Resource<WindowListener> window;

	static final Resource<WorkbenchListener> workbench;

	static final ResourceProvider provider;

	static
	{
		ResourceProvider.Builder builder = ResourceProviders.builder();

		command               = forge(CommandListener.class, CommandHandler.INSTANCE, builder);
		commandCategory       = forge(CommandCategoryListener.class, CommandCategoryHandler.INSTANCE, builder);
		commandContext        = forge(CommandContextListener.class, CommandContextHandler.INSTANCE, builder);
		commandContextManager = forge(CommandContextManagerListener.class, CommandContextManagerHandler.INSTANCE, builder);
		commandExecution      = forge(CommandExecutionListener.class, CommandExecutionHandler.INSTANCE, builder);
		commandHandler        = forge(CommandHandlerListener.class, CommandHandlerHandler.INSTANCE, builder);
		commandManager        = forge(CommandManagerListener.class, CommandManagerHandler.INSTANCE, builder);
		completion            = forge(CompletionListener.class, CompletionHandler.INSTANCE, builder);
		debugEvents           = forge(DebugEventsListener.class, DebugEventsHandler.INSTANCE, builder);
		document              = forge(DocumentListener.class, DocumentHandler.INSTANCE, builder);
		editor                = forge(EditorListener.class, EditorHandler.INSTANCE, builder);
		fileBuffer            = forge(FileBufferListener.class, FileBufferHandler.INSTANCE, builder);
		gitConfiguration      = forge(GitConfigurationListener.class, GitConfigurationHandler.INSTANCE, builder);
		gitIndex              = forge(GitIndexListener.class, GitIndexHandler.INSTANCE, builder);
		gitReference          = forge(GitReferenceListener.class, GitReferenceHandler.INSTANCE, builder);
		gitRepository         = forge(GitRepositoryListener.class, GitRepositoryHandler.INSTANCE, builder);
		javaElement           = forge(JavaElementListener.class, JavaElementHandler.INSTANCE, builder);
		launch                = forge(LaunchListener.class, LaunchHandler.INSTANCE, builder);
		launchConfiguration   = forge(LaunchConfigurationListener.class, LaunchConfigurationHandler.INSTANCE, builder);
		launches              = forge(LaunchesListener.class, LaunchesHandler.INSTANCE, builder);
		markSelection         = forge(MarkSelectionListener.class, MarkSelectionHandler.INSTANCE, builder);
		operationHistory      = forge(OperationHistoryListener.class, OperationHistoryHandler.INSTANCE, builder);
		page                  = forge(PageListener.class, PageHandler.INSTANCE, builder);
		part                  = forge(PartListener.class, PartHandler.INSTANCE, builder);
		perspective           = forge(PerspectiveListener.class, PerspectiveHandler.INSTANCE, builder);
		refactoringExecution  = forge(RefactoringExecutionListener.class, RefactoringExecutionHandler.INSTANCE, builder);
		refactoringHistory    = forge(RefactoringHistoryListener.class, RefactoringHistoryHandler.INSTANCE, builder);
		resource              = forge(ResourceListener.class, ResourceHandler.INSTANCE, builder);
		searchQuery           = forge(SearchQueryListener.class, SearchQueryHandler.INSTANCE, builder);
		searchResult          = forge(SearchResultListener.class, SearchResultHandler.INSTANCE, builder);
		selection             = forge(SelectionListener.class, SelectionHandler.INSTANCE, builder);
		structuredSelection   = forge(StructuredSelectionListener.class, StructuredSelectionHandler.INSTANCE, builder);
		testRun               = forge(TestRunListener.class, TestRunHandler.INSTANCE, builder);
		textSelection         = forge(TextSelectionListener.class, TextSelectionHandler.INSTANCE, builder);
		window                = forge(WindowListener.class, WindowHandler.INSTANCE, builder);
		workbench             = forge(WorkbenchListener.class, WorkbenchHandler.INSTANCE, builder);

		provider = builder.build();
	}

	private DefaultResources()
	{
		throw new AssertionError();
	}

	private static final <L extends Listener> void safePreRegister(final L listener)
	{
		try
		{
			listener.preRegister();
		}
		catch (Exception failure)
		{
			throw MoreThrowables.initializeCause(new ListenerRegistrationException(), failure);
		}
	}

	private static final <L extends Listener> void safeRegisterTo(final Resource<? super L> resource, final L listener)
	{
		try
		{
			resource.register(listener);
		}
		catch (UnsupportedResourceException failure)
		{
			log.error(failure, "Unsupported resource %s failed registering listener %s", resource, listener);
		}
	}

	private static final <L extends Listener> void safePostRegister(final Listener listener)
	{
		try
		{
			listener.postRegister();
		}
		catch (Exception failure)
		{
			throw MoreThrowables.initializeCause(new ListenerRegistrationException(), failure);
		}
	}

	private static final <L extends Listener> void safePreUnregister(final L listener)
	{
		try
		{
			listener.preUnregister();
		}
		catch (Exception failure)
		{
			throw MoreThrowables.initializeCause(new ListenerUnregistrationException(), failure);
		}
	}

	private static final <L extends Listener> void safeUnregisterFrom(final Resource<? super L> resource, final L listener)
	{
		try
		{
			resource.unregister(listener);
		}
		catch (UnsupportedResourceException failure)
		{
			log.error(failure, "Unsupported resource %s failed unregistering listener %s", resource, listener);
		}
	}

	private static final <L extends Listener> void safePostUnregister(final L listener)
	{
		try
		{
			listener.postUnregister();
		}
		catch (Exception failure)
		{
			throw MoreThrowables.initializeCause(new ListenerUnregistrationException(), failure);
		}
	}

	public static final <L extends Listener> void registerTo(final Resource<L> resource, final L listener)
	{
		safePreRegister(listener);
		safeRegisterTo(resource, listener);
		safePostRegister(listener);
	}

	public static final <L extends Listener> void registerTo(final Iterable<Resource<? super L>> resources, final L listener)
	{
		safePreRegister(listener);

		for (Resource<? super L> resource: resources)
		{
			safeRegisterTo(resource, listener);
		}

		safePostRegister(listener);
	}

	public static final <L extends Listener> void unregisterFrom(final Resource<L> resource, final L listener)
	{
		safePreUnregister(listener);
		safeUnregisterFrom(resource, listener);
		safePostUnregister(listener);
	}

	public static final <L extends Listener> void unregisterFrom(final Iterable<Resource<? super L>> resources, final L listener)
	{
		safePreUnregister(listener);

		for (Resource<? super L> resource: resources)
		{
			safeUnregisterFrom(resource, listener);
		}

		safePostUnregister(listener);
	}

	private static final class ManagerHolder
	{
		static final ResourceManager instance;

		static
		{
			instance = ResourceManagers.create();
		}

		private ManagerHolder()
		{
			throw new AssertionError();
		}
	}

	private static final class ServiceHolder
	{
		static final ResourceService instance;

		static
		{
			ResourceService.Builder builder = ResourceServices.builder();

			builder.provider(provider);
			builder.manager(ManagerHolder.instance);

			instance = builder.build();
		}

		private ServiceHolder()
		{
			throw new AssertionError();
		}
	}

	/**
	 * Returns the default resource provider. The returned provider is a
	 * standard resource provider constructed using the standard provider
	 * builder from {@link ResourceProviders#builder()} factory method.
	 * Its direct parent and the only predecessor in the resource provider
	 * hierarchy is the system resource provider.
	 *
	 * <p>The default resource provider is lazily
	 * initialized at the first call of this method.
	 *
	 * @return the default resource provider
	 *
	 * @see ResourceProvider
	 * @see ResourceProviders#builder()
	 * @see ResourceProviders#getSystemProvider()
	 */
	public static final ResourceProvider getDefaultResourceProvider()
	{
		return provider;
	}

	/**
	 * Returns the default resource manager. The returned
	 * manager is a standard resource manager constructed by
	 * the {@link ResourceManagers#create()} factory method.
	 *
	 * <p>The default resource provider is lazily
	 * initialized at the first call of this method.
	 *
	 * @return the default resource manager
	 *
	 * @see ResourceManager
	 * @see ResourceManagers#create()
	 */
	public static final ResourceManager getDefaultResourceManager()
	{
		return ManagerHolder.instance;
	}

	/**
	 * Returns the default resource service. The returned service is a
	 * standard resource service constructed using the standard service
	 * builder from {@link ResourceServices#builder()} factory method.
	 * It contains the default resource provider and manager.
	 *
	 * <p>The default resource provider is lazily
	 * initialized at the first call of this method.
	 *
	 * <p><b>Note:</b> The returned service may be unusable if it
	 * has been retrieved by this method earlier and then stopped.
	 *
	 * @return the default resource service
	 *
	 * @see ResourceService
	 * @see ResourceServices#builder()
	 * @see #getDefaultResourceProvider()
	 * @see #getDefaultResourceManager()
	 */
	public static final ResourceService getDefaultResourceService()
	{
		return ServiceHolder.instance;
	}

	/**
	 * Returns the default resource names supplier.
	 * The built supplier dynamically supplies resource
	 * names associated with listener types based on the
	 * currently used {@code ResourceProvider} obtained by this
	 * {@code Services.getResourceService().getResourceProvider()}
	 * method call at supplying.
	 *
	 * @return the default resource names supplier
	 *
	 * @see ResourceNamesSupplier
	 * @see #getDefaultResourceProvider()
	 */
	public static final ResourceNamesSupplier getDefaultResourceNamesSupplier()
	{
		return new ResourceNamesSupplier()
		{
			public final SetMultimap<Class<? extends Listener>, String> get()
			{
				return ResourceProviders.toResourceNamesMultimap(Services.getResourceService().getResourceProvider());
			}
		};
	}

	private static final <L extends Listener> Resource<L> forge(final Class<L> type, final Handler<L> handler, final Builder builder)
	{
		boolean unsupported = handler.getClass().isAnnotationPresent(Unimplemented.class);

		Resource<L> resource = StandardResource.newInstance(Pools.safe(Pools.getListenerPoolFactory().create(handler), type), unsupported);

		builder.add(type, resource);

		return resource;
	}

	public static final Resource<CommandListener> getCommandResource()
	{
		return DefaultResources.command;
	}

	public static final Resource<CommandCategoryListener> getCommandCategoryResource()
	{
		return DefaultResources.commandCategory;
	}

	public static final Resource<CommandContextListener> getCommandContextResource()
	{
		return DefaultResources.commandContext;
	}

	public static final Resource<CommandContextManagerListener> getCommandContextManagerResource()
	{
		return DefaultResources.commandContextManager;
	}

	public static final Resource<CommandExecutionListener> getCommandExecutionResource()
	{
		return DefaultResources.commandExecution;
	}

	public static final Resource<CommandHandlerListener> getCommandHandlerResource()
	{
		return DefaultResources.commandHandler;
	}

	public static final Resource<CommandManagerListener> getCommandManagerResource()
	{
		return DefaultResources.commandManager;
	}

	public static final Resource<CompletionListener> getCompletionResource()
	{
		return DefaultResources.completion;
	}

	public static final Resource<DebugEventsListener> getDebugEventsResource()
	{
		return DefaultResources.debugEvents;
	}

	public static final Resource<DocumentListener> getDocumentResource()
	{
		return DefaultResources.document;
	}

	public static final Resource<EditorListener> getEditorResource()
	{
		return DefaultResources.editor;
	}

	public static final Resource<FileBufferListener> getFileBufferResource()
	{
		return DefaultResources.fileBuffer;
	}

	public static final Resource<GitConfigurationListener> getGitConfigurationResource()
	{
		return DefaultResources.gitConfiguration;
	}

	public static final Resource<GitIndexListener> getGitIndexResource()
	{
		return DefaultResources.gitIndex;
	}

	public static final Resource<GitReferenceListener> getGitReferenceResource()
	{
		return DefaultResources.gitReference;
	}

	public static final Resource<GitRepositoryListener> getGitRepositoryResource()
	{
		return DefaultResources.gitRepository;
	}

	public static final Resource<JavaElementListener> getJavaElementResource()
	{
		return DefaultResources.javaElement;
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

	public static final Resource<MarkSelectionListener> getMarkSelectionResource()
	{
		return DefaultResources.markSelection;
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

	public static final Resource<ResourceListener> getResourceResource()
	{
		return DefaultResources.resource;
	}

	public static final Resource<SearchQueryListener> getSearchQueryResource()
	{
		return DefaultResources.searchQuery;
	}

	public static final Resource<SearchResultListener> getSearchResultResource()
	{
		return DefaultResources.searchResult;
	}

	public static final Resource<SelectionListener> getSelectionResource()
	{
		return DefaultResources.selection;
	}

	public static final Resource<StructuredSelectionListener> getStructuredSelectionResource()
	{
		return DefaultResources.structuredSelection;
	}

	public static final Resource<TestRunListener> getTestRunResource()
	{
		return DefaultResources.testRun;
	}

	public static final Resource<TextSelectionListener> getTextSelectionResource()
	{
		return DefaultResources.textSelection;
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
