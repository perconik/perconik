package sk.stuba.fiit.perconik.activity.debug.listeners.ui;

import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.activity.debug.listeners.AbstractLifecycleListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;

@DebugImplementation
@Version("0.0.1.alpha")
public final class WorkbenchLifecycleListener extends AbstractLifecycleListener implements WorkbenchListener {
  public WorkbenchLifecycleListener() {}

  public void postStartup(final IWorkbench workbench) {
    this.mark(workbench, "workbench", "post startup");
  }

  public boolean preShutdown(final IWorkbench workbench, final boolean forced) {
    this.mark(workbench, "workbench", "pre shutdown");

    return true;
  }

  public void postShutdown(final IWorkbench workbench) {
    this.mark(workbench, "workbench", "post shutdown");
  }
}
