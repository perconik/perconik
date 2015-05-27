package sk.stuba.fiit.perconik.activity.preferences;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.activity.listeners.ActivityListener.LoggingOptions;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener.PersistenceOptions;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener.ProbingOptions;
import sk.stuba.fiit.perconik.activity.plugin.Activator;
import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchPreferences;
import sk.stuba.fiit.perconik.preferences.AbstractObjectPreferences;
import sk.stuba.fiit.perconik.preferences.AbstractPreferences;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.activity.plugin.Activator.PLUGIN_ID;
import static sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences.Keys.listenerDefaultOptions;
import static sk.stuba.fiit.perconik.core.plugin.Activator.classResolver;
import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAsState;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.compound;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.defaults;

/**
 * Activity preferences. Supports both <i>default</i>
 * and <i>configuration</i> (actually used and stored) scopes.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ActivityPreferences extends AbstractObjectPreferences {
  static final String qualifier = join(PLUGIN_ID, "preferences");

  private ActivityPreferences(final Scope scope) {
    super(scope, qualifier, classResolver());
  }

  /**
   * Used to aid in default activity preferences initialization.
   *
   * <p><b>Warning:</b> Users should not explicitly instantiate this class.
   *
   * @author Pavol Zbell
   * @since 1.0
   */
  public static final class Initializer extends AbstractPreferences.Initializer {
    /**
     * The constructor.
     */
    public Initializer() {}

    /**
     * Called by the preference initializer to
     * initialize default activity preferences.
     *
     * <p><b>Warning:</b> Clients should not call this method.
     * It will be called automatically by the preference initializer
     * when the appropriate default preference node is accessed.
     */
    @Override
    public void initializeDefaultPreferences() {
      ActivityPreferences preferences = ActivityPreferences.getDefault();

      preferences.setListenerDefaultOptions(defaultOptions());
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String listenerDefaultOptions = join(qualifier, "listeners", "defaultOptions");
  }

  /**
   * Gets default scoped core preferences.
   */
  public static ActivityPreferences getDefault() {
    return new ActivityPreferences(Scope.DEFAULT);
  }

  /**
   * Gets configuration scoped core preferences.
   */
  public static ActivityPreferences getShared() {
    return new ActivityPreferences(Scope.CONFIGURATION);
  }

  static Options defaultOptions() {
    Builder<Options> builder = ImmutableList.builder();

    builder.add(defaults(ProbingOptions.Schema.class));
    builder.add(defaults(PersistenceOptions.Schema.class));
    builder.add(defaults(LoggingOptions.Schema.class));

    builder.add(ElasticsearchPreferences.getShared());
    builder.add(UacaPreferences.getShared());

    return compound(builder.build());
  }

  private static void reportFailure(final Throwable failure) {
    Activator.defaultInstance().getConsole().error(failure, toString(failure));
  }

  /**
   * Sets default activity listener options.
   * @param options default activity listener options
   * @throws ClassCastException if options type is invalid
   * @throws NullPointerException if options is {@code null}
   */
  public void setListenerDefaultOptions(final Options options) {
    try {
      this.putObject(listenerDefaultOptions, checkNotNull(options));
    } catch (RuntimeException e) {
      reportFailure(e);
    }
  }

  /**
   * Gets default activity listener options.
   * @throws ClassCastException if options type is invalid
   * @throws IllegalStateException if options is {@code null}
   */
  public Options getListenerDefaultOptions() {
    if (this.scope() == Scope.DEFAULT) {
      return checkNotNullAsState(defaultOptions());
    }

    try {
      return Options.class.cast(checkNotNullAsState(this.getObject(listenerDefaultOptions)));
    } catch (RuntimeException e) {
      reportFailure(e);

      Options options = checkNotNullAsState(defaultOptions());

      this.setListenerDefaultOptions(options);

      try {
        this.synchronize();
      } catch (RuntimeException x) {
        reportFailure(x);
      }

      return options;
    }
  }
}
