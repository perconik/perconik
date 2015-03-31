package sk.stuba.fiit.perconik.activity.debug.listeners;

import javax.annotation.Nullable;

import jersey.repackaged.com.google.common.base.Joiner;

import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;

@DebugImplementation
public abstract class AbstractLifecycleListener extends ActivityListener {
  public AbstractLifecycleListener() {}

  private void report(final Object subject, final String prefix, final String format, @Nullable final Object ... args) {
    this.log.print("%s: %s -> %s", prefix, subject, String.format(format, args));
  }

  protected final void mark(final Object subject, final String prefix, final String format, @Nullable final Object ... args) {
    if (this.log.isEnabled()) {
      this.report(subject, prefix, format, args);
    }
  }

  protected final void mark(final Iterable<?> subjects, final String prefix, final String format, @Nullable final Object ... args) {
    if (this.log.isEnabled()) {
      this.report(Joiner.on(", ").join(subjects), prefix, format, args);
    }
  }
}
