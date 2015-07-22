package sk.stuba.fiit.perconik.elasticsearch;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.elasticsearch.plugin.Activator;
import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions;
import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema;
import sk.stuba.fiit.perconik.elasticsearch.ui.preferences.ElasticsearchMessageDialogs;

import static java.lang.String.format;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

final class ElasticsearchReporter {
  final ElasticsearchOptions options;

  final PluginConsole console;

  ElasticsearchReporter(final ElasticsearchOptions options) {
    this.options = checkNotNull(options);
    this.console = Activator.defaultInstance().getConsole();
  }

  void logNotice(final String message) {
    if (!Schema.logNotices.getValue(this.options)) {
      return;
    }

    this.console.notice(format("ElasticsearchProxy: %s", message));
  }

  void logError(final String message, @Nullable final Exception failure) {
    if (!Schema.logErrors.getValue(this.options)) {
      return;
    }

    this.console.error(failure, format("ElasticsearchProxy: %s", message));
  }

  void displayError(final String message, @Nullable final Exception failure) {
    if (!Schema.displayErrors.getValue(this.options)) {
      return;
    }

    synchronized (ElasticsearchReporter.class) {
      ElasticsearchMessageDialogs.openError(Schema.displayErrors.getKey(), format("ElasticsearchProxy: %s", isNullOrEmpty(message) ? failure.getMessage() : message));
    }
  }
}
