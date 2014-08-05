package com.gratex.perconik.activity.ide;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.gratex.perconik.activity.TimeSupplier;

import sk.stuba.fiit.perconik.environment.Environment;

import static com.google.common.base.Throwables.propagate;

final class Internals {
  static final boolean debug = Environment.debug;

  static final TimeZone timeZone = TimeZone.getTimeZone("UTC");

  static final int unknonwnPid = -1;

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
