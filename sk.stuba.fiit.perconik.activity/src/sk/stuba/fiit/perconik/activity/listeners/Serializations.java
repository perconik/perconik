package sk.stuba.fiit.perconik.activity.listeners;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Nullable;

import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IProjectNatureDescriptor;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.FooterLine;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPart2;
import org.eclipse.ui.IWorkbenchPart3;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;
import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitRepositories;

import static java.util.Arrays.asList;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static com.google.common.collect.Lists.newArrayListWithExpectedSize;

import static org.eclipse.core.resources.IncrementalProjectBuilder.AUTO_BUILD;
import static org.eclipse.core.resources.IncrementalProjectBuilder.CLEAN_BUILD;
import static org.eclipse.core.resources.IncrementalProjectBuilder.FULL_BUILD;
import static org.eclipse.core.resources.IncrementalProjectBuilder.INCREMENTAL_BUILD;

import static sk.stuba.fiit.perconik.activity.listeners.Utilities.dereferencePart;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

final class Serializations {
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.SOURCE)
  @interface PropagatesSerialization {}

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.SOURCE)
  @interface RequiresDisplayThread {}

  private Serializations() {}

  private static StructuredContent newStructuredContent() {
    return new AnyStructuredData();
  }

  private static <T> List<T> newEmptyListSuitableFor(final Iterable<?> iterable) {
    if (iterable instanceof Collection) {
      return newArrayListWithCapacity(((Collection<?>) iterable).size());
    }

    return newArrayListWithExpectedSize(16);
  }

  private static void putObject(final StructuredContent content, final Object object) {
    content.put(key("class"), object.getClass().getName());
    content.put(key("hash"), object.hashCode());
  }

  static StructuredContent identify(@Nullable final Object object) {
    if (object == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, object);

    return content;
  }

  private static void putWorkbench(final StructuredContent content, final IWorkbench workbench) {
    content.put(key("isStarting"), workbench.isStarting());
    content.put(key("isClosing"), workbench.isClosing());
  }

  @PropagatesSerialization
  @RequiresDisplayThread
  private static void putWorkbenchTree(final StructuredContent content, final IWorkbench workbench) {
    content.put(key("activeWindow"), identify(workbench.getActiveWorkbenchWindow()));
    content.put(key("windows"), serializeWindowsTree(asList(workbench.getWorkbenchWindows())));
  }

  @SuppressWarnings("unused")
  private static void putWindow(final StructuredContent content, final IWorkbenchWindow window) {
  }

  @PropagatesSerialization
  @RequiresDisplayThread
  private static void putWindowTree(final StructuredContent content, final IWorkbenchWindow window) {
    content.put(key("activePage"), identify(window.getActivePage()));
    content.put(key("pages"), serializePagesTree(asList(window.getPages())));

    try {
      content.put(key("selection"), identify(window.getSelectionService().getSelection()));
    } catch (NullPointerException ignore) {}
  }

  private static void putPage(final StructuredContent content, final IWorkbenchPage page) {
    content.put(key("label"), page.getLabel());

    content.put(key("perspective"), serializePerspective(page.getPerspective()));

    content.put(key("isEditorAreaVisible"), page.isEditorAreaVisible());
    content.put(key("isPageZoomed"), page.isPageZoomed());
  }

  @PropagatesSerialization
  @RequiresDisplayThread
  private static void putPageTree(final StructuredContent content, final IWorkbenchPage page) {
    content.put(key("activePart"), identify(page.getActivePartReference()));
    content.put(key("parts"), serializePartReferences(concat(asList(page.getViewReferences()), asList(page.getEditorReferences()))));

    try {
      content.put(key("selection"), identify(page.getSelection()));
    } catch (NullPointerException ignore) {}
  }

  static StructuredContent serializeWorkbench(@Nullable final IWorkbench workbench) {
    if (workbench == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, workbench);
    putWorkbench(content, workbench);

    return content;
  }

  @PropagatesSerialization
  @RequiresDisplayThread
  static StructuredContent serializeWorkbenchTree(@Nullable final IWorkbench workbench) {
    if (workbench == null) {
      return null;
    }

    StructuredContent content = serializeWorkbench(workbench);

    putWorkbenchTree(content, workbench);

    return content;
  }

  static StructuredContent serializeWindow(@Nullable final IWorkbenchWindow window) {
    if (window == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, window);
    putWindow(content, window);

    return content;
  }

  @PropagatesSerialization
  @RequiresDisplayThread
  static StructuredContent serializeWindowTree(@Nullable final IWorkbenchWindow window) {
    if (window == null) {
      return null;
    }

    StructuredContent content = serializeWindow(window);

    putWindowTree(content, window);

    return content;
  }

  static List<StructuredContent> serializeWindows(final Iterable<? extends IWorkbenchWindow> windows) {
    List<StructuredContent> contents = newEmptyListSuitableFor(windows);

    for (IWorkbenchWindow window: windows) {
      contents.add(serializeWindow(window));
    }

    return contents;
  }

  @PropagatesSerialization
  @RequiresDisplayThread
  static List<StructuredContent> serializeWindowsTree(final Iterable<? extends IWorkbenchWindow> windows) {
    List<StructuredContent> contents = newEmptyListSuitableFor(windows);

    for (IWorkbenchWindow window: windows) {
      contents.add(serializeWindowTree(window));
    }

    return contents;
  }

  static StructuredContent serializePage(@Nullable final IWorkbenchPage page) {
    if (page == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, page);
    putPage(content, page);

    return content;
  }

  @PropagatesSerialization
  @RequiresDisplayThread
  static StructuredContent serializePageTree(@Nullable final IWorkbenchPage page) {
    if (page == null) {
      return null;
    }

    StructuredContent content = serializePage(page);

    putPageTree(content, page);

    return content;
  }

  static List<StructuredContent> serializePages(final Iterable<? extends IWorkbenchPage> pages) {
    List<StructuredContent> contents = newEmptyListSuitableFor(pages);

    for (IWorkbenchPage page: pages) {
      contents.add(serializePage(page));
    }

    return contents;
  }

  @PropagatesSerialization
  @RequiresDisplayThread
  static List<StructuredContent> serializePagesTree(final Iterable<? extends IWorkbenchPage> pages) {
    List<StructuredContent> contents = newEmptyListSuitableFor(pages);

    for (IWorkbenchPage page: pages) {
      contents.add(serializePageTree(page));
    }

    return contents;
  }

  private static void putPerspective(final StructuredContent content, final IPerspectiveDescriptor descriptor) {
    content.put(key("perspective", "identifier"), descriptor.getId());
    content.put(key("perspective", "description"), descriptor.getDescription());
    content.put(key("perspective", "label"), descriptor.getLabel());
  }

  static StructuredContent serializePerspective(@Nullable final IPerspectiveDescriptor descriptor) {
    if (descriptor == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, descriptor);
    putPerspective(content, descriptor);

    return content;
  }

  private static void putPart(final StructuredContent content, final IWorkbenchPart part) {
    content.put(key("title"), part.getTitle());
    content.put(key("titleTooltip"), part.getTitleToolTip());

    if (part instanceof IWorkbenchPart2) {
      IWorkbenchPart2 part2 = (IWorkbenchPart2) part;

      content.put(key("name"), part2.getPartName());
      content.put(key("contentDescription"), part2.getContentDescription());

      if (part2 instanceof IWorkbenchPart3) {
        IWorkbenchPart3 part3 = (IWorkbenchPart3) part2;

        content.put(key("properties"), part3.getPartProperties());
      }
    }
  }

  @SuppressWarnings("unused")
  private static void putView(final StructuredContent content, final IViewPart part) {
  }

  @RequiresDisplayThread
  private static void putEditor(final StructuredContent content, final IEditorPart part) {
    content.put(key("editorInput"), serializeEditorInput(part.getEditorInput()));

    content.put(key("isSaveAsAllowed"), part.isSaveAsAllowed());
    content.put(key("isSaveOnCloseNeeded"), part.isSaveOnCloseNeeded());

    Object viewer = part.getAdapter(ITextOperationTarget.class);

    if (viewer instanceof ITextViewer) {
      content.put(key("viewer"), serializeTextViewer((ITextViewer) viewer));
    }
  }

  @RequiresDisplayThread
  static StructuredContent serializePart(@Nullable final IWorkbenchPart part) {
    if (part == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, part);
    putPart(content, part);

    if (part instanceof IViewPart) {
      putView(content, (IViewPart) part);
    }

    if (part instanceof IEditorPart) {
      putEditor(content, (IEditorPart) part);
    }

    return content;
  }

  @RequiresDisplayThread
  static List<StructuredContent> serializeParts(final Iterable<? extends IWorkbenchPart> parts) {
    List<StructuredContent> contents = newEmptyListSuitableFor(parts);

    for (IWorkbenchPart part: parts) {
      contents.add(serializePart(part));
    }

    return contents;
  }

  @RequiresDisplayThread
  static StructuredContent serializeView(@Nullable final IViewPart part) {
    return serializePart(part);
  }

  @RequiresDisplayThread
  static StructuredContent serializeEditor(@Nullable final IEditorPart part) {
    return serializePart(part);
  }

  @RequiresDisplayThread
  private static void putPartReference(final StructuredContent content, final IWorkbenchPartReference reference) {
    content.put(key("identifier"), reference.getId());
    content.put(key("title"), reference.getTitle());
    content.put(key("titleTooltip"), reference.getTitleToolTip());

    content.put(key("part"), serializePart(dereferencePart(reference)));
    content.put(key("partName"), reference.getPartName());

    content.put(key("isDirty"), reference.isDirty());
  }

  private static void putViewReference(final StructuredContent content, final IViewReference reference) {
    content.put(key("secondaryIdentifier"), reference.getSecondaryId());

    content.put(key("isFastView"), reference.isFastView());
  }

  private static void putEditorReference(final StructuredContent content, final IEditorReference reference) {
    content.put(key("isPinned"), reference.isPinned());
  }

  @RequiresDisplayThread
  static StructuredContent serializePartReference(@Nullable final IWorkbenchPartReference reference) {
    if (reference == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, reference);
    putPartReference(content, reference);

    if (reference instanceof IViewReference) {
      putViewReference(content, (IViewReference) reference);
    }

    if (reference instanceof IEditorReference) {
      putEditorReference(content, (IEditorReference) reference);
    }

    return content;
  }

  @RequiresDisplayThread
  static Iterable<StructuredContent> serializePartReferences(final Iterable<? extends IWorkbenchPartReference> references) {
    List<StructuredContent> contents = newEmptyListSuitableFor(references);

    for (IWorkbenchPartReference reference: references) {
      contents.add(serializePartReference(reference));
    }

    return contents;
  }

  @RequiresDisplayThread
  static StructuredContent serializeViewReference(@Nullable final IViewReference reference) {
    return serializePartReference(reference);
  }

  @RequiresDisplayThread
  static StructuredContent serializeEditorReference(@Nullable final IEditorReference reference) {
    return serializePartReference(reference);
  }

  private static void putEditorInput(final StructuredContent content, final IEditorInput input) {
    content.put(key("name"), input.getName());
    content.put(key("tooltip"), input.getToolTipText());

    content.put(key("exists"), input.exists());
  }

  private static void putFileEditorInput(final StructuredContent content, final IFileEditorInput input) {
    content.put(key("file"), serializeFile(input.getFile()));
  }

  private static void putPathEditorInput(final StructuredContent content, final IPathEditorInput input) {
    content.put(key("path"), serializePath(input.getPath()));
  }

  private static void putUriEditorInput(final StructuredContent content, final IURIEditorInput input) {
    content.put(key("uri"), input.getURI());
  }

  static StructuredContent serializeEditorInput(@Nullable final IEditorInput input) {
    if (input == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, input);
    putEditorInput(content, input);

    if (input instanceof IFileEditorInput) {
      putFileEditorInput(content, (IFileEditorInput) input);
    }

    if (input instanceof IPathEditorInput) {
      putPathEditorInput(content, (IPathEditorInput) input);
    }

    if (input instanceof IURIEditorInput) {
      putUriEditorInput(content, (IURIEditorInput) input);
    }

    return content;
  }

  static StructuredContent serializeFileEditorInput(@Nullable final IFileEditorInput input) {
    return serializeEditorInput(input);
  }

  static StructuredContent serializePathEditorInput(@Nullable final IPathEditorInput input) {
    return serializeEditorInput(input);
  }

  static StructuredContent serializeUriEditorInput(@Nullable final IURIEditorInput input) {
    return serializeEditorInput(input);
  }

  @RequiresDisplayThread
  private static void putTextViewer(final StructuredContent content, final ITextViewer viewer) {
    content.put(key("top", "inset"), viewer.getTopInset());

    content.put(key("top", "index"), viewer.getTopIndex());
    content.put(key("top", "offset"), viewer.getTopIndexStartOffset());

    content.put(key("bottom", "index"), viewer.getBottomIndex());
    content.put(key("bottom", "offset"), viewer.getBottomIndexEndOffset());

    content.put(key("selection"), serializeSelection(viewer.getSelectionProvider().getSelection()));

    IRegion visibleRegion = viewer.getVisibleRegion();

    content.put(key("vision", "offset"), visibleRegion.getOffset());
    content.put(key("vision", "length"), visibleRegion.getLength());

    content.put(key("document"), serializeDocument(viewer.getDocument()));

    content.put(key("isEditable"), viewer.isEditable());

    if (viewer instanceof ITextViewerExtension) {
      ITextViewerExtension extension = (ITextViewerExtension) viewer;

      content.put(key("mark"), extension.getMark());
    }
  }

  @RequiresDisplayThread
  private static void putSourceViewer(final StructuredContent content, final ISourceViewer viewer) {
    IRegion rangeIndication = viewer.getRangeIndication();

    content.put(key("indication", "offset"), rangeIndication.getOffset());
    content.put(key("indication", "length"), rangeIndication.getLength());
  }

  @RequiresDisplayThread
  private static void putDocument(final StructuredContent content, final IDocument document) {
    content.put(key("length"), document.getLength());
    content.put(key("linesCount"), document.getNumberOfLines());

    if (document instanceof IDocumentExtension4) {
      IDocumentExtension4 extension4 = (IDocumentExtension4) document;

      content.put(key("modificationTimestamp"), extension4.getModificationStamp());
    }
  }

  @RequiresDisplayThread
  static StructuredContent serializeTextViewer(@Nullable final ITextViewer viewer) {
    if (viewer == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, viewer);
    putTextViewer(content, viewer);

    if (viewer instanceof ISourceViewer) {
      putSourceViewer(content, (ISourceViewer) viewer);
    }

    return content;
  }

  @RequiresDisplayThread
  static StructuredContent serializeSourceViewer(@Nullable final ISourceViewer viewer) {
    return serializeTextViewer(viewer);
  }

  @RequiresDisplayThread
  static StructuredContent serializeDocument(@Nullable final IDocument document) {
    if (document == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, document);
    putDocument(content, document);

    return content;
  }

  private static void putSelection(final StructuredContent content, final ISelection selection) {
    content.put(key("isEmpty"), selection.isEmpty());
  }

  private static void putMarkSelection(final StructuredContent content, final IMarkSelection selection) {
    content.put(key("offset"), selection.getOffset());
    content.put(key("length"), selection.getLength());

    // TODO swt access content.put(key("document"), serializeDocument(selection.getDocument()));
  }

  private static void putStructuredSelection(final StructuredContent content, final IStructuredSelection selection) {
    content.put(key("elements"), selection.toList());
  }

  private static void putTextSelection(final StructuredContent content, final ITextSelection selection) {
    content.put(key("offset"), selection.getOffset());
    content.put(key("length"), selection.getLength());

    content.put(key("line", "start"), selection.getStartLine());
    content.put(key("line", "end"), selection.getEndLine());

    content.put(key("text"), selection.getText());
  }

  static StructuredContent serializeSelection(@Nullable final ISelection selection) {
    if (selection == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, selection);
    putSelection(content, selection);

    if (selection instanceof IMarkSelection) {
      putMarkSelection(content, (IMarkSelection) selection);
    }

    if (selection instanceof IStructuredSelection) {
      putStructuredSelection(content, (IStructuredSelection) selection);
    }

    if (selection instanceof ITextSelection) {
      putTextSelection(content, (ITextSelection) selection);
    }

    return content;
  }

  static StructuredContent serializeMarkSelection(@Nullable final IMarkSelection selection) {
    return serializeSelection(selection);
  }

  static StructuredContent serializeStructuredSelection(@Nullable final IStructuredSelection selection) {
    return serializeSelection(selection);
  }

  static StructuredContent serializeTextSelection(@Nullable final ITextSelection selection) {
    return serializeSelection(selection);
  }

  private static void putWorkspace(final StructuredContent content, final IWorkspace workspace) {
    IWorkspaceRoot root = workspace.getRoot();

    content.put(key("root", "name"), root.getName());
    content.put(key("root", "uri"), root.getLocationURI());

    IWorkspaceDescription description = workspace.getDescription();

    content.put(key("build", "order"), description.getBuildOrder());
    content.put(key("build", "maxIterations"), description.getMaxBuildIterations());

    List<Content> natures = newArrayListWithExpectedSize(8);

    for (IProjectNatureDescriptor descriptor: workspace.getNatureDescriptors()) {
      StructuredContent natureContent = newStructuredContent();

      natureContent.put(key("identifier"), descriptor.getNatureId());
      natureContent.put(key("label"), descriptor.getLabel());

      natures.add(natureContent);
    }

    content.put(key("knownNatures"), natures);

    content.put(key("snapshotInterval"), description.getSnapshotInterval());

    content.put(key("isAutoBuilding"), workspace.isAutoBuilding());
    content.put(key("isTreeLocked"), workspace.isTreeLocked());
  }

  static StructuredContent serializeWorkspace(@Nullable final IWorkspace workspace) {
    if (workspace == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, workspace);
    putWorkspace(content, workspace);

    return content;
  }

  private static void putResource(final StructuredContent content, final IResource resource) {
    content.put(key("type"), ResourceType.valueOf(resource.getType()).toString().toLowerCase());
    content.put(key("name"), resource.getName());

    content.put(key("path", "local"), serializePath(resource.getLocation()));
    content.put(key("path", "project"), serializePath(resource.getProjectRelativePath()));
    content.put(key("path", "workspace"), serializePath(resource.getFullPath()));

    content.put(key("uri"), resource.getLocationURI());

    content.put(key("raw", "path", "local"), serializePath(resource.getRawLocation()));
    content.put(key("raw", "uri"), resource.getRawLocationURI());

    content.put(key("timestamp", "local"), resource.getLocalTimeStamp());
    content.put(key("timestamp", "modification"), resource.getModificationStamp());

    content.put(key("exists"), resource.exists());

    content.put(key("isAccessible"), resource.isAccessible());
    content.put(key("isDerived"), resource.isDerived());
    content.put(key("isLinked"), resource.isLinked());
    content.put(key("isPhantom"), resource.isPhantom());
    content.put(key("isVirtual"), resource.isVirtual());

    ResourceAttributes attributes = resource.getResourceAttributes();

    if (attributes != null) {
      content.put(key("isArchive"), attributes.isArchive());
      content.put(key("isExecutable"), attributes.isExecutable());
      content.put(key("isHidden"), attributes.isHidden());
      content.put(key("isReadOnly"), attributes.isReadOnly());
      content.put(key("isSymbolicLink"), attributes.isSymbolicLink());
    }
  }

  private static void putResourceGit(final StructuredContent content, final IResource resource) {
    Repository repository = EGitAccess.getRepository(resource);

    if (repository != null) {
      StructuredContent gitContent = newStructuredContent();

      putRepositoryDirectories(gitContent, repository);
      putRepositoryRemotes(gitContent, repository);
      putRepositoryBranch(gitContent, repository);

      String path = EGitAccess.getRepositoryRelativePath(resource);

      if (path != null) {
        RevCommit commit = GitRepositories.getMostRecentCommit(repository, path);

        gitContent.put(key("mostRecentCommit", "name"), commit != null ? commit.getName() : null);
      }

      content.put(key("git"), gitContent);
    }
  }

  @SuppressWarnings("unused")
  private static void putContainer(final StructuredContent content, final IContainer container) {
  }

  @SuppressWarnings("unused")
  private static void putRoot(final StructuredContent content, final IWorkspaceRoot root) {
  }

  private static void putProject(final StructuredContent content, final IProject project) {
    try {
      List<Content> natures = newArrayListWithExpectedSize(8);

      for (IProjectNatureDescriptor descriptor: project.getWorkspace().getNatureDescriptors()) {
        String identifier = descriptor.getNatureId();

        if (project.hasNature(identifier)) {
          IProjectNature nature = project.getNature(identifier);

          StructuredContent natureContent = newStructuredContent();

          natureContent.put(key("class"), nature.getClass().getName());

          natureContent.put(key("identifier"), identifier);
          natureContent.put(key("label"), descriptor.getLabel());

          natureContent.put(key("isEnabled"), project.isNatureEnabled(identifier));

          natures.add(natureContent);
        }
      }
    }
    catch (CoreException ignore) {}

    try {
      IProjectDescription description = project.getDescription();

      content.put(key("comment"), description.getComment());

      List<Content> builders = newArrayListWithExpectedSize(8);

      for (ICommand builder: description.getBuildSpec()) {
        StructuredContent builderContent = newStructuredContent();

        builderContent.put(key("name"), builder.getBuilderName());
        builderContent.put(key("arguments"), builder.getArguments());

        builderContent.put(key("canAuto"), builder.isBuilding(AUTO_BUILD));
        builderContent.put(key("canFull"), builder.isBuilding(FULL_BUILD));
        builderContent.put(key("canIncremental"), builder.isBuilding(INCREMENTAL_BUILD));
        builderContent.put(key("canClean"), builder.isBuilding(CLEAN_BUILD));

        builderContent.put(key("isConfigurable"), builder.isConfigurable());

        builders.add(builderContent);
      }

      content.put(key("build", "builders"), builders);
    } catch (CoreException ignore) {}

    try {
      content.put(key("build", "activeConfiguration"), project.getActiveBuildConfig().getName());

      List<String> configurations = newArrayListWithExpectedSize(8);

      for (IBuildConfiguration configuration: project.getBuildConfigs()) {
        String name = configuration.getName();

        if (name != null) {
          configurations.add(configuration.getName());
        }
      }

      content.put(key("build", "configurations"), configurations);
    } catch (CoreException ignore) {}

    content.put(key("isOpen"), project.isOpen());
  }

  @SuppressWarnings("unused")
  private static void putFolder(final StructuredContent content, final IFolder folder) {
  }

  private static void putFile(final StructuredContent content, final IFile file) {
    try {
      content.put(key("charset"), file.getCharset());
    } catch (CoreException ignore) {}

    try {
      IContentDescription description = file.getContentDescription();

      if (description != null) {
        IContentType type = description.getContentType();

        if (type != null) {
          content.put(key("contentType", "identifier"), type.getId());
          content.put(key("contentType", "name"), type.getName());
        }
      }
    } catch (CoreException ignore) {}
  }

  static StructuredContent serializeResource(@Nullable final IResource resource) {
    if (resource == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putObject(content, resource);
    putResource(content, resource);
    putResourceGit(content, resource);

    if (resource instanceof IContainer) {
      putContainer(content, (IContainer) resource);
    }

    if (resource instanceof IWorkspaceRoot) {
      putRoot(content, (IWorkspaceRoot) resource);
    }

    if (resource instanceof IProject) {
      putProject(content, (IProject) resource);
    }

    if (resource instanceof IFolder) {
      putFolder(content, (IFolder) resource);
    }

    if (resource instanceof IFile) {
      putFile(content, (IFile) resource);
    }

    return content;
  }

  static StructuredContent serializeContainer(@Nullable final IContainer container) {
    return serializeResource(container);
  }

  static StructuredContent serializeRoot(@Nullable final IWorkspaceRoot root) {
    return serializeResource(root);
  }

  static StructuredContent serializeProject(@Nullable final IProject project) {
    return serializeResource(project);
  }

  static StructuredContent serializeFolder(@Nullable final IFolder folder) {
    return serializeResource(folder);
  }

  static StructuredContent serializeFile(@Nullable final IFile file) {
    return serializeResource(file);
  }

  private static void putPath(final StructuredContent content, final IPath path) {
    content.put(key("device"), path.getDevice());
    content.put(key("segments"), path.segments());
    content.put(key("extension"), path.getFileExtension());

    content.put(key("value", "standard"), path.toString());
    content.put(key("value", "platform"), path.toOSString());
    content.put(key("value", "portable"), path.toPortableString());

    content.put(key("isAbsolute"), path.isAbsolute());
    content.put(key("isEmpty"), path.isEmpty());
    content.put(key("isRoot"), path.isRoot());
    content.put(key("isUnc"), path.isUNC());
  }

  static StructuredContent serializePath(@Nullable final IPath path) {
    if (path == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putPath(content, path);

    return content;
  }

  private static void putRepository(final StructuredContent content, final Repository repository) {
    putRepositoryDirectories(content, repository);
    putRepositoryRemotes(content, repository);
    putRepositoryBranch(content, repository);
    putRepositoryBranches(content, repository);

    content.put(key("state"), repository.getRepositoryState().toString().toLowerCase());

    content.put(key("isBare"), repository.isBare());
  }

  private static void putRepositoryDirectories(final StructuredContent content, final Repository repository) {
    try {
      content.put(key("directory", "root"), repository.getWorkTree().toPath());
    } catch (NoWorkTreeException ignore) {}

    content.put(key("directory", "meta"), repository.getDirectory().toPath());
  }

  private static void putRepositoryRemotes(final StructuredContent content, final Repository repository) {
    List<Content> remotes = newArrayListWithExpectedSize(16);

    Config configuration = repository.getConfig();

    for (String name: repository.getRemoteNames()) {
      StructuredContent remoteContent = newStructuredContent();

      remoteContent.put(key("name"), name);
      remoteContent.put(key("url"), configuration.getString("remote", name, "url"));

      remotes.add(remoteContent);
    }

    content.put(key("remotes"), remotes);
  }

  private static void putRepositoryBranch(final StructuredContent content, final Repository repository) {
    try {
      content.put(key("branch", "short"), repository.getBranch());
      content.put(key("branch", "full"), repository.getFullBranch());
    } catch (IOException ignore) {}
  }

  private static void putRepositoryBranches(final StructuredContent content, final Repository repository) {
    try {
      List<Content> branches = newArrayListWithExpectedSize(32);

      for (Ref reference: new Git(repository).branchList().setListMode(ListMode.ALL).call()) {
        StructuredContent branchContent = newStructuredContent();

        branchContent.put(key("name"), reference.getName());
        branchContent.put(key("commit"), reference.getObjectId().getName());

        branches.add(branchContent);
      }

      content.put(key("branches"), branches);
    } catch (GitAPIException ignore) {}
  }

  private static void putIdentity(final StructuredContent content, final PersonIdent identity) {
    content.put(key("email"), identity.getEmailAddress());
    content.put(key("name"), identity.getName());
    content.put(key("timestamp"), identity.getWhen().getTime());

    content.put(key("timezone", "offset"), identity.getTimeZoneOffset());

    TimeZone zone = identity.getTimeZone();

    if (zone != null) {
      content.put(key("timezone", "identifier"), zone.getID());
    }
  }

  private static void putCommit(final StructuredContent content, final RevCommit commit) {
    content.put(key("timestamp"), commit.getCommitTime());
    content.put(key("name"), commit.getName());

    content.put(key("author"), serializeIdentity(commit.getAuthorIdent()));
    content.put(key("committer"), serializeIdentity(commit.getCommitterIdent()));

    content.put(key("message", "short"), commit.getShortMessage());
    content.put(key("message", "full"), commit.getFullMessage());

    content.put(key("encoding"), commit.getEncoding().toString());

    List<Content> lines = newArrayListWithExpectedSize(8);

    for (FooterLine line: commit.getFooterLines()) {
      StructuredContent lineContent = newStructuredContent();

      lineContent.put(key("key"), line.getKey());
      lineContent.put(key("value"), line.getValue());
      lineContent.put(key("email"), line.getEmailAddress());

      lines.add(lineContent);
    }

    content.put(key("footer"), lines);
  }

  static StructuredContent serializeRepository(@Nullable final Repository repository) {
    if (repository == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putRepository(content, repository);

    return content;
  }

  static StructuredContent serializeIdentity(@Nullable final PersonIdent identity) {
    if (identity == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putIdentity(content, identity);

    return content;
  }

  static StructuredContent serializeCommit(@Nullable final RevCommit commit) {
    if (commit == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    putCommit(content, commit);

    return content;
  }
}
