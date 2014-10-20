package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import java.util.Map;

import org.eclipse.jdt.core.dom.AST;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.TreeApiLevel;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.immutableEnumMap;
import static com.google.common.collect.Maps.newHashMap;

final class StandardTreeFactory implements TreeFactory {
  static final Map<TreeApiLevel, TreeFactory> INSTANCES;

  private final TreeApiLevel level;

  static {
    Map<TreeApiLevel, TreeFactory> map = newHashMap();

    for (TreeApiLevel level: TreeApiLevel.values()) {
      map.put(level, new StandardTreeFactory(level));
    }

    INSTANCES = immutableEnumMap(map);
  }

  private StandardTreeFactory(final TreeApiLevel level) {
    this.level = checkNotNull(level);
  }

  public AST newTree() {
    return AST.newAST(this.level.getValue());
  }
}
