package sk.stuba.fiit.perconik.eclipse.jface.viewers;

import java.util.List;

import org.eclipse.jface.viewers.TreePath;

import static com.google.common.collect.Lists.newArrayListWithCapacity;

public final class TreePaths {
  private TreePaths() {}

  public static List<Object> segments(final TreePath path) {
    int count = path.getSegmentCount();

    List<Object> segments = newArrayListWithCapacity(count);

    for (int i = 0; i < count; i ++) {
      segments.add(path.getSegment(i));
    }

    return segments;
  }
}
