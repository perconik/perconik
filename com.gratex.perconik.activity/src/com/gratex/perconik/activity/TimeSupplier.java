package com.gratex.perconik.activity;

import javax.xml.datatype.XMLGregorianCalendar;

public interface TimeSupplier {
  public XMLGregorianCalendar from(long time);
}
