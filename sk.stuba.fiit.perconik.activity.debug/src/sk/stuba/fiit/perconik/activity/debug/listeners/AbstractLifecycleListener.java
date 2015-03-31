package sk.stuba.fiit.perconik.activity.debug.listeners;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.toDefaultString;

public abstract class AbstractLifecycleListener extends ActivityListener {
  public AbstractLifecycleListener() {}

  protected final void mark(final Object subject, final String prefix, final String format, @Nullable final Object ... args) {
    this.mark(asList(subject), prefix, format, args);
  }

  protected final void mark(final Iterable<?> subjects, final String prefix, final String format, @Nullable final Object ... args) {
    if (this.log.isEnabled()) {
      StringBuilder builder = new StringBuilder();

      for (Object subject: subjects) {
        builder.append(toDefaultString(subject)).append(", ");
      }

      this.log.print("%s: %s -> %s", prefix, builder.substring(0, builder.length() - 2), String.format(format, args));
    }
  }
}
