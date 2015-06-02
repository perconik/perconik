package sk.stuba.fiit.perconik.utilities;

import java.util.Collection;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public final class MorePreconditions {
  private MorePreconditions() {}

  public static <T extends CharSequence> T checkNotNullOrEmpty(final T reference) {
    checkArgument(checkNotNull(reference).length() > 0);

    return reference;
  }

  public static <T extends CharSequence> T checkNotNullOrEmpty(final T reference, @Nullable final Object message) {
    checkArgument(checkNotNull(reference, message).length() > 0, message);

    return reference;
  }

  public static <T extends CharSequence> T checkNotNullOrEmpty(final T reference, @Nullable final String format, @Nullable final Object ... args) {
    checkArgument(checkNotNull(reference, format, args).length() > 0, format, args);

    return reference;
  }

  public static <T extends Collection<?>> T checkNotNullOrEmpty(final T reference) {
    checkArgument(!checkNotNull(reference).isEmpty());

    return reference;
  }

  public static <T extends Collection<?>> T checkNotNullOrEmpty(final T reference, @Nullable final Object message) {
    checkArgument(!checkNotNull(reference, message).isEmpty(), message);

    return reference;
  }

  public static <T extends Collection<?>> T checkNotNullOrEmpty(final T reference, @Nullable final String format, @Nullable final Object ... args) {
    checkArgument(!checkNotNull(reference, format, args).isEmpty(), format, args);

    return reference;
  }

  public static <T> T checkNotNullAsState(final T reference) {
    checkState(reference != null);

    return reference;
  }

  public static <T> T checkNotNullAsState(final T reference, @Nullable final Object message) {
    checkState(reference != null, message);

    return reference;
  }

  public static <T> T checkNotNullAsState(final T reference, @Nullable final String format, @Nullable final Object ... args) {
    checkState(reference != null, format, args);

    return reference;
  }
}
