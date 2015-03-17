package com.gratex.perconik.uaca;

import java.util.Map;

import javax.annotation.Nullable;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.gratex.perconik.uaca.preferences.UacaPreferences;
import com.gratex.perconik.uaca.preferences.UacaPreferences.Keys;
import com.gratex.perconik.uaca.ui.preferences.UacaMessageDialogs;

import sk.stuba.fiit.perconik.data.bind.Mapper;
import sk.stuba.fiit.perconik.data.bind.Writer;

import static java.lang.String.format;

import static com.google.common.base.Strings.isNullOrEmpty;

final class UacaReporter {
  private UacaReporter() {}

  static void logRequest(final WebTarget target, @Nullable final Object request) {
    if (!UacaPreferences.getShared().isEventLogEnabled()) {
      return;
    }

    try {
      Map<?, ?> requestProperties = Mapper.getShared().convertValue(request, Mapper.getMapType());
      String serializedRequest = Writer.getPretty().writeValueAsString(requestProperties);

      UacaConsole.getShared().notice(format("%s%n%s", target.getUri(), serializedRequest));
    } catch (JsonProcessingException | RuntimeException failure) {
      UacaConsole.getShared().error(failure, "UacaProxy: Unable to format object");
    }
  }

  static void logError(final String message, @Nullable final Exception failure) {
    if (!UacaPreferences.getShared().isErrorLogEnabled()) {
      return;
    }

    UacaConsole.getShared().error(failure, message);
  }

  static void displayError(final String message, @Nullable final Exception failure) {
    if (!UacaPreferences.getShared().isErrorDialogEnabled()) {
      return;
    }

    UacaMessageDialogs.openError(Keys.displayErrors, isNullOrEmpty(message) ? failure.getMessage() : message);
  }
}
