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
import sk.stuba.fiit.perconik.core.listeners.TextInputListener;
import sk.stuba.fiit.perconik.core.listeners.TextListener;
import sk.stuba.fiit.perconik.core.listeners.TextPresentationListener;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;
import sk.stuba.fiit.perconik.core.listeners.ViewListener;
import sk.stuba.fiit.perconik.core.listeners.ViewportListener;
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

import static sk.stuba.fiit.perconik.core.plugin.Activator.defaultInstance;
import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeCause;

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
public final class DefaultResources {
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

  static final Resource<TextListener> text;

  static final Resource<TextInputListener> textInput;

  static final Resource<TextPresentationListener> textPresentation;

  static final Resource<TextSelectionListener> textSelection;

  static final Resource<ViewListener> view;

  static final Resource<ViewportListener> viewport;

  static final Resource<WindowListener> window;

  static final Resource<WorkbenchListener> workbench;

  static final ResourceProvider provider;

  static {
    ResourceProvider.Builder builder = ResourceProviders.builder();

    command = forge(CommandListener.class, CommandHandler.INSTANCE, builder);
    commandCategory = forge(CommandCategoryListener.class, CommandCategoryHandler.INSTANCE, builder);
    commandContext = forge(CommandContextListener.class, CommandContextHandler.INSTANCE, builder);
    commandContextManager = forge(CommandContextManagerListener.class, CommandContextManagerHandler.INSTANCE, builder);
    commandExecution = forge(CommandExecutionListener.class, CommandExecutionHandler.INSTANCE, builder);
    commandHandler = forge(CommandHandlerListener.class, CommandHandlerHandler.INSTANCE, builder);
    commandManager = forge(CommandManagerListener.class, CommandManagerHandler.INSTANCE, builder);
    completion = forge(CompletionListener.class, CompletionHandler.INSTANCE, builder);
    debugEvents = forge(DebugEventsListener.class, DebugEventsHandler.INSTANCE, builder);
    document = forge(DocumentListener.class, DocumentHandler.INSTANCE, builder);
    editor = forge(EditorListener.class, EditorHandler.INSTANCE, builder);
    fileBuffer = forge(FileBufferListener.class, FileBufferHandler.INSTANCE, builder);
    gitConfiguration = forge(GitConfigurationListener.class, GitConfigurationHandler.INSTANCE, builder);
    gitIndex = forge(GitIndexListener.class, GitIndexHandler.INSTANCE, builder);
    gitReference = forge(GitReferenceListener.class, GitReferenceHandler.INSTANCE, builder);
    gitRepository = forge(GitRepositoryListener.class, GitRepositoryHandler.INSTANCE, builder);
    javaElement = forge(JavaElementListener.class, JavaElementHandler.INSTANCE, builder);
    launch = forge(LaunchListener.class, LaunchHandler.INSTANCE, builder);
    launchConfiguration = forge(LaunchConfigurationListener.class, LaunchConfigurationHandler.INSTANCE, builder);
    launches = forge(LaunchesListener.class, LaunchesHandler.INSTANCE, builder);
    markSelection = forge(MarkSelectionListener.class, MarkSelectionHandler.INSTANCE, builder);
    operationHistory = forge(OperationHistoryListener.class, OperationHistoryHandler.INSTANCE, builder);
    page = forge(PageListener.class, PageHandler.INSTANCE, builder);
    part = forge(PartListener.class, PartHandler.INSTANCE, builder);
    perspective = forge(PerspectiveListener.class, PerspectiveHandler.INSTANCE, builder);
    refactoringExecution = forge(RefactoringExecutionListener.class, RefactoringExecutionHandler.INSTANCE, builder);
    refactoringHistory = forge(RefactoringHistoryListener.class, RefactoringHistoryHandler.INSTANCE, builder);
    resource = forge(ResourceListener.class, ResourceHandler.INSTANCE, builder);
    searchQuery = forge(SearchQueryListener.class, SearchQueryHandler.INSTANCE, builder);
    searchResult = forge(SearchResultListener.class, SearchResultHandler.INSTANCE, builder);
    selection = forge(SelectionListener.class, SelectionHandler.INSTANCE, builder);
    structuredSelection = forge(StructuredSelectionListener.class, StructuredSelectionHandler.INSTANCE, builder);
    testRun = forge(TestRunListener.class, TestRunHandler.INSTANCE, builder);
    text = forge(TextListener.class, TextHandler.INSTANCE, builder);
    textInput = forge(TextInputListener.class, TextInputHandler.INSTANCE, builder);
    textPresentation = forge(TextPresentationListener.class, TextPresentationHandler.INSTANCE, builder);
    textSelection = forge(TextSelectionListener.class, TextSelectionHandler.INSTANCE, builder);
    view = forge(ViewListener.class, ViewHandler.INSTANCE, builder);
    viewport = forge(ViewportListener.class, ViewportHandler.INSTANCE, builder);
    window = forge(WindowListener.class, WindowHandler.INSTANCE, builder);
    workbench = forge(WorkbenchListener.class, WorkbenchHandler.INSTANCE, builder);

    provider = builder.build();
  }

  private DefaultResources() {}

  /**
   * Creates default resource provider. The returned provider is a
   * standard resource provider constructed using the standard provider
   * builder from {@link ResourceProviders#builder()} factory method.
   * Its direct parent and the only predecessor in the resource provider
   * hierarchy is the system resource provider.
   *
   * @return the default resource provider
   *
   * @see ResourceProvider
   * @see ResourceProviders#builder()
   * @see ResourceProviders#superResourceProvider()
   */
  public static ResourceProvider createResourceProvider() {
    return provider;
  }

  /**
   * Creates default resource manager. The returned
   * manager is a standard resource manager constructed by
   * the {@link ResourceManagers#create()} factory method.
   *
   * @return the default resource manager
   *
   * @see ResourceManager
   * @see ResourceManagers#create()
   */
  public static ResourceManager createResourceManager() {
    return ResourceManagers.create();
  }

  /**
   * Creates default resource service. The returned service is a
   * standard resource service constructed using the standard service
   * builder from {@link ResourceServices#builder()} factory method.
   * It contains the default resource provider and manager.
   *
   * @return the default resource service
   *
   * @see ResourceService
   * @see ResourceServices#builder()
   * @see #createResourceProvider()
   * @see #createResourceManager()
   */
  public static ResourceService createResourceService() {
    return ResourceServices.builder().provider(createResourceProvider()).manager(createResourceManager()).build();
  }

  /**
   * Creates default resource names supplier.
   * The built supplier dynamically supplies resource
   * names associated with listener types based on the
   * currently used {@code ResourceProvider} obtained by this
   * {@code Services.getResourceService().getResourceProvider()}
   * method call at supplying.
   *
   * @return the default resource names supplier
   *
   * @see ResourceNamesSupplier
   * @see #createResourceProvider()
   */
  public static ResourceNamesSupplier createResourceNamesSupplier() {
    return new ResourceNamesSupplier() {
      public SetMultimap<Class<? extends Listener>, String> get() {
        return ResourceProviders.toResourceNamesMultimap(Services.getResourceService().getResourceProvider());
      }
    };
  }

  private static <L extends Listener> void safePreRegister(final L listener) {
    try {
      listener.preRegister();
    } catch (Exception failure) {
      throw initializeCause(new ListenerRegistrationException(), failure);
    }
  }

  private static <L extends Listener> void safeRegisterTo(final Resource<? super L> resource, final L listener) {
    try {
      resource.register(listener);
    } catch (UnsupportedResourceException failure) {
      defaultInstance().getConsole().error(failure, "Unsupported resource %s failed registering listener %s", resource, listener);
    }
  }

  private static <L extends Listener> void safePostRegister(final Listener listener) {
    try {
      listener.postRegister();
    } catch (Exception failure) {
      throw initializeCause(new ListenerRegistrationException(), failure);
    }
  }

  private static <L extends Listener> void safePreUnregister(final L listener) {
    try {
      listener.preUnregister();
    } catch (Exception failure) {
      throw initializeCause(new ListenerUnregistrationException(), failure);
    }
  }

  private static <L extends Listener> void safeUnregisterFrom(final Resource<? super L> resource, final L listener) {
    try {
      resource.unregister(listener);
    } catch (UnsupportedResourceException failure) {
      defaultInstance().getConsole().error(failure, "Unsupported resource %s failed unregistering listener %s", resource, listener);
    }
  }

  private static <L extends Listener> void safePostUnregister(final L listener) {
    try {
      listener.postUnregister();
    } catch (Exception failure) {
      throw initializeCause(new ListenerUnregistrationException(), failure);
    }
  }

  public static <L extends Listener> void registerTo(final Resource<L> resource, final L listener) {
    safePreRegister(listener);
    safeRegisterTo(resource, listener);
    safePostRegister(listener);
  }

  public static <L extends Listener> void registerTo(final Iterable<Resource<? super L>> resources, final L listener) {
    safePreRegister(listener);

    for (Resource<? super L> resource: resources) {
      safeRegisterTo(resource, listener);
    }

    safePostRegister(listener);
  }

  public static <L extends Listener> void unregisterFrom(final Resource<L> resource, final L listener) {
    safePreUnregister(listener);
    safeUnregisterFrom(resource, listener);
    safePostUnregister(listener);
  }

  public static <L extends Listener> void unregisterFrom(final Iterable<Resource<? super L>> resources, final L listener) {
    safePreUnregister(listener);

    for (Resource<? super L> resource: resources) {
      safeUnregisterFrom(resource, listener);
    }

    safePostUnregister(listener);
  }

  private static <L extends Listener> Resource<L> forge(final Class<L> type, final Handler<L> handler, final Builder builder) {
    boolean unsupported = handler.getClass().isAnnotationPresent(Unimplemented.class);

    Resource<L> resource = StandardResource.newInstance(Pools.safe(Pools.getListenerPoolFactory().create(handler), type), unsupported);

    builder.add(type, resource);

    return resource;
  }

  public static Resource<CommandListener> getCommandResource() {
    return DefaultResources.command;
  }

  public static Resource<CommandCategoryListener> getCommandCategoryResource() {
    return DefaultResources.commandCategory;
  }

  public static Resource<CommandContextListener> getCommandContextResource() {
    return DefaultResources.commandContext;
  }

  public static Resource<CommandContextManagerListener> getCommandContextManagerResource() {
    return DefaultResources.commandContextManager;
  }

  public static Resource<CommandExecutionListener> getCommandExecutionResource() {
    return DefaultResources.commandExecution;
  }

  public static Resource<CommandHandlerListener> getCommandHandlerResource() {
    return DefaultResources.commandHandler;
  }

  public static Resource<CommandManagerListener> getCommandManagerResource() {
    return DefaultResources.commandManager;
  }

  public static Resource<CompletionListener> getCompletionResource() {
    return DefaultResources.completion;
  }

  public static Resource<DebugEventsListener> getDebugEventsResource() {
    return DefaultResources.debugEvents;
  }

  public static Resource<DocumentListener> getDocumentResource() {
    return DefaultResources.document;
  }

  public static Resource<EditorListener> getEditorResource() {
    return DefaultResources.editor;
  }

  public static Resource<FileBufferListener> getFileBufferResource() {
    return DefaultResources.fileBuffer;
  }

  public static Resource<GitConfigurationListener> getGitConfigurationResource() {
    return DefaultResources.gitConfiguration;
  }

  public static Resource<GitIndexListener> getGitIndexResource() {
    return DefaultResources.gitIndex;
  }

  public static Resource<GitReferenceListener> getGitReferenceResource() {
    return DefaultResources.gitReference;
  }

  public static Resource<GitRepositoryListener> getGitRepositoryResource() {
    return DefaultResources.gitRepository;
  }

  public static Resource<JavaElementListener> getJavaElementResource() {
    return DefaultResources.javaElement;
  }

  public static Resource<LaunchListener> getLaunchResource() {
    return DefaultResources.launch;
  }

  public static Resource<LaunchesListener> getLaunchesResource() {
    return DefaultResources.launches;
  }

  public static Resource<LaunchConfigurationListener> getLaunchConfigurationResource() {
    return DefaultResources.launchConfiguration;
  }

  public static Resource<MarkSelectionListener> getMarkSelectionResource() {
    return DefaultResources.markSelection;
  }

  public static Resource<OperationHistoryListener> getOperationHistoryResource() {
    return DefaultResources.operationHistory;
  }

  public static Resource<PageListener> getPageResource() {
    return DefaultResources.page;
  }

  public static Resource<PartListener> getPartResource() {
    return DefaultResources.part;
  }

  public static Resource<PerspectiveListener> getPerspectiveResource() {
    return DefaultResources.perspective;
  }

  public static Resource<RefactoringExecutionListener> getRefactoringExecutionResource() {
    return DefaultResources.refactoringExecution;
  }

  public static Resource<RefactoringHistoryListener> getRefactoringHistoryResource() {
    return DefaultResources.refactoringHistory;
  }

  public static Resource<ResourceListener> getResourceResource() {
    return DefaultResources.resource;
  }

  public static Resource<SearchQueryListener> getSearchQueryResource() {
    return DefaultResources.searchQuery;
  }

  public static Resource<SearchResultListener> getSearchResultResource() {
    return DefaultResources.searchResult;
  }

  public static Resource<SelectionListener> getSelectionResource() {
    return DefaultResources.selection;
  }

  public static Resource<StructuredSelectionListener> getStructuredSelectionResource() {
    return DefaultResources.structuredSelection;
  }

  public static Resource<TestRunListener> getTestRunResource() {
    return DefaultResources.testRun;
  }

  public static Resource<TextListener> getTextResource() {
    return DefaultResources.text;
  }

  public static Resource<TextInputListener> getTextInputResource() {
    return DefaultResources.textInput;
  }

  public static Resource<TextPresentationListener> getTextPresentationResource() {
    return DefaultResources.textPresentation;
  }

  public static Resource<TextSelectionListener> getTextSelectionResource() {
    return DefaultResources.textSelection;
  }

  public static Resource<ViewListener> getViewResource() {
    return DefaultResources.view;
  }

  public static Resource<ViewportListener> getViewportResource() {
    return DefaultResources.viewport;
  }

  public static Resource<WindowListener> getWindowResource() {
    return DefaultResources.window;
  }

  public static Resource<WorkbenchListener> getWorkbenchResource() {
    return DefaultResources.workbench;
  }
}
