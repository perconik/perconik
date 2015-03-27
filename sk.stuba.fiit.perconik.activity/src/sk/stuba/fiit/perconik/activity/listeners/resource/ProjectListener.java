package sk.stuba.fiit.perconik.activity.listeners.resource;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.resource.ProjectSerializer;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;

import static sk.stuba.fiit.perconik.activity.listeners.resource.ProjectListener.Action.ADD;
import static sk.stuba.fiit.perconik.activity.listeners.resource.ProjectListener.Action.BUILD;
import static sk.stuba.fiit.perconik.activity.listeners.resource.ProjectListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.resource.ProjectListener.Action.DELETE;
import static sk.stuba.fiit.perconik.activity.listeners.resource.ProjectListener.Action.OPEN;
import static sk.stuba.fiit.perconik.activity.listeners.resource.ProjectListener.Action.REMOVE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_BUILD;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_CLOSE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_DELETE;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
@Unsupported
public final class ProjectListener extends AbstractResourceListener {
  static final Set<ResourceEventType> resourceEventTypes = ImmutableSet.of(PRE_BUILD, PRE_CLOSE, PRE_DELETE, POST_CHANGE);

  public ProjectListener() {}

  // TODO test DELETE vs REMOVE
  // TODO figure out how to distinguish CREATE from ADD

  enum Action implements ActivityListener.Action {
    ADD,

    REMOVE,

    DELETE,

    OPEN,

    CLOSE,

    BUILD;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "project", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action, final ResourceEventVisit visit, final IProject project) {
    Event data = LocalEvent.of(time, action.getName());

    put(data, visit);

    data.put(key("project"), new ProjectSerializer().serialize(project));

    return data;
  }

  void process(final long time, final Action action, final ResourceEventVisit visit, final IProject project) {
    this.send(action.getPath(), build(time, action, visit, project));
  }

  private final class ProjectEventResolver extends ResourceEventResolver {
    ProjectEventResolver(final long time, final ResourceEventInput input) {
      super(time, input);
    }

    @Override
    protected boolean resolveDelta(final ResourceEventInput input, final IResourceDelta delta) throws CoreException {
      IResource resource = delta.getResource();

      switch (ResourceType.valueOf(resource.getType())) {
        case ROOT:
          return true;

        case PROJECT:
          return this.resolveProject(ResourceEventVisit.of(input, delta), delta, (IProject) resource);

        default:
          return false;
      }
    }

    @Override
    protected boolean resolveResource(final ResourceEventInput input, final IResource resource) {
      switch (ResourceType.valueOf(resource.getType())) {
        case ROOT:
          return true;

        case PROJECT:
          return this.resolveProject(ResourceEventVisit.of(input, resource), (IProject) resource);

        default:
          return false;
      }
    }

    private boolean resolveProject(final ResourceEventVisit visit, final IResourceDelta delta, final IProject project) {
      if (visit.input.type == POST_CHANGE) {
        ResourceDeltaKind kind = ResourceDeltaKind.valueOf(delta.getKind());
        Set<ResourceDeltaFlag> flags = ResourceDeltaFlag.setOf(delta.getFlags());

        if (kind == ResourceDeltaKind.ADDED) {
          this.process(ADD, visit, project);
        }

        if (kind == ResourceDeltaKind.REMOVED) {
          this.process(REMOVE, visit, project);
        }

        if (flags.contains(ResourceDeltaFlag.OPEN) && project.isOpen()) {
          this.process(OPEN, visit, project);
        }

        return false;
      }

      return this.resolveProject(visit, project);
    }

    private boolean resolveProject(final ResourceEventVisit visit, final IProject project) {
      switch (visit.input.type) {
        case PRE_BUILD:
          this.process(BUILD, visit, project);
          break;

        case PRE_CLOSE:
          this.process(CLOSE, visit, project);
          break;

        case PRE_DELETE:
          this.process(DELETE, visit, project);
          break;

        default:
          break;
      }

      return false;
    }

    void process(final Action action, final ResourceEventVisit visit, final IProject project) {
      ProjectListener.this.process(this.time, action, visit, project);
    }
  }

  @Override
  void resolve(final long time, final IResourceChangeEvent event) {
    new ProjectEventResolver(time, ResourceEventInput.of(event)).resolve(event);
  }

  public Set<ResourceEventType> getEventTypes() {
    return resourceEventTypes;
  }
}
