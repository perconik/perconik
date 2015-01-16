package com.gratex.perconik.uaca.preferences;

import java.net.URL;

import sk.stuba.fiit.perconik.utilities.configuration.Options;

public interface UacaOptions extends Options {
  public URL getApplicationUrl();

  public boolean isConnectionCheckEnabled();

  public boolean isErrorDialogEnabled();

  public boolean isErrorLogEnabled();

  public boolean isEventLogEnabled();
}
