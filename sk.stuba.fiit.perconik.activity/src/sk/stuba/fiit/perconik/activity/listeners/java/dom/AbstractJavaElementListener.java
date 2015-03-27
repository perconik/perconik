package sk.stuba.fiit.perconik.activity.listeners.java.dom;

import org.eclipse.jdt.core.ElementChangedEvent;

import sk.stuba.fiit.perconik.activity.listeners.ActivityEventListener;
import sk.stuba.fiit.perconik.core.listeners.JavaElementListener;

abstract class AbstractJavaElementListener extends ActivityEventListener implements JavaElementListener {
  AbstractJavaElementListener() {}

  abstract void process(final long time, final ElementChangedEvent event);

  public final void elementChanged(final ElementChangedEvent event) {
    final long time = this.currentTime();

    this.execute(new Runnable() {
      public void run() {
        process(time, event);
      }
    });
  }
}
