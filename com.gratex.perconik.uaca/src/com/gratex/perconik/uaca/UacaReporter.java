package com.gratex.perconik.uaca;

import java.util.Map;

import javax.annotation.Nullable;
import javax.ws.rs.client.WebTarget;

import com.gratex.perconik.uaca.preferences.UacaPreferences;
import com.gratex.perconik.uaca.preferences.UacaPreferences.Keys;
import com.gratex.perconik.uaca.ui.UacaMessageDialogs;

import sk.stuba.fiit.perconik.data.bind.Mapper;
import sk.stuba.fiit.perconik.data.bind.Writer;

import static java.lang.String.format;

import static com.google.common.base.Strings.isNullOrEmpty;

final class UacaReporter {
  private UacaReporter() {
    throw new AssertionError();
  }

  static final void logRequest(final WebTarget target, @Nullable final Object request) {
    if (!UacaPreferences.getShared().isEventLoggerEnabled()) {
      return;
    }

    try {
      Map<?, ?> requestProperties = Mapper.getShared().convertValue(request, Mapper.getMapType());
      String serializedRequest = Writer.getPretty().writeValueAsString(requestProperties);

      UacaConsole.getInstance().notice(format("%s%n%s", target.getUri(), serializedRequest));
    } catch (Exception e) {
      UacaConsole.getInstance().error(e, "UacaProxy: Unable to format object");
    }
  }

  static final void logError(final String message, @Nullable final Exception failure) {
    if (!UacaPreferences.getShared().isErrorLoggerEnabled()) {
      return;
    }

    UacaConsole.getInstance().error(failure, message);
  }

  static final void displayError(final String message, @Nullable final Exception failure) {
    if (!UacaPreferences.getShared().getPreferenceStore().getBoolean(Keys.displayErrors)) {
      return;
    }

    UacaMessageDialogs.openError(Keys.displayErrors, isNullOrEmpty(message) ? failure.getMessage() : message);
  }
}
