package sk.stuba.fiit.perconik.utilities;

import java.util.Collection;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkState;

public final class MorePreconditions {
  private MorePreconditions() {
    throw new AssertionError();
  }

  public static <T extends CharSequence> T checkNotNullAndNotEmpty(T reference) {
    checkState(reference.length() > 0);

    return reference;
  }

  public static <T extends CharSequence> T checkNotNullAndNotEmpty(T reference, @Nullable Object message) {
    checkState(reference.length() > 0, message);

    return reference;
  }

  public static <T extends CharSequence> T checkNotNullAndNotEmpty(T reference, @Nullable String format, @Nullable Object ... args) {
    checkState(reference.length() > 0, format, args);

    return reference;
  }

  public static <T extends Collection<?>> T checkNotNullAndNotEmpty(T reference) {
    checkState(!reference.isEmpty());

    return reference;
  }

  public static <T extends Collection<?>> T checkNotNullAndNotEmpty(T reference, @Nullable Object message) {
    checkState(!reference.isEmpty(), message);

    return reference;
  }

  public static <T extends Collection<?>> T checkNotNullAndNotEmpty(T reference, @Nullable String format, @Nullable Object ... args) {
    checkState(!reference.isEmpty(), format, args);

    return reference;
  }

  public static <T> T checkNotNullAsState(T reference) {
    checkState(reference != null);

    return reference;
  }

  public static <T> T checkNotNullAsState(T reference, @Nullable Object message) {
    checkState(reference != null, message);

    return reference;
  }

  public static <T> T checkNotNullAsState(T reference, @Nullable String format, @Nullable Object ... args) {
    checkState(reference != null, format, args);

    return reference;
  }
}
