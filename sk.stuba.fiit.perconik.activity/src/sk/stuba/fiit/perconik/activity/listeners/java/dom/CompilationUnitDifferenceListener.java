package sk.stuba.fiit.perconik.activity.listeners.java.dom;

import java.net.URI;
import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import sk.stuba.fiit.perconik.activity.listeners.ActivityEventListener;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.java.JavaElements;
import sk.stuba.fiit.perconik.core.java.dom.CompilationUnits;
import sk.stuba.fiit.perconik.core.java.dom.difference.NodeDeltaSet;
import sk.stuba.fiit.perconik.eclipse.core.resources.Resources;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;

import static java.util.Arrays.asList;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.cache.CacheBuilder.newBuilder;

import static sk.stuba.fiit.perconik.activity.listeners.java.dom.AbstractCachingNodeListener.KeyerFailureBehavior.RAISE;
import static sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType.POST_RECONCILE;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
@Unsupported
public final class CompilationUnitDifferenceListener extends AbstractNodeDifferenceListener<URI, CompilationUnit, ASTNode> {
  static final Set<JavaElementEventType> javaElementEventTypes = ImmutableSet.of(POST_CHANGE, POST_RECONCILE);

  public CompilationUnitDifferenceListener() {
    super(configuration());
  }

  private static DifferenceConfiguration<URI, CompilationUnit, ASTNode> configuration() {
    DifferenceConfiguration<URI, CompilationUnit, ASTNode> configuration = new DifferenceConfiguration<>();

    configuration.collector(IdentityCollector.instance);
    configuration.keyer(UriKeyer.instance);
    configuration.behavior(RAISE);
    configuration.builder(newBuilder().weakValues());
    configuration.loader(UriLoader.instance);
    configuration.differencer(new CompilationUnitDifferencer<>(new RelevantNodeCollector()));

    return configuration;
  }

  private enum IdentityCollector implements ListCollector<CompilationUnit, CompilationUnit> {
    instance;

    public List<CompilationUnit> apply(final CompilationUnit unit) {
      return asList(unit);
    }
  }

  private enum UriKeyer implements Function<CompilationUnit, Optional<URI>> {
    instance;

    public Optional<URI> apply(final CompilationUnit unit) {
      return fromNullable(Resources.getLocationUri(JavaElements.resource(CompilationUnits.element(unit))));
    }
  }

  private static final class UriLoader extends CacheLoader<URI, CompilationUnit> {
    static final UriLoader instance = new UriLoader();

    private UriLoader() {}

    @Override
    public CompilationUnit load(final URI key) throws Exception {
      // TODO see what happens
      throw new UnsupportedOperationException("YOLO");

      //return (CompilationUnit) TreeParsers.parse(Paths.get(key));
    }
  }

  enum Action implements ActivityEventListener.Action {
    ;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "TODO", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  @Override
  void process(final long time, final ElementChangedEvent event, final URI key, final CompilationUnit original, final CompilationUnit revised, final NodeDeltaSet<ASTNode> difference) {


    // TODO serialize only stuff that can not be recomputed from supplied arguments later
  }

  @Override
  public Set<JavaElementEventType> getEventTypes() {
    return javaElementEventTypes;
  }
}
