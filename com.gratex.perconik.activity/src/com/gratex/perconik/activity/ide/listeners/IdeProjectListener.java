package com.gratex.perconik.activity.ide.listeners;

import java.util.Set;

import javax.annotation.concurrent.GuardedBy;

import com.google.common.collect.ImmutableSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import com.gratex.perconik.activity.uaca.IdeUacaProxy;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventType;

import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaResolver;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;

import static com.gratex.perconik.activity.ide.IdeData.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeData.setEventData;
import static com.gratex.perconik.activity.ide.IdeData.setProjectData;
import static com.gratex.perconik.activity.ide.listeners.Utilities.currentTime;
import static com.gratex.perconik.activity.ide.listeners.Utilities.isNull;

import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.OPEN;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.ADDED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_CLOSE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_DELETE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_REFRESH;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.PROJECT;

/**
 * A listener of IDE project events. This listener handles desired
 * events and eventually builds corresponding data transfer objects
 * of type {@link IdeProjectEventRequest} and passes them to the
 * {@link IdeUacaProxy} to be transferred into the <i>User Activity Central
 * Application</i> for further processing.
 *
 * <p>Project operation types that this listener is interested in are
 * determined by the {@link IdeProjectEventType} enumeration:
 *
 * <ul>
 *   <li>Add - a project is added into the workspace.
 *   <li>Close - an opened project is closed.
 *   <li>Open - a closed project is opened.
 *   <li>Refresh - a project is refreshed.
 *   <li>Remove - a project is removed from the workspace.
 *   <li>Rename - currently not supported.
 *   <li>Switch to - focus is changed from one project to another
 *   and editor selections (tabs and text) are supported. Note that
 *   structured selections in package explorer are not supported.
 * </ul>
 *
 * <p>Data available in an {@code IdeProjectEventRequest}:
 *
 * <ul>
 *   <li>See {@link IdeListener} for documentation of inherited data.
 * </ul>
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeProjectListener extends IdeListener implements ResourceListener, SelectionListener {
  // TODO rename not implemented
  // TODO switch to --> explorer/a editor/b/file explorer/b --> generates switch-to(a,b,a,b)

  static final boolean processStructuredSelections = false;

  static final Set<ResourceEventType> resourceEventTypes = ImmutableSet.of(PRE_CLOSE, PRE_DELETE, PRE_REFRESH, POST_CHANGE);

  private final Object lock = new Object();

  @GuardedBy("lock")
  private IProject project;

  public IdeProjectListener() {}

  private boolean updateProject(final IProject project) {
    if (project != null) {
      synchronized (this.lock) {
        if (!project.equals(this.project)) {
          this.project = project;

          return true;
        }
      }
    }

    return false;
  }

  static IdeProjectEventRequest build(final long time, final IProject project) {
    final IdeProjectEventRequest data = new IdeProjectEventRequest();

    setProjectData(data, project);
    setApplicationData(data);
    setEventData(data, time);

    return data;
  }

  private final class ResourceDeltaVisitor extends ResourceDeltaResolver {
    private final long time;

    private final ResourceEventType type;

    ResourceDeltaVisitor(final long time, final ResourceEventType type) {
      assert time >= 0 && type != null;

      this.time = time;
      this.type = type;
    }

    @Override
    protected boolean resolveDelta(final IResourceDelta delta, final IResource resource) {
      //			// TODO rm
      //			if (IdeApplication.getInstance().isDebug()) { console.put("resource: "+ resource);
      //			console.put("  type: "+ this.type);console.put("  kind: "+ ResourceDeltaKind.valueOf(delta.getKind()).toString());
      //			console.print("  flags: "+ResourceDeltaFlag.setOf(delta.getFlags()).toString()); }

      assert delta != null && resource != null;

      if (ResourceType.valueOf(resource.getType()) != PROJECT) {
        return true;
      }

      if (this.type == POST_CHANGE) {
        IProject project = (IProject) resource;

        ResourceDeltaKind kind = ResourceDeltaKind.valueOf(delta.getKind());
        Set<ResourceDeltaFlag> flags = ResourceDeltaFlag.setOf(delta.getFlags());

        IdeUacaProxy proxy = IdeProjectListener.this.proxy;

        if (kind == ADDED) {
          proxy.sendProjectEvent(build(this.time, project), IdeProjectEventType.ADD);
        }

        if (flags.contains(OPEN) && project.isOpen()) {
          proxy.sendProjectEvent(build(this.time, project), IdeProjectEventType.OPEN);
        }

        return false;
      }

      return this.resolveResource(resource);
    }

    @Override
    protected boolean resolveResource(final IResource resource) {
      assert ResourceType.valueOf(resource.getType()) == PROJECT;

      IProject project = (IProject) resource;

      IdeUacaProxy proxy = IdeProjectListener.this.proxy;

      switch (this.type) {
        case PRE_CLOSE:
          proxy.sendProjectEvent(build(this.time, project), IdeProjectEventType.CLOSE);
          break;

        case PRE_DELETE:
          proxy.sendProjectEvent(build(this.time, project), IdeProjectEventType.REMOVE);
          break;

        case PRE_REFRESH:
          proxy.sendProjectEvent(build(this.time, project), IdeProjectEventType.REFRESH);
          break;

        default:
          break;
      }

      return false;
    }
  }

  void processResource(final long time, final IResourceChangeEvent event) {
    ResourceEventType type = ResourceEventType.valueOf(event.getType());
    IResourceDelta delta = event.getDelta();

    new ResourceDeltaVisitor(time, type).visitOrProbe(delta, event);
  }

  void processSelection(final long time, final IWorkbenchPart part, final ISelection selection) {
    IProject project = null;

    if (processStructuredSelections) {
      if (selection instanceof IStructuredSelection) {
        project = Projects.fromSelection((IStructuredSelection) selection);
      }
    }

    if (isNull(project) && part instanceof IEditorPart) {
      project = Projects.fromEditor((IEditorPart) part);
    }

    if (isNull(project)) {
      project = Projects.fromPage(part.getSite().getPage());
    }

    if (this.updateProject(project)) {
      this.proxy.sendProjectEvent(build(time, project), IdeProjectEventType.SWITCH_TO);
    }
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
        processSelection(time, part, selection);
      }
    });
  }

  public Set<ResourceEventType> getEventTypes() {
    return resourceEventTypes;
  }
}
