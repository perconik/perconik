package sk.stuba.fiit.perconik.activity.serializers.ui.selection;

import org.eclipse.jface.viewers.TreePath;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ObjectIdentitySerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.TreePaths;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TreePathSerializer extends AbstractConfigurableMultiSerializer<TreePath> {
  public TreePathSerializer(final Option ... options) {
    super(options);
  }

  public TreePathSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTreePath(final StructuredContent content, final TreePath path) {
    content.put(key("segments"), new ObjectIdentitySerializer().serialize(TreePaths.segments(path)));
  }

  @Override
  protected void put(final StructuredContent content, final TreePath path) {
    putTreePath(content, path);
  }
}
