package sk.stuba.fiit.perconik.core.debug;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.utilities.MoreStrings;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newTreeSet;

public final class DebugResources {
  private DebugResources() {}

  public static String toString(final Class<? extends Resource<?>> type) {
    return type.getName();
  }

  public static String toString(final Resource<?> resource) {
    return MoreStrings.toStringFallback(resource);
  }

  public static void printRegistered() {
    printRegistered(Debug.getDefaultConsole());
  }

  public static void printRegistered(final DebugConsole console) {
    console.put(dumpRegistered());
  }

  public static void printRegistrations() {
    printRegistrations(Debug.getDefaultConsole());
  }

  public static void printRegistrations(final DebugConsole console) {
    console.put(dumpRegistrations());
  }

  static String dumpRegistered() {
    SmartStringBuilder builder = new SmartStringBuilder();

    builder.appendln("Registered resources:").tab();

    List<Resource<?>> resources = newArrayList(Resources.registrations().values());

    if (!resources.isEmpty()) {
      Collections.sort(resources, MoreStrings.toStringComparator());

      for (Resource<?> resource: resources) {
        builder.appendln(toString(resource));
      }
    } else {
      builder.appendln("none");
    }

    return builder.toString();
  }

  static String dumpRegistrations() {
    SmartStringBuilder builder = new SmartStringBuilder();

    builder.appendln("Registered listener type to resources map:").tab();

    SetMultimap<Class<? extends Listener>, Resource<?>> map = Resources.registrations();

    SortedSet<Class<? extends Listener>> types = newTreeSet(MoreStrings.toStringComparator());

    types.addAll(map.keySet());

    if (!map.isEmpty()) {
      for (Class<? extends Listener> type: types) {
        builder.appendln(DebugListeners.toString(type)).tab();

        List<Resource<?>> resources = newArrayList(map.get(type));

        if (!resources.isEmpty()) {
          Collections.sort(resources, MoreStrings.toStringComparator());

          for (Resource<?> resource: resources) {
            builder.appendln(toString(resource));
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
