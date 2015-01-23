package sk.stuba.fiit.perconik.core.preferences;

import java.util.Map;
import java.util.Set;

import org.osgi.service.prefs.BackingStoreException;

import sk.stuba.fiit.perconik.core.persistence.Registration;
import sk.stuba.fiit.perconik.core.preferences.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.preferences.AbstractObjectPreferences;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.core.plugin.Activator.classResolver;

abstract class RegistrationWithOptionPreferences<R extends Registration, K> extends AbstractObjectPreferences {
  RegistrationWithOptionPreferences(final Scope scope, final String qualifier) {
    super(scope, qualifier, classResolver());
  }

  private static void reportFailure(final Throwable failure, final String format, final Object ... args) {
    PluginConsoles.create(Activator.defaultInstance()).error(failure, format, args);
  }

  abstract Set<R> castRegistrations(Object object);

  abstract Map<K, Options> castOptions(Object object);

  final void setRegistrations(final String key, final Set<R> registrations) {
    try {
      this.putObject(key, this.castRegistrations(registrations));
    } catch (RuntimeException e) {
      reportFailure(e, "Unable to write registrations under key %s", key);
    }
  }

  final void setOptions(final String key, final Map<K, Options> options) {
    try {
      this.putObject(key, this.castOptions(options));
    } catch (RuntimeException e) {
      reportFailure(e, "Unable to write options under key %s", key);
    }
  }

  final Set<R> getRegistrations(final String key) {
    if (this.scope() == Scope.DEFAULT) {
      return this.getDefaultRegistrations();
    }

    try {
      return this.castRegistrations(requireNonNull(this.getObject(key)));
    } catch (RuntimeException e) {
      reportFailure(e, "Unable to read registrations under key %s", key);

      Set<R> registrations = this.getDefaultRegistrations();

      this.setRegistrations(key, registrations);

      try {
        this.synchronize();
      } catch (BackingStoreException x) {
        reportFailure(e, "Unable to synchronize registrations under key %s", key);
      }

      return registrations;
    }
  }

  final Map<K, Options> getOptions(final String key) {
    if (this.scope() == Scope.DEFAULT) {
      return this.getDefaultOptions();
    }

    try {
      return this.castOptions(requireNonNull(this.getObject(key)));
    } catch (RuntimeException e) {
      reportFailure(e, "Unable to read options under key %s", key);

      Map<K, Options> configurations = this.getDefaultOptions();

      this.setOptions(key, configurations);

      try {
        this.synchronize();
      } catch (BackingStoreException x) {
        reportFailure(e, "Unable to synchronize options under key %s", key);
      }

      return configurations;
    }
  }

  abstract Set<R> getDefaultRegistrations();

  abstract Map<K, Options> getDefaultOptions();
}
