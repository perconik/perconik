package sk.stuba.fiit.perconik.activity.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.Version;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.elasticsearch.indices.IndexMissingException;

import com.gratex.perconik.uaca.SharedUacaProxy;
import com.gratex.perconik.uaca.data.UacaEvent;
import com.gratex.perconik.uaca.preferences.UacaOptions;

import sk.stuba.fiit.perconik.activity.data.core.StandardCoreProbe;
import sk.stuba.fiit.perconik.activity.data.platform.StandardPlatformProbe;
import sk.stuba.fiit.perconik.activity.data.process.StandardProcessProbe;
import sk.stuba.fiit.perconik.activity.data.system.StandardSystemProbe;
import sk.stuba.fiit.perconik.activity.listeners.RegularListener.RegularConfiguration.Builder;
import sk.stuba.fiit.perconik.activity.plugin.Activator;
import sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.core.Registrables;
import sk.stuba.fiit.perconik.core.preferences.ListenerPreferences;
import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.bind.Defaults;
import sk.stuba.fiit.perconik.data.bind.Mapper;
import sk.stuba.fiit.perconik.data.bind.Writer;
import sk.stuba.fiit.perconik.data.content.AnyStructuredContent;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.events.Event;
import sk.stuba.fiit.perconik.data.store.Store;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ForwardingPluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.elasticsearch.SharedElasticsearchProxy;
import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeUnits;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;
import sk.stuba.fiit.perconik.utilities.configuration.Configurables;
import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.asList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Maps.newHashMap;

import static com.gratex.perconik.uaca.GenericUacaProxyConstants.GENERIC_EVENT_PATH;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.POST_REGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.POST_UNREGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.PRE_REGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.PRE_UNREGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.ActivityListener.LoggingOptions.Schema.logDebug;
import static sk.stuba.fiit.perconik.activity.listeners.ActivityListener.LoggingOptions.Schema.logErrors;
import static sk.stuba.fiit.perconik.activity.listeners.ActivityListener.LoggingOptions.Schema.logEvents;
import static sk.stuba.fiit.perconik.activity.listeners.ActivityListener.LoggingOptions.Schema.logNotices;
import static sk.stuba.fiit.perconik.activity.listeners.ActivityListener.PersistenceOptions.Schema.persistenceElasticsearch;
import static sk.stuba.fiit.perconik.activity.listeners.ActivityListener.PersistenceOptions.Schema.persistenceUaca;
import static sk.stuba.fiit.perconik.activity.listeners.RegularListener.RegularConfiguration.builder;
import static sk.stuba.fiit.perconik.activity.plugin.Activator.PLUGIN_ID;
import static sk.stuba.fiit.perconik.data.content.StructuredContent.separator;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.sequence;
import static sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor.defaultSynchronous;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.nodeName;
import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAsState;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullOrEmpty;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toDefaultString;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCaseFunction;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.trimLeading;
import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeSuppressor;
import static sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors.defaultPoolSizeScalingFactor;
import static sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors.newLimitedThreadPool;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.emptyOptions;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.option;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.booleanParser;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ActivityListener extends RegularListener<ActivityListener> {
  static final float sharedExecutorPoolSizeScalingFactor = defaultPoolSizeScalingFactor();

  static final float probeExecutorPoolSizeScalingFactor = 0.5f;

  protected static final String qualifier = join(PLUGIN_ID, "preferences");

  private static final Builder<ActivityListener, ActivityListener> sharedBuilder;

  static {
    sharedBuilder = builder();

    sharedBuilder.contextType(ActivityListener.class);
    sharedBuilder.listenerType(ActivityListener.class);

    sharedBuilder.optionsLoader(OptionsSupplier.instance);

    sharedBuilder.pluginConsole(PluginConsoles.create(Activator.defaultInstance()));

    sharedBuilder.diplayExecutor(defaultSynchronous());
    sharedBuilder.sharedExecutor(newLimitedThreadPool(sharedExecutorPoolSizeScalingFactor));

    sharedBuilder.persistenceStore(ProxySupplier.instance);
    sharedBuilder.sendFailureHandler(ProxySendFailureHandler.instance);

    Map<String, Probe<?>> probes = newHashMap();

    probes.put(key("monitor", "core"), new StandardCoreProbe());
    //probes.put(key("monitor", "management"), new StandardManagementProbe());// TODO
    probes.put(key("monitor", "platform"), new StandardPlatformProbe());
    probes.put(key("monitor", "process"), new StandardProcessProbe());
    probes.put(key("monitor", "system"), new StandardSystemProbe());

    sharedBuilder.probeMappings(probes);
    sharedBuilder.probeFilter(ProbingOptionsFilterSupplierFunction.instance);
    sharedBuilder.probeExecutor(newLimitedThreadPool(probeExecutorPoolSizeScalingFactor));

    sharedBuilder.registerFailureHandler(LoggingRegisterFailureHandler.instance);
    sharedBuilder.disposalHook(ProxyDisposalHook.instance);
  }

  /**
   * Underlying listener specific console, output does not depend on any options.
   * <p>
   * Note that underlying {@code pluginConsole} provides not-listener-specific
   * access to respective plug-in console and therefore should not be used directly.
   */
  protected final Console console;

  /**
   * Underlying listener debug log, output depends on the {@code log.debug} option.
   * <p>
   * Note that underlying {@code pluginConsole} provides not-listener-specific
   * access to respective plug-in console and therefore should not be used directly.
   */
  protected final Log log;

  /**
   * Constructor for use by subclasses.
   */
  protected ActivityListener() {
    super(newConfiguration());

    this.console = new Console(this);
    this.log = new Log(this);
  }

  static final Configuration<ActivityListener, ActivityListener> newConfiguration() {
    return sharedBuilder.build();
  }

  public interface ProbingOptions extends Options {
    public static final class Schema {
      public static final OptionAccessor<Boolean> monitorCore = option(booleanParser(), join(qualifier, "monitor", "core"), false);

      // TODO public static final OptionAccessor<Boolean> monitorManagement = option(booleanParser(), join(qualifier, "monitor", "management"), false);

      public static final OptionAccessor<Boolean> monitorPlatform = option(booleanParser(), join(qualifier, "monitor", "platform"), false);

      public static final OptionAccessor<Boolean> monitorProcess = option(booleanParser(), join(qualifier, "monitor", "process"), false);

      public static final OptionAccessor<Boolean> monitorSystem = option(booleanParser(), join(qualifier, "monitor", "system"), false);

      public static final OptionAccessor<Boolean> listenerInstance = option(booleanParser(), join(qualifier, "listener", "instance"), true);

      public static final OptionAccessor<Boolean> listenerConfiguration = option(booleanParser(), join(qualifier, "listener", "configuration"), false);

      public static final OptionAccessor<Boolean> listenerRegistration = option(booleanParser(), join(qualifier, "listener", "registration"), false);

      public static final OptionAccessor<Boolean> listenerOptions = option(booleanParser(), join(qualifier, "listener", "options"), true);

      public static final OptionAccessor<Boolean> listenerStatistics = option(booleanParser(), join(qualifier, "listener", "statistics"), true);

      static final ImmutableMap<String, OptionAccessor<Boolean>> probeKeyToOptionAccessor;

      static {
        Map<String, OptionAccessor<Boolean>> map = newHashMap();

        for (OptionAccessor<Boolean> accessor: Configurables.accessors(Schema.class, Boolean.class)) {
          String key = accessor.getKey();

          checkState(!map.containsKey(key), "%s: internal probe key conflict detected on %s", Schema.class, key);

          map.put(key, accessor);
        }

        probeKeyToOptionAccessor = ImmutableMap.copyOf(map);
      }

      private Schema() {}
    }
  }

  public interface PersistenceOptions extends Options {
    public static final class Schema {
      public static final OptionAccessor<Boolean> persistenceElasticsearch = option(booleanParser(), join(qualifier, "persistence", "elasticsearch"), false);

      public static final OptionAccessor<Boolean> persistenceUaca = option(booleanParser(), join(qualifier, "persistence", "uaca"), true);

      private Schema() {}
    }
  }

  public interface LoggingOptions extends Options {
    public static final class Schema {
      public static final OptionAccessor<Boolean> logDebug = option(booleanParser(), join(qualifier, "log", "debug"), false);

      public static final OptionAccessor<Boolean> logEvents = option(booleanParser(), join(qualifier, "log", "events"), false);

      public static final OptionAccessor<Boolean> logNotices = option(booleanParser(), join(qualifier, "log", "notices"), false);

      public static final OptionAccessor<Boolean> logErrors = option(booleanParser(), join(qualifier, "log", "errors"), true);

      private Schema() {}
    }
  }

  private static final class OptionsLoader extends AbstractOptionsLoader<ActivityListener> {
    protected OptionsLoader(final ActivityListener listener) {
      super(listener);
    }

    @Override
    protected ActivityPreferences defaultPreferences() {
      return ActivityPreferences.getShared();
    }

    @Override
    protected ListenerPreferences customPreferences() {
      return ListenerPreferences.getShared();
    }

    @Override
    protected Options adjustDefaultOptions(final ActivityListener listener) {
      return listener.adjustDefaultOptions();
    }

    @Override
    protected Options adjustCustomOptions(final ActivityListener listener) {
      return emptyOptions();
    }
  }

  private enum OptionsSupplier implements Function<ActivityListener, OptionsLoader> {
    instance;

    public OptionsLoader apply(final ActivityListener listener) {
      return new OptionsLoader(listener);
    }
  }

  // TODO refactor somehow
  @SuppressWarnings("static-method")
  protected Options adjustDefaultOptions() {
    return emptyOptions();
  }

  protected static final class Console extends ForwardingPluginConsole {
    private final ActivityListener listener;

    private final PluginConsole console;

    Console(final ActivityListener listener) {
      this.listener = checkNotNull(listener);
      this.console = checkNotNull(listener.pluginConsole);
    }

    @Override
    protected PluginConsole delegate() {
      return this.console;
    }

    @Override
    public void notice(@Nullable final String message) {
      super.notice("%s: %s", this.listener, message);
    }

    @Override
    public void notice(final String format, final Object ... args) {
      super.notice("%s: " + format, this.listener, asList(this.listener, args));
    }

    @Override
    public void warning(@Nullable final String message) {
      super.warning("%s: %s", this.listener, message);
    }

    @Override
    public void warning(final String format, final Object ... args) {
      super.warning("%s: " + format, this.listener, asList(this.listener, args));
    }

    @Override
    public void error(@Nullable final String message) {
      super.error("%s: %s", this.listener, message);
    }

    @Override
    public void error(final String format, final Object ... args) {
      super.error("%s: " + format, this.listener, asList(this.listener, args));
    }

    @Override
    public void error(final Throwable failure, @Nullable final String message) {
      super.error(failure, "%s: %s", this.listener, message);
    }

    @Override
    public void error(final Throwable failure, final String format, final Object ... args) {
      super.error(failure, "%s: " + format, this.listener, asList(this.listener, args));
    }
  }

  private enum LoggingRegisterFailureHandler implements RegisterFailureHandler<ActivityListener> {
    instance;

    static void report(final ActivityListener listener, final RegistrationHook hook, final Runnable task, final Exception failure) {
      if (logErrors.getValue(listener.effectiveOptions())) {
        listener.console.error(failure, "unexpected failure while executing %s as %s hook", task, hook);
      }
    }

    public void preRegisterFailure(final ActivityListener listener, final Runnable task, final Exception failure) {
      report(listener, PRE_REGISTER, task, failure);
    }

    public void postRegisterFailure(final ActivityListener listener, final Runnable task, final Exception failure) {
      report(listener, POST_REGISTER, task, failure);
    }

    public void preUnregisterFailure(final ActivityListener listener, final Runnable task, final Exception failure) {
      report(listener, PRE_UNREGISTER, task, failure);
    }

    public void postUnregisterFailure(final ActivityListener listener, final Runnable task, final Exception failure) {
      report(listener, POST_UNREGISTER, task, failure);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private static final class ElasticsearchProxy extends SharedElasticsearchProxy implements Store<ActivityListener, Event> {
    ElasticsearchProxy(final ElasticsearchOptions options) {
      super(options);
    }

    private enum IndexSupplier implements Function<Event, String> {
      instance;

      public String apply(final Event data) {
        long timestamp = data.getTimestamp();

        return "perconik-events-" + new SimpleDateFormat("yyyyMMdd").format(new Date(timestamp));
      }
    }

    private enum TypeSupplier implements Function<Event, String> {
      instance;

      public String apply(final Event data) {
        return "event";
      }
    }

    public Content load(final ActivityListener listener, final String path, @Nullable final Event data) {
      throw new UnsupportedOperationException();
    }

    public void save(final ActivityListener listener, final String path, @Nullable final Event data) {
      String index = IndexSupplier.instance.apply(data);
      String type = TypeSupplier.instance.apply(data);

      Map<String, Object> source = data.toMap();

      source.put("path", path);


      try {
        IndexResponse response = this.index(index, type, source);

        checkState(response.isCreated(), "%s: document %s not created", listener, toDefaultString(data));
      } catch (IndexMissingException failure) {
        if (ElasticsearchOptions.Schema.logNotices.getValue(this.options)) {
          this.reportMessage(format("index %s not exists, create index and reindex event", index));
        }

        this.create(listener, index, type);

        IndexResponse response = this.index(index, type, source);

        checkState(response.isCreated(), "%s: document %s not created", listener, toDefaultString(data));
      }
    }

    void create(final ActivityListener listener, final String index, final String type) {
      try {
        Map<String, Object> source = IndexSource.get(listener, type);

        if (ElasticsearchOptions.Schema.logNotices.getValue(this.options)) {
          String raw = AnyStructuredData.of(source).toString(true);

          this.reportMessage(format("creating index %s from source: %s", index, raw));
        }

        CreateIndexResponse response = this.createIndex(index, source);

        checkState(response.isAcknowledged(), "%s: index %s not acknowledged", listener, index);
      } catch (IndexAlreadyExistsException failure) {
        if (ElasticsearchOptions.Schema.logNotices.getValue(this.options)) {
          this.reportMessage(format("index %s already exists, reindex event", index));
        }
      }
    }

    private static final class IndexSource {
      private static final String VERSION = "0.0.1";

      private IndexSource() {}

      private static Map<String, Object> settings() {
        AnyStructuredContent settings = new AnyStructuredData();

        settings.put(key("number_of_shards"), 1);
        settings.put(key("number_of_replicas"), 0);

        settings.put(key("mapper", "allow_dynamic"), false);

        settings.put(key("analysis"), analysis());

        return settings.toMap();
      }

      private static Map<String, Object> analysis() {
        AnyStructuredContent analysis = new AnyStructuredData();

        analysis.put(key("analyzer", "action_analyzer", "type"), "custom");
        analysis.put(key("analyzer", "action_analyzer", "tokenizer"), "action_tokenizer");

        analysis.put(key("analyzer", "path_analyzer", "type"), "custom");
        analysis.put(key("analyzer", "path_analyzer", "tokenizer"), "path_tokenizer");

        analysis.put(key("tokenizer", "action_tokenizer", "type"), "PathHierarchy");
        analysis.put(key("tokenizer", "action_tokenizer", "delimeter"), '.');

        analysis.put(key("tokenizer", "path_tokenizer", "type"), "PathHierarchy");
        analysis.put(key("tokenizer", "path_tokenizer", "delimeter"), '/');

        return analysis.toMap();
      }

      private static Map<String, Object> mappings(final ActivityListener listener) {
        AnyStructuredContent mappings = new AnyStructuredData();

        mappings.put(key("_all", "enabled"), true);
        mappings.put(key("_uid", "enabled"), false);
        mappings.put(key("_source", "enabled"), true);

        mappings.put(key("_index", "enabled"), true);
        mappings.put(key("_index", "type"), "string");
        mappings.put(key("_index", "store"), true);
        mappings.put(key("_index", "index"), "not_analyzed");

        mappings.put(key("_type", "enabled"), true);
        mappings.put(key("_type", "type"), "string");
        mappings.put(key("_type", "store"), true);
        mappings.put(key("_type", "index"), "not_analyzed");

        mappings.put(key("_id", "enabled"), true);
        mappings.put(key("_id", "type"), "string");
        mappings.put(key("_id", "store"), true);
        mappings.put(key("_id", "index"), "not_analyzed");

        mappings.put(key("_timestamp", "enabled"), true);
        mappings.put(key("_timestamp", "type"), "date");
        mappings.put(key("_timestamp", "store"), true);
        mappings.put(key("_timestamp", "index"), "not_analyzed");
        mappings.put(key("_timestamp", "format"), Defaults.TIME_PATTERN);

        mappings.put(key("_meta"), meta(listener));

        mappings.put(key("properties"), properties());

        return mappings.toMap();
      }

      private static Map<String, Object> meta(final ActivityListener listener) {
        AnyStructuredContent meta = new AnyStructuredData();

        Options options = listener.effectiveOptions();

        meta.put(key("creator", "node", "name"), options.get(nodeName.getKey()));
        meta.put(key("creator", "node", "version"), Version.CURRENT.toString());

        meta.put(key("creator", "listener", "class"), listener.getClass().getName());
        meta.put(key("creator", "listener", "version"), Registrables.getVersion(listener.getClass()));

        meta.put(key("version"), VERSION);

        meta.put(key("tagline"), "You Know, for Research");

        return meta.toMap();
      }

      private static Map<String, Object> properties() {
        AnyStructuredContent properties = new AnyStructuredData();

        properties.put(key("path", "type"), "string");
        properties.put(key("path", "store"), true);
        properties.put(key("path", "index"), "analyzed");
        properties.put(key("path", "analyzer"), "path_analyzer");

        properties.put(key("action", "type"), "string");
        properties.put(key("action", "store"), true);
        properties.put(key("action", "index"), "analyzed");
        properties.put(key("action", "analyzer"), "action_analyzer");

        properties.put(key("timestamp", "type"), "date");
        properties.put(key("timestamp", "store"), true);
        properties.put(key("timestamp", "index"), "not_analyzed");
        properties.put(key("timestamp", "format"), Defaults.TIME_PATTERN);

        return properties.toMap();
      }

      static Map<String, Object> get(final ActivityListener listener, final String type) {
        AnyStructuredContent source = new AnyStructuredData();

        source.put(key("settings"), settings());

        source.put(key("mappings", type), mappings(listener));

        return source.toMap();
      }
    }

    private CreateIndexResponse createIndex(final String index, final Map<String, Object> source) {
      return this.execute(new Task<CreateIndexResponse>() {
        public CreateIndexResponse perform(final TransportClient client) {
          return client.admin().indices().prepareCreate(index).setSource(source).get();
        }
      });
    }

    private IndexResponse index(final String index, final String type, final Map<String, Object> source) {
      return this.execute(new Task<IndexResponse>() {
        public IndexResponse perform(final TransportClient client) {
          return client.prepareIndex(index, type).setSource(source).get();
        }
      });
    }
  }

  private static final class UacaProxy extends SharedUacaProxy implements Store<ActivityListener, Object> {
    UacaProxy(final UacaOptions options) {
      super(options);
    }

    public Content load(final ActivityListener listener, final String path, @Nullable final Object request) {
      throw new UnsupportedOperationException();
    }

    public void save(final ActivityListener listener, final String path, @Nullable final Object resource) {
      this.send(GENERIC_EVENT_PATH, UacaEvent.of(path, resource));
    }
  }

  private static final class ProxyPersistenceStore implements PersistenceStore<ActivityListener> {
    private final ElasticsearchProxy elasticsearch;

    private final UacaProxy uaca;

    ProxyPersistenceStore(final ElasticsearchProxy elasticsearch, final UacaProxy uaca) {
      this.elasticsearch = checkNotNull(elasticsearch);
      this.uaca = checkNotNull(uaca);
    }

    public void persist(final ActivityListener listener, final String path, final Event data) throws Exception {
      Options options = listener.effectiveOptions();

      if (logEvents.getValue(options)) {
        report(listener, path, data);
      }

      List<Exception> failures = newLinkedList();

      if (persistenceElasticsearch.getValue(options)) {
        save(this.elasticsearch, listener, path, data, failures);
      }

      if (persistenceUaca.getValue(options)) {
        save(this.uaca, listener, path, data, failures);
      }

      handle(failures);
    }

    private static void report(final ActivityListener listener, final String path, final Event data) {
      try {
        Map<?, ?> raw = Mapper.getShared().convertValue(data, Mapper.getMapType());
        String serial = Writer.getPretty().writeValueAsString(raw);

        listener.console.notice(format("%s%n%s", path, serial));
      } catch (JsonProcessingException | RuntimeException failure) {
        listener.console.error(failure, "unable to format %s", toDefaultString(data));
      }
    }

    private static void save(final Store<? super ActivityListener, ? super Event> store, final ActivityListener listener, final String path, final Event data, final List<Exception> failures) {
      try {
        store.save(listener, path, data);
      } catch (Exception failure) {
        failures.add(failure);
      }
    }

    private static void close(final Store<? super ActivityListener, ? super Event> store, final List<Exception> failures) {
      try {
        store.close();
      } catch (Exception failure) {
        failures.add(failure);
      }
    }

    private static void handle(final List<Exception> failures) throws Exception {
      if (!failures.isEmpty()) {
        throw initializeSuppressor(new Exception(), failures);
      }
    }

    void reload(final ActivityListener listener) {
      if (logNotices.getValue(listener.effectiveOptions())) {
        listener.console.notice("updating %s", ElasticsearchProxy.class.getName());
      }

      this.elasticsearch.update();
    }

    public void close() throws Exception {
      List<Exception> failures = newLinkedList();

      close(this.elasticsearch, failures);
      close(this.uaca, failures);

      handle(failures);
    }

    @Override
    public String toString() {
      return toStringHelper(this).add("elasticsearch", this.elasticsearch).add("uaca", this.uaca).toString();
    }
  }

  private enum ProxySupplier implements Function<ActivityListener, PersistenceStore<ActivityListener>> {
    instance;

    public PersistenceStore<ActivityListener> apply(final ActivityListener listener) {
      ElasticsearchProxy elasticsearch = null;

      try {
        elasticsearch = new ElasticsearchProxy(listener.getElasticsearchOptions());
      } catch (Exception failure) {
        handleConstructFailure(listener, ElasticsearchProxy.class, failure);
      }

      UacaProxy uaca = null;

      try {
        uaca = new UacaProxy(listener.getUacaOptions());
      } catch (Exception failure) {
        handleConstructFailure(listener, UacaProxy.class, failure);
      }

      checkState(elasticsearch != null && uaca != null, "%s: unable to construct %s", this, ProxyPersistenceStore.class.getName());

      return new ProxyPersistenceStore(elasticsearch, uaca);
    }

    private static void handleConstructFailure(final ActivityListener listener, final Class<? extends Store<?, ?>> implementation, final Exception failure) {
      if (logErrors.getValue(listener.effectiveOptions())) {
        listener.console.error(failure, "unable to construct %s", implementation.getName());
      }
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum ProxySendFailureHandler implements SendFailureHandler<ActivityListener> {
    instance;

    public void handleSendFailure(final ActivityListener listener, final String path, final Event data, final Exception failure) {
      if (logErrors.getValue(listener.effectiveOptions())) {
        listener.console.error(failure, "unable to send %s to %s using %s", toDefaultString(data), path, listener.persistenceStore);
      }
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum ProxyDisposalHook implements DisposalHook<ActivityListener> {
    instance;

    public void onDispose(final ActivityListener listener) {
      try {
        listener.persistenceStore.close();
      } catch (Exception failure) {
        handleDisposeFailure(listener, failure);
      }
    }

    private static void handleDisposeFailure(final ActivityListener listener, final Exception failure) {
      if (logErrors.getValue(listener.effectiveOptions())) {
        listener.console.error(failure, "unable to dispose %s", listener.persistenceStore);
      }
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  @Override
  protected Map<String, InternalProbe<?>> internalProbeMappings() {
    ImmutableMap.Builder<String, InternalProbe<?>> builder = ImmutableMap.builder();

    builder.put(key("listener", "instance"), new RegularInstanceProbe());
    builder.put(key("listener", "configuration"), new RegularConfigurationProbe());
    builder.put(key("listener", "registration"), new RegularRegistrationProbe());
    builder.put(key("listener", "options"), new RegularOptionsProbe());
    builder.put(key("listener", "statistics"), new RegularStatisticsProbe());

    return builder.build();
  }

  private enum ProbingOptionsFilterSupplierFunction implements Function<ActivityListener, Predicate<Entry<String, Probe<?>>>> {
    instance;

    public Predicate<Entry<String, Probe<?>>> apply(@Nonnull final ActivityListener listener) {
      return new ProbingOptionsFilter(listener);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private static final class ProbingOptionsFilter implements Predicate<Entry<String, Probe<?>>> {
    private final ActivityListener listener;

    private final Options options;

    private final Map<String, OptionAccessor<Boolean>> accessors;

    ProbingOptionsFilter(final ActivityListener listener) {
      this.listener = checkNotNull(listener);
      this.accessors = checkNotNull(listener.probeKeyToOptionAccessor());
      this.options = checkNotNull(listener.effectiveOptions());
    }

    public boolean apply(@Nonnull final Entry<String, Probe<?>> entry) {
      String key = probeKeyToOptionKey(entry.getKey());

      OptionAccessor<Boolean> accessor = this.accessors.get(key);

      checkNotNullAsState(accessor, "%s: no accessor found for %s option, available accessors %s", this.listener, key, this.accessors);

      return accessor.getValue(this.options);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  protected static String probeKeyToOptionKey(final String key) {
    return qualifier + separator + trimLeading(key, internalProbeKeyPrefix + separator);
  }

  protected Map<String, OptionAccessor<Boolean>> probeKeyToOptionAccessor() {
    checkNotNull(this.optionsProvider);

    return ProbingOptions.Schema.probeKeyToOptionAccessor;
  }

  public interface Action {
    public String getName();

    public String getPath();
  }

  public static final String actionName(final Object ... components) {
    return key(transform(asList(components), toLowerCaseFunction()));
  }

  public static final String actionPath(final Object ... components) {
    StringBuilder builder = new StringBuilder(16 * components.length);

    for (Object component: components) {
      for (String item: sequence(component.toString())) {
        builder.append(item.replace('_', '-').toLowerCase()).append('/');
      }
    }

    return builder.substring(0, builder.length() - 1);
  }

  protected static abstract class ContinuousEvent<L extends ActivityListener, E> extends RegularListener.ContinuousEvent<L, E> {
    protected final String identifier;

    protected final Log log;

    protected ContinuousEvent(final L listener, final String identifier, final long pause, final long window, final TimeUnit unit) {
      super(listener, pause, window, unit);

      this.identifier = checkNotNullOrEmpty(identifier);
      this.log = this.listener.log;
    }

    protected ContinuousEvent(final L listener, final String identifier, final TimeValue pause, final TimeValue window) {
      this(listener, identifier, pause.durationToMillis(), window.durationToMillis(), MILLISECONDS);
    }

    protected String formatElapsedTime(final long delta, final long total) {
      return format("pause %s, window %s", formatTimeComparison(delta, this.pause), formatTimeComparison(total, this.window));
    }

    protected String formatTimeComparison(final long value, final long limit) {
      return format("%d %s %d%s", value, value < limit ? "<" : ">=", limit, TimeUnits.toString(this.unit));
    }

    @Override
    protected void watchRunningButEventsNotContinouous() {
      if (this.log.isEnabled()) {
        this.log.print("%s: watch running but %1$s events not continuous -> process", this.identifier);
      }
    }

    @Override
    protected void watchNotRunning() {
      if (this.log.isEnabled()) {
        this.log.print("%s: watch not running -> clear", this.identifier);
      }
    }

    @Override
    protected void watchTimeNotElapsed(final long delta) {
      if (this.log.isEnabled()) {
        this.log.print("%s: %s -> wait", this.identifier, this.formatElapsedTime(delta, this.total()));
      }
    }

    @Override
    protected void watchTimeElapsedAndAboutToProcess(final long delta) {
      if (this.log.isEnabled()) {
        this.log.print("%s: %s -> process", this.identifier, this.formatElapsedTime(delta, this.total()));
      }
    }
  }

  protected static final class Log extends ForwardingPluginConsole {
    private final Options options;

    private final PluginConsole console;

    Log(final ActivityListener listener) {
      this.options = checkNotNull(listener.effectiveOptions());
      this.console = checkNotNull(listener.pluginConsole);
    }

    @Override
    protected PluginConsole delegate() {
      return this.isEnabled() ? this.console : PluginConsoles.silent();
    }

    public static void format(final String content) {
      SmartStringBuilder.builder(2 * content.length()).format(valueOf(content));
    }

    public static void format(final String format, final Object ... args) {
      SmartStringBuilder.builder(4 * format.length()).format(format, args);
    }

    public boolean isEnabled() {
      return logDebug.getValue(this.options);
    }
  }

  @Override
  protected final void onOptionsReload() {
    super.onOptionsReload();

    ((ProxyPersistenceStore) this.persistenceStore).reload(this);

    this.onOptionsReloadHook();
  }

  protected void onOptionsReloadHook() {}

  final ElasticsearchOptions getElasticsearchOptions() {
    return ElasticsearchOptions.View.of(this.effectiveOptions());
  }

  final UacaOptions getUacaOptions() {
    return UacaOptions.View.of(this.effectiveOptions());
  }
}
