package com.gratex.perconik.activity.ide.listeners;

import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

final class Log {
  private Log() {
    throw new AssertionError();
  }

  static final boolean enabled() {
    return UacaPreferences.getShared().isErrorLoggerEnabled();
  }

  static final SmartStringBuilder message() {
    return SmartStringBuilder.builder();
  }
}
