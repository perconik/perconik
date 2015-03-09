package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;

import com.google.common.util.concurrent.Service.State;

/**
 * A mirror of {@code com.google.common.util.concurrent.Service.Listener}.
 * Use when the mirrored class name clashes with core {@code Listener}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class ServiceListener extends Service.Listener {
  /**
   * Constructor for use by subclasses.
   */
  protected ServiceListener() {}

  @SuppressWarnings("unused")
  protected void transit(final State from, final State to, @Nullable final Throwable failure) {}

  @Override
  public void starting() {
    this.transit(State.NEW, State.STARTING, null);
  }

  @Override
  public void running() {
    this.transit(State.STARTING, State.RUNNING, null);
  }

  @Override
  public void stopping(final State from) {
    this.transit(from, State.STOPPING, null);
  }

  @Override
  public void terminated(final State from) {
    this.transit(from, State.TERMINATED, null);
  }

  @Override
  public void failed(final State from, final Throwable failure) {
    this.transit(from, State.FAILED, failure);
  }
}
