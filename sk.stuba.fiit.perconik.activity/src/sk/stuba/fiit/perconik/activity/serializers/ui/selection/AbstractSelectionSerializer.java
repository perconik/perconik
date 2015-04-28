package sk.stuba.fiit.perconik.activity.serializers.ui.selection;

import org.eclipse.jface.text.IBlockTextSelection;
import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.ui.selection.BlockTextSelectionSerializer.putBlockTextSelection;
import static sk.stuba.fiit.perconik.activity.serializers.ui.selection.MarkSelectionSerializer.putMarkSelection;
import static sk.stuba.fiit.perconik.activity.serializers.ui.selection.SelectionSerializer.putSelection;
import static sk.stuba.fiit.perconik.activity.serializers.ui.selection.StructuredSelectionSerializer.putStructuredSelection;
import static sk.stuba.fiit.perconik.activity.serializers.ui.selection.TextSelectionSerializer.putTextSelection;
import static sk.stuba.fiit.perconik.activity.serializers.ui.selection.TreeSelectionSerializer.putTreeSelection;

abstract class AbstractSelectionSerializer<T extends ISelection> extends AbstractConfigurableMultiSerializer<T> {
  AbstractSelectionSerializer(final Option ... options) {
    super(options);
  }

  AbstractSelectionSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T selection) {
    putObjectIdentity(content, selection);
    putSelection(content, selection);

    if (selection instanceof IMarkSelection) {
      putMarkSelection(content, (IMarkSelection) selection, this.options);
    }

    if (selection instanceof IStructuredSelection) {
      putStructuredSelection(content, (IStructuredSelection) selection);

      if (selection instanceof ITreeSelection) {
        putTreeSelection(content, (ITreeSelection) selection);
      }
    }

    if (selection instanceof ITextSelection) {
      putTextSelection(content, (ITextSelection) selection);

      if (selection instanceof IBlockTextSelection) {
        putBlockTextSelection(content, (IBlockTextSelection) selection, this.options);
      }
    }
  }
}
