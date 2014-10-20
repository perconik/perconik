package sk.stuba.fiit.perconik.core.debug;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import com.google.common.collect.Multimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.utilities.MoreStrings;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newTreeSet;

public final class DebugListeners {
  private DebugListeners() {}

  public static String toString(final Class<? extends Listener> type) {
    return type.getName();
  }

  public static String toString(final Listener listener) {
    return MoreStrings.toStringFallback(listener);
  }

  public static void printRegistered() {
    printRegistered(Listener.class);
  }

  public static void printRegistered(final Class<? extends Listener> type) {
    printRegistered(type, Debug.getDefaultConsole());
  }

  public static void printRegistered(final Class<? extends Listener> type, final DebugConsole console) {
    console.put(dumpRegistered(type));
  }

  public static void printRegistrations() {
    printRegisterations(Debug.getDefaultConsole());
  }

  public static void printRegisterations(final DebugConsole console) {
    console.put(dumpRegistrations());
  }

  static String dumpRegistered(final Class<? extends Listener> type) {
    SmartStringBuilder builder = new SmartStringBuilder();

    builder.appendln("Registered listeners:").tab();

    List<? extends Listener> listeners = newArrayList(Listeners.registered(type));

    if (!listeners.isEmpty()) {
      Collections.sort(listeners, MoreStrings.toStringComparator());

      for (Listener listener: listeners) {
        builder.appendln(toString(listener));
      }
    } else {
      builder.appendln("none");
    }

    return builder.toString();
  }

  static String dumpRegistrations() {
    SmartStringBuilder builder = new SmartStringBuilder();

    builder.appendln("Registered resource to listeners map:").tab();

    Multimap<Resource<?>, Listener> map = Listeners.registrations();

    SortedSet<Resource<?>> resources = newTreeSet(MoreStrings.toStringComparator());

    resources.addAll(map.keySet());

    if (!map.isEmpty()) {
      for (Resource<?> resource: resources) {
        builder.appendln(DebugResources.toString(resource)).tab();

        List<Listener> listeners = newArrayList(map.get(resource));

        if (!listeners.isEmpty()) {
          Collections.sort(listeners, MoreStrings.toStringComparator());

          for (Listener listener: listeners) {
            builder.appendln(toString(listener));
          }
        } else {
          builder.appendln("none");
        }

        builder.untab();
      }
    } else {
      builder.appendln("none");
    }

    return builder.toString();
  }
}
