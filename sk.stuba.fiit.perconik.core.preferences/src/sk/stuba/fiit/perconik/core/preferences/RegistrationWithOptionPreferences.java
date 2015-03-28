package sk.stuba.fiit.perconik.core.preferences;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;

import sk.stuba.fiit.perconik.core.persistence.Registration;
import sk.stuba.fiit.perconik.core.preferences.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.preferences.AbstractObjectPreferences;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Options;
import sk.stuba.fiit.perconik.utilities.configuration.ScopedConfigurable;
import sk.stuba.fiit.perconik.utilities.configuration.StandardScope;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.core.plugin.Activator.classResolver;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAsState;

abstract class RegistrationWithOptionPreferences<R extends Registration, K> extends AbstractObjectPreferences {
  RegistrationWithOptionPreferences(final Scope scope, final String qualifier) {
    super(scope, qualifier, classResolver());
  }

  private static void reportFailure(final Throwable failure) {
    PluginConsoles.create(Activator.defaultInstance()).error(failure, toString(failure));
  }

  abstract Set<R> castRegistrations(Object object);

  abstract Map<K, Options> castOptions(Object object);

  final void putDefaultOptionsIfPresent(final Map<K, Options> options, final K key, final Object object) {
    if (object instanceof ScopedConfigurable) {
      Options untrusted = checkNotNull(((ScopedConfigurable) object).getOptions(StandardScope.DEFAULT));

      options.put(key, MapOptions.from(ImmutableMap.copyOf(untrusted.toMap())));
    }
  }

  final void setRegistrations(final String key, final Set<R> registrations) {
    try {
      this.putObject(key, this.castRegistrations(checkNotNull(registrations)));
    } catch (RuntimeException e) {
      reportFailure(e);
    }
  }

  final void setOptions(final String key, final Map<K, Options> options) {
    try {
      this.putObject(key, this.castOptions(checkNotNull(options)));
    } catch (RuntimeException e) {
      reportFailure(e);
    }
  }

  final Set<R> getRegistrations(final String key) {
    if (this.scope() == Scope.DEFAULT) {
      return checkNotNullAsState(this.getDefaultRegistrations());
    }

    try {
      return this.castRegistrations(checkNotNullAsState(this.getObject(key)));
    } catch (RuntimeException e) {
      reportFailure(e);

      Set<R> registrations = checkNotNullAsState(this.getDefaultRegistrations());

      this.setRegistrations(key, registrations);

      try {
        this.synchronize();
      } catch (RuntimeException x) {
        reportFailure(x);
      }

      return registrations;
    }
  }

  final Map<K, Options> getOptions(final String key) {
    if (this.scope() == Scope.DEFAULT) {
      return checkNotNullAsState(this.getDefaultOptions());
    }

    try {
      return this.castOptions(checkNotNullAsState(this.getObject(key)));
    } catch (RuntimeException e) {
      reportFailure(e);

      Map<K, Options> options = checkNotNullAsState(this.getDefaultOptions());

      this.setOptions(key, options);

      try {
        this.synchronize();
      } catch (RuntimeException x) {
        reportFailure(x);
      }

      return options;
    }
  }

  abstract Set<R> getDefaultRegistrations();

  abstract Map<K, Options> getDefaultOptions();
}
