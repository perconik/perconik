package sk.stuba.fiit.perconik.core.plugin;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;

import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.environment.Environment;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayListWithCapacity;

abstract class AbstractExtensionProcessor<T> {
  private final ExtendedPlugin plugin;

  private final String point;

  private final Set<Class<?>> types;

  private final SetMultimap<Class<?>, String> contributors;

  private final ListMultimap<Class<?>, Object> extensions;

  AbstractExtensionProcessor(final String point, final Set<Class<?>> types) {
    checkArgument(!isNullOrEmpty(point));

    this.plugin = Activator.defaultInstance();
    this.point = point;

    this.types = ImmutableSet.copyOf(types);
    this.contributors = LinkedHashMultimap.create(this.types.size(), 4);
    this.extensions = LinkedListMultimap.create(this.types.size());
  }

  final PluginConsole console() {
    return this.plugin.getConsole();
  }

  final T process() {
    return this.processRegistry(Platform.getExtensionRegistry());
  }

  private final T processRegistry(final IExtensionRegistry registry) {
    IConfigurationElement[] elements = registry.getConfigurationElementsFor(this.point);

    for (IConfigurationElement element: elements) {
      try {
        String contributor = element.getContributor().getName();

        if (!Environment.debug && contributor.endsWith("debug")) {
          continue;
        }

        Object object = element.createExecutableExtension("class");

        this.collectExecutableExtensions(element.getContributor().getName(), object);

        element.getContributor();
      } catch (CoreException failure) {
        this.console().error("Creating executable extension " + element.getName() + " failed for value " + element.getValue(), failure);
      }
    }

    return this.processExtensions();
  }

  abstract T processExtensions();

  private final void collectExecutableExtensions(final String contributor, final Object object) {
    int matches = 0;

    for (Class<?> type: this.types) {
      if (type.isInstance(object)) {
        this.contributors.put(type, contributor);
        this.extensions.put(type, type.cast(object));

        matches ++;
      }
    }

    if (matches == 0) {
      this.handleUnknownExecutableExtension(object);
    }
  }

  private final void handleUnknownExecutableExtension(final Object object) {
    UnsupportedOperationException failure = new UnsupportedOperationException(object.toString());

    this.console().error("Unable to process executable extension of type " + object.getClass().getName(), failure);
  }

  abstract class SafeBlock implements ISafeRunnable {
    private final AbstractExtensionProcessor<T> processor;

    final Object object;

    SafeBlock(final Object object) {
      this.processor = AbstractExtensionProcessor.this;
      this.object = requireNonNull(object);
    }

    public final void execute() {
      SafeRunner.run(this);
    }

    public final void handleException(final Throwable exception) {
      this.processor.console().error("Exception in a block with object " + String.valueOf(this.object), exception);
    }
  }

  abstract class SafeGet<R> extends SafeBlock {
    final Class<R> type;

    private R result;

    SafeGet(final Object object, final Class<R> type) {
      super(object);

      this.type = requireNonNull(type);
    }

    public final void run() throws Exception {
      this.result = this.type.cast(this.get());
    }

    abstract R get() throws Exception;

    public final R getResult() {
      this.execute();

      return this.result;
    }
  }

  @SuppressWarnings("static-method")
  final <R> R resultOf(final SafeGet<R> operation) {
    return operation.getResult();
  }

  final boolean atLeastOneSupplied(final Class<?> ... types) {
    return this.atLeastOneSupplied(asList(types));
  }

  final boolean atLeastOneSupplied(final Iterable<Class<?>> types) {
    for (Class<?> type: types) {
      if (this.hasExtensions(type)) {
        return true;
      }
    }

    return false;
  }

  final boolean emptyWithNotice(final List<?> objects, final String subject) {
    if (objects.isEmpty()) {
      this.console().notice("No %s provided, using default %s", subject, this.point, subject);

      return true;
    }

    return false;
  }

  final boolean emptyOrNotSingletonWithWarning(final List<?> objects, final String subject) {
    if (objects.isEmpty()) {
      return true;
    }

    if (objects.size() != 1) {
      this.console().warning("More than one %s provided, using default %s", subject, this.point, subject);

      return true;
    }

    return false;
  }

  private final <E> Class<E> requireType(final Class<E> type) {
    checkArgument(this.types.contains(type));

    return type;
  }

  final boolean hasContributors(final Class<?> type) {
    return this.contributors.containsKey(type);
  }

  final boolean hasExtensions(final Class<?> type) {
    return this.extensions.containsKey(type);
  }

  final Set<String> getContributors(final Class<?> type) {
    return this.contributors.get(type);
  }

  final <E> List<E> getExtensions(final Class<E> type) {
    List<?> extensions = this.extensions.get(this.requireType(type));

    List<E> result = newArrayListWithCapacity(extensions.size());

    for (Object extension: extensions) {
      result.add(type.cast(extension));
    }

    return result;
  }
}
