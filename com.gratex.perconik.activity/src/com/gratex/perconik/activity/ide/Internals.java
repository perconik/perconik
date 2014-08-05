package com.gratex.perconik.activity.ide;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;

import com.gratex.perconik.activity.TimeSupplier;
import com.gratex.perconik.activity.plugin.Activator;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.environment.Environment;

import static java.lang.String.valueOf;

import static com.google.common.base.Strings.nullToEmpty;
import static com.google.common.base.Throwables.propagate;

final class Internals {
  static final boolean debug = Environment.debug;

  static final TimeZone timeZone = TimeZone.getTimeZone("UTC");

  static final int unknonwnPid = -1;

  static final String optionsSequence;

  static final Map<String, String> options;

  static {
    optionsSequence = nullToEmpty(Environment.getVariable("UACA")).toLowerCase();

    Splitter entries = Splitter.on(CharMatcher.anyOf(";,")).trimResults();
    Splitter keys = Splitter.on(CharMatcher.anyOf(":=")).trimResults().limit(2);

    ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();

    for (String option: entries.split(optionsSequence)) {
      Iterator<String> parts = keys.split(option).iterator();

      builder.put(parts.next(), parts.hasNext() ? parts.next() : "true");
    }

    options = builder.build();

    if (debug) {
      PluginConsole console = PluginConsoles.create(Activator.getDefault());

      MapJoiner joiner = Joiner.on("; ").withKeyValueSeparator(": ");

      console.print("UACA environment variable: %s", valueOf(optionsSequence));
      console.print("UACA extracted flags: %s", options.isEmpty() ? "none" : joiner.join(options));
    }
  }

  static final DatatypeFactory datatypeFactory;

  static {
    try {
      datatypeFactory = DatatypeFactory.newInstance();
    } catch (DatatypeConfigurationException e) {
      throw propagate(e);
    }
  }

  static final TimeSupplier timeSupplier;

  static {
    timeSupplier = new TimeSupplier() {
      public final XMLGregorianCalendar from(final long time) {
        GregorianCalendar calendar = new GregorianCalendar(timeZone);

        calendar.setTimeInMillis(time);

        return datatypeFactory.newXMLGregorianCalendar(calendar);
      }
    };
  }

  private Internals() {
    throw new AssertionError();
  }

  static final int pid() {
    try {
      return Environment.getProcessIdentifier();
    } catch (RuntimeException e) {
      return unknonwnPid;
    }
  }
}
