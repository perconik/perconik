package com.gratex.perconik.activity.ide.listeners;

import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

final class Log {
  private Log() {}

  static boolean enabled() {
    return UacaPreferences.getShared().isErrorLogEnabled();
  }

  static SmartStringBuilder message() {
    return SmartStringBuilder.builder();
  }
}
