package com.gratex.perconik.activity.ide.listeners;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;

import com.gratex.perconik.activity.ide.IdeGitProjects;
import com.gratex.perconik.activity.uaca.IdeUacaProxy;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventType;

import sk.stuba.fiit.perconik.core.java.JavaElements;
import sk.stuba.fiit.perconik.core.java.JavaProjects;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaResolver;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;
import sk.stuba.fiit.perconik.eclipse.core.runtime.RuntimeCoreException;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static java.util.Arrays.asList;

import static com.google.common.base.Objects.equal;

import static com.gratex.perconik.activity.ide.IdeData.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeData.setEventData;
import static com.gratex.perconik.activity.ide.listeners.Utilities.currentTime;
import static com.gratex.perconik.activity.ide.listeners.Utilities.dereferenceEditor;
import static com.gratex.perconik.activity.ide.listeners.Utilities.isNull;

import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.MOVED_TO;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.OPEN;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.ADDED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.REMOVED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.FILE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.PROJECT;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.ROOT;

/**
 * A listener of IDE document events. This listener handles desired
 * events and eventually builds corresponding data transfer objects
 * of type {@link IdeDocumentEventRequest} and passes them to the
 * {@link IdeUacaProxy} to be transferred into the <i>User Activity Central
 * Application</i> for further processing.
 *
 * <p>Document operation types that this listener is interested in are
 * determined by the {@link IdeDocumentEventType} enumeration:
 *
 * <ul>
 *   <li>Add - a document is added into the project.
 *   <li>Close - an opened document is closed.
 *   <li>Open - a closed document is opened.
 *   <li>Remove - a document is removed from the project.
 *   <li>Rename - currently not supported.
 *   <li>Save - a document is saved.
 *   <li>Switch to - focus is changed from one document to another
 *   and editor selections (tabs and text). Note that structured
 *   selections in package explorer are supported.
 * </ul>
 *
 * <p>Data available in an {@code IdeDocumentEventRequest}:
 *
 * <ul>
 *   <li>{@code document} - see {@code IdeDocumentDto} below.
 *   <li>See {@link IdeListener} for documentation of inherited data.
 * </ul>
 *
 * <p>Data available in an {@code IdeDocumentDto}:
 *
 * <ul>
 *   <li>{@code branch} - current Git branch name for the document.
 *   <li>{@code changesetIdInRcs} - most recent Git commit
 *   identifier for the document (40 hexadecimal characters),
 *   for example {@code "984dd5f359532d7d806a92b47ef5bfc39d772d64"}.
 *   <li>{@code localPath} - path to the document relative to the workspace root,
 *   for example {@code "com.gratex.perconik.activity/src/com/gratex/perconik/activity/ide/listeners/IdeCommitListener.java"}.
 *   <li>{@code rcsServer} - see documentation of {@code RcsServerDto}
 *   in {@link IdeCommitListener} for more details.
 *   <li>{@code serverPath} - always the same as {@code localPath}.
 * </ul>
 *
 * <p>Note that in case of not editable source code, such as classes from JRE
 * system library, fields {@code branchName}, {@code changesetIdInRcs},
 * and {@code rcsServer} are unused and set to {@code null}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeDocumentListener extends IdeListener implements EditorListener, FileBufferListener, ResourceListener, SelectionListener {
  // TODO note that switch_to is sometimes generated before open/close
  // TODO open is also generated on initial switch to previously opened tab directly after eclipse launch

  static final boolean processStructuredSelections = false;

  static final Predicate<IResource> resourceDeltaFilter = Predicates.or(asList(OutputLocationFilter.INSTANCE, GitInternalFilter.INSTANCE, GitIgnoreFilter.INSTANCE));

  static final Set<ResourceEventType> resourceEventTypes = ImmutableSet.of(POST_CHANGE);

  private final Object lock = new Object();

  @GuardedBy("lock")
  private UnderlyingResource<?> resource;

  public IdeDocumentListener() {}

  private boolean updateResource(final UnderlyingResource<?> resource) {
    if (resource != null) {
      synchronized (this.lock) {
        if (!resource.equals(this.resource)) {
          this.resource = resource;

          return true;
        }
      }
    }

    return false;
  }

  static IdeDocumentEventRequest build(final long time, final IFile file) {
    return build(time, UnderlyingResource.of(file));
  }

  static IdeDocumentEventRequest build(final long time, final UnderlyingResource<?> resource) {
    final IdeDocumentEventRequest data = new IdeDocumentEventRequest();

    resource.setDocumentData(data);
    resource.setProjectData(data);

    setApplicationData(data);
    setEventData(data, time);

    return data;
  }

  private final class ResourceDeltaVisitor extends ResourceDeltaResolver {
    private final long time;

    private final ResourceEventType type;

    private final Predicate<IResource> filter;

    private final SetMultimap<IdeDocumentEventType, IFile> operations;

    ResourceDeltaVisitor(final long time, final ResourceEventType type) {
      assert time >= 0 && type != null;

      this.time = time;
      this.type = type;

      this.filter = resourceDeltaFilter;
      this.operations = LinkedHashMultimap.create(3, 2);
    }

    @Override
    protected boolean resolveDelta(final IResourceDelta delta, final IResource resource) {
      assert delta != null && resource != null;

      if (this.type != POST_CHANGE || this.filter.apply(resource)) {
        return false;
      }

      ResourceDeltaKind kind = ResourceDeltaKind.valueOf(delta.getKind());

      ResourceType type = ResourceType.valueOf(resource.getType());

      Set<ResourceDeltaFlag> flags = ResourceDeltaFlag.setOf(delta.getFlags());

      if (type == PROJECT && (kind == ADDED || kind == REMOVED || flags.contains(OPEN))) {
        return false;
      }

      if (type != FILE) {
        return true;
      }

      if (flags.contains(MOVED_TO)) {
        IPath path = delta.getMovedToPath();
        IPath other = resource.getFullPath();

        if (path != null && other != null && !equal(path.lastSegment(), other.lastSegment())) {
          this.operations.put(IdeDocumentEventType.RENAME, (IFile) resource);

          return false;
        }
      }

      switch (kind) {
        case ADDED:
          this.operations.put(IdeDocumentEventType.ADD, (IFile) resource);
          break;

        case REMOVED:
          this.operations.put(IdeDocumentEventType.REMOVE, (IFile) resource);
          break;

        default:
          break;
      }

      return false;
    }

    @Override
    protected boolean resolveResource(final IResource resource) {
      return false;
    }

    @Override
    protected void postVisitOrProbe() {
      if (this.operations.containsKey(IdeDocumentEventType.RENAME)) {
        this.operations.removeAll(IdeDocumentEventType.ADD);
      }

      IdeUacaProxy proxy = IdeDocumentListener.this.proxy;

      for (Entry<IdeDocumentEventType, IFile> entry: this.operations.entries()) {
        proxy.sendDocumentEvent(build(this.time, entry.getValue()), entry.getKey());
      }
    }
  }

  private enum OutputLocationFilter implements Predicate<IResource> {
    INSTANCE;

    public boolean apply(@Nonnull final IResource resource) {
      IProject project = resource.getProject();

      if (project == null) {
        return false;
      }

      try {
        // TODO on POST_CHANGE when project gets deleted it has no more Java nature -> needs PRE_DELETE hook?
        if (JavaProjects.inOutputLocation(project, resource)) {
          return true;
        }
      } catch (RuntimeCoreException e) {}

      return false;
    }
  }

  private enum GitInternalFilter implements Predicate<IResource> {
    INSTANCE;

    public boolean apply(@Nonnull final IResource resource) {
      IPath path = resource.getLocation();

      if (path == null) {
        path = resource.getFullPath();
      }

      for (String segment: path.segments()) {
        if (segment.equals(Constants.DOT_GIT)) {
          return true;
        }
      }

      return false;
    }
  }

  private enum GitIgnoreFilter implements Predicate<IResource> {
    INSTANCE;

    public boolean apply(@Nonnull final IResource resource) {
      ResourceType type = ResourceType.valueOf(resource.getType());

      if (type == ROOT || type == PROJECT) {
        return false;
      }

      IPath path = resource.getLocation();

      if (path == null) {
        // TODO location is null on project delete and egit repository is null too
        // try to resolve this in (project) PRE_DELETE events -> but that code probably
        // can not run in parallel (i.e. all pre* code, only post code can....)

        path = resource.getFullPath();
      }

      if (!IdeGitProjects.isMapped(path)) {
        return false;
      }

      try {
        return IdeGitProjects.isIgnored(path);
      } catch (IOException e) {
        return false;
      }
    }
  }

  void processResource(final long time, final IResourceChangeEvent event) {
    ResourceEventType type = ResourceEventType.valueOf(event.getType());
    IResourceDelta delta = event.getDelta();

    new ResourceDeltaVisitor(time, type).visitOrProbe(delta, event);
  }

  void processResource(final long time, final IEditorReference reference, final IdeDocumentEventType type) {
    UnderlyingResource<?> resource = UnderlyingResource.from(dereferenceEditor(reference));

    if (resource != null) {
      this.proxy.sendDocumentEvent(build(time, resource), type);
    }
  }

  void processSelection(final long time, final IWorkbenchPart part, final ISelection selection) {
    UnderlyingResource<?> resource = null;

    if (processStructuredSelections) {
      if (selection instanceof StructuredSelection) {
        Object element = ((StructuredSelection) selection).getFirstElement();

        resource = UnderlyingResource.resolve(element);

        if (resource == null && element instanceof IJavaElement) {
          IResource other = JavaElements.resource((IJavaElement) element);

          if (other instanceof IFile) {
            resource = UnderlyingResource.of((IFile) other);
          }
        }
      }
    }

    if (isNull(resource) && part instanceof IEditorPart) {
      resource = UnderlyingResource.from((IEditorPart) part);
    }

    if (this.updateResource(resource)) {
      this.proxy.sendDocumentEvent(build(time, resource), IdeDocumentEventType.SWITCH_TO);
    }
  }

  @Override
  public void postRegister() {
    execute(new Runnable() {
      @Override
      public void run() {
        IEditorPart editor = execute(DisplayTask.of(Editors.activeEditorSupplier()));

        UnderlyingResource<?> resource = UnderlyingResource.from(editor);

        if (resource == null) {
          return;
        }

        IdeDocumentListener.this.proxy.sendDocumentEvent(build(currentTime(), resource), IdeDocumentEventType.OPEN);
      }
    });
  }

  public void resourceChanged(final IResourceChangeEvent event) {
    final long time = currentTime();

    execute(new Runnable() {
      public void run() {
        processResource(time, event);
      }
    });
  }

  public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
    final long time = currentTime();

    execute(new Runnable() {
      public void run() {
        IdeDocumentListener.this.processSelection(time, part, selection);
      }
    });
  }

  public void editorOpened(final IEditorReference reference) {
    final long time = currentTime();

    execute(new Runnable() {
      public void run() {
        processResource(time, reference, IdeDocumentEventType.OPEN);
      }
    });
  }

  // TODO close not working for locally build class files
  public void editorClosed(final IEditorReference reference) {
    final long time = currentTime();

    execute(new Runnable() {
      public void run() {
        processResource(time, reference, IdeDocumentEventType.CLOSE);
      }
    });
  }

  public void editorActivated(final IEditorReference reference) {}

  public void editorDeactivated(final IEditorReference reference) {}

  public void editorVisible(final IEditorReference reference) {}

  public void editorHidden(final IEditorReference reference) {}

  public void editorBroughtToTop(final IEditorReference reference) {}

  public void editorInputChanged(final IEditorReference reference) {}

  public void bufferCreated(final IFileBuffer buffer) {}

  public void bufferDisposed(final IFileBuffer buffer) {}

  public void bufferContentAboutToBeReplaced(final IFileBuffer buffer) {}

  public void bufferContentReplaced(final IFileBuffer buffer) {}

  public void stateChanging(final IFileBuffer buffer) {}

  public void stateChangeFailed(final IFileBuffer buffer) {}

  public void stateValidationChanged(final IFileBuffer buffer, final boolean stateValidated) {}

  public void dirtyStateChanged(final IFileBuffer buffer, final boolean dirty) {
    final long time = currentTime();

    execute(new Runnable() {
      public void run() {
        if (!dirty) {
          IFile file = FileBuffers.getWorkspaceFileAtLocation(buffer.getLocation());

          IdeDocumentListener.this.proxy.sendDocumentEvent(build(time, file), IdeDocumentEventType.SAVE);
        }
      }
    });
  }

  public void underlyingFileMoved(final IFileBuffer buffer, final IPath path) {}

  public void underlyingFileDeleted(final IFileBuffer buffer) {}

  public Set<ResourceEventType> getEventTypes() {
    return resourceEventTypes;
  }
}
