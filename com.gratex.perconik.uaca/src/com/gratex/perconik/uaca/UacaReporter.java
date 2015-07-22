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

import static java.lang.String.format;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

final class UacaReporter {
  final UacaOptions options;

  final UacaConsole console;

  UacaReporter(final UacaOptions options) {
    this.options = checkNotNull(options);
    this.console = UacaConsole.getShared();
  }

  void logRequest(final WebTarget target, @Nullable final Object request) {
    if (!this.options.isRequestLogEnabled()) {
      return;
    }

    try {
      Map<?, ?> requestProperties = Mapper.getShared().convertValue(request, Mapper.getMapType());
      String serializedRequest = Writer.getPretty().writeValueAsString(requestProperties);

      this.console.notice(format("%s%n%s", target.getUri(), serializedRequest));
    } catch (JsonProcessingException | RuntimeException failure) {
      this.logError("Unable to format request", failure);
    }
  }

  void logNotice(final String message) {
    if (!this.options.isNoticeLogEnabled()) {
      return;
    }

    this.console.notice(format("UacaProxy: %s", message));
  }

  void logError(final String message, @Nullable final Exception failure) {
    if (!this.options.isErrorLogEnabled()) {
      return;
    }

    this.console.error(failure, format("UacaProxy: %s", message));
  }

  void displayError(final String message, @Nullable final Exception failure) {
    if (!this.options.isErrorDialogEnabled()) {
      return;
    }

    synchronized (UacaReporter.class) {
      UacaMessageDialogs.openError(Keys.displayErrors, format("UacaProxy: %s", isNullOrEmpty(message) ? failure.getMessage() : message));
    }
  }
}
