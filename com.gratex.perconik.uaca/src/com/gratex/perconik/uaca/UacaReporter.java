package com.gratex.perconik.uaca;

import java.util.Map;

import javax.annotation.Nullable;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.gratex.perconik.uaca.preferences.UacaOptions;
import com.gratex.perconik.uaca.preferences.UacaPreferences.Keys;
import com.gratex.perconik.uaca.ui.preferences.UacaMessageDialogs;

import sk.stuba.fiit.perconik.data.bind.Mapper;
import sk.stuba.fiit.perconik.data.bind.Writer;
import sk.stuba.fiit.perconik.utilities.time.TimeSource;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import static com.google.common.base.Strings.isNullOrEmpty;

final class UacaReporter {
  final UacaConsole console;

  final UacaOptions options;

  UacaReporter(final UacaOptions options, final TimeSource source) {
    this.options = requireNonNull(options);

    this.console = UacaConsole.create(options, source);
  }

  void logRequest(final WebTarget target, @Nullable final Object request) {
    if (!this.options.isEventLogEnabled()) {
      return;
    }

    try {
      Map<?, ?> requestProperties = Mapper.getShared().convertValue(request, Mapper.getMapType());
      String serializedRequest = Writer.getPretty().writeValueAsString(requestProperties);

      this.console.notice(format("%s%n%s", target.getUri(), serializedRequest));
    } catch (JsonProcessingException | RuntimeException failure) {
      this.console.error(failure, "UacaProxy: Unable to format object");
    }
  }

  void logError(final String message, @Nullable final Exception failure) {
    if (!this.options.isErrorLogEnabled()) {
      return;
    }

    this.console.error(failure, message);
  }

  void displayError(final String message, @Nullable final Exception failure) {
    if (!this.options.isErrorDialogEnabled()) {
      return;
    }

    UacaMessageDialogs.openError(Keys.displayErrors, isNullOrEmpty(message) ? failure.getMessage() : message);
  }
}
