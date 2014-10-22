package sk.stuba.fiit.perconik.activity.listeners;

import java.util.concurrent.Executor;

import com.gratex.perconik.uaca.UacaConsole;

import sk.stuba.fiit.perconik.activity.data.core.ListenerData;
import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.uaca.UacaProxy;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Throwables.propagate;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class Listener extends Adapter {
  private static final Executor displayExecutor = DisplayExecutor.defaultSynchronous();

  private static final Executor sharedExecutor = PlatformExecutors.newLimitedThreadPool();

  private final UacaConsole console;

  private final UacaProxy proxy;

  Listener() {
    this.console = UacaConsole.getInstance();

    try {
      this.proxy = new UacaProxy();
    } catch (Exception failure) {
      this.console.error(failure, "Unable to open UACA proxy");

      throw propagate(failure);
    }
  }

  static final void execute(final Runnable command) {
    sharedExecutor.execute(command);
  }

  static final <V> V execute(final DisplayTask<V> task) {
    return task.get(displayExecutor);
  }

  @Override
  public final void preRegister() {}

  @Override
  public final void postUnregister() {
    try {
      this.proxy.close();
    } catch (Exception failure) {
      this.console.error(failure, "Unable to close UACA proxy");
    }
  }

  final void persist(final String path, final Event data) {
    assert !isNullOrEmpty(path);
    assert data.getTimestamp() > 0L;
    assert !isNullOrEmpty(data.getAction());

    data.put("meta.listener", ListenerData.of(this));

    this.save(path, data);
  }

  private final void save(final String path, final Content resource) {
    try {
      this.proxy.save(path, resource);
    } catch (Exception failure) {
      this.console.error(failure, "Unable to save data with UACA proxy");
    }
  }
}
