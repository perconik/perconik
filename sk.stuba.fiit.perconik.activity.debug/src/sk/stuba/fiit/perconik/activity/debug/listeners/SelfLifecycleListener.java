package sk.stuba.fiit.perconik.activity.debug.listeners;

import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.POST_REGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.POST_UNREGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.PRE_REGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.PRE_UNREGISTER;

@DebugImplementation
@Version("0.0.1.alpha")
public final class SelfLifecycleListener extends AbstractLifecycleListener implements WorkbenchListener {
  // TODO bug: core does not correctly register listeners from preferences
  // which implement no core listener interfaces (hence this implements WorkbenchListener)

  public SelfLifecycleListener() {
    final Object subject = this;

    PRE_REGISTER.add(this, new NamedRunnable(this.getClass(), "PreRegisterMarker") {
      public void run() {
        mark(subject, "listener", "pre register");
      }
    });

    POST_REGISTER.add(this, new NamedRunnable(this.getClass(), "PostRegisterMarker") {
      public void run() {
        mark(subject, "listener", "post register");
      }
    });

    PRE_UNREGISTER.add(this, new NamedRunnable(this.getClass(), "PreUnregisterMarker") {
      public void run() {
        mark(subject, "listener", "pre unregister");
      }
    });

    POST_UNREGISTER.add(this, new NamedRunnable(this.getClass(), "PostUnregisterMarker") {
      public void run() {
        mark(subject, "listener", "post unregister");
      }
    });
  }

  public void postStartup(final IWorkbench workbench) {
    // ignore
  }

  public boolean preShutdown(final IWorkbench workbench, final boolean forced) {
    // ignore

    return true;
  }

  public void postShutdown(final IWorkbench workbench) {
    // ignore
  }
}
