package sk.stuba.fiit.perconik.core.java.dom.compatibility;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.TreeApiLevel;

public final class TreeCompatibility {
  private TreeCompatibility() {}

  public static NodeFactory getNodeFactory() {
    return StandardNodeFactory.INSTANCE;
  }

  public static TreeFactory getTreeFactory() {
    return getTreeFactory(TreeApiLevel.latest());
  }

  public static TreeFactory getTreeFactory(final TreeApiLevel level) {
    return StandardTreeFactory.INSTANCES.get(level);
  }
}
